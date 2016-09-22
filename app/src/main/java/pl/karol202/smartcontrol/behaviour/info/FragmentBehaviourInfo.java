package pl.karol202.smartcontrol.behaviour.info;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class FragmentBehaviourInfo extends Fragment
{
	private Behaviour behaviour;
	
	private ListView listBehaviourInfo;
	
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
		AdapterBehaviourInfo adapter = new AdapterBehaviourInfo(getActivity(), behaviour);
		
		listBehaviourInfo = (ListView) view.findViewById(R.id.list_behaviour_info);
		listBehaviourInfo.setAdapter(adapter);
		listBehaviourInfo.setOnItemClickListener(adapter);
		return view;
	}
}
