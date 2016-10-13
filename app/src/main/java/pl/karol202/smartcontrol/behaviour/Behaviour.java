package pl.karol202.smartcontrol.behaviour;

import android.content.SharedPreferences;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.notification.ActionNotification;
import pl.karol202.smartcontrol.behaviour.actions.soundmode.ActionSoundMode;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.OnConditionChangedListener;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTime;

import java.util.ArrayList;

public class Behaviour implements OnConditionChangedListener
{
	private String name;
	private int icon;
	private boolean enabled;
	private ArrayList<Condition> conditions;
	private ArrayList<Action> actionsStart;
	private ArrayList<Action> actionsEnd;
	
	private boolean active;
	private int behaviourId;
	
	public Behaviour(int behaviourId)
	{
		this.name = "";
		this.conditions = new ArrayList<>();
		this.actionsStart = new ArrayList<>();
		this.actionsEnd = new ArrayList<>();
		this.behaviourId = behaviourId;
	}
	
	public Behaviour(int behaviourId, String name, int icon, boolean enabled)
	{
		this.name = name;
		this.icon = icon;
		this.enabled = enabled;
		this.conditions = new ArrayList<>();
		this.actionsStart = new ArrayList<>();
		this.actionsEnd = new ArrayList<>();
		this.behaviourId = behaviourId;
	}
	
	public Behaviour defaultBehaviour()
	{
		name = "Nowe zachowanie";
		icon = 0;
		enabled = true;
		conditions.clear();
		actionsStart.clear();
		actionsEnd.clear();
		return this;
	}
	
	public static Behaviour loadBehaviour(SharedPreferences prefs, int behaviourId)
	{
		Behaviour behaviour = new Behaviour(behaviourId);
		String header = "behaviour" + behaviourId;
		
		behaviour.setName(prefs.getString(header + "name", ""));
		behaviour.setIcon(prefs.getInt(header + "icon", -1));
		behaviour.setEnabled(prefs.getBoolean(header + "enabled", false));
		
		int conditionsLength = prefs.getInt(header + "conditionsLength", 0);
		for(int i = 0; i < conditionsLength; i++)
		{
			int type = prefs.getInt(header + "condition" + i + "type", -1);
			Condition condition;
			switch(type)
			{
			case Condition.CONDITION_TIME:
				condition = new ConditionTime(behaviour, i);
				break;
			default:
				throw new RuntimeException("Error during loading behaviour: invalid condition type " + type + ".");
			}
			condition.loadCondition(prefs, behaviourId, i);
			behaviour.conditions.add(condition);
		}
		
		int actionsStartLength = prefs.getInt(header + "actionsStartLength", 0);
		for(int i = 0; i < actionsStartLength; i++)
		{
			String actionHeader = header + "actionStart" + i;
			behaviour.addActionStart(loadAction(prefs, actionHeader));
		}
		
		int actionsEndLength = prefs.getInt(header + "actionsEndLength", 0);
		for(int i = 0; i < actionsEndLength; i++)
		{
			String actionHeader = header + "actionEnd" + i;
			behaviour.addActionEnd(loadAction(prefs, actionHeader));
		}
		
		behaviour.onConditionChanged();
		
		return behaviour;
	}
	
	private static Action loadAction(SharedPreferences prefs, String actionHeader)
	{
		int type = prefs.getInt(actionHeader + "type", -1);
		Action action;
		switch(type)
		{
		case Action.ACTION_NOTIFICATION:
			action = new ActionNotification();
			break;
		case Action.ACTION_SOUND_MODE:
			action = new ActionSoundMode();
			break;
		default:
			throw new RuntimeException("Error during loading behaviour: invalid action type " + type + ".");
		}
		action.loadAction(prefs, actionHeader);
		return action;
	}
	
	public void saveBehaviour(SharedPreferences.Editor editor, int behaviourId)
	{
		String header = "behaviour" + behaviourId;
		editor.putString(header + "name", name);
		editor.putInt(header + "icon", icon);
		editor.putBoolean(header + "enabled", enabled);
		
		editor.putInt(header + "conditionsLength", conditions.size());
		for(int i = 0; i < conditions.size(); i++)
		{
			Condition condition = conditions.get(i);
			editor.putInt(header + "condition" + i + "type", condition.getConditionType().getId());
			conditions.get(i).saveCondition(editor, behaviourId, i);
		}
		
		editor.putInt(header + "actionsStartLength", actionsStart.size());
		for(int i = 0; i < actionsStart.size(); i++)
		{
			String actionHeader = header + "actionStart" + i;
			Action action = actionsStart.get(i);
			editor.putInt(actionHeader + "type", action.getActionType().getId());
			actionsStart.get(i).saveAction(editor, actionHeader);
		}
		
		editor.putInt(header + "actionsEndLength", actionsEnd.size());
		for(int i = 0; i < actionsEnd.size(); i++)
		{
			String actionHeader = header + "actionEnd" + i;
			Action action = actionsEnd.get(i);
			editor.putInt(actionHeader + "type", action.getActionType().getId());
			actionsEnd.get(i).saveAction(editor, actionHeader);
		}
	}
	
	public void registerConditions()
	{
		if(!enabled) return;
		for(Condition condition : conditions) condition.registerCondition();
	}
	
	@Override
	public void onConditionChanged()
	{
		boolean active = false;
		for(Condition condition : conditions)
		{
			if(!condition.isActive())
			{
				active = false;
				break;
			}
			active = true;
		}
		if(active && !this.active) onConditionsTrue();
		else if(!active && this.active) onConditionsFalse();
		this.active = active;
	}
	
	private void onConditionsTrue()
	{
		for(Action action : actionsStart) action.execute();
	}
	
	private void onConditionsFalse()
	{
		for(Action action : actionsEnd) action.execute();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getIcon()
	{
		return icon;
	}
	
	public void setIcon(int icon)
	{
		this.icon = icon;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		if(enabled) registerConditions();
		else for(Condition condition : conditions) condition.unregisterCondition();
	}
	
	public void addCondition(Condition condition)
	{
		this.conditions.add(condition);
		onConditionChanged();
	}
	
	public Condition getCondition(int position)
	{
		return conditions.get(position);
	}
	
	public void removeCondition(int conditionId)
	{
		this.conditions.remove(conditionId);
		for(int i = 0; i < conditions.size(); i++)
		{
			Condition condition = conditions.get(i);
			condition.setConditionId(i);
			condition.unregisterCondition();
			if(enabled) condition.registerCondition();
		}
		onConditionChanged();
	}
	
	public int getConditionsLength()
	{
		return conditions.size();
	}
	
	public void addActionStart(Action action)
	{
		this.actionsStart.add(action);
	}
	
	public Action getActionStart(int position)
	{
		return actionsStart.get(position);
	}
	
	public void removeActionStart(int position)
	{
		this.actionsStart.remove(position);
	}
	
	public int getActionsStartLength()
	{
		return actionsStart.size();
	}
	
	public void addActionEnd(Action action)
	{
		this.actionsEnd.add(action);
	}
	
	public Action getActionEnd(int position)
	{
		return actionsEnd.get(position);
	}
	
	public void removeActionEnd(int position)
	{
		this.actionsEnd.remove(position);
	}
	
	public int getActionsEndLength()
	{
		return actionsEnd.size();
	}
	
	public int getBehaviourId()
	{
		return behaviourId;
	}
	
	public void setBehaviourId(int behaviourId)
	{
		this.behaviourId = behaviourId;
	}
}