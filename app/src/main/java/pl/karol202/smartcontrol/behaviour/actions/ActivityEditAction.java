package pl.karol202.smartcontrol.behaviour.actions;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.activity.ActivityWithToolbar;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.actions.Action.WhichAction;

public abstract class ActivityEditAction extends ActivityWithToolbar
{
	protected int behaviourId;
	protected WhichAction whichAction;
	protected int actionId;
	protected Action action;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		behaviourId = getIntent().getIntExtra("behaviourId", -1);
		if(behaviourId == -1) throw new RuntimeException("behaviourId not passed to ActivityEditAction");
		Behaviour behaviour = BehavioursManager.getBehaviour(behaviourId);
		
		whichAction = WhichAction.getById(getIntent().getIntExtra("whichAction", -1));
		
		actionId = getIntent().getIntExtra("actionId", -1);
		if(whichAction == WhichAction.START)
		{
			if(actionId == -1)
			{
				actionId = behaviour.getActionsStartLength();
				behaviour.addActionStart(createAction());
				BehavioursManager.saveBehaviours();
			}
			action = behaviour.getActionStart(actionId);
		}
		else
		{
			if(actionId == -1)
			{
				actionId = behaviour.getActionsEndLength();
				behaviour.addActionEnd(createAction());
				BehavioursManager.saveBehaviours();
			}
			action = behaviour.getActionEnd(actionId);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_edit_action, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.item_action_delete:
			showDeleteDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void showDeleteDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.message_dialog_action_delete);
		builder.setPositiveButton(R.string.button_positive_dialog_action_delete, (dialog, which) -> {
			if(whichAction == WhichAction.START) BehavioursManager.getBehaviour(behaviourId).removeActionStart(actionId);
			else BehavioursManager.getBehaviour(behaviourId).removeActionEnd(actionId);
			BehavioursManager.saveBehaviours();
			ActivityEditAction.this.finish();
		});
		builder.setNegativeButton(R.string.button_negative_dialog_action_delete, null);
		builder.show();
	}
	
	protected abstract Action createAction();
}
