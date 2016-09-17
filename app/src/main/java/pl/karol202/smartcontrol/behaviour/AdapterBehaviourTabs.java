package pl.karol202.smartcontrol.behaviour;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.info.FragmentBehaviourInfo;

import java.util.ArrayList;
import java.util.List;

public class AdapterBehaviourTabs extends FragmentPagerAdapter
{
	private List<Fragment> fragments;
	private List<String> titles;
	private Bundle arguments;
	
	public AdapterBehaviourTabs(FragmentManager manager, Context context, int behaviourId)
	{
		super(manager);
		fragments = new ArrayList<>();
		titles = new ArrayList<>();
		arguments = new Bundle();
		arguments.putInt("behaviourId", behaviourId);
		
		addFragment(new FragmentBehaviourInfo(), context.getString(R.string.tab_info));
		//addFragment(new FragmentBehaviourInfo(), context.getString(R.string.tab_info));
	}
	
	@Override
	public int getCount()
	{
		return fragments.size();
	}
	
	@Override
	public Fragment getItem(int position)
	{
		return fragments.get(position);
	}
	
	@Override
	public CharSequence getPageTitle(int position)
	{
		return titles.get(position);
	}
	
	private void addFragment(Fragment fragment, String title)
	{
		fragment.setArguments(arguments);
		fragments.add(fragment);
		titles.add(title);
	}
}