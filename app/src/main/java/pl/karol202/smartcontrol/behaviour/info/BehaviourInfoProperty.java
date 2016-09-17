package pl.karol202.smartcontrol.behaviour.info;

import android.view.View;
import pl.karol202.smartcontrol.behaviour.Behaviour;

public abstract class BehaviourInfoProperty
{
	protected Behaviour behaviour;
	
	public BehaviourInfoProperty(Behaviour behaviour)
	{
		this.behaviour = behaviour;
	}
	
	public abstract int getLayout();
	
	public abstract void initView(View view);
	
	public abstract void onItemClick();
}