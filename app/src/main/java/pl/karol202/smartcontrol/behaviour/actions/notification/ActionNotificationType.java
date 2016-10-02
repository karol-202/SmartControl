package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ActionNotificationType extends ActionNotificationProperty<ActionNotificationType.ViewHolderType>
{
	class ViewHolderType extends RecyclerView.ViewHolder
	{
		private TextView textType;
		
		public ViewHolderType(View view)
		{
			super(view);
			textType = (TextView) view.findViewById(R.id.text_action_notification_type_value);
		}
		
		public void bind(ActionNotification action)
		{
			textType.setText(action.getName());
		}
	}
	
	public ActionNotificationType(ActionNotification action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderType createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_notification_type_row, parent, false);
		return new ViewHolderType(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderType) holder).bind(action);
	}
}
