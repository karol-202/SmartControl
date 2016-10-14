package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.OnBehaviourChangeListener;

import java.util.ArrayList;

public class AdapterBehaviourInfo extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBehaviourChangeListener
{
	private ArrayList<BehaviourInfoProperty> properties;
	
	public AdapterBehaviourInfo(Context context, Behaviour behaviour)
	{
		this.properties = new ArrayList<>();
		this.properties.add(new BehaviourInfoName(behaviour, context, this));
		this.properties.add(new BehaviourInfoIcon(behaviour, context, this));
		this.properties.add(new BehaviourInfoEnabled(behaviour, context));
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
	public void onBehaviourChange()
	{
		notifyDataSetChanged();
	}
}
