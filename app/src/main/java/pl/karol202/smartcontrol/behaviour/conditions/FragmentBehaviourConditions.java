package pl.karol202.smartcontrol.behaviour.conditions;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ListView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.conditions.AdapterBehaviourConditions.OnItemClickListener;

public class FragmentBehaviourConditions extends Fragment
{
	private int behaviourId;
	private Behaviour behaviour;
	private AdapterBehaviourConditions adapter;
	
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
		OnItemClickListener listener = this::editCondition;
		adapter = new AdapterBehaviourConditions(getActivity(), behaviour, listener);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_behaviour_conditions);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
		return view;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
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
			newCondition();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
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