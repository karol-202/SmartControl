package pl.karol202.smartcontrol.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTime;

public class BootReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		ConditionTime.init(context);
		BehavioursManager.init(context);
		BehavioursManager.registerConditions();
	}
}
