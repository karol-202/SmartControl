package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.content.SharedPreferences;
import pl.karol202.smartcontrol.behaviour.conditions.ActivityEditCondition;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.ConditionType;
import pl.karol202.smartcontrol.util.Time;

public class ConditionTime implements Condition
{
	private Time startTime;
	private Time endTime;
	private boolean precise;
	
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