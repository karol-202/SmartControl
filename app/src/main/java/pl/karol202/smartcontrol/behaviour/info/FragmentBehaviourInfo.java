package pl.karol202.smartcontrol.behaviour.info;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class FragmentBehaviourInfo extends Fragment
{
	private Behaviour behaviour;
	private AdapterBehaviourInfo adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		int behaviourId = getArguments().getInt("behaviourId");
		
		behaviour = BehavioursManager.getBehaviour(behaviourId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_behaviour_info, container, false);
		adapter = new AdapterBehaviourInfo(getActivity(), behaviour);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_behaviour_info);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(getActivity()));
		return view;
	}
}
