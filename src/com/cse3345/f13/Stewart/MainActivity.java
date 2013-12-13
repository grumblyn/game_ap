package com.cse3345.f13.Stewart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Stewart.R;


public class MainActivity extends Activity {
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
		setContentView(R.layout.activity_main);
		Button start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
				    Intent myIntent = new Intent(MainActivity.this, newActivity.class);
				    MainActivity.this.startActivity(myIntent);
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
	
}
