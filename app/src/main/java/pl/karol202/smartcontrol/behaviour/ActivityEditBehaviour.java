package pl.karol202.smartcontrol.behaviour;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.activity.ActivityWithToolbar;

public class ActivityEditBehaviour extends ActivityWithToolbar
{
	private ViewPager viewPager;
	private TabLayout tabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_behaviour);
		createToolbar(true);
		int behaviourId = getIntent().getIntExtra("behaviourId", -1);
		
		viewPager = (ViewPager) findViewById(R.id.viewPager_behaviour);
		AdapterBehaviourTabs adapter = new AdapterBehaviourTabs(getFragmentManager(), this, behaviourId);
		viewPager.setAdapter(adapter);
		
		tabLayout = (TabLayout) findViewById(R.id.toolbar_tabs);
		tabLayout.setupWithViewPager(viewPager);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_edit_behaviour, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.item_behaviour_apply:
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
