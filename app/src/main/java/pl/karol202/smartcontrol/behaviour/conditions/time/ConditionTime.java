package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
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
			Toast.makeText(context, "To działa!", Toast.LENGTH_LONG).show();
		}
	}
	
	private static Context context;
	private static AlarmManager alarmManager;
	
	private Time startTime;
	private Time endTime;
	private boolean precise;
	
	private boolean registered;
	private PendingIntent piStart;
	private PendingIntent piEnd;
	
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
	public void registerCondition()
	{
		Intent intentStart = new Intent(context, ConditionTimeReceiver.class);
		//intentStart.putExtra("")
		piStart = PendingIntent.getBroadcast(context, 0, intentStart, 0);
		Intent intentEnd = new Intent(context, ConditionTimeReceiver.class);
		piEnd = PendingIntent.getBroadcast(context, 0, intentEnd, 0);
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.HOUR_OF_DAY, startTime.getHour());
		calStart.set(Calendar.MINUTE, startTime.getMinute());
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.HOUR_OF_DAY, endTime.getHour());
		calEnd.set(Calendar.MINUTE, endTime.getMinute());
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
	}
	
	public Time getEndTime()
	{
		return endTime;
	}
	
	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}
	
	public boolean isPrecise()
	{
		return precise;
	}
	
	public void setPrecise(boolean precise)
	{
		this.precise = precise;
	}
}
