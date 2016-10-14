package pl.karol202.smartcontrol.behaviour.actions.bluetooth;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class ActivityEditActionBluetooth extends ActivityEditAction
{
	private ActionBluetooth actionBluetooth;
	private AdapterActionBluetooth adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(action instanceof ActionBluetooth))
			throw new RuntimeException("Action passed to ActivityEditActionBluetooth is of invalid type.");
		actionBluetooth = (ActionBluetooth) action;
		setContentView(R.layout.activity_edit_action_bluetooth);
		createToolbar(true);
		
		adapter = new AdapterActionBluetooth(actionBluetooth, this);
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_action_bluetooth);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
	}
	
	@Override
	protected Action createAction()
	{
		return new ActionBluetooth(false);
	}
}