package pl.karol202.smartcontrol.behaviour.actions.wifi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class ActionWifiEnabled extends ActionWifiProperty<ActionWifiEnabled.ViewHolderEnabled>
{
	class ViewHolderEnabled extends RecyclerView.ViewHolder
	{
		private Switch switchEnabled;
		
		public ViewHolderEnabled(View view)
		{
			super(view);
			switchEnabled = (Switch) view.findViewById(R.id.switch_action_wifi_enabled);
			switchEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
				action.setWifiEnabled(isChecked);
				BehavioursManager.saveBehaviours();
			});
		}
		
		public void bind(ActionWifi action)
		{
			switchEnabled.setChecked(action.isWifiEnabled());
		}
	}
	
	public ActionWifiEnabled(ActionWifi action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderEnabled createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_wifi_enabled_row, parent, false);
		return new ViewHolderEnabled(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderEnabled) holder).bind(action);
	}
}
