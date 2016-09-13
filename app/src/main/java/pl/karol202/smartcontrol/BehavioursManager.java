package pl.karol202.smartcontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

public class BehavioursManager
{
	private static SharedPreferences prefs;
	private static SharedPreferences.Editor editor;
	private static ArrayList<Behaviour> behaviours = new ArrayList<>();
	
	public static void init(Context context)
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		editor = prefs.edit();
		behaviours.add(new Behaviour("Zachowanie 1", 0, true));
	}
	
	public static ArrayList<Behaviour> getBehaviours()
	{
		return behaviours;
	}
}
