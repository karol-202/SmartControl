package pl.karol202.smartcontrol.behaviour.actions.wifi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ActionWifiType extends ActionWifiProperty<ActionWifiType.ViewHolderType>
{
	class ViewHolderType extends RecyclerView.ViewHolder
	{
		private TextView textType;
		
		public ViewHolderType(View view)
		{
			super(view);
			textType = (TextView) view.findViewById(R.id.text_action_wifi_type_value);
		}
		
		public void bind(ActionWifi action)
		{
			textType.setText(action.getName());
		}
	}
	
	public ActionWifiType(ActionWifi action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderType createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_wifi_type_row, parent, false);
		return new ViewHolderType(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderType) holder).bind(action);
	}
}
