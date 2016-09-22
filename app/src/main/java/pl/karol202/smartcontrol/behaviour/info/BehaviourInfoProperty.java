package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.view.View;
import pl.karol202.smartcontrol.behaviour.Behaviour;

public abstract class BehaviourInfoProperty
{
	protected Behaviour behaviour;
	protected Context context;
	
	public BehaviourInfoProperty(Behaviour behaviour, Context context)
	{
		this.behaviour = behaviour;
		this.context = context;
	}
	
	public abstract int getLayout();
	
	public abstract void initView(View view);
	
	public abstract void onItemClick();
}