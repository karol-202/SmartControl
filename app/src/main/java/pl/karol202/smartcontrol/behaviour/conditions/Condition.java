package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.SharedPreferences;
import pl.karol202.smartcontrol.behaviour.Behaviour;

public abstract class Condition
{
	public static final int CONDITION_TIME = 0;
	
	protected Behaviour behaviour;
	protected int conditionId;
	
	public Condition(Behaviour behaviour, int conditionId)
	{
		this.behaviour = behaviour;
		this.conditionId = conditionId;
	}
	
	public abstract ConditionType getConditionType();
	
	public abstract int getName();
	
	public abstract int getIcon();
	
	public abstract String getInfo();
	
	public abstract Class<? extends ActivityEditCondition> getEditActivity();
	
	public abstract boolean isActive();
	
	public abstract void registerCondition();
	
	public abstract void unregisterCondition();
	
	public abstract void loadCondition(SharedPreferences prefs, int behaviourId, int conditionId);
	
	//Save condition including the condition#type
	public abstract void saveCondition(SharedPreferences.Editor editor, int behaviourId, int conditionId);
	
	public int getConditionId()
	{
		return conditionId;
	}
	
	public void setConditionId(int conditionId)
	{
		this.conditionId = conditionId;
	}
}
