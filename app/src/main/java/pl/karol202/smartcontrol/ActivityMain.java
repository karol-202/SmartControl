package pl.karol202.smartcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ActivityMain extends AppCompatActivity
{
	private FirebaseAnalytics firebaseAnalytics;
	private ArrayAdapter<Behaviour> listAdapter;
	
	private Toolbar toolbar;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BehavioursManager.init(this);
		
		firebaseAnalytics = FirebaseAnalytics.getInstance(this);
		
		listAdapter = new AdapterBehavioursList(this, BehavioursManager.getBehaviours());
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		listView = (ListView) findViewById(R.id.list_behaviours);
		listView.setAdapter(listAdapter);
	}
}