package pl.karol202.smartcontrol.behaviour.conditions;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.activity.ActivityWithToolbar;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public abstract class ActivityEditCondition extends ActivityWithToolbar
{
	protected int behaviourId;
	protected int conditionId;
	protected Condition condition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		behaviourId = getIntent().getIntExtra("behaviourId", -1);
		conditionId = getIntent().getIntExtra("conditionId", -1);
		Behaviour behaviour = BehavioursManager.getBehaviour(behaviourId);
		if(conditionId == -1)
		{
			conditionId = behaviour.getConditionsLength();
			behaviour.addCondition(createCondition());
			BehavioursManager.saveBehaviours();
		}
		condition = behaviour.getCondition(conditionId);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_edit_condition, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.item_condition_delete:
			showDeleteDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void showDeleteDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.message_dialog_condition_delete);
		builder.setPositiveButton(R.string.button_positive_dialog_condition_delete, (dialog, which) -> {
			BehavioursManager.getBehaviour(behaviourId).removeCondition(conditionId);
			BehavioursManager.saveBehaviours();
			ActivityEditCondition.this.finish();
		});
		builder.setNegativeButton(R.string.button_negative_dialog_behaviour_delete, null);
		builder.show();
	}
	
	protected abstract Condition createCondition();
}
