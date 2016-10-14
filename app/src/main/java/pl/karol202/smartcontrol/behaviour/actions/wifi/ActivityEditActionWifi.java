package pl.karol202.smartcontrol.behaviour.actions.wifi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class ActivityEditActionWifi extends ActivityEditAction
{
	private ActionWifi actionWifi;
	private AdapterActionWifi adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(action instanceof ActionWifi))
			throw new RuntimeException("Action passed to ActivityEditActionWifi is of invalid type.");
		actionWifi = (ActionWifi) action;
		setContentView(R.layout.activity_edit_action_wifi);
		createToolbar(true);
		
		adapter = new AdapterActionWifi(actionWifi, this);
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_action_wifi);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
	}
	
	@Override
	protected Action createAction()
	{
		return new ActionWifi(true);
	}
}
