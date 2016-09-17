package pl.karol202.smartcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.analytics.FirebaseAnalytics;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.ActivityEditBehaviour;
import pl.karol202.smartcontrol.behaviour.AdapterBehavioursList;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class ActivityMain extends ActivityWithToolbar
{
	private FirebaseAnalytics firebaseAnalytics;
	private ArrayAdapter<Behaviour> listAdapter;
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createToolbar(false);
		BehavioursManager.init(this);
		
		firebaseAnalytics = FirebaseAnalytics.getInstance(this);
		
		listAdapter = new AdapterBehavioursList(this, BehavioursManager.getBehaviours());
		
		listView = (ListView) findViewById(R.id.list_behaviours);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener((adapter, view, position, id) -> editBehaviour(position));
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		listAdapter.notifyDataSetChanged();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.item_behaviour_add:
			editBehaviour(-1);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	private void editBehaviour(int position)
	{
		Intent intent = new Intent(this, ActivityEditBehaviour.class);
		intent.putExtra("behaviourId", position);
		startActivity(intent);
	}
}