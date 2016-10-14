package pl.karol202.smartcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.ActivityEditBehaviour;
import pl.karol202.smartcontrol.behaviour.AdapterBehaviours;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.actions.bluetooth.ActionBluetooth;
import pl.karol202.smartcontrol.behaviour.actions.notification.ActionNotification;
import pl.karol202.smartcontrol.behaviour.actions.soundmode.ActionSoundMode;
import pl.karol202.smartcontrol.behaviour.actions.wifi.ActionWifi;
import pl.karol202.smartcontrol.behaviour.conditions.time.ConditionTime;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class ActivityMain extends ActivityWithToolbar
{
	private FirebaseAnalytics firebaseAnalytics;
	private AdapterBehaviours adapter;
	
	private RecyclerView recyclerView;
	private FloatingActionButton fab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createToolbar(false);
		
		FirebaseAnalytics.getInstance(this);
		ConditionTime.init(this);
		ActionNotification.init(this);
		ActionSoundMode.init(this);
		ActionWifi.init(this);
		ActionBluetooth.init(this);
		BehavioursManager.init(this);
		BehavioursManager.registerConditions();
		
		adapter = new AdapterBehaviours(this, this::editBehaviour);
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_behaviours);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
		
		fab = (FloatingActionButton) findViewById(R.id.fab_add_behaviour);
		fab.setOnClickListener(v -> editBehaviour(-1));
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	private void editBehaviour(int position)
	{
		Intent intent = new Intent(this, ActivityEditBehaviour.class);
		intent.putExtra("behaviourId", position);
		startActivity(intent);
	}
}