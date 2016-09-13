package pl.karol202.smartcontrol;

public class Behaviour
{
	private String name;
	private int icon;
	private boolean enabled;
	
	public Behaviour(String name, int icon, boolean enabled)
	{
		this.name = name;
		this.icon = icon;
		this.enabled = enabled;
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
	}
}
