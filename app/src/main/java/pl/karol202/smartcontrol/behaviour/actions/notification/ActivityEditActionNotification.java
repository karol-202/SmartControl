package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class ActivityEditActionNotification extends ActivityEditAction
{
	private ActionNotification actionNotification;
	private AdapterActionNotification adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(action instanceof ActionNotification))
			throw new RuntimeException("Action passed to ActivityEditActionNotification is of invalid type.");
		actionNotification = (ActionNotification) action;
		setContentView(R.layout.activity_edit_action_notification);
		createToolbar(true);
		
		adapter = new AdapterActionNotification(actionNotification, this);
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_action_notification);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
	}
	
	@Override
	protected Action createAction()
	{
		return new ActionNotification(getString(R.string.action_notification_text_example));
	}
}
