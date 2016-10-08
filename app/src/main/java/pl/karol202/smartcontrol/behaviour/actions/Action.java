package pl.karol202.smartcontrol.behaviour.actions;

import android.content.SharedPreferences;

public interface Action
{
	enum WhichAction
	{
		START, END;
		
		public static WhichAction getById(int id)
		{
			if(id == -1 || id >= values().length) throw new RuntimeException("No such action: " + id);
			return values()[id];
		}
	}
	
	int ACTION_NOTIFICATION = 0;
	
	ActionType getActionType();
	
	int getName();
	
	int getIcon();
	
	String getInfo();
	
	Class<? extends ActivityEditAction> getEditActivity();
	
	void execute();
	
	void loadAction(SharedPreferences prefs, String header);
	
	void saveAction(SharedPreferences.Editor editor, String header);
}
