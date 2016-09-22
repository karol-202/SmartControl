package pl.karol202.smartcontrol.behaviour.conditions;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ListView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class FragmentBehaviourConditions extends Fragment
{
	private int behaviourId;
	private Behaviour behaviour;
	private AdapterBehaviourConditions adapter;
	
	private RecyclerView recyclerView;
	private FloatingActionButton fab;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		behaviourId = getArguments().getInt("behaviourId");
		behaviour = BehavioursManager.getBehaviour(behaviourId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_behaviour_conditions, container, false);
		adapter = new AdapterBehaviourConditions(getActivity(), behaviour, this::editCondition);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_behaviour_conditions);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(getActivity()));
		
		fab = (FloatingActionButton) view.findViewById(R.id.fab_add_condition);
		fab.setOnClickListener(v -> newCondition());
		return view;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	private void newCondition()
	{
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_condition_type, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_dialog_condition_type);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		
		AdapterConditionTypes adapter = new AdapterConditionTypes(getActivity());
		ListView list = (ListView) view.findViewById(R.id.list_condition_types);
		list.setAdapter(adapter);
		list.setOnItemClickListener((parent, view1, position, id) -> {
			editCondition(-1, (ConditionType) adapter.getItem(position));
			dialog.dismiss();
		});
		
		dialog.show();
	}
	
	private void editCondition(int position)
	{
		editCondition(position, behaviour.getCondition(position).getConditionType());
	}
	
	private void editCondition(int position, ConditionType type)
	{
		Intent intent = new Intent(getActivity(), type.getEditActivity());
		intent.putExtra("behaviourId", behaviourId);
		intent.putExtra("conditionId", position);
		startActivity(intent);
	}
}