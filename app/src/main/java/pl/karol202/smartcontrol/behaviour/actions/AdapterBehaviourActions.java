package pl.karol202.smartcontrol.behaviour.actions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.util.OnItemClickListener;

public class AdapterBehaviourActions extends Adapter<AdapterBehaviourActions.ViewHolderActions>
{
	public class ViewHolderActions extends RecyclerView.ViewHolder
	{
		private int position;
		private ImageView imageIcon;
		private TextView textName;
		private TextView textInfo;
		
		public ViewHolderActions(View view)
		{
			super(view);
			imageIcon = (ImageView) view.findViewById(R.id.image_action_icon);
			textName = (TextView) view.findViewById(R.id.text_action_name);
			textInfo = (TextView) view.findViewById(R.id.text_action_info);
		}
		
		public void bind(int position, Action action)
		{
			this.position = position;
			imageIcon.setImageResource(action.getIcon());
			textName.setText(action.getName());
			textInfo.setText(action.getInfo());
		}
	}
	
	private Context context;
	private Behaviour behaviour;
	private OnItemClickListener listener;
	
	public AdapterBehaviourActions(Context context, Behaviour behaviour, OnItemClickListener listener)
	{
		this.context = context;
		this.behaviour = behaviour;
		this.listener = listener;
	}
	
	@Override
	public ViewHolderActions onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_behaviour_action_row, parent, false);
		ViewHolderActions viewHolder = new ViewHolderActions(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(ViewHolderActions holder, int position)
	{
		holder.bind(position, behaviour.getAction(position));
	}
	
	@Override
	public int getItemCount()
	{
		return behaviour.getActionsLength();
	}
}