package pl.karol202.smartcontrol.behaviour.conditions;

import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.conditions.time.ActivityEditConditionTime;

public enum ConditionType
{
	TIME(Condition.CONDITION_TIME, R.string.condition_time_name, R.drawable.ic_cond_time_black_48dp, ActivityEditConditionTime.class);
	
	private int id;
	private int name;
	private int icon;
	private Class<? extends ActivityEditCondition> editActivity;
	
	ConditionType(int id, int name, int icon, Class<? extends ActivityEditCondition> editActivity)
	{
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.editActivity = editActivity;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getName()
	{
		return name;
	}
	
	public int getIcon()
	{
		return icon;
	}
	
	public Class<? extends ActivityEditCondition> getEditActivity()
	{
		return editActivity;
	}
}
