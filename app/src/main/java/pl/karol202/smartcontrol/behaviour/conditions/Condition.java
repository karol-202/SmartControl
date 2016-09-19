package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.SharedPreferences;

public interface Condition
{
	int CONDITION_TIME = 0;
	
	String getName();
	
	int getIcon();
	
	String getInfo();
	
	void loadCondition(SharedPreferences prefs, int behaviourId, int conditionId);
	
	//Save condition including the condition#Type
	void saveCondition(SharedPreferences.Editor editor, int behaviourId, int conditionId);
}
