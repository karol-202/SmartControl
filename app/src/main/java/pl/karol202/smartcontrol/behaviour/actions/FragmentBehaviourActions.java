package pl.karol202.smartcontrol.behaviour.actions;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.actions.Action.WhichAction;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class FragmentBehaviourActions extends Fragment
{
	private int behaviourId;
	private Behaviour behaviour;
	private WhichAction whichAction;
	private AdapterBehaviourActions adapter;
	
	private RecyclerView recyclerView;
	private FloatingActionButton fab;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		behaviourId = getArguments().getInt("behaviourId");
		if(behaviourId == -1) throw new RuntimeException("behaviourId parameter not passed to FragmentBehaviourActions");
		behaviour = BehavioursManager.getBehaviour(behaviourId);
		
		whichAction = WhichAction.getById(getArguments().getInt("whichAction", -1));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_behaviour_actions, container, false);
		adapter = new AdapterBehaviourActions(getActivity(), behaviour, whichAction, this::editAction);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_behaviour_actions);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(getActivity()));
		
		fab = (FloatingActionButton) view.findViewById(R.id.fab_add_action);
		fab.setOnClickListener(v -> newAction());
		return view;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	private void newAction()
	{
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_action_type, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_dialog_action_type);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		
		AdapterActionTypes adapter = new AdapterActionTypes(getActivity());
		ListView list = (ListView) view.findViewById(R.id.list_action_types);
		list.setAdapter(adapter);
		list.setOnItemClickListener((parent, view1, position, id) -> {
			editAction(-1, (ActionType) adapter.getItem(position));
			dialog.dismiss();
		});
		
		dialog.show();
	}
	
	private void editAction(int position)
	{
		Action ac = whichAction == WhichAction.START ? behaviour.getActionStart(position) : behaviour.getActionEnd(position);
		editAction(position, ac.getActionType());
	}
	
	private void editAction(int position, ActionType type)
	{
		Intent intent = new Intent(getActivity(), type.getEditActivity());
		intent.putExtra("behaviourId", behaviourId);
		intent.putExtra("actionId", position);
		intent.putExtra("whichAction", whichAction.ordinal());
		startActivity(intent);
	}
}
