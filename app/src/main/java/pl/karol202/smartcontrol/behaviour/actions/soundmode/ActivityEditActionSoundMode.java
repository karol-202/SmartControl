package pl.karol202.smartcontrol.behaviour.actions.soundmode;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;
import pl.karol202.smartcontrol.behaviour.actions.soundmode.ActionSoundMode.SoundMode;
import pl.karol202.smartcontrol.util.ItemDecorationDivider;

public class ActivityEditActionSoundMode extends ActivityEditAction
{
	private ActionSoundMode actionSoundMode;
	private AdapterActionSoundMode adapter;
	
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!(action instanceof ActionSoundMode))
			throw new RuntimeException("Action passed to ActivityEditActionSoundMode is of invalid type.");
		actionSoundMode = (ActionSoundMode) action;
		setContentView(R.layout.activity_edit_action_sound_mode);
		createToolbar(true);
		
		adapter = new AdapterActionSoundMode(actionSoundMode, this);
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_action_sound_mode);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new ItemDecorationDivider(this));
	}
	
	@Override
	protected Action createAction()
	{
		int volume = ActionSoundMode.getAudioManager().getStreamVolume(AudioManager.STREAM_RING);
		return new ActionSoundMode(SoundMode.NORMAL, volume - 1);
	}
}
