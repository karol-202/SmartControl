package pl.karol202.smartcontrol.behaviour.actions.soundmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActionType;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;

public class ActionSoundMode implements Action
{
	enum SoundMode
	{
		NORMAL(AudioManager.RINGER_MODE_NORMAL, R.string.sound_mode_normal),
		VIBRATE(AudioManager.RINGER_MODE_VIBRATE, R.string.sound_mode_vibrate),
		SILENT(AudioManager.RINGER_MODE_SILENT, R.string.sound_mode_silent);
		
		private int ringerMode;
		private int name;
		
		SoundMode(int ringerMode, int name)
		{
			this.ringerMode = ringerMode;
			this.name = name;
		}
		
		public static SoundMode getById(int id)
		{
			if(id == -1 || id >= values().length) throw new RuntimeException("No such sound soundMode: " + id);
			return values()[id];
		}
		
		public int getRingerMode()
		{
			return ringerMode;
		}
		
		public int getName()
		{
			return name;
		}
	}
	
	private static Context context;
	private static AudioManager audioManager;
	
	private SoundMode soundMode;
	//Volume of STREAM_RING, 0 is still value greater than vibration level.
	private int volume;
	
	public ActionSoundMode() { }
	
	public ActionSoundMode(SoundMode soundMode, int volume)
	{
		this.soundMode = soundMode;
		this.volume = volume;
	}
	
	public static void init(Context context)
	{
		ActionSoundMode.context = context;
		ActionSoundMode.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}
	
	@Override
	public ActionType getActionType()
	{
		return ActionType.SOUND_MODE;
	}
	
	@Override
	public int getName()
	{
		return getActionType().getName();
	}
	
	@Override
	public int getIcon()
	{
		return getActionType().getIcon();
	}
	
	@Override
	public String getInfo()
	{
		return context.getString(soundMode.getName());
	}
	
	@Override
	public Class<? extends ActivityEditAction> getEditActivity()
	{
		return getActionType().getEditActivity();
	}
	
	@Override
	public void execute()
	{
		audioManager.setRingerMode(soundMode.getRingerMode());
		if(soundMode == SoundMode.NORMAL) audioManager.setStreamVolume(AudioManager.STREAM_RING, volume + 1, 0);
	}
	
	@Override
	public void loadAction(SharedPreferences prefs, String header)
	{
		soundMode = SoundMode.getById(prefs.getInt(header + "soundmode", -1));
	}
	
	@Override
	public void saveAction(SharedPreferences.Editor editor, String header)
	{
		editor.putInt(header + "soundmode", soundMode.ordinal());
	}
	
	public static AudioManager getAudioManager()
	{
		return audioManager;
	}
	
	public SoundMode getSoundMode()
	{
		return soundMode;
	}
	
	public void setSoundMode(SoundMode soundMode)
	{
		this.soundMode = soundMode;
	}
	
	public int getVolume()
	{
		return volume;
	}
	
	public void setVolume(int volume)
	{
		this.volume = volume;
	}
}