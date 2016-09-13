package pl.karol202.smartcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ActivityMain extends AppCompatActivity
{
	private FirebaseAnalytics firebaseAnalytics;
	
	private Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		firebaseAnalytics = FirebaseAnalytics.getInstance(this);
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}
}