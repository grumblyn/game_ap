package com.example.game_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
	private boolean changedirx;
	private boolean changediry;
	private float currentx;
	private float currenty;
	private float tempx;
	private float tempy;
	private float xmove;
	private float ymove;
	private float x;
	private float y;
	private FrameLayout ball;
	private float startx;
	private float starty;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics ();
		display.getMetrics(outMetrics);
		
		x = 1000;
		y = 1000;
		currentx=0;
		currenty=0;
		
		ball = (FrameLayout) findViewById(R.id.ball);
		startx = ball.getLeft();
		starty = ball.getTop();
		Button launcher = (Button) findViewById(R.id.launch);
		launcher.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		          launch();
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

//	public void launch(View v, float x, float y, float currentx, float currenty) {
	public void launch() {
		//ball = (FrameLayout) findViewById(R.id.ball);
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		//float currentx = 0;
		//float currenty = 0;
		
		changedirx = false;
		changediry = false;
		
		int maxw = parent.getWidth();
		int maxh = parent.getHeight();
		maxw = maxw - ball.getWidth();
		maxh = maxh - ball.getHeight();
			int time = 500;

			tempx = currentx + x;
			tempy = currenty + y;
			if(ball.getLeft() + x <= 0) {
				changedirx = true;
				tempx = (-1)*ball.getLeft();
			}
			if(tempx + ball.getLeft() >= maxw) {
				changedirx = true;
				tempx = maxw - ball.getLeft();
				//tempx = maxw - currentx;
				//tempx = maxw;
				
			}
			if(ball.getTop() + y <= 0) {
				changediry = true;
				//tempy = ball.getTop();
				tempy = (-1)*ball.getTop();
			}
			if(ball.getTop() + tempy >= maxh) {
				changediry = true;
				tempy = maxh - ball.getTop();
			}
			
			Animation animation = new TranslateAnimation(
					Animation.ABSOLUTE, currentx, 
					Animation.ABSOLUTE, tempx, 
					Animation.ABSOLUTE, currenty, 
					Animation.ABSOLUTE, tempy);
			animation.setDuration(time);
			animation.setFillEnabled(true);
			animation.setFillAfter(true);
			animation.setInterpolator(new LinearInterpolator());
			
			animation.setAnimationListener(new AnimationListener(){

				@Override
                        public void onAnimationEnd(Animation animation) {
					if(!changedirx){
						currentx = currentx + x;
					}
					if(!changediry){
						currenty = currenty + y;
					}
//					currentx = currentx + tempx;
//					currenty = currenty + tempy;
					if(changedirx == true){
						x = -x;	
						currentx = tempx;
					}
					if(changediry == true){
						
						y = y*(-1);
						currenty = tempy;
					}
					//FrameLayout ball = (FrameLayout) findViewById(R.id.ball);
					int checkx = ball.getLeft();
					int checky = ball.getTop();
					ball.setTop(checky);
					ball.setLeft(checkx);
					x = (float) (.8*x);
					y = (float) (.8*y);
					//Toast.makeText(getApplicationContext(), "c "+currentx+" x "+x+" t"+tempx, Toast.LENGTH_SHORT).show();
					if(Math.abs(x) >= 1 || Math.abs(y) >= 1){
						launch();
					}
                        }

				@Override
                        public void onAnimationRepeat(Animation animation) {
	                        // TODO Auto-generated method stub
	                        
                        }

				@Override
                        public void onAnimationStart(Animation animation) {
	                        // TODO Auto-generated method stub
	                        
                        }});
			ball.startAnimation(animation);
		
	}
	
}
