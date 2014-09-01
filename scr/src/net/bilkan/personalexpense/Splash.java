package net.bilkan.personalexpense;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{

Handler handler;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.splash);
		handler=new Handler();
		
		handler.postDelayed(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				intent.setClass(Splash.this, MainActivity.class);
				Splash.this.startActivity(intent);
				Splash.this.finish();
			}
		
		}, 1000);
			
		
		
		
	}
	
	
}
