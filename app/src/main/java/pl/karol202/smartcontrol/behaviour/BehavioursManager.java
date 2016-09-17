package pl.karol202.smartcontrol.behaviour;

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
		loadBehaviours();
		if(behaviours.size() == 0) behaviours.add(new Behaviour("Zachowanie 1", 0, true));
	}
	
	private static void loadBehaviours()
	{
		int length = prefs.getInt("behavioursLength", 0);
		for(int i = 0; i < length; i++) behaviours.add(Behaviour.loadBehaviour(prefs, i));
	}
	
	public static void saveBehaviours()
	{
		editor.putInt("behavioursLength", behaviours.size());
		for(int i = 0; i < behaviours.size(); i++) behaviours.get(i).saveBehaviour(editor, i);
		editor.commit();
	}
	
	public static ArrayList<Behaviour> getBehaviours()
	{
		return behaviours;
	}
}
