package pl.karol202.smartcontrol.behaviour.conditions.time;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
	public interface OnTimeSelectedListener
	{
		void onTimeSelected(int hour, int minute, int actionId);
	}
	
	private OnTimeSelectedListener listener;
	private TimePickerDialog dialog;
	private int actionId;
	
	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
		try
		{
			listener = (OnTimeSelectedListener) context;
		}
		catch(ClassCastException ex)
		{
			throw new RuntimeException("Class must implement OnTimeSelectedListener.", ex);
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		int hour = getArguments().getInt("hour");
		int minute = getArguments().getInt("minute");
		actionId = getArguments().getInt("actionId");
		
		dialog = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
		return dialog;
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute)
	{
		listener.onTimeSelected(hourOfDay, minute, actionId);
	}
	
	@Override
	public TimePickerDialog getDialog()
	{
		return dialog;
	}
	
	public static void showTimePicker(FragmentManager manager, Time time, int actionId)
	{
		TimePickerFragment picker = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putInt("hour", time.getHour());
		args.putInt("minute", time.getMinute());
		args.putInt("actionId", actionId);
		picker.setArguments(args);
		picker.show(manager, "timePicker");
	}
}