package pl.karol202.smartcontrol.behaviour.actions.soundmode;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.BehavioursManager;

public class ActionSoundModeVolume extends ActionSoundModeProperty<ActionSoundModeVolume.ViewHolderVolume>
{
	class ViewHolderVolume extends RecyclerView.ViewHolder
	{
		private TextView volumeText;
		private SeekBar seekBar;
		
		public ViewHolderVolume(View view)
		{
			super(view);
			volumeText = (TextView) view.findViewById(R.id.text_action_sound_mode_volume);
			seekBar = (SeekBar) view.findViewById(R.id.seekBar_action_sound_mode_volume);
			seekBar.setMax(ActionSoundMode.getAudioManager().getStreamMaxVolume(AudioManager.STREAM_RING) - 1);
			seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					action.setVolume(progress);
					BehavioursManager.saveBehaviours();
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) { }
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) { }
			});
		}
		
		public void bind(ActionSoundMode action)
		{
			volumeText.setEnabled(action.getSoundMode() == ActionSoundMode.SoundMode.NORMAL);
			seekBar.setEnabled(action.getSoundMode() == ActionSoundMode.SoundMode.NORMAL);
			seekBar.setProgress(action.getVolume());
		}
	}
	
	public ActionSoundModeVolume(ActionSoundMode action, Context context)
	{
		super(action, context);
	}
	
	@Override
	public ViewHolderVolume createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.list_action_sound_mode_volume_row, parent, false);
		return new ViewHolderVolume(view);
	}
	
	@Override
	public void bindViewHolder(RecyclerView.ViewHolder holder)
	{
		((ViewHolderVolume) holder).bind(action);
	}
}
