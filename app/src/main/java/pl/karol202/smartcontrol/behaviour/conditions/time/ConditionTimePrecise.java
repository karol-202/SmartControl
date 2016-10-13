package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class ConditionTimePrecise extends ConditionTimeProperty<ConditionTimePrecise.ViewHolderPrecise>
{
	class ViewHolderPrecise extends RecyclerView.ViewHolder
	{
		private Switch switchPrecise;
		private boolean binding;
		
		public ViewHolderPrecise(View view)
		{
			super(view);
			switchPrecise = (Switch) view.findViewById(R.id.switch_condition_time_precise);
			switchPrecise.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if(binding) return;
				condition.setPrecise(isChecked);
				BehavioursManager.saveBehaviours();
			});
		}
		
		public void bind(ConditionTime condition)
		{
			binding = true;
			switchPrecise.setChecked(condition.isPrecise());
			binding = false;
		}
	}
	
	public ConditionTimePrecise(ConditionTime condition, Context context)
	{
		super(condition, context);
	}
	
	@Override
	public ViewHolderPrecise createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_condition_time_precise_row, parent, false);
		return new ViewHolderPrecise(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderPrecise) holder).bind(condition);
	}
}
