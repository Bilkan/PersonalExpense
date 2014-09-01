package net.bilkan.personalexpense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class IncomeActivity extends Activity {

	TextView tvSrc, tvMoney, tvTime;

	EditText etSrc, etMoney, etTime;
	Button btOk, iBack;

	String[] incomeCats;
	SimpleDateFormat format;
	Operator operator;

	Calendar calendar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);

		tvSrc = (TextView) findViewById(R.id.tvSrc);
		tvMoney = (TextView) findViewById(R.id.tvMoney);

		tvTime = (TextView) findViewById(R.id.tvTime);
		etSrc = (EditText) findViewById(R.id.etSrc);
		etMoney = (EditText) findViewById(R.id.etMoney);
		etTime = (EditText) findViewById(R.id.etTime);
		btOk = (Button) findViewById(R.id.btnOk);
		iBack = (Button) findViewById(R.id.iback);
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		operator = new Operator(this);
		updateTime();

		btOk.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				int check = checkText();
				if (check > 0) {
					if (check == 2) {
						operator.addCategory(etSrc.getText().toString(), 0);
					}

					long time;
					try {
						time = format.parse(etTime.getText().toString())
								.getTime();
						int catId = operator.getCatId(etSrc.getText()
								.toString());
						operator.addIncome(etSrc.getText().toString(), Double
								.parseDouble(etMoney.getText().toString()),
								time, catId);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					clean();
				}
			}

		});
		iBack.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stubv
				Intent intent = new Intent(IncomeActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();

			}

		});
		etSrc.setOnClickListener(new EditText.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				incomeCats = operator.getIncomeName();
				AlertDialog.Builder builder = new AlertDialog.Builder(
						IncomeActivity.this);
				builder.setTitle("كىرىم تۈرلىرى");
				builder.setItems(incomeCats,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// Do something with the selection

								etSrc.setText(incomeCats[item]);

							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}

		});

	}

	private void updateTime() {
		// TODO Auto-generated method stub
		calendar = Calendar.getInstance();
		String Time = format.format(calendar.getTime());
		etTime.setText(Time);
	}

	private void clean() {
		// TODO Auto-generated method stub
		etSrc.setText("");
		etSrc.requestFocus();
		etMoney.setText("");
		updateTime();
	}

	@SuppressLint("ShowToast")
	private int checkText() {
		// TODO Auto-generated method stub

		if (etSrc.getText().toString().equals("")
				|| etMoney.getText().toString().equals("")
				|| etTime.getText().toString().equals("")) {
			Toast.makeText(IncomeActivity.this, "تولۇق تولدۇرۇڭ!",
					Toast.LENGTH_LONG).show();
			return 0;
		} else if (operator.checkName(etSrc.getText().toString())) {
			return 1;
		}

		return 2;
	}
	public boolean onKeyDown(int keyCode, KeyEvent evetn){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent(IncomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
		
	}
}
