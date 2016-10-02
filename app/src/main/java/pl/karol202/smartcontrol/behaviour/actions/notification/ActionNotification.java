package pl.karol202.smartcontrol.behaviour.actions.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import pl.karol202.smartcontrol.R;
import pl.karol202.smartcontrol.behaviour.actions.Action;
import pl.karol202.smartcontrol.behaviour.actions.ActionType;
import pl.karol202.smartcontrol.behaviour.actions.ActivityEditAction;

public class ActionNotification implements Action
{
	private static Context context;
	private static NotificationManager notificationManager;
	private static int nextId = 0;
	
	private String text;
	
	private int notificationId;
	
	public ActionNotification()
	{
		this.notificationId = nextId++;
	}

	public ActionNotification(String text)
	{
		this();
		this.text = text;
	}
	
	public static void init(Context context)
	{
		ActionNotification.context = context;
		ActionNotification.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	@Override
	public ActionType getActionType()
	{
		return ActionType.NOTIFICATION;
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
		return text;
	}
	
	@Override
	public Class<? extends ActivityEditAction> getEditActivity()
	{
		return getActionType().getEditActivity();
	}
	
	@Override
	public void execute()
	{
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setContentTitle("SmartControl");
		builder.setContentText(text);
		notificationManager.notify(notificationId, builder.build());
	}
	
	@Override
	public void loadAction(SharedPreferences prefs, int behaviourId, int actionId)
	{
		String header = "behaviour" + behaviourId + "action" + actionId;
		
		text = prefs.getString(header + "text", "");
	}
	
	@Override
	public void saveAction(SharedPreferences.Editor editor, int behaviourId, int actionId)
	{
		String header = "behaviour" + behaviourId + "action" + actionId;
		
		editor.putString(header + "text", text);
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
