package pl.karol202.smartcontrol.behaviour.conditions;

import android.os.Bundle;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ActivityEditConditionTime extends ActivityEditCondition
{
	private ConditionTime conditionTime;
	
	private TextView textConditionType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(condition instanceof ConditionTime))
			throw new RuntimeException("Condition passed to ActivityEditConditionTime is of invalid type.");
		conditionTime = (ConditionTime) condition;
		setContentView(R.layout.activity_edit_condition_time);
		createToolbar(true);
		
		textConditionType = (TextView) findViewById(R.id.text_condition_type);
		textConditionType.setText(String.format(getResources().getString(R.string.text_condition_type), getResources().getString(condition.getName())));
	}
	
	@Override
	protected Condition createCondition()
	{
		return new ConditionTime();
	}
}