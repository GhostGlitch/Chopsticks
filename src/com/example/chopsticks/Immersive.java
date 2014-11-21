package com.example.chopsticks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Immersive extends Activity
{
	int L1Val = 1;
	int L2Val = 1;
	int R1Val = 1;
	int R2Val = 1;
	private Button L1;
	private Button L2;
	private Button R1;
	private Button R2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		L1 = (Button) findViewById(R.id.L1);
		L2 = (Button) findViewById(R.id.L2);
		R1 = (Button) findViewById(R.id.R1);
		R2 = (Button) findViewById(R.id.R2);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_immersive);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
		{
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	private int addLoopBaseFive(int startVal, int addVal)
	{
		if (startVal > 5 | addVal > 5 | startVal < 0 | addVal < 0)
			throw new IndexOutOfBoundsException();
		else
			return ((startVal + addVal) % 5);
	}
}
