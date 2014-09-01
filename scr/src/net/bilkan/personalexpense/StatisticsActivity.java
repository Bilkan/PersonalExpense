package net.bilkan.personalexpense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

public class StatisticsActivity extends Activity {
	int DATE_DIALOG_ID = 1;
	TextView tvOut, tvOutMoney, tvIn, tvInMoney, timeStart, timeEnd;
	ExpandableListView eList;
	Button customT, customTB;
	SimpleDateFormat format;
	Operator operator;
	DatePicker setDateEnd, setDateStart;
	Calendar calendar;
	public boolean outlayShow = true;
	private int year;
	private int month;
	private int day;
	SQLiteHelper sqlHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics);

		init();

		tvOut.setOnClickListener(new TextView.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				income();
				outlay();

				outlayShow = true;
			}

		});

		tvIn.setOnClickListener(new TextView.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				outlayShow = false;
				outlay();
				income();
			}

		});

		timeEnd.setOnClickListener(new TextView.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);

			}

		});
		timeStart.setOnClickListener(new TextView.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(2);

			}

		});
		sqlHelper = new SQLiteHelper(this);
		
			
		
		eList.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@SuppressWarnings("static-access")
			public boolean onItemLongClick(final AdapterView<?> adapter,
					View arg1, final int p, long arg3) {
				
				// TODO Auto-generated method stub
				if (!outlayShow) {

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							StatisticsActivity.this);

					alertDialogBuilder.setTitle("ئۆچۈرەمسىز!");
					alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("ھەئە!",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											// boolean deleted =
											
											Object object = adapter
													.getItemAtPosition(p);
											Income income = (Income) object;
											int i = income.ID;
											sqlHelper.DeleteIncome(i);
											income();
											String checkName = income.Name;
											int columnNumber = sqlHelper
													.ChechCat(checkName);
											if (columnNumber == 0) {
												sqlHelper
														.DeleteOutlayCat(checkName);
											}

											// onCreate(savedInstanceState);
										}
									})
							.setNegativeButton("ياق!",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											return;
										}
									});

					alertDialogBuilder.show();
				}
				else if(outlayShow){
					if( eList.getPackedPositionType(arg3)==ExpandableListView.PACKED_POSITION_TYPE_CHILD){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							StatisticsActivity.this);

					alertDialogBuilder.setTitle("ئۆچۈرەمسىز!");
					alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("ھەئە!",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											// boolean deleted =
											if(eList.isGroupExpanded(p)){
												Log.d("Log p:",""+p);
											}
											Object object = adapter
													.getItemAtPosition(p);
											Outlay outlay = (Outlay) object;
											
											int i = outlay.ID;
											sqlHelper.DeleteOutlay(i);
											outlay();
											String checkName = outlay.Name;
											int columnNumber = sqlHelper
													.ChechCat(checkName);
											if (columnNumber == 0) {
												sqlHelper
														.DeleteOutlayCat(checkName);
											}

											// onCreate(savedInstanceState);
										}
									})
							.setNegativeButton("ياق!",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											return;
										}
									});

					alertDialogBuilder.show();
				
				}
			}
				return true;
			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		case 2:
			return new DatePickerDialog(this, datePickerListenerbt, year,
					month, day);

		}

		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			timeEnd.setText(new StringBuilder().append(year).append("-")
					.append(month + 1).append("-").append(day).append(" "));

			// set selected date into datepicker also
			// setDateEnd.init(year, month, day, null);
			outlay();
			income();
			if (outlayShow) {
				outlay();
			} else {
				income();
			}
		}
	};
	private DatePickerDialog.OnDateSetListener datePickerListenerbt = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			timeStart.setText(new StringBuilder().append(year).append("-")
					.append(month + 1).append("-").append(day).append(" "));

			outlay();
			income();
			if (outlayShow) {
				outlay();
			} else {
				income();
			}
		}
	};

	private void init() {
		// TODO Auto-generated method stub
		timeStart = (TextView) findViewById(R.id.timeStart);
		timeEnd = (TextView) findViewById(R.id.timeEnd);
		tvOut = (TextView) findViewById(R.id.tvOut);
		tvOutMoney = (TextView) findViewById(R.id.tvOutMoney);
		tvIn = (TextView) findViewById(R.id.tvIn);
		tvInMoney = (TextView) findViewById(R.id.tvInMoney);

		eList = (ExpandableListView) findViewById(R.id.eList);
		Display display = getWindowManager().getDefaultDisplay();

	//	int width = display.getWidth();

		eList.setIndicatorBounds(0, 0);
		format = new SimpleDateFormat("yyyy-MM-dd");
		operator = new Operator(this);

		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		// String Time = format.format(calendar.getTime());

		setThisMonth();
		income();
		outlay();

	}

	private void outlay() {
		// TODO Auto-generated method stub
		try {
			ArrayList<TotalOutlay> group = operator.getOutlayStat(
					format.parse(timeStart.getText().toString()).getTime(),
					format.parse(timeEnd.getText().toString()).getTime());
			ArrayList<List<Outlay>> child = operator.getChildList();

			CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(
					StatisticsActivity.this, group, child);
			eList.setAdapter(adapter);
			eList.setCacheColorHint(0);
			tvOutMoney.setText("" + operator.getOutMoney());
			tvInMoney.setText("" + operator.getInMoney());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void income() {
		// TODO Auto-generated method stub

		try {
			ArrayList<Income> incomes = operator.getIncomeStat(
					format.parse(timeStart.getText().toString()).getTime(),
					format.parse(timeEnd.getText().toString()).getTime());

			CustomIncomeAdapter adapter = new CustomIncomeAdapter(
					StatisticsActivity.this, incomes, null);
			eList.setAdapter(adapter);
			eList.setCacheColorHint(0);
			tvOutMoney.setText("" + operator.getOutMoney());
			tvInMoney.setText("" + operator.getInMoney());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setThisMonth() {
		int thisYear = calendar.get(Calendar.YEAR);
		int thisMonth = calendar.get(Calendar.MONTH) + 1;
		int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
		String start = "" + thisYear + "-" + thisMonth + "-01";
		String end = "" + thisYear + "-" + thisMonth + "-" + thisDay;
		timeStart.setText(start);
		timeEnd.setText(end);

	}

	public boolean onKeyDown(int keyCode, KeyEvent evetn) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(StatisticsActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;

	}
}
