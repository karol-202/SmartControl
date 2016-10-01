package pl.karol202.smartcontrol.behaviour.actions;

import android.content.SharedPreferences;

public interface Action
{
	ActionType getActionType();
	
	int getName();
	
	int getIcon();
	
	String getInfo();
	
	Class<? extends ActivityEditAction> getEditActivity();
	
	void loadAction(SharedPreferences prefs, int behaviourId, int actionId);
	
	void saveAction(SharedPreferences.Editor editor, int behaviourId, int actionId);
}
