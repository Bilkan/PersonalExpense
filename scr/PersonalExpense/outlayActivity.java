package com.example.hidayat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class outlayActivity extends Activity {
	TextView tvSrc, tvMoney,tvTime,tvizahat;
	EditText etSrc,etMoney,etTime,etizahat;
	Button btOk;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outlay);
        tvSrc=(TextView)findViewById(R.id.turi);
        tvMoney=(TextView)findViewById(R.id.sommisi);
        tvTime=(TextView)findViewById(R.id.wakti);
        tvizahat=(TextView)findViewById(R.id.izahat);
        etSrc=(EditText)findViewById(R.id.editText1);
        etTime=(EditText)findViewById(R.id.editText4);
        etMoney=(EditText)findViewById(R.id.editText2);
        etizahat=(EditText)findViewById(R.id.editText3);
        btOk=(Button)findViewById(R.id.btnOk);
        btOk.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stubv
				
			}
        	
        });
    }   	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_incom, menu);
        return true;
    }
}
