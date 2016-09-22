package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ConditionTimeProperty<VH extends RecyclerView.ViewHolder>
{
	protected ConditionTime condition;
	protected Context context;
	
	public ConditionTimeProperty(ConditionTime condition, Context context)
	{
		this.condition = condition;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}
