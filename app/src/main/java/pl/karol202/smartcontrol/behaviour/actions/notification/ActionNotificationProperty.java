package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ActionNotificationProperty<VH extends RecyclerView.ViewHolder>
{
	protected ActionNotification action;
	protected Context context;
	
	public ActionNotificationProperty(ActionNotification action, Context context)
	{
		this.action = action;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}
