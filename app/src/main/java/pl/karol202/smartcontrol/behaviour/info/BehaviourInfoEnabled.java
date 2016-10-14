package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class BehaviourInfoEnabled extends BehaviourInfoProperty<BehaviourInfoEnabled.ViewHolderEnabled>
{
	class ViewHolderEnabled extends RecyclerView.ViewHolder
	{
		private Switch switchEnabled;
		
		public ViewHolderEnabled(View view)
		{
			super(view);
			switchEnabled = (Switch) view.findViewById(R.id.switch_behaviour_enabled);
			switchEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
				behaviour.setEnabled(isChecked);
				BehavioursManager.saveBehaviours();
			});
		}
		
		public void bind(Behaviour behaviour)
		{
			switchEnabled.setChecked(behaviour.isEnabled());
		}
	}
	
	public BehaviourInfoEnabled(Behaviour behaviour, Context context)
	{
		super(behaviour, context);
	}
	
	@Override
	public ViewHolderEnabled createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_behaviour_enabled_row, parent, false);
		return new ViewHolderEnabled(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderEnabled) holder).bind(behaviour);
	}
}