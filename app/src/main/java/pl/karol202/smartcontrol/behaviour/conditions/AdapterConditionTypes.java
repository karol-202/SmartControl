package pl.karol202.smartcontrol.behaviour.conditions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class AdapterConditionTypes extends BaseAdapter
{
	private Context context;
	
	public AdapterConditionTypes(Context context)
	{
		this.context = context;
	}
	
	@Override
	public int getCount()
	{
		return ConditionType.values().length;
	}
	
	@Override
	public Object getItem(int position)
	{
		return ConditionType.values()[position];
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
		if(convertView == null) view = LayoutInflater.from(context).inflate(R.layout.list_condition_types_row, parent, false);
		else view = convertView;
		ConditionType type = (ConditionType) getItem(position);
		
		ImageView image = (ImageView) view.findViewById(R.id.image_condition_type);
		image.setImageResource(type.getIcon());
		
		TextView text = (TextView) view.findViewById(R.id.text_condition_type);
		text.setText(type.getName());
		
		return view;
	}
}
