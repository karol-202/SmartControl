package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import pl.karol202.smartcontrol.behaviour.conditions.OnConditionChangedListener;
import pl.karol202.smartcontrol.util.TimePickerFragment.OnTimeSelectedListener;

import java.util.ArrayList;

public class AdapterConditionTime extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnTimeSelectedListener, OnConditionChangedListener
{
	private Context context;
	private ConditionTime conditionTime;
	private ArrayList<ConditionTimeProperty<? extends RecyclerView.ViewHolder>> properties;
	
	public AdapterConditionTime(Context context, ConditionTime conditionTime, FragmentManager manager)
	{
		this.context = context;
		this.conditionTime = conditionTime;
		this.properties = new ArrayList<>();
		this.properties.add(new ConditionTimeType(conditionTime, context));
		this.properties.add(new ConditionTimeTime(conditionTime, context, ConditionTimeTime.WhichTime.START, manager, this));
		this.properties.add(new ConditionTimeTime(conditionTime, context, ConditionTimeTime.WhichTime.END, manager, this));
		this.properties.add(new ConditionTimePrecise(conditionTime, context));
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
	
	@Override
	public void onTimeSelected(int hour, int minute, int actionId)
	{
		for(ConditionTimeProperty property : properties)
		{
			if(property instanceof OnTimeSelectedListener)
				((OnTimeSelectedListener) property).onTimeSelected(hour, minute, actionId);
		}
	}
	
	@Override
	public void onConditionChanged()
	{
		notifyDataSetChanged();
	}
}
