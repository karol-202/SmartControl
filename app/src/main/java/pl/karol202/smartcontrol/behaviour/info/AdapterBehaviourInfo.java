package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import pl.karol202.smartcontrol.behaviour.Behaviour;
import pl.karol202.smartcontrol.behaviour.OnBehaviourChangeListener;

import java.util.ArrayList;

public class AdapterBehaviourInfo extends BaseAdapter implements AdapterView.OnItemClickListener, OnBehaviourChangeListener
{
	private Context context;
	private ArrayList<BehaviourInfoProperty> properties;
	
	public AdapterBehaviourInfo(Context context, Behaviour behaviour)
	{
		this.context = context;
		this.properties = new ArrayList<>();
		this.properties.add(new BehaviourInfoName(behaviour, context, this));
		this.properties.add(new BehaviourInfoIcon(behaviour, context, this));
		this.properties.add(new BehaviourInfoEnabled(behaviour, context));
	}
	
	@Override
	public int getCount()
	{
		return properties.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return properties.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		BehaviourInfoProperty property = (BehaviourInfoProperty) getItem(position);
		if(convertView == null || !(convertView.getTag().getClass().equals(property.getClass())))
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(property.getLayout(), parent, false);
			view.setTag(property);
		}
		else view = convertView;
		property.initView(view);
		return view;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		((BehaviourInfoProperty) getItem(position)).onItemClick();
	}
	
	@Override
	public void onBehaviourChange()
	{
		notifyDataSetChanged();
	}
}
