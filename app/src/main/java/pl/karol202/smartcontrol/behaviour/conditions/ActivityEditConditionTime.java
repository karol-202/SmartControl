package pl.karol202.smartcontrol.behaviour.conditions;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.util.Time;
import pl.karol202.smartcontrol.util.TimePickerFragment;

public class ActivityEditConditionTime extends ActivityEditCondition implements TimePickerDialog.OnTimeSetListener
{
	private ConditionTime conditionTime;
	private boolean currentStart;
	
	private TextView textType;
	private Button buttonTimeSince;
	private Button buttonTimeTo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(condition instanceof ConditionTime))
			throw new RuntimeException("Condition passed to ActivityEditConditionTime is of invalid type.");
		conditionTime = (ConditionTime) condition;
		setContentView(R.layout.activity_edit_condition_time);
		createToolbar(true);
		
		textType = (TextView) findViewById(R.id.text_condition_type);
		textType.setText(String.format(getResources().getString(R.string.text_condition_type), getResources().getString(condition.getName())));
		
		buttonTimeSince = (Button) findViewById(R.id.button_condition_time_since);
		buttonTimeSince.setOnClickListener(v -> showTimePicker(true));
		
		buttonTimeTo = (Button) findViewById(R.id.button_condition_time_to);
		buttonTimeTo.setOnClickListener(v -> showTimePicker(false));
		updateButtons();
	}
	
	private void updateButtons()
	{
		buttonTimeSince.setText(conditionTime.getStartTime().toString());
		buttonTimeTo.setText(conditionTime.getEndTime().toString());
	}
	
	@Override
	protected Condition createCondition()
	{
		return new ConditionTime();
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute)
	{
		if(currentStart) conditionTime.setStartTime(new Time(hourOfDay, minute));
		else conditionTime.setEndTime(new Time(hourOfDay, minute));
		BehavioursManager.saveBehaviours();
		updateButtons();
	}
	
	private void showTimePicker(boolean start)
	{
		this.currentStart = start;
		Time time = start ? conditionTime.getStartTime() : conditionTime.getEndTime();
		
		TimePickerFragment picker = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("hour", time.getHour());
		args.putInt("minute", time.getMinute());
		picker.setArguments(args);
		picker.show(getFragmentManager(), "timePicker");
	}
}