package pl.karol202.smartcontrol.behaviour;

import android.content.SharedPreferences;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTime;

import java.util.ArrayList;

public class Behaviour
{
	private String name;
	private int icon;
	private boolean enabled;
	private ArrayList<Condition> conditions;
	
	public Behaviour()
	{
		this.name = "";
		this.conditions = new ArrayList<>();
	}
	
	public Behaviour(String name, int icon, boolean enabled)
	{
		this.name = name;
		this.icon = icon;
		this.enabled = enabled;
		this.conditions = new ArrayList<>();
	}
	
	public Behaviour defaultBehaviour()
	{
		name = "Nowe zachowanie";
		icon = 0;
		enabled = true;
		conditions.clear();
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
				condition = new ConditionTime(behaviour);
				break;
			default:
				throw new RuntimeException("Error during loading behaviour: invalid condition type " + type + ".");
			}
			condition.loadCondition(prefs, behaviourId, i);
			behaviour.addCondition(condition);
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
}
