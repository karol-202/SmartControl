package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.SharedPreferences;

public interface Condition
{
	int CONDITION_TIME = 0;
	
	ConditionType getConditionType();
	
	int getName();
	
	int getIcon();
	
	String getInfo();
	
	Class<? extends ActivityEditCondition> getEditActivity();
	
	boolean isActive();
	
	void registerCondition();
	
	void unregisterCondition();
	
	void loadCondition(SharedPreferences prefs, int behaviourId, int conditionId);
	
	//Save condition including the condition#Type
	void saveCondition(SharedPreferences.Editor editor, int behaviourId, int conditionId);
}
