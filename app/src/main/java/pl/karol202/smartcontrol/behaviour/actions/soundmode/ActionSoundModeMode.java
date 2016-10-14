package pl.karol202.smartcontrol.behaviour.actions.soundmode;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;
import pl.karol202.smartcontrol.behaviour.actions.OnActionChangedListener;
import pl.karol202.smartcontrol.behaviour.actions.soundmode.ActionSoundMode.SoundMode;

public class ActionSoundModeMode extends ActionSoundModeProperty<ActionSoundModeMode.ViewHolderMode>
{
	class ViewHolderMode extends RecyclerView.ViewHolder
	{
		private TextView textMode;
		
		public ViewHolderMode(View view)
		{
			super(view);
			view.setOnClickListener(v -> showDialog());
			textMode = (TextView) view.findViewById(R.id.text_action_sound_mode_mode_value);
		}
		
		public void bind(ActionSoundMode action)
		{
			textMode.setText(action.getSoundMode().getName());
		}
	}
	
	private OnActionChangedListener listener;
	
	public ActionSoundModeMode(ActionSoundMode action, Context context, OnActionChangedListener listener)
	{
		super(action, context);
		this.listener = listener;
	}
	
	@Override
	public ViewHolderMode createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_sound_mode_mode_row, parent, false);
		return new ViewHolderMode(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderMode) holder).bind(action);
	}
	
	private void showDialog()
	{
		CharSequence[] items = new CharSequence[] { context.getString(SoundMode.NORMAL.getName()),
													context.getString(SoundMode.VIBRATE.getName()),
													context.getString(SoundMode.SILENT.getName()) };
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.title_dialog_action_sound_mode_mode);
		builder.setSingleChoiceItems(items, action.getSoundMode().ordinal(), (dialog, which) -> {
			action.setSoundMode(SoundMode.values()[which]);
			BehavioursManager.saveBehaviours();
			listener.onActionChanged();
			dialog.dismiss();
		});
		builder.show();
	}
}
