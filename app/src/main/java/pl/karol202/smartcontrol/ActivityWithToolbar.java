package pl.karol202.smartcontrol;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public abstract class ActivityWithToolbar extends AppCompatActivity
{
	protected Toolbar toolbar;
	
	void createToolbar(boolean navButton)
	{
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if(toolbar == null) Log.e("SmartControl", "Toolbar not found in activity: " + getTitle());
		setSupportActionBar(toolbar);
		if(navButton)
		{
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == android.R.id.home) finish();
		return super.onOptionsItemSelected(item);
	}
}