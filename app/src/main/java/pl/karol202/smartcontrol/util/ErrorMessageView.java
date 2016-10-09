package pl.karol202.smartcontrol.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import pl.karol202.smartcontrol.R;

public class ErrorMessageView extends TextView
{
	private int animationDuration;
	
	public ErrorMessageView(Context context, AttributeSet attrs)
	{
		this(context, attrs, R.attr.errorMessageViewStyle);
	}
	
	public ErrorMessageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		animationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
		setTranslationY(-getHeight());
		setVisibility(GONE);
	}
	
	public void show()
	{
		setVisibility(VISIBLE);
		animate().translationY(0).setDuration(animationDuration).setListener(null);
	}
	
	public void hide()
	{
		animate().translationY(-getHeight()).setDuration(animationDuration).setListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation)
			{
				setVisibility(GONE);
			}
		});
	}
}
