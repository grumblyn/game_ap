package com.example.game_app;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics ();
		display.getMetrics(outMetrics);

		float density  = getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels / density;
		float dpWidth  = outMetrics.widthPixels / density;
		
		Button launcher = (Button) findViewById(R.id.launch);
		launcher.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    float changex = 110;
			    float changey = -100;
		          launch(v, changex, changey);
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
	

	public void launch(View v, float x, float y) {
		FrameLayout ball = (FrameLayout) findViewById(R.id.ball);
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		float currentx = 0;
		float currenty = 0;
		boolean changedirx = false;
		boolean changediry = false;
		
		int maxw = parent.getWidth();
		int maxh = parent.getHeight();
		maxw = maxw - ball.getWidth();
		maxh = maxh - ball.getHeight();
		AnimatorSet set = new AnimatorSet();
		while (1 < Math.abs(x) || 1 < Math.abs(y)){
			boolean xisdone = false;
			boolean yisdone = false;
			float tempx = x;
			float tempy = y;
			if(ball.getLeft() + x <= 0) {
				changedirx = true;
				tempx = (-1)*ball.getLeft();
			}
			if(ball.getLeft() + x >= maxw) {
				changedirx = true;
				tempx = maxw - currentx;
				
			}
			if(ball.getTop() + y <= 0) {
				changediry = true;
				tempy = ball.getTop();
			}
			if(ball.getTop() + y >= maxh) {
				changediry = true;
				tempy = maxh - currenty;
			}
			ObjectAnimator xani = new ObjectAnimator();
			ObjectAnimator yani = new ObjectAnimator();
			xani.addListener(new AnimatorListener() {
				    @Override 
				    public void onAnimationEnd(Animator animation) {
				                
				    }

				@Override
                        public void onAnimationCancel(Animator animation) {
	                        // TODO Auto-generated method stub
	                        
                        }

				@Override
                        public void onAnimationRepeat(Animator animation) {
	                        // TODO Auto-generated method stub
	                        
                        }

				@Override
                        public void onAnimationStart(Animator animation) {
	                        // TODO Auto-generated method stub
	                        
                        }
				});
//			set.playTogether(
//			    ObjectAnimator.ofFloat(ball, "translationX", currentx, tempx),
//			    ObjectAnimator.ofFloat(ball, "translationY", currenty, tempy)
//			);
			set.playTogether(
					xani.ofFloat(ball, "translationX", currentx, tempx),
					yani.ofFloat(ball, "translationY", currenty, tempy)
			);
			set.setDuration(3000).start();
			//set direction changes
			if(changedirx){
				x = x*(-1);
			}
			if(changediry){
				y = y*(-1);
			}
			
			currentx = currentx + tempx;
			currenty = currenty - tempy;
			x = (float) (.7*x);
			y = (float) (.7*y);
			changedirx = false;
			changediry = false;
		}
		
	}
	
}
