package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import pl.karol202.smartcontrol.behaviour.actions.OnActionChangedListener;

import java.util.ArrayList;

public class AdapterActionNotification extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnActionChangedListener
{
	private ArrayList<ActionNotificationProperty<? extends RecyclerView.ViewHolder>> properties;
	
	public AdapterActionNotification(ActionNotification action, Context context)
	{
		this.properties = new ArrayList<>();
		this.properties.add(new ActionNotificationType(action, context));
		this.properties.add(new ActionNotificationText(action, context, this));
	}
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return properties.get(viewType).createViewHolder(parent);
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
	{
		properties.get(position).bindViewHolder(holder);
	}
	
	@Override
	public int getItemCount()
	{
		return properties.size();
	}
	
	@Override
	public int getItemViewType(int position)
	{
		return position;
	}
	
	@Override
	public void onActionChanged()
	{
		notifyDataSetChanged();
	}
}
