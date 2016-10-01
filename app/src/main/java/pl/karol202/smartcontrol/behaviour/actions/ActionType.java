package pl.karol202.smartcontrol.behaviour.actions;

public enum ActionType
{
	;
	
	private int id;
	private int name;
	private int icon;
	private Class<? extends ActivityEditAction> editActivity;
	
	ActionType(int id, int name, int icon, Class<? extends ActivityEditAction> editActivity)
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
	
	public Class<? extends ActivityEditAction> getEditActivity()
	{
		return editActivity;
	}
}