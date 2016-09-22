package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.conditions.ActivityEditCondition;
import pl.karol202.smartcontrol.behaviour.conditions.Condition;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;
import pl.karol202.smartcontrol.util.TimePickerFragment.OnTimeSelectedListener;

public class ActivityEditConditionTime extends ActivityEditCondition implements OnTimeSelectedListener
{
	private ConditionTime conditionTime;
	private AdapterConditionTime adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(condition instanceof ConditionTime))
			throw new RuntimeException("Condition passed to ActivityEditConditionTime is of invalid type.");
		conditionTime = (ConditionTime) condition;
		setContentView(R.layout.activity_edit_condition_time);
		createToolbar(true);
		
		adapter = new AdapterConditionTime(this, conditionTime, getFragmentManager());
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_condition_time);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
	}
	
	@Override
	protected Condition createCondition()
	{
		return new ConditionTime();
	}
	
	@Override
	public void onTimeSelected(int hour, int minute, int actionId)
	{
		adapter.onTimeSelected(hour, minute, actionId);
	}
}