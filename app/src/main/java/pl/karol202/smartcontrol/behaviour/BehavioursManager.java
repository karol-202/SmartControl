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
	}
	
	private static void loadBehaviours()
	{
		int length = prefs.getInt("behavioursLength", 0);
		behaviours.clear();
		for(int i = 0; i < length; i++) behaviours.add(Behaviour.loadBehaviour(prefs, i));
	}
	
	public static void saveBehaviours()
	{
		editor.putInt("behavioursLength", behaviours.size());
		for(int i = 0; i < behaviours.size(); i++) behaviours.get(i).saveBehaviour(editor, i);
		editor.commit();
	}
	
	public static void registerConditions()
	{
		for(Behaviour behaviour : behaviours) behaviour.registerConditions();
	}
	
	public static void addBehaviour(Behaviour behaviour)
	{
		behaviours.add(behaviour);
	}
	
	public static Behaviour getBehaviour(int position)
	{
		return behaviours.get(position);
	}
	
	public static void removeBehaviour(int position)
	{
		behaviours.remove(position);
	}
	
	public static int getBehaviourLength()
	{
		return behaviours.size();
	}
}