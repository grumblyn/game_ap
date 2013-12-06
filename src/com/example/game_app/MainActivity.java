package com.example.game_app;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity {
	private boolean changedirx;
	private boolean changediry;
	private float currentx;
	private float currenty;
	private float tempx;
	private float tempy;
	private float x;
	private float y;
	private FrameLayout ball;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics ();
		display.getMetrics(outMetrics);
		
		x = 200;
		y = 0;
		currentx=0;
		currenty=0;
		
		ball = (FrameLayout) findViewById(R.id.ball);
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
		AnimatorSet set = new AnimatorSet();
		//while (1 < Math.abs(x) || 1 < Math.abs(y)){
			tempx = x;
			tempy = y;
//			if(ball.getLeft() + x <= 0) {
//				changedirx = true;
//				tempx = (-1)*ball.getLeft();
//			}
//			if(ball.getLeft() + x >= maxw) {
//				changedirx = true;
//				tempx = maxw - ball.getLeft();
//				
//			}
//			if(ball.getTop() + y <= 0) {
//				changediry = true;
//				//tempy = ball.getTop();
//				tempy = (-1)*ball.getTop();
//			}
//			if(ball.getTop() + y >= maxh) {
//				changediry = true;
//				tempy = maxh - ball.getTop();
//			}
			
			//Animation animation = new TranslateAnimation(currentx, tempx,currenty, tempy);
			Animation animation = new TranslateAnimation(
					Animation.ABSOLUTE, ball.getLeft(), 
					Animation.ABSOLUTE, tempx, 
					Animation.ABSOLUTE, ball.getTop(), 
					Animation.ABSOLUTE, tempy);
			//Animation animation = new TranslateAnimation(ball.getLeft(), tempx,ball.getTop(), tempy);
			animation.setDuration(1000);
			animation.setFillBefore(false);
			animation.setFillEnabled(true);
			animation.setFillAfter(true);
			
			animation.setAnimationListener(new AnimationListener(){

				@Override
                        public void onAnimationEnd(Animation animation) {
	                        // TODO Auto-generated method stub
					//set direction changes
					currentx = currentx + x;
					if(true == true){
						x = -x;
					}
					if(changediry == true){
						y = y*(-1);
					}
					//FrameLayout ball = (FrameLayout) findViewById(R.id.ball);
					int checkx = ball.getLeft();
					int checky = ball.getTop();
					ball.setTop(checky);
					ball.setLeft(checkx);
					
					//int temp_tempx = (int) tempx*ball.getWidth();
					
					//currenty = currenty - tempy;
					//x = (float) (.6*x);
					y = (float) (.6*y);
					Toast.makeText(getApplicationContext(), ""+currentx, Toast.LENGTH_SHORT).show();
					if(x >= .05 || y >= .05){
					//	launch();
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
