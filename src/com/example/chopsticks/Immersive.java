package com.example.chopsticks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Immersive extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_immersive);
	}
	@Override
	 public void onWindowFocusChanged(boolean hasFocus) {
	     super.onWindowFocusChanged(hasFocus);
	     if (hasFocus)
	     {
	         getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	                 | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	                 | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	                 | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	                 | View.SYSTEM_UI_FLAG_FULLSCREEN
	                 | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	     }
	 }
}
