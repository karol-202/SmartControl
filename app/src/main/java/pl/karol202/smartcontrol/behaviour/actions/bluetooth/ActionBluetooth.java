package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActionType;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;

public class ActionBluetooth implements Action
{
	private static BluetoothAdapter bluetoothAdapter;
	private static String textEnabled;
	private static String textDisabled;
	
	private boolean bluetoothEnabled;
	
	public ActionBluetooth() { }
	
	public ActionBluetooth(boolean bluetoothEnabled)
	{
		this.bluetoothEnabled = bluetoothEnabled;
	}
	
	public static void init(Context context)
	{
		if(Build.VERSION.SDK_INT >= 18)
			bluetoothAdapter = ((BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
		else bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		textEnabled = context.getString(R.string.action_bluetooth_enabled);
		textDisabled = context.getString(R.string.action_bluetooth_disabled);
	}
	
	@Override
	public ActionType getActionType()
	{
		return ActionType.BLUETOOTH;
	}
	
	@Override
	public int getName()
	{
		return getActionType().getName();
	}
	
	@Override
	public int getIcon()
	{
		return getActionType().getIcon();
	}
	
	@Override
	public String getInfo()
	{
		return bluetoothEnabled ? textEnabled : textDisabled;
	}
	
	@Override
	public Class<? extends ActivityEditAction> getEditActivity()
	{
		return getActionType().getEditActivity();
	}
	
	@Override
	public void execute()
	{
		if(bluetoothEnabled) bluetoothAdapter.enable();
		else bluetoothAdapter.disable();
	}
	
	@Override
	public void loadAction(SharedPreferences prefs, String header)
	{
		bluetoothEnabled = prefs.getBoolean(header + "bluetoothEnabled", false);
	}
	
	@Override
	public void saveAction(SharedPreferences.Editor editor, String header)
	{
		editor.putBoolean(header + "bluetoothEnabled", bluetoothEnabled);
	}
	
	public boolean isBluetoothEnabled()
	{
		return bluetoothEnabled;
	}
	
	public void setBluetoothEnabled(boolean bluetoothEnabled)
	{
		this.bluetoothEnabled = bluetoothEnabled;
	}
}