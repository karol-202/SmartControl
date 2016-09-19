package pl.karol202.smartcontrol.behaviour.conditions;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class FragmentBehaviourConditions extends Fragment
{
	private int behaviourId;
	private Behaviour behaviour;
	
	private RecyclerView recyclerView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		behaviourId = getArguments().getInt("behaviourId");
		
		behaviour = BehavioursManager.getBehaviours().get(behaviourId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_behaviour_conditions, container, false);
		AdapterBehaviourConditions adapter = new AdapterBehaviourConditions(getActivity(), behaviour);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_behaviour_conditions);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_fragment_behaviour_conditions, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.item_condition_add:
			editCondition(-1);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	private void editCondition(int position)
	{
		Intent intent = new Intent(getActivity(), ActivityEditCondition.class);
		intent.putExtra("behaviourId", behaviourId);
		intent.putExtra("conditionId", position);
		startActivity(intent);
	}
}