package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

import static android.R.attr.action;

public class ActionBluetoothEnabled extends ActionBluetoothProperty<ActionBluetoothEnabled.ViewHolderEnabled>
{
	class ViewHolderEnabled extends RecyclerView.ViewHolder
	{
		private Switch switchEnabled;
		
		public ViewHolderEnabled(View view)
		{
			super(view);
			switchEnabled = (Switch) view.findViewById(R.id.switch_action_bluetooth_enabled);
			switchEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
				action.setBluetoothEnabled(isChecked);
				BehavioursManager.saveBehaviours();
			});
		}
		
		public void bind(ActionBluetooth action)
		{
			switchEnabled.setChecked(action.isBluetoothEnabled());
		}
	}
	
	public ActionBluetoothEnabled(ActionBluetooth action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderEnabled createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_bluetooth_enabled_row, parent, false);
		return new ViewHolderEnabled(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderEnabled) holder).bind(action);
	}
}