package com.example.chopsticks;

import java.util.ArrayList;

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
	/**
	 * True = Player 1 ...................... False = Player 2 / CPU
	 */
	private boolean turn;
	private String prevBtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		L1 = (Button) findViewById(R.id.L1);
		L2 = (Button) findViewById(R.id.L2);
		R1 = (Button) findViewById(R.id.R1);
		R2 = (Button) findViewById(R.id.R2);
		turn = Math.random() > .5;

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_immersive);
		setListeners();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
		{
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	private void setListeners()
	{
		L1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				if (L1Val != 5)
				{
					if (turn == true && (prevBtn == null || prevBtn == "L2"))
					{
						prevBtn = "L1";
					}
					else if (prevBtn == "R1")
					{
						L1Val = add(L1Val, R1Val);
						prevBtn = null;
					}
					else if (prevBtn == "R2")
					{
						L1Val = add(L1Val, R2Val);
						prevBtn = null;
					}
				}
			}
		});
		L2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				if (L2Val != 5)
				{
					if (turn == true && (prevBtn == null || prevBtn == "L1"))
					{
						prevBtn = "L2";
					}
					else if (prevBtn == "R1")
					{
						L2Val = add(L2Val, R1Val);
						prevBtn = null;
					}
					else if (prevBtn == "R2")
					{
						L2Val = add(L2Val, R2Val);
						prevBtn = null;
					}
				}
			}
		});
		R1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				if (R1Val != 5)
				{
					if (turn == false && (prevBtn == null || prevBtn == "R2"))
					{
						prevBtn = "R1";
					}
					else if (prevBtn == "L1")
					{
						R1Val = add(R1Val, L1Val);
						prevBtn = null;
					}
					else if (prevBtn == "L2")
					{
						R1Val = add(R1Val, L2Val);
						prevBtn = null;
					}
				}
			}
		});
		R2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				if (R2Val != 5)
				{
					if (turn == false && (prevBtn == null || prevBtn == "R1"))
					{
						prevBtn = "R2";
					}
					else if (prevBtn == "L1")
					{
						R1Val = add(R2Val, L1Val);
						prevBtn = null;
					}
					else if (prevBtn == "L2")
					{
						R1Val = add(R2Val, L2Val);
						prevBtn = null;
					}
				}
			}
		});
	}

	private static int add(int startVal, int addVal)
	{
		if (startVal > 5 | addVal > 5 | startVal < 0 | addVal < 0)
			throw new IndexOutOfBoundsException();
		else
			return ((startVal + addVal) % 5);
	}

	@SuppressWarnings("unused")
	private static int[][] dostuff(int[] thing)
	{
		/*
		 * Hand possibilities [index0][possibility#] // index0 2 = left hand
		 * value / index0 3 = right hand value / index0 4 = move // move 0 =
		 * cL-pL / move 1 = cL-pR / move 2 = cR-pL / move 3 = cR-pR
		 */
		int[][] pos = new int[5][16];
		for (int i = 0; i < 16; i++)
		{
			for (int hand = 0; hand < 4; hand++)
			{
				pos[hand][i] = thing[hand];
			}
		}
		for (int i = 0; i < 4; i++)
		{
			for (int i1 = 0; i1 < 4; i1++)
			{
				pos[4][(4 * i) + i1] = i;
			}
		}
		for (int i1 = 0; i1 < 4; i1++)
		{
			pos[0][(0) + i1] = add(pos[0][(0) + i1], thing[2]);
			pos[1][(4) + i1] = add(pos[0][(4) + i1], thing[2]);
			pos[0][(8) + i1] = add(pos[1][(8) + i1], thing[3]);
			pos[1][(12) + i1] = add(pos[1][(12) + i1], thing[3]);
		}
		int i = 0;
		for (int i1 = 0; i1 < 4; i1++)
		{
			for (int i2 = 2; i2 < 4; i2++)
			{
				for (int i3 = 0; i3 < 2; i3++, i++)
				{
					pos[i2][i] = add(pos[i2][i], pos[i3][i1 * 4]);
				}
			}
		}
		return pos;
	}
}