package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.SharedPreferences;

public class ConditionTime implements Condition
{
	@Override
	public String getName()
	{
		return null;
	}
	
	@Override
	public int getIcon()
	{
		return 0;
	}
	
	@Override
	public String getInfo()
	{
		return null;
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
