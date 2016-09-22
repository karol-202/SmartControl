package pl.karol202.smartcontrol.behaviour.info;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.OnBehaviourChangeListener;

public class BehaviourInfoName extends BehaviourInfoProperty
{
	private OnBehaviourChangeListener listener;
	
	public BehaviourInfoName(Behaviour behaviour, Context context, OnBehaviourChangeListener listener)
	{
		super(behaviour, context);
		this.listener = listener;
	}
	
	@Override
	public int getLayout()
	{
		return R.layout.list_behaviour_name_row;
	}
	
	@Override
	public void initView(View view)
	{
		TextView textValue = (TextView) view.findViewById(R.id.text_behaviour_name_value);
		textValue.setText(behaviour.getName());
	}
	
	@Override
	public void onItemClick()
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_behaviour_name, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.title_dialog_behaviour_name);
		builder.setView(view);
		
		EditText editText = (EditText) view.findViewById(R.id.editText_behaviour_name);
		editText.setText(behaviour.getName());
		
		builder.setPositiveButton(R.string.button_positive_dialog_behaviour_name, (dialog, which) -> {
			behaviour.setName(editText.getText().toString());
			BehavioursManager.saveBehaviours();
			listener.onBehaviourChange();
		});
		builder.setNegativeButton(R.string.button_negative_dialog_behaviour_name, null);
		builder.show();
	}
}