package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;

public class AdapterBehaviourConditions extends RecyclerView.Adapter<AdapterBehaviourConditions.ViewHolder>
{
	public interface OnItemClickListener
	{
		void onItemClick(int position);
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		private int position;
		private ImageView imageIcon;
		private TextView textName;
		private TextView textInfo;
		
		public ViewHolder(View view)
		{
			super(view);
			view.setOnClickListener(v -> listener.onItemClick(position));
			imageIcon = (ImageView) view.findViewById(R.id.image_condition_icon);
			textName = (TextView) view.findViewById(R.id.text_condition_name);
			textInfo = (TextView) view.findViewById(R.id.text_condition_info);
		}
		
		public void bind(int position, Condition condition)
		{
			this.position = position;
			imageIcon.setImageResource(condition.getIcon());
			textName.setText(condition.getName());
			textInfo.setText(condition.getInfo());
		}
	}
	
	private Context context;
	private Behaviour behaviour;
	private OnItemClickListener listener;
	
	public AdapterBehaviourConditions(Context context, Behaviour behaviour, OnItemClickListener listener)
	{
		this.context = context;
		this.behaviour = behaviour;
		this.listener = listener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_behaviour_condition_row, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}
	
	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		holder.bind(position, behaviour.getCondition(position));
	}
	
	@Override
	public int getItemCount()
	{
		return behaviour.getConditionsLength();
	}
}
