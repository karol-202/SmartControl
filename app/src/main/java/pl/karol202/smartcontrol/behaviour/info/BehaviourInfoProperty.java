package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import pl.karol202.smartcontrol.behaviour.Behaviour;

public abstract class BehaviourInfoProperty<VH extends RecyclerView.ViewHolder>
{
	protected Behaviour behaviour;
	protected Context context;
	
	public BehaviourInfoProperty(Behaviour behaviour, Context context)
	{
		this.behaviour = behaviour;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}