package com.example.hidayat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IncomeActivity extends Activity {
	
	TextView tvSrc, tvMoney,tvTime;
	
	EditText etSrc,etMoney,etTime;
	Button btOk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        
        tvSrc=(TextView)findViewById(R.id.tvSrc);
        tvMoney=(TextView)findViewById(R.id.tvMoney);
        
        tvTime=(TextView)findViewById(R.id.tvTime);
        etSrc=(EditText)findViewById(R.id.etSrc);
        etMoney=(EditText)findViewById(R.id.etMoney);
        etTime=(EditText)findViewById(R.id.etTime);
        btOk=(Button)findViewById(R.id.btnOk);
        btOk.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_income, menu);
        return true;
    }
}
