package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ConditionTimeType extends ConditionTimeProperty<ConditionTimeType.ViewHolderType>
{
	class ViewHolderType extends RecyclerView.ViewHolder
	{
		private TextView textType;
		
		public ViewHolderType(View view)
		{
			super(view);
			textType = (TextView) view.findViewById(R.id.text_condition_time_type_value);
		}
		
		public void bind(ConditionTime condition)
		{
			textType.setText(condition.getName());
		}
	}
	
	public ConditionTimeType(ConditionTime condition, Context context)
	{
		super(condition, context);
	}
	
	@Override
	public ViewHolderType createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_condition_time_type_row, parent, false);
		return new ViewHolderType(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderType) holder).bind(condition);
	}
}