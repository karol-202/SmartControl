package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.conditions.ActivityEditCondition;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.ConditionType;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTimeTime.WhichTime;
import pl.karol202.smartcontrol.util.Time;

import java.util.Calendar;

public class ConditionTime implements Condition
{
	public static class ConditionTimeReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			int behaviourId = intent.getIntExtra("behaviourId", -1);
			int conditionId = intent.getIntExtra("conditionId", -1);
			int event = intent.getIntExtra("whichTime", -1);
			if(conditionId == -1) throw new RuntimeException("conditionId parameter not passed to receiver.");
			if(event == -1) throw new RuntimeException("event paramter not passed to receiver.");
			Log.d("SC", "ConditionTimeReceiver: " + conditionId);
			WhichTime time = event == EVENT_START ? WhichTime.START : WhichTime.END;
			ConditionTime ct = (ConditionTime) BehavioursManager.getBehaviour(behaviourId).getCondition(conditionId);
			ct.onEvent(time);
		}
	}
	
	private static final int EVENT_START = 0;
	private static final int EVENT_END = 1;
	
	private static Context context;
	private static AlarmManager alarmManager;
	
	private Time startTime;
	private Time endTime;
	private boolean precise;
	
	private int behaviourId;
	private int conditionId;
	private Behaviour behaviour;
	private PendingIntent piStart;
	private PendingIntent piEnd;
	private boolean active;
	
	public ConditionTime(int behaviourId, int conditionId, Behaviour behaviour)
	{
		this.behaviourId = behaviourId;
		this.conditionId = conditionId;
		this.behaviour = behaviour;
	}
	
	public ConditionTime(int behaviourId, int conditionId, Behaviour behaviour, Time startTime, Time endTime, boolean precise)
	{
		this(behaviourId, conditionId, behaviour);
		this.startTime = startTime;
		this.endTime = endTime;
		this.precise = precise;
	}
	
	public static void init(Context context)
	{
		ConditionTime.context = context;
		ConditionTime.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	}
	
	@Override
	public ConditionType getConditionType()
	{
		return ConditionType.TIME;
	}
	
	@Override
	public int getName()
	{
		return getConditionType().getName();
	}
	
	@Override
	public int getIcon()
	{
		return getConditionType().getIcon();
	}
	
	@Override
	public String getInfo()
	{
		return startTime.toString() + " - " + endTime.toString();
	}
	
	@Override
	public Class<? extends ActivityEditCondition> getEditActivity()
	{
		return getConditionType().getEditActivity();
	}
	
	@Override
	public boolean isActive()
	{
		return active;
	}
	
	@Override
	public void registerCondition()
	{
		Bundle bundle = new Bundle();
		bundle.putInt("behaviourId", behaviourId);
		bundle.putInt("conditionId", conditionId);
		
		Intent intentStart = new Intent(context, ConditionTimeReceiver.class);
		bundle.putInt("whichTime", EVENT_START);
		intentStart.putExtras(bundle);
		piStart = PendingIntent.getBroadcast(context, EVENT_START, intentStart, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Intent intentEnd = new Intent(context, ConditionTimeReceiver.class);
		bundle.putInt("whichTime", EVENT_END);
		intentEnd.putExtras(bundle);
		piEnd = PendingIntent.getBroadcast(context, EVENT_END, intentEnd, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Calendar calStart = timeToCalendar(startTime);
		Calendar calEnd = timeToCalendar(endTime);
		if(calEnd.before(calStart)) calEnd.roll(Calendar.DATE, true);
		if(calStart.before(Calendar.getInstance())) getNextDate(calStart);
		if(calEnd.before(Calendar.getInstance())) getNextDate(calEnd);
		
		if(precise && Build.VERSION.SDK_INT >= 19)
		{
			alarmManager.setExact(AlarmManager.RTC_WAKEUP, calStart.getTimeInMillis(), piStart);
			alarmManager.setExact(AlarmManager.RTC_WAKEUP, calEnd.getTimeInMillis(), piEnd);
		}
		else
		{
			alarmManager.set(AlarmManager.RTC_WAKEUP, calStart.getTimeInMillis(), piStart);
			alarmManager.set(AlarmManager.RTC_WAKEUP, calEnd.getTimeInMillis(), piEnd);
		}
	}
	
	@Override
	public void unregisterCondition()
	{
		if(piStart != null) alarmManager.cancel(piStart);
		if(piEnd != null) alarmManager.cancel(piEnd);
	}
	
	private void update()
	{
		unregisterCondition();
		if(behaviour.isEnabled()) registerCondition();
	}
	
	private void onEvent(WhichTime time)
	{
		if(time == WhichTime.START) active = true;
		else active = false;
		behaviour.onConditionChanged();
		update();
	}
	
	private Calendar timeToCalendar(Time time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
		calendar.set(Calendar.MINUTE, time.getMinute());
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}
	
	private Calendar getNextDate(Calendar calendar)
	{
		calendar.roll(Calendar.DATE, true);
		return calendar;
	}
	
	@Override
	public void loadCondition(SharedPreferences prefs, int behaviourId, int conditionId)
	{
		String header = "behaviour" + behaviourId + "condition" + conditionId;
		
		int startHour = prefs.getInt(header + "startHour", 0);
		int startMinute = prefs.getInt(header + "startMinute", 0);
		startTime = new Time(startHour, startMinute);
		
		int endHour = prefs.getInt(header + "endHour", 0);
		int endMinute = prefs.getInt(header + "endMinute", 0);
		endTime = new Time(endHour, endMinute);
		
		precise = prefs.getBoolean(header + "precise", false);
	}
	
	@Override
	public void saveCondition(SharedPreferences.Editor editor, int behaviourId, int conditionId)
	{
		String header = "behaviour" + behaviourId + "condition" + conditionId;
		
		editor.putInt(header + "startHour", startTime.getHour());
		editor.putInt(header + "startMinute", startTime.getMinute());
		editor.putInt(header + "endHour", endTime.getHour());
		editor.putInt(header + "endMinute", endTime.getMinute());
		editor.putBoolean(header + "precise", precise);
	}
	
	public Time getStartTime()
	{
		return startTime;
	}
	
	public void setStartTime(Time startTime)
	{
		this.startTime = startTime;
		update();
	}
	
	public Time getEndTime()
	{
		return endTime;
	}
	
	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
		update();
	}
	
	public boolean isPrecise()
	{
		return precise;
	}
	
	public void setPrecise(boolean precise)
	{
		this.precise = precise;
		update();
	}
}