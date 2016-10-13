package pl.karol202.smartcontrol.behaviour.conditions.time;

import java.text.DateFormat;
import java.util.Calendar;

public class Time
{
	private int hour;
	private int minute;
	
	public Time(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}
	
	public boolean isBefore(Time time)
	{
		if(hour < time.getHour()) return true;
		else if(hour > time.getHour()) return false;
		else
		{
			return minute < time.getMinute();
		}
	}
	
	public boolean isAfter(Time time)
	{
		if(hour > time.getHour()) return true;
		else if(hour < time.getHour()) return false;
		else
		{
			return minute > time.getMinute();
		}
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public void setHour(int hour)
	{
		if(hour < 0 || hour > 23) throw new RuntimeException("Invalid hour: " + hour);
		this.hour = hour;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public void setMinute(int minute)
	{
		if(minute < 0 || minute > 59) throw new RuntimeException("Invalid minute: " + minute);
		this.minute = minute;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Time time = (Time) o;
		
		if(hour != time.hour) return false;
		return minute == time.minute;
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
