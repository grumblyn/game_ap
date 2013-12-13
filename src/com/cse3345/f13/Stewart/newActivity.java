package com.cse3345.f13.Stewart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Stewart.R;


public class newActivity extends Activity {
	private boolean changedirx;
	private boolean changediry;
	
	private int origx;
	private int origy;
	
	private float currentx;
	private float currenty;
	private float tempx;
	private float tempy;
	private float x;
	private float y;
	private FrameLayout ball;
	private double accx;
	private double accy;
	private TextView score;
	private Animation animation;
	
	private int onTouchX;
      private int onTouchY;
	private int onFinishX;
      private int onFinishY;
      
      private boolean aniInit;
      private Button actionShadow;
      private long timeStart;
      private long timeStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		LinearLayout scoreboard = (LinearLayout) findViewById(R.id.linear);
		scoreboard.bringToFront();
		score = (TextView) findViewById(R.id.score);
		
		aniInit = false;
		
		x = 400;
		y = 400;
		currentx=0;
		currenty=0;
		
		ball = (FrameLayout) findViewById(R.id.ball);
		//Button start = (Button) findViewById(R.id.start);
//		ball.setOnClickListener(new View.OnClickListener() {
//			    @Override
//			    public void onClick(View v) {
//			          RelativeLayout front = (RelativeLayout) findViewById(R.id.front);
//			          front.setVisibility(View.GONE);
//			          LinearLayout back = (LinearLayout) findViewById(R.id.back);
//			          back.setVisibility(View.VISIBLE);
////			          actionShadow = (Button) findViewById(R.id.a);
////			          actionShadow.setVisibility(View.VISIBLE);
//			    }
//			});
		
		actionShadow = (Button) findViewById(R.id.a);
		
		actionShadow.setOnTouchListener(new OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				  if(event.getAction() == MotionEvent.ACTION_DOWN){
					  onTouchX =(int)  event.getRawX();
		                    onTouchY =(int)  event.getRawY();
		                    timeStart = System.currentTimeMillis();
//		                    Toast.makeText(getApplicationContext(), Integer.toString(ball.getLeft()), 
//     						   Toast.LENGTH_LONG).show();
				  }
			        if(event.getAction() == MotionEvent.ACTION_UP){
			      	  onFinishX =(int)  event.getRawX();
		                    onFinishY =(int)  event.getRawY();
		                    x = onFinishX - onTouchX;
		                    y = onFinishY - onTouchY;
		                    timeStop = System.currentTimeMillis(); 
		                    long diff = timeStop - timeStart;
		                    diff = 2500/diff;
		                    x = x*diff;
		                    y = y*diff;
		                    launch();
		                    RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		                    parent.removeView(ball);
		  			  parent.addView(ball);
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
			    //stop();
		    }
		});
	}

      @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
      	MenuInflater inflater = getMenuInflater();
      	inflater.inflate(R.menu.main, menu);
      	return super.onCreateOptionsMenu(menu);
	}
      
      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          // Handle presses on the action bar items
          switch (item.getItemId()) {
              case R.id.action_settings:
            	  Toast.makeText(getApplicationContext(), "Developer: Robert Stewart", 
            						   Toast.LENGTH_LONG).show();
                  return true;
              default:
                  return super.onOptionsItemSelected(item);
          }
      }
	
      public void stop() {
      	x = 0;
      	y = 0;
      }
      
	public void restart() {
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
		if(aniInit == false){
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
			if(ball.getLeft() + currentx + x <= 0) {
				changedirx = true;
				//tempx = (-1)*currentx + ball.getLeft();
				tempx = (-1)*ball.getLeft();
			}
			if(tempx + ball.getLeft() >= maxw) {
				changedirx = true;
				tempx = maxw - ball.getLeft();
				
			}
			if(ball.getTop() + currenty + y <= 0) {
				changediry = true;
				tempy = (-1)*ball.getTop();
			}
			if(ball.getTop() + tempy >= maxh) {
				changediry = true;
				tempy = maxh - ball.getTop();
			}
			
			if(changedirx){
				time = (int) (time*Math.abs((tempx-currentx)/x));
			}
			
			if(changediry){
				time = (int) (time*Math.abs((tempy-currenty)/y));
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
                        }});
			ball.startAnimation(animation);
		
	}
	
}
