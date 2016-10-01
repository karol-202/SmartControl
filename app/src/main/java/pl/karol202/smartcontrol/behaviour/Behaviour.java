package pl.karol202.smartcontrol.behaviour;

import android.content.SharedPreferences;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.notification.ActionNotification;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTime;

import java.util.ArrayList;

public class Behaviour
{
	private String name;
	private int icon;
	private boolean enabled;
	private ArrayList<Condition> conditions;
	private ArrayList<Action> actions;
	
	public Behaviour()
	{
		this.name = "";
		this.conditions = new ArrayList<>();
		this.actions = new ArrayList<>();
	}
	
	public Behaviour(String name, int icon, boolean enabled)
	{
		this.name = name;
		this.icon = icon;
		this.enabled = enabled;
		this.conditions = new ArrayList<>();
		this.actions = new ArrayList<>();
	}
	
	public Behaviour defaultBehaviour()
	{
		name = "Nowe zachowanie";
		icon = 0;
		enabled = true;
		conditions.clear();
		actions.clear();
		return this;
	}
	
	public static Behaviour loadBehaviour(SharedPreferences prefs, int behaviourId)
	{
		Behaviour behaviour = new Behaviour();
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
				condition = new ConditionTime(behaviourId, i, behaviour);
				break;
			default:
				throw new RuntimeException("Error during loading behaviour: invalid condition type " + type + ".");
			}
			condition.loadCondition(prefs, behaviourId, i);
			behaviour.addCondition(condition);
		}
		
		int actionsLength = prefs.getInt(header + "actionsLength", 0);
		for(int i = 0; i < actionsLength; i++)
		{
			int type = prefs.getInt(header + "action" + i + "type", -1);
			Action action;
			switch(type)
			{
			case Action.ACTION_NOTIFICATION:
				action = new ActionNotification();
				break;
			default:
				throw new RuntimeException("Error during loading behaviour: invalid action type " + type + ".");
			}
			action.loadAction(prefs, behaviourId, i);
			behaviour.addAction(action);
		}
		
		return behaviour;
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
		
		editor.putInt(header + "actionsLength", actions.size());
		for(int i = 0; i < actions.size(); i++)
		{
			Action action = actions.get(i);
			editor.putInt(header + "action" + i + "type", action.getActionType().getId());
			actions.get(i).saveAction(editor, behaviourId, i);
		}
	}
	
	public void registerConditions()
	{
		if(!enabled) return;
		for(Condition condition : conditions) condition.registerCondition();
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
	}
	
	public Condition getCondition(int position)
	{
		return conditions.get(position);
	}
	
	public void removeCondition(int conditionId)
	{
		this.conditions.remove(conditionId);
	}
	
	public int getConditionsLength()
	{
		return conditions.size();
	}
	
	public void addAction(Action action)
	{
		this.actions.add(action);
	}
	
	public Action getAction(int position)
	{
		return actions.get(position);
	}
	
	public void removeAction(int position)
	{
		this.actions.remove(position);
	}
	
	public int getActionsLength()
	{
		return actions.size();
	}
}
