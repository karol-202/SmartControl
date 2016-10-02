package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.actions.OnActionChangedListener;

public class ActionNotificationText extends ActionNotificationProperty<ActionNotificationText.ViewHolderText>
{
	class ViewHolderText extends RecyclerView.ViewHolder
	{
		private TextView textValue;
		
		public ViewHolderText(View view)
		{
			super(view);
			view.setOnClickListener(v -> showTextDialog());
			textValue = (TextView) view.findViewById(R.id.text_action_notification_text_value);
		}
		
		public void bind(ActionNotification action)
		{
			textValue.setText(action.getText());
		}
	}
	
	private OnActionChangedListener listener;
	
	public ActionNotificationText(ActionNotification action, Context context, OnActionChangedListener listener)
	{
		super(action, context);
		this.listener = listener;
	}
	
	@Override
	public ViewHolderText createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_notification_text_row, parent, false);
		return new ViewHolderText(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderText) holder).bind(action);
	}
	
	private void showTextDialog()
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_action_notification_text, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.title_dialog_action_notification_text);
		builder.setView(view);
		
		EditText editText = (EditText) view.findViewById(R.id.editText_action_notification_text);
		editText.setText(action.getText());
		
		builder.setPositiveButton(R.string.button_positive_dialog_action_notification_text, (dialog, which) -> {
			action.setText(editText.getText().toString());
			BehavioursManager.saveBehaviours();
			listener.onActionChanged();
		});
		builder.setNegativeButton(R.string.button_negative_dialog_action_notification_text, null);
		builder.show();
	}
}
