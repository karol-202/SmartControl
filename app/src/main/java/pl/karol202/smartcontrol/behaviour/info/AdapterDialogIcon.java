package pl.karol202.smartcontrol.behaviour.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import pl.karol202.smartcontrol.R;

import java.util.ArrayList;

public class AdapterDialogIcon extends BaseAdapter
{
	private Context context;
	private ArrayList<Integer> icons;
	
	public AdapterDialogIcon(Context context)
	{
		this.context = context;
		this.icons = new ArrayList<>();
		this.icons.add(R.drawable.ic_beh_time_black_48dp);
		this.icons.add(R.drawable.ic_beh_night_black_48dp);
	}
	
	@Override
	public int getCount()
	{
		return icons.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return icons.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView view;
		if(convertView == null || !(convertView instanceof ImageView))
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = (ImageView) inflater.inflate(R.layout.grid_dialog_icons_item, parent, false);
		}
		else view = (ImageView) convertView;
		
		view.setImageResource(icons.get(position));
		
		return view;
	}
}
