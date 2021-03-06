package pl.karol202.smartcontrol.behaviour.actions.wifi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ActionWifiProperty<VH extends RecyclerView.ViewHolder>
{
	protected ActionWifi action;
	protected Context context;
	
	public ActionWifiProperty(ActionWifi action, Context context)
	{
		this.action = action;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}
