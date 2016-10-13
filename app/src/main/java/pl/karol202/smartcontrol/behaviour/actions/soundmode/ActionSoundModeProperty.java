package pl.karol202.smartcontrol.behaviour.actions.soundmode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ActionSoundModeProperty<VH extends RecyclerView.ViewHolder>
{
	protected ActionSoundMode action;
	protected Context context;
	
	public ActionSoundModeProperty(ActionSoundMode action, Context context)
	{
		this.action = action;
		this.context = context;
	}
	
	public abstract VH createViewHolder(ViewGroup parent);
	
	public abstract void bindViewHolder(RecyclerView.ViewHolder holder);
}
