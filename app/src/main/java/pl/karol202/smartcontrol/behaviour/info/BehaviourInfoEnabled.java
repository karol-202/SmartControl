package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.view.View;
import android.widget.Switch;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class BehaviourInfoEnabled extends BehaviourInfoProperty
{
	private Context context;
	
	public BehaviourInfoEnabled(Behaviour behaviour, Context context)
	{
		super(behaviour);
		this.context = context;
	}
	
	@Override
	public int getLayout()
	{
		return R.layout.list_behaviour_enabled_row;
	}
	
	@Override
	public void initView(View view)
	{
		Switch switchEnabled = (Switch) view.findViewById(R.id.switch_behaviour_enabled);
		switchEnabled.setChecked(behaviour.isEnabled());
		switchEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
			behaviour.setEnabled(isChecked);
			BehavioursManager.saveBehaviours();
		});
	}
	
	@Override
	public void onItemClick() { }
}