package pl.karol202.smartcontrol.behaviour;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import pl.karol202.smartcontrol.util.OnItemClickListener;
import pl.karol202.smartcontrol.R;

public class AdapterBehaviours extends RecyclerView.Adapter<AdapterBehaviours.ViewHolder>
{
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		private int position;
		private Behaviour behaviour;
		private ImageView imageIcon;
		private TextView textName;
		private Switch switchEnabled;
		
		public ViewHolder(View view)
		{
			super(view);
			view.setOnClickListener(v -> listener.onItemClick(position));
			imageIcon = (ImageView) view.findViewById(R.id.image_behaviour_icon);
			textName = (TextView) view.findViewById(R.id.text_behaviour_name);
			switchEnabled = (Switch) view.findViewById(R.id.switch_behaviour_enabled);
			switchEnabled.setOnCheckedChangeListener((button, checked) ->
			{
				behaviour.setEnabled(checked);
				BehavioursManager.saveBehaviours();
			});
		}
		
		public void bind(int position, Behaviour behaviour)
		{
			this.position = position;
			this.behaviour = behaviour;
			imageIcon.setImageResource(behaviour.getIcon());
			textName.setText(behaviour.getName());
			switchEnabled.setChecked(behaviour.isEnabled());
		}
	}
	
	private Context context;
	private OnItemClickListener listener;
	
	public AdapterBehaviours(Context context, OnItemClickListener listener)
	{
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_behaviours_row, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		holder.bind(position, BehavioursManager.getBehaviours().get(position));
	}
	
	@Override
	public int getItemCount()
	{
		return BehavioursManager.getBehaviours().size();
	}
}
