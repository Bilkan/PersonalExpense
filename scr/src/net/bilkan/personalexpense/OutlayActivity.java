package net.bilkan.personalexpense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OutlayActivity extends Activity {
	TextView tvSrc, tvMoney, tvTime, tvizahat;
	EditText etSrc, etMoney, etTime, etizahat;
	Button btOk, oBack;

	String[] outlayCats;
	SimpleDateFormat format;
	Operator operator;

	Calendar calendar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outlay);
		init();
		

		btOk.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stubv

				int check = checkText();
				if (check > 0) {
					if (check == 2) {
						operator.addCategory(etSrc.getText().toString(), 1);
					}

					long time;
					try {
						time = format.parse(etTime.getText().toString()).getTime();
						int catId = operator.getCatId(etSrc.getText()
								.toString());
						operator.addOutlay(etSrc.getText().toString(), Double
								.parseDouble(etMoney.getText().toString()),
								time, etizahat.getText().toString(), catId);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					clean();
				}

			}

		});
		oBack.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stubv
				Intent intent=new Intent(OutlayActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				
			}

		});

		etSrc.setOnClickListener(new EditText.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				outlayCats = operator.getOutlayName();
				AlertDialog.Builder builder = new AlertDialog.Builder(
						OutlayActivity.this);
				builder.setTitle("چىقىم تۈرلىرى");
				builder.setItems(outlayCats,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// Do something with the selection

								etSrc.setText(outlayCats[item]);

							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}

		});
	}


	private void clean() {
		// TODO Auto-generated method stub
		etSrc.setText("");
		etSrc.requestFocus();
		etMoney.setText("");
		etizahat.setText("");
		updateTime();
	}

	
	private void updateTime() {
		// TODO Auto-generated method stub
		calendar = Calendar.getInstance();
		String Time = format.format(calendar.getTime());
		etTime.setText(Time);
	}


	private void init() {
		// TODO Auto-generated method stub
		tvSrc = (TextView) findViewById(R.id.turi);
		tvMoney = (TextView) findViewById(R.id.sommisi);
		tvTime = (TextView) findViewById(R.id.wakti);
		tvizahat = (TextView) findViewById(R.id.izahat);
		etSrc = (EditText) findViewById(R.id.outlaycat);
		etMoney = (EditText) findViewById(R.id.outlayexp);
		etTime = (EditText) findViewById(R.id.outlaytime);
		etizahat = (EditText) findViewById(R.id.outlaycom);
		btOk = (Button) findViewById(R.id.outlayconfirm);
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		oBack =(Button)findViewById(R.id.iback);
		operator = new Operator(this);
		updateTime();
		
	}

	@SuppressLint("ShowToast")
	private int checkText() {
		// TODO Auto-generated method stub

		if (etSrc.getText().toString().equals("")
				|| etMoney.getText().toString().equals("")
				|| etTime.getText().toString().equals("")
				|| etizahat.getText().toString().equals("")) {
			Toast.makeText(OutlayActivity.this, "تولۇق تولدۇرۇڭ!",
					Toast.LENGTH_LONG).show();
			return 0;
		} else if (operator.checkName(etSrc.getText().toString())) {
			return 1;
		}

		return 2;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent evetn){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent(OutlayActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
		
	}

}
