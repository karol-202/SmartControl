package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.conditions.OnConditionChangedListener;
import pl.karol202.smartcontrol.util.Time;
import pl.karol202.smartcontrol.util.TimePickerFragment;
import pl.karol202.smartcontrol.util.TimePickerFragment.OnTimeSelectedListener;

public class ConditionTimeTime extends ConditionTimeProperty<ConditionTimeTime.ViewHolderTime> implements OnTimeSelectedListener
{
	class ViewHolderTime extends RecyclerView.ViewHolder
	{
		private TextView textTime;
		private TextView textTimeValue;
		
		public ViewHolderTime(View view)
		{
			super(view);
			view.setOnClickListener(v -> {
				int action = which == WhichTime.START ? ACTION_TIME_START : ACTION_TIME_END;
				Time time = which == WhichTime.START ? condition.getStartTime() : condition.getEndTime();
				TimePickerFragment.showTimePicker(fragmentManager, time, action);
			});
			textTime = (TextView) view.findViewById(R.id.text_condition_time_time);
			textTimeValue = (TextView) view.findViewById(R.id.text_condition_time_time_value);
		}
		
		public void bind(ConditionTime condition)
		{
			textTime.setText(which == WhichTime.START ? R.string.condition_time_start : R.string.condition_time_end);
			String value = which == WhichTime.START ? condition.getStartTime().toString() : condition.getEndTime().toString();
			textTimeValue.setText(value);
		}
	}
	
	enum WhichTime
	{
		START, END
	}
	
	private final int ACTION_TIME_START = 0;
	private final int ACTION_TIME_END = 1;
	
	private WhichTime which;
	private FragmentManager fragmentManager;
	private OnConditionChangedListener listener;
	
	public ConditionTimeTime(ConditionTime condition, Context context, WhichTime which, FragmentManager fragmentManager, OnConditionChangedListener listener)
	{
		super(condition, context);
		this.which = which;
		this.fragmentManager = fragmentManager;
		this.listener = listener;
	}
	
	@Override
	public ViewHolderTime createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_condition_time_time_row, parent, false);
		return new ViewHolderTime(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderTime) holder).bind(condition);
	}
	
	@Override
	public void onTimeSelected(int hour, int minute, int actionId)
	{
		if(actionId == ACTION_TIME_START) condition.setStartTime(new Time(hour, minute));
		else if(actionId == ACTION_TIME_END) condition.setEndTime(new Time(hour, minute));
		else return;
		BehavioursManager.saveBehaviours();
		listener.onConditionChanged();
	}
}
