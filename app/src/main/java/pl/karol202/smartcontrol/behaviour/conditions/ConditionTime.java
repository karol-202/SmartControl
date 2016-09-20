package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.SharedPreferences;

public class ConditionTime implements Condition
{
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
		return "";
	}
	
	@Override
	public Class<? extends ActivityEditCondition> getEditActivity()
	{
		return getConditionType().getEditActivity();
	}
	
	@Override
	public void loadCondition(SharedPreferences prefs, int behaviourId, int conditionId)
	{
		
	}
	
	@Override
	public void saveCondition(SharedPreferences.Editor editor, int behaviourId, int conditionId)
	{
		
	}
}
