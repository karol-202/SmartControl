package pl.karol202.smartcontrol.behaviour;

import android.content.SharedPreferences;

public class Behaviour
{
	private String name;
	private int icon;
	private boolean enabled;
	
	public Behaviour()
	{
		this.name = "";
	}
	
	public Behaviour(String name, int icon, boolean enabled)
	{
		this.name = name;
		this.icon = icon;
		this.enabled = enabled;
	}
	
	public static Behaviour loadBehaviour(SharedPreferences prefs, int behaviourId)
	{
		Behaviour behaviour = new Behaviour();
		
		String header = "behaviour" + behaviourId;
		behaviour.setName(prefs.getString(header + "name", ""));
		behaviour.setIcon(prefs.getInt(header + "icon", -1));
		behaviour.setEnabled(prefs.getBoolean(header + "enabled", false));
		
		return behaviour;
	}
	
	public void saveBehaviour(SharedPreferences.Editor editor, int behaviourId)
	{
		String header = "behaviour" + behaviourId;
		editor.putString(header + "name", name);
		editor.putInt(header + "icon", icon);
		editor.putBoolean(header + "enabled", enabled);
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
