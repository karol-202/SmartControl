package pl.karol202.smartcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.analytics.FirebaseAnalytics;
import pl.karol202.smartcontrol.behaviour.AdapterBehavioursList;
import pl.karol202.smartcontrol.behaviour.ActivityEditBehaviour;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.R;

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
	
	private void editBehaviour(int position)
	{
		Intent intent = new Intent(ActivityMain.this, ActivityEditBehaviour.class);
		intent.putExtra("behaviourId", position);
		startActivity(intent);
	}
}