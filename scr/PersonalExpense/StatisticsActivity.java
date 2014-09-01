package com.example.hidayat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class StatisticsActivity extends Activity {

	DatePicker dpStart,dpEnd;
	TextView tvOut,tvOutMoney,tvIn,tvInMoney;
	ExpandableListView eList;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        
        dpStart=(DatePicker)findViewById(R.id.dpStart);
        dpEnd=(DatePicker)findViewById(R.id.dpEnd);
        tvOut=(TextView)findViewById(R.id.tvOut);
        tvOutMoney=(TextView)findViewById(R.id.tvOutMoney);
        tvIn=(TextView)findViewById(R.id.tvIn);
        tvInMoney=(TextView)findViewById(R.id.tvInMoney);
        
        eList=(ExpandableListView)findViewById(R.id.eList);
        
        
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics, menu);
        return true;
    }
}
