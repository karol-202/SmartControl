package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ActionBluetoothProperty<VH extends RecyclerView.ViewHolder>
{
	protected ActionBluetooth action;
	protected Context context;
	
	public ActionBluetoothProperty(ActionBluetooth action, Context context)
	{
		this.action = action;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}