package pl.karol202.smartcontrol.behaviour.actions;

import android.content.SharedPreferences;

public interface Action
{
	int ACTION_NOTIFICATION = 0;
	
	ActionType getActionType();
	
	int getName();
	
	int getIcon();
	
	String getInfo();
	
	Class<? extends ActivityEditAction> getEditActivity();
	
	void execute();
	
	void loadAction(SharedPreferences prefs, int behaviourId, int actionId);
	
	void saveAction(SharedPreferences.Editor editor, int behaviourId, int actionId);
}
