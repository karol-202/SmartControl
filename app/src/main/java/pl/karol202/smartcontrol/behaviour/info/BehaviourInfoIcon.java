package pl.karol202.smartcontrol.behaviour.info;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.Behaviour.BehaviourIcon;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.OnBehaviourChangeListener;

public class BehaviourInfoIcon extends BehaviourInfoProperty<BehaviourInfoIcon.ViewHolderIcon>
{
	class ViewHolderIcon extends RecyclerView.ViewHolder
	{
		private ImageView imageIcon;
		
		public ViewHolderIcon(View view)
		{
			super(view);
			view.setOnClickListener(v -> showIconDialog());
			imageIcon = (ImageView) view.findViewById(R.id.image_behaviour_icon);
		}
		
		public void bind(Behaviour behaviour)
		{
			imageIcon.setImageResource(behaviour.getIcon().getResource());
		}
	}
	
	private OnBehaviourChangeListener listener;
	
	public BehaviourInfoIcon(Behaviour behaviour, Context context, OnBehaviourChangeListener listener)
	{
		super(behaviour, context);
		this.listener = listener;
	}
	
	@Override
	public ViewHolderIcon createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_behaviour_icon_row, parent, false);
		return new ViewHolderIcon(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderIcon) holder).bind(behaviour);
	}
	
	public void showIconDialog()
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_behaviour_icon, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.title_dialog_behaviour_icon);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		
		GridView gridView = (GridView) view.findViewById(R.id.grid_dialog_icons);
		AdapterDialogIcon adapter = new AdapterDialogIcon(context);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener((parent, view1, position, id) -> {
			behaviour.setIcon((BehaviourIcon) adapter.getItem(position));
			BehavioursManager.saveBehaviours();
			listener.onBehaviourChange();
			dialog.dismiss();
		});
		
		dialog.show();
	}
}