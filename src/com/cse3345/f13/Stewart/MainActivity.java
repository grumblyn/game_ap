package com.cse3345.f13.Stewart;

import com.example.Stewart.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


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
	private double accx;
	private double accy;
	private TextView score;
	private Animation animation;
	private GestureDetector gest;
	
	private int onTouchX;
      private int onTouchY;
	private int onFinishX;
      private int onFinishY;
      private RelativeLayout.LayoutParams params;
      
      private boolean aniInit;
      private Button actionShadow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout scoreboard = (LinearLayout) findViewById(R.id.linear);
		scoreboard.bringToFront();
		score = (TextView) findViewById(R.id.score);
		
		aniInit = false;
		
		//restart();
		x = 400;
		y = 400;
		currentx=0;
		currenty=0;
		
		ball = (FrameLayout) findViewById(R.id.ball);
		startx = ball.getLeft();
		starty = ball.getTop();
		actionShadow = (Button) findViewById(R.id.a);
		
		actionShadow.setOnTouchListener(new OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				  if(event.getAction() == MotionEvent.ACTION_DOWN){
					  onTouchX =(int)  event.getRawX();
		                    onTouchY =(int)  event.getRawY();
				  }
			        if(event.getAction() == MotionEvent.ACTION_UP){
			      	  onFinishX =(int)  event.getRawX();
		                    onFinishY =(int)  event.getRawY();
		                    x = onFinishX - onTouchX;
		                    y = onFinishY - onTouchY;
		                    if(aniInit == false){	
		                    	launch();
		                    }
		                    else {
		              		launch();
		                    }
			        }
			        
			        return true;
			    }
			});
		
		Button restart = (Button) findViewById(R.id.restart);
		restart.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
			          restart();
			    }
			});
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
		Toast.makeText(getApplicationContext(), "Robert Stewart", 
				   Toast.LENGTH_LONG).show();
		return true;
	}
	
	public void restart() {
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		if(aniInit == false){
			int checkx = ball.getLeft();
			int checky = ball.getTop();
			x = 400;
			y = 400;
			
			parent.removeView(ball);
			parent.addView(ball);
			currentx = 0;
			currenty = 0;
			
			score.setText("0");
		}
		
		else{
			if(animation.hasEnded()){
			int checkx = ball.getLeft();
			int checky = ball.getTop();
			x = 400;
			y = 400;
			
			parent.removeView(ball);
			parent.addView(ball);
			currentx = 0;
			currenty = 0;
			
			score.setText("0");
			}
		}
	}
	
	public void launch() {
		aniInit = true;
		
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		accx = .8;
		accy = .8;
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
				
			}
			if(ball.getTop() + y <= 0) {
				changediry = true;
				tempy = (-1)*ball.getTop();
			}
			if(ball.getTop() + tempy >= maxh) {
				changediry = true;
				tempy = maxh - ball.getTop();
			}
			
			//Animation animation = new TranslateAnimation(
			animation = new TranslateAnimation(
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
					boolean hitx = false;
					boolean hity = false;
					
					if(!changedirx){
						currentx = currentx + x;
					}
					if(!changediry){
						currenty = currenty + y;
					}

					if(changedirx == true){
						x = -x;	
						currentx = tempx;
						hitx = true;
					}
					if(changediry == true){			
						y = y*(-1);
						currenty = tempy;
						hity = true;
					}
					
					int checkx = ball.getLeft();
					int checky = ball.getTop();
					
					ball.setTop(checky);
					ball.setLeft(checkx);
					x = (float) (accx*x);
					y = (float) (accy*y);
					
					//RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
					//parent.removeView(ball);
//			            params.leftMargin = ball.getLeft();
//			            params.topMargin = ball.getTop();
//			            params.rightMargin = ball.getRight();
//			            params.bottomMargin = ball.getBottom();
//			            ball.setLayoutParams(params);
//			            //parent.addView(ball);
//			            ball.setOnTouchListener(new OnTouchListener() {
//						    @Override
//						    public boolean onTouch(View v, MotionEvent event) {
//							  if(event.getAction() == MotionEvent.ACTION_DOWN){
//								  onTouchX =(int)  event.getRawX();
//					                    onTouchY =(int)  event.getRawY();
//							  }
//						        if(event.getAction() == MotionEvent.ACTION_UP){
//						      	  onFinishX =(int)  event.getRawX();
//					                    onFinishY =(int)  event.getRawY();
//					                    x = onFinishX - onTouchX;
//					                    y = onFinishY - onTouchY;
//					                    	launch();
//						        }
//						        return true;
//						    }
//						});
					
					if(hitx){
						//String newscore = score.getText().toString();
						int iscore = Integer.parseInt(score.getText().toString());
						iscore = iscore + 1;
						String newscore = Integer.toString(iscore);
						score.setText(newscore);
					}
					if(hity){
						//String newscore = score.getText().toString();
						int iscore = Integer.parseInt(score.getText().toString());
						iscore = iscore + 1;
						String newscore = Integer.toString(iscore);
						score.setText(newscore);
					}
					

			                
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
					params = (RelativeLayout.LayoutParams) ball.getLayoutParams();
                        }});
			ball.startAnimation(animation);
		
	}
	
}
