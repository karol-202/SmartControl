package pl.karol202.smartcontrol.behaviour.actions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class AdapterActionTypes extends BaseAdapter
{
	private Context context;
	
	public AdapterActionTypes(Context context)
	{
		this.context = context;
	}
	
	@Override
	public int getCount()
	{
		return ActionType.values().length;
	}
	
	@Override
	public Object getItem(int position)
	{
		return ActionType.values()[position];
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
		if(convertView == null) view = LayoutInflater.from(context).inflate(R.layout.list_action_types_row, parent, false);
		else view = convertView;
		ActionType type = (ActionType) getItem(position);
		
		ImageView image = (ImageView) view.findViewById(R.id.image_action_type);
		image.setImageResource(type.getIcon());
		
		TextView text = (TextView) view.findViewById(R.id.text_action_type);
		text.setText(type.getName());
		
		return view;
	}
}
