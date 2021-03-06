package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ActionBluetoothType extends ActionBluetoothProperty<ActionBluetoothType.ViewHolderType>
{
	class ViewHolderType extends RecyclerView.ViewHolder
	{
		private TextView textType;
		
		public ViewHolderType(View view)
		{
			super(view);
			textType = (TextView) view.findViewById(R.id.text_action_bluetooth_type_value);
		}
		
		public void bind(ActionBluetooth action)
		{
			textType.setText(action.getName());
		}
	}
	
	public ActionBluetoothType(ActionBluetooth action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderType createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_bluetooth_type_row, parent, false);
		return new ViewHolderType(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderType) holder).bind(action);
	}
}