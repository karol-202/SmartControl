package pl.karol202.smartcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBehavioursList extends ArrayAdapter<Behaviour>
{
	private Context context;
	
	public AdapterBehavioursList(Context context, ArrayList<Behaviour> items)
	{
		super(context, R.layout.list_behaviours_row, items);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_behaviours_row, parent, false);
		}
		else view = convertView;
		Behaviour item = getItem(position);
		
		TextView textName = (TextView) view.findViewById(R.id.text_behaviour_name);
		textName.setText(item.getName());
		
		ImageView imageIcon = (ImageView) view.findViewById(R.id.image_behaviour_icon);
		imageIcon.setImageResource(item.getIcon());
		
		Switch switchEnabled = (Switch) view.findViewById(R.id.switch_behaviour_enabled);
		switchEnabled.setChecked(item.isEnabled());
		switchEnabled.setOnCheckedChangeListener((button, checked) ->
		{
			item.setEnabled(checked);
			BehavioursManager.saveBehaviours();
		});
		
		return view;
	}
}
