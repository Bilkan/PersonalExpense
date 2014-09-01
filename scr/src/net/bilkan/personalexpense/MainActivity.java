package net.bilkan.personalexpense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	SQLiteHelper sqlHelper;
	ListView outlay;
	Button btoutley, btkir, btstk;
	Operator operator;
	TextView tvincome, tvoutlay, tvrest,timeStart, timeEnd;
	ArrayList<Outlay> newOutlays;
	Calendar calendar;
	SimpleDateFormat format;
	
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sqlHelper = new SQLiteHelper(MainActivity.this);
		setContentView(R.layout.main);
		operator = new Operator(this);
		calendar = Calendar.getInstance();
		//outlay = (ListView) findViewById(R.id.outlay);
		btoutley = (Button) findViewById(R.id.btoutlwy);
		btkir = (Button) findViewById(R.id.buttonL);
		btstk = (Button) findViewById(R.id.btstk);
		tvincome = (TextView) findViewById(R.id.tvincome_thismonth_money);
		tvoutlay = (TextView) findViewById(R.id.tvoutcome_thismonth_money);
		tvrest = (TextView) findViewById(R.id.tvrest_money);
		operator = new Operator(this);
		setThisMonth();
		format = new SimpleDateFormat("yyyy-MM-dd");
		newOutlays = operator.getOutlays();
		try {
			operator.getIncomeStat(format.parse(timeStart.getText().toString()).getTime(),
					format.parse(timeEnd.getText().toString()).getTime());
			operator.getOutlayStat(format.parse(timeStart.getText().toString()).getTime(),
					format.parse(timeEnd.getText().toString()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tvoutlay.setText("" + operator.getOutMoney());
		tvincome.setText("" + operator.getInMoney());
		double incomeMT, outlayMT;
		incomeMT=operator.getInMoney();
		outlayMT=operator.getOutMoney();
		double restofMoney=incomeMT-outlayMT;
		if(restofMoney>=0&outlayMT<=incomeMT*0.8){
			tvrest.setTextColor(Color.GREEN);
		}
		else if(incomeMT>=outlayMT&outlayMT>=incomeMT*0.8){
			tvrest.setTextColor(Color.YELLOW);
		}
		else{
			tvrest.setTextColor(Color.RED);
		}
		tvrest.setText(""+restofMoney);
	//	NewOutlayAdapter adapter = new NewOutlayAdapter(this, newOutlays);
		//outlay.setAdapter(adapter);
		//outlay.setCacheColorHint(0);
//		outlay.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			public boolean onItemLongClick(final AdapterView<?> adapter,
//					View arg1, final int p, long arg3) {
//				// TODO Auto-generated method stub
//				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//						MainActivity.this);
//
//				alertDialogBuilder.setTitle("ئۆچۈرەمسىز!");
//				alertDialogBuilder
//						.setCancelable(false)
//						.setPositiveButton("ھەئە!",
//								new DialogInterface.OnClickListener() {
//
//									public void onClick(DialogInterface dialog,
//											int which) {
//										// TODO Auto-generated method stub
//										// boolean deleted =
//										Object object = adapter
//												.getItemAtPosition(p);
//										Outlay outlay = (Outlay) object;
//										int i = outlay.ID;
//										sqlHelper.DeleteOutlay(i);
//										String checkName = outlay.Name;
//										int columnNumber = sqlHelper
//												.ChechCat(checkName);
//										if (columnNumber == 0) {
//											sqlHelper
//													.DeleteOutlayCat(checkName);
//										}
//
//										onCreate(savedInstanceState);
//									}
//								})
//						.setNegativeButton("ياق!",
//								new DialogInterface.OnClickListener() {
//
//									public void onClick(DialogInterface dialog,
//											int which) {
//										// TODO Auto-generated method stub
//										return;
//									}
//								});
//				alertDialogBuilder.show();
//
//				return true;
//			}
//
//		});

		// outlay.setOnItemClickListener(new AdapterView.OnItemClickListener(){
		//
		// public void onItemClick(final AdapterView<?> adapter, View arg1, int
		// arg2,
		// long arg3) {
		// // TODO Auto-generated method stub
		//
		// AlertDialog.Builder alertDialog = new
		// AlertDialog.Builder(MainActivity.this);
		// alertDialog.setTitle("Delete...");
		// alertDialog.setMessage("Are you sure you want to delete this note?");
		// alertDialog.setCancelable(false);
		//
		// final int p = arg2;
		//
		// alertDialog.setPositiveButton("Yes", new
		// DialogInterface.OnClickListener() {
		//
		// public void onClick(DialogInterface dialog, int which) {
		// // for(int i=0;i<14;i++){
		// Object object = adapter.getItemAtPosition(p);
		// Outlay outlay=(Outlay) object;
		// int i=outlay.ID;
		//
		//
		// sqlHelper.DeleteOutlay(i);
		// // }
		// //int i=outlay
		// //outlay.
		// }
		// });
		//
		// alertDialog.setNegativeButton("No", null);
		// alertDialog.show();
		//
		// }
		//
		// });
		//

		btoutley.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,
						OutlayActivity.class);
				startActivity(intent);
				finish();
			}

		});

		btkir.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,
						IncomeActivity.class);
				startActivity(intent);
				finish();
			}

		});

		btstk.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,
						StatisticsActivity.class);
				startActivity(intent);
				finish();
			}

		});
//
	}

	
	public void setThisMonth() {
		
		int thisYear = calendar.get(Calendar.YEAR);
		int thisMonth = calendar.get(Calendar.MONTH) + 1;
		int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
		String start = "" + thisYear + "-" + thisMonth + "-01";
		String end = "" + thisYear + "-" + thisMonth + "-" + thisDay;
		timeStart=new TextView(this);
		timeEnd=new TextView(this);
		timeStart.setText(start);
		timeEnd.setText(end);

	}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  
	  menu.add(0, 0, 0, "ھەققىدە");
	  return super.onCreateOptionsMenu(menu);
	 }
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle item selection
	     switch (item.getItemId()) {
	     case 0:
	        Intent intent=new Intent(MainActivity.this, About.class);
	        startActivity(intent);
	         return true;
	    
	     default:
	         return super.onOptionsItemSelected(item);
	     }
	 }
}
