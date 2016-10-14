package pl.karol202.smartcontrol.behaviour.actions;

import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.bluetooth.ActivityEditActionBluetooth;
import pl.karol202.smartcontrol.behaviour.actions.notification.ActivityEditActionNotification;
import pl.karol202.smartcontrol.behaviour.actions.soundmode.ActivityEditActionSoundMode;
import pl.karol202.smartcontrol.behaviour.actions.wifi.ActivityEditActionWifi;

public enum ActionType
{
	NOTIFICATION(Action.ACTION_NOTIFICATION, R.string.action_notification_name, R.drawable.ic_action_notification_black_48dp, ActivityEditActionNotification.class),
	SOUND_MODE(Action.ACTION_SOUND_MODE, R.string.action_sound_mode_name, R.drawable.ic_action_sound_mode_black_48dp, ActivityEditActionSoundMode.class),
	WIFI(Action.ACTION_WIFI, R.string.action_wifi_name, R.drawable.ic_action_wifi_black_24dp, ActivityEditActionWifi.class),
	BLUETOOTH(Action.ACTION_BLUETOOTH, R.string.action_bluetooth_name, R.drawable.ic_action_bluetooth_black_24dp, ActivityEditActionBluetooth.class);
	//DATA(Action.ACTION_DATA, R.string.action_data_name, R.drawable.ic_action_data_black_24dp, );
	
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