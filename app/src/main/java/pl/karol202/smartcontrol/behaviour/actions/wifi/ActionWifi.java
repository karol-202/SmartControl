package pl.karol202.smartcontrol.behaviour.actions.wifi;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActionType;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;

public class ActionWifi implements Action
{
	private static WifiManager wifiManager;
	private static String textEnabled;
	private static String textDisabled;
	
	private boolean wifiEnabled;
	
	public ActionWifi() { }
	
	public ActionWifi(boolean wifiEnabled)
	{
		this.wifiEnabled = wifiEnabled;
	}
	
	public static void init(Context context)
	{
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		textEnabled = context.getString(R.string.action_wifi_enabled);
		textDisabled = context.getString(R.string.action_wifi_disabled);
	}
	
	@Override
	public ActionType getActionType()
	{
		return ActionType.WIFI;
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
		return wifiEnabled ? textEnabled : textDisabled;
	}
	
	@Override
	public Class<? extends ActivityEditAction> getEditActivity()
	{
		return getActionType().getEditActivity();
	}
	
	@Override
	public void execute()
	{
		wifiManager.setWifiEnabled(wifiEnabled);
	}
	
	@Override
	public void loadAction(SharedPreferences prefs, String header)
	{
		wifiEnabled = prefs.getBoolean(header + "wifiEnabled", true);
	}
	
	@Override
	public void saveAction(SharedPreferences.Editor editor, String header)
	{
		editor.putBoolean(header + "wifiEnabled", wifiEnabled);
	}
	
	public boolean isWifiEnabled()
	{
		return wifiEnabled;
	}
	
	public void setWifiEnabled(boolean wifiEnabled)
	{
		this.wifiEnabled = wifiEnabled;
	}
}