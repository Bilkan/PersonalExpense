package com.example.hidayat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class mainActivity extends Activity {
	
	
	ListView   outlay;
	Button    btoutley, btkir,btstk;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        outlay=(ListView)findViewById(R.id.outlay);
        btoutley=(Button)findViewById(R.id.btoutlwy);
        btkir=(Button)findViewById(R.id.btkir);
        btstk=(Button)findViewById(R.id.btstk);
        
     btoutley.setOnClickListener(new Button.OnClickListener(){

		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	 
     });
         
     btkir.setOnClickListener(new Button.OnClickListener(){

 		public void onClick(View v) {
 			// TODO Auto-generated method stub
 			
 		}
     	 
      });
     
     btstk.setOnClickListener(new Button.OnClickListener(){

 		public void onClick(View v) {
 			// TODO Auto-generated method stub
 			
 		}
     	 
      });
         
      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inco, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
