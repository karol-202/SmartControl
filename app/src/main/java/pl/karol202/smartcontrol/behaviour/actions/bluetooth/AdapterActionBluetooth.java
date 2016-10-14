package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterActionBluetooth extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private ArrayList<ActionBluetoothProperty<? extends RecyclerView.ViewHolder>> properties;
	
	public AdapterActionBluetooth(ActionBluetooth action, Context context)
	{
		this.properties = new ArrayList<>();
		this.properties.add(new ActionBluetoothType(action, context));
		this.properties.add(new ActionBluetoothEnabled(action, context));
	}
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return properties.get(viewType).createViewHolder(parent);
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
	{
		properties.get(position).bindViewHolder(holder);
	}
	
	@Override
	public int getItemCount()
	{
		return properties.size();
	}
	
	@Override
	public int getItemViewType(int position)
	{
		return position;
	}
}