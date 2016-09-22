package pl.karol202.smartcontrol.behaviour;

import android.app.AlertDialog;
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
	private int behaviourId;
	
	private ViewPager viewPager;
	private TabLayout tabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_behaviour);
		createToolbar(true);
		
		behaviourId = getIntent().getIntExtra("behaviourId", -1);
		if(behaviourId == -1)
		{
			behaviourId = BehavioursManager.getBehaviourLength();
			BehavioursManager.addBehaviour(new Behaviour().defaultBehaviour());
			BehavioursManager.saveBehaviours();
		}
		
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
		case R.id.item_behaviour_delete:
			showDeleteDialog();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	private void showDeleteDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.message_dialog_behaviour_delete);
		builder.setPositiveButton(R.string.button_positive_dialog_behaviour_delete, (dialog, which) -> {
			BehavioursManager.removeBehaviour(behaviourId);
			BehavioursManager.saveBehaviours();
			ActivityEditBehaviour.this.finish();
		});
		builder.setNegativeButton(R.string.button_negative_dialog_behaviour_delete, null);
		builder.show();
	}
}
