package pl.karol202.smartcontrol.util;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;

public class TimePickerFragment extends DialogFragment
{
	private TimePickerDialog.OnTimeSetListener listener;
	private TimePickerDialog dialog;
	
	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
		try
		{
			listener = (TimePickerDialog.OnTimeSetListener) context;
		}
		catch(ClassCastException ex)
		{
			throw new RuntimeException("Class must implement OnTimeSetListener.", ex);
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		int hour = getArguments().getInt("hour");
		int minute = getArguments().getInt("minute");
		
		dialog = new TimePickerDialog(getActivity(), listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
		return dialog;
	}
	
	@Override
	public TimePickerDialog getDialog()
	{
		return dialog;
	}
}