package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.conditions.ActivityEditCondition;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.ConditionType;
import pl.karol202.smartcontrol.util.Time;

import java.util.Calendar;

public class ConditionTime implements Condition
{
	public static class ConditionTimeReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if(notificationManager == null) notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			//Toast.makeText(context, "To działa!", Toast.LENGTH_LONG).show();
			int event = intent.getIntExtra("event", -1);
			System.out.println("onReceive " + event);
			if(event == -1) throw new RuntimeException("Event parameter not passed to receiver.");
			boolean start = event == EVENT_START;
			Notification.Builder builder = new Notification.Builder(context);
			builder.setSmallIcon(R.mipmap.ic_launcher);
			builder.setContentTitle("SmartControl");
			builder.setContentText(start ? "Poczatek" : "Koniec");
			notificationManager.notify(start ? 0 : 1, builder.build());
		}
	}
	
	static final int EVENT_START = 0;
	static final int EVENT_END = 1;
	
	private static Context context;
	private static NotificationManager notificationManager;
	private static AlarmManager alarmManager;
	
	private Time startTime;
	private Time endTime;
	private boolean precise;
	
	private PendingIntent piStart;
	private PendingIntent piEnd;
	private Behaviour behaviour;
	
	public ConditionTime(Behaviour behaviour)
	{
		this.behaviour = behaviour;
	}
	
	public ConditionTime(Behaviour behaviour, Time startTime, Time endTime, boolean precise)
	{
		this(behaviour);
		this.startTime = startTime;
		this.endTime = endTime;
		this.precise = precise;
	}
	
	public static void init(Context context)
	{
		ConditionTime.context = context;
		ConditionTime.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
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
	public void registerCondition()
	{
		Bundle bundle = new Bundle();
		
		Intent intentStart = new Intent(context, ConditionTimeReceiver.class);
		bundle.putInt("event", EVENT_START);
		intentStart.putExtras(bundle);
		piStart = PendingIntent.getBroadcast(context, EVENT_START, intentStart, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Intent intentEnd = new Intent(context, ConditionTimeReceiver.class);
		bundle.putInt("event", EVENT_END);
		intentEnd.putExtras(bundle);
		piEnd = PendingIntent.getBroadcast(context, EVENT_END, intentEnd, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.HOUR_OF_DAY, startTime.getHour());
		calStart.set(Calendar.MINUTE, startTime.getMinute());
		calStart.set(Calendar.SECOND, 0);
		if(calStart.before(Calendar.getInstance())) calStart.roll(Calendar.DATE, true);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.HOUR_OF_DAY, endTime.getHour());
		calEnd.set(Calendar.MINUTE, endTime.getMinute());
		calEnd.set(Calendar.SECOND, 0);
		if(calEnd.before(Calendar.getInstance())) calEnd.roll(Calendar.DATE, true);
		
		if(precise)
		{
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calStart.getTimeInMillis(), AlarmManager.INTERVAL_DAY, piStart);
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calEnd.getTimeInMillis(), AlarmManager.INTERVAL_DAY, piEnd);
		}
		else
		{
			alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calStart.getTimeInMillis(), AlarmManager.INTERVAL_DAY, piStart);
			alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calEnd.getTimeInMillis(), AlarmManager.INTERVAL_DAY, piEnd);
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
		update();	}
}
