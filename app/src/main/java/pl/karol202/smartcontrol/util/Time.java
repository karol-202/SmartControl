package pl.karol202.smartcontrol.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.util.Calendar;

public class Time implements Parcelable
{
	private int hour;
	private int minute;
	
	public Time(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}
	
	protected Time(Parcel in)
	{
		hour = in.readInt();
		minute = in.readInt();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(hour);
		dest.writeInt(minute);
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}
	
	public static final Creator<Time> CREATOR = new Creator<Time>()
	{
		@Override
		public Time createFromParcel(Parcel in)
		{
			return new Time(in);
		}
		
		@Override
		public Time[] newArray(int size)
		{
			return new Time[size];
		}
	};
	
	public int getHour()
	{
		return hour;
	}
	
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public void setMinute(int minute)
	{
		this.minute = minute;
	}
	
	@Override
	public String toString()
	{
		DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		return format.format(calendar.getTime());
	}
}
