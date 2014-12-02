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

	private int add(int startVal, int addVal)
	{
		if (startVal > 5 | addVal > 5 | startVal < 0 | addVal < 0)
			throw new IndexOutOfBoundsException();
		else
			return ((startVal + addVal) % 5);
	}

	@SuppressWarnings("unused")
	private Array[][] dostuff(int[] thing)
	{
		/*
		 * Player hand possibilities [index0][possibility#] // index0 0 = left
		 * hand value / index0 1 = right hand value
		 */
		int[][] pPos = new int[3][4];
		for (int i = 0; i < 4; i++)
		{
			for (int hand = 0; hand < 2; hand++)
			{
				pPos[hand][i] = thing[hand];
			}
		}
		pPos[0][0] = add(pPos[0][0], thing[2]);
		pPos[1][1] = add(pPos[0][1], thing[2]);
		pPos[0][2] = add(pPos[1][2], thing[3]);
		pPos[1][3] = add(pPos[1][3], thing[3]);
		/*
		 * CPU hand possibilities [index0][possibility#] // index0 0 = left hand
		 * value / index0 1 = right hand value / index0 2 = move // move 0 =
		 * p2L-p1L / move 1 = p2L-p1R / move 1 = p2R-p1L / move 1 = p2R-p1R
		 */
		int[][] cPos = new int[3][16];
		for (int i = 0; i < 16; i++)
		{
			for (int hand = 0; hand < 2; hand++)
			{
				cPos[hand][i] = thing[hand+2];
			}
		}
		for (int i = 0; i < 4; i++)
		{
			cPos[0][4 * i] = i;
			cPos[0][(4 * i) + 1] = i;
			cPos[0][(4 * i) + 2] = i;
			cPos[0][(4 * i) + 3] = i;
		}
		cPos[0][0] = add(cPos[0][0], pPos[0][0]);
		cPos[0][1] = add(cPos[0][1], pPos[1][0]);
		cPos[1][2] = add(cPos[1][2], pPos[0][0]);
		cPos[1][3] = add(cPos[1][3], pPos[1][0]);
		cPos[0][4] = add(cPos[0][4], pPos[0][1]);
		cPos[0][5] = add(cPos[0][5], pPos[1][1]);
		cPos[1][6] = add(cPos[1][6], pPos[0][1]);
		cPos[1][7] = add(cPos[1][7], pPos[1][1]);
		cPos[0][8] = add(cPos[0][8], pPos[0][2]);
		cPos[0][9] = add(cPos[0][9], pPos[1][2]);
		cPos[1][10] = add(cPos[1][10], pPos[0][2]);
		cPos[1][11] = add(cPos[1][11], pPos[1][2]);
		cPos[0][12] = add(cPos[0][12], pPos[0][3]);
		cPos[0][13] = add(cPos[0][13], pPos[1][3]);
		cPos[1][14] = add(cPos[1][14], pPos[0][3]);
		cPos[1][15] = add(cPos[1][15], pPos[1][3]);
		return cPos;
	}
}