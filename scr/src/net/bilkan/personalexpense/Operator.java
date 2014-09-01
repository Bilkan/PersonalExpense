package net.bilkan.personalexpense;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.widget.ExpandableListView;

public class Operator {
	// declare child view of ExpandableListView
	ArrayList<List<Outlay>> child = new ArrayList<List<Outlay>>();
	double totalIncome;
	double totalOutlay;
	SQLiteHelper dbHelper;
	Context ctx;
 
	public Operator(Context context) {
		ctx = context;
		dbHelper = new SQLiteHelper(context);
	}

	// method£º get outlay details
	public ArrayList<Outlay> getOutlays() {
		// declare variable
		ArrayList<Outlay> mArrayList = new ArrayList<Outlay>();
		Cursor myCursor = dbHelper.OutOutlayNew();
		// cursor is moving the first item and cycling
		myCursor.moveToFirst();
		for (int i = 0; i < myCursor.getCount(); i++) {
			// create an object
			Outlay out = new Outlay();
			// get data from database and initialize each members of object
			out.Name = myCursor.getString(1);
			out.Money = Double.parseDouble(myCursor.getString(2));
		//	String time = myCursor.getString(3);
			out.Time = myCursor.getLong(4);
			out.ID=myCursor.getInt(0);
			myCursor.moveToNext();
			// add my outlay object to ArrayList
			mArrayList.add(out);
		}
		// return ArrayList
		return mArrayList;

	}

	// method£º get outlay items name without repeated;
	public String[] getOutlayName() {

		Cursor myCursor = dbHelper.GetCategorybyid(1);
		String[] outlayArray = new String[myCursor.getCount()];
		myCursor.moveToFirst();
		for (int i = 0; i < myCursor.getCount(); i++) {
			// id
			outlayArray[i] = myCursor.getString(1);
			myCursor.moveToNext();

		}

		return outlayArray;

	}
	
	public String[] getIncomeName() {

		Cursor myCursor = dbHelper.GetCategorybyid(0);
		String[] outlayArray = new String[myCursor.getCount()];
		myCursor.moveToFirst();
		for (int i = 0; i < myCursor.getCount(); i++) {
			// id
			outlayArray[i] = myCursor.getString(1);
			myCursor.moveToNext();

		}

		return outlayArray;

	}

	/*
	 * method: get difference in outlay and income
	 */
	public double getDif() {

		return 0;

	}

	// method£º get detailed information about total outlay
	public ArrayList<TotalOutlay> getOutlayStat(long timeStart, long timeEnd) {
		Cursor mypCursor, mycCursor;
		ArrayList<TotalOutlay> group = new ArrayList<TotalOutlay>();
	
		child.clear();
		totalOutlay = 0.0;
		mypCursor = dbHelper.GetCategorybyid(1);
		mypCursor.moveToFirst();
		for (int i = 0; i < mypCursor.getCount(); i++) {
			TotalOutlay tOutlay = new TotalOutlay();
			tOutlay.Name = mypCursor.getString(1);
			tOutlay.id = mypCursor.getInt(0);
			ArrayList<Outlay> outlays = new ArrayList<Outlay>();
			double totalmoney = 0.0;
			int catId = mypCursor.getInt(0);
			mycCursor = dbHelper.GetOutlayByTime(catId, timeStart, timeEnd+24*60*60*1000);
			mycCursor.moveToFirst();
			for (int j = 0; j < mycCursor.getCount(); j++) {

				Outlay out = new Outlay();
				out.Name = mycCursor.getString(1);
				out.Money = mycCursor.getDouble(2);
				out.Time = mycCursor.getLong(4);
				out.Comment = mycCursor.getString(3);
				out.ID=mycCursor.getInt(0);
				outlays.add(out);
				totalmoney += mycCursor.getDouble(2);
				mycCursor.moveToNext();
			}
			tOutlay.tMoney = totalmoney;
			child.add(outlays);
			group.add(tOutlay);
			mypCursor.moveToNext();
			totalOutlay += totalmoney;
		}
		for (int k = 0; k < group.size(); k++) {
		
			group.get(k).percentage = roundTwoDecimals(group.get(k).tMoney*100 / totalOutlay);
		}

		return group;

	}
	double roundTwoDecimals(double d)
	{
	    DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(d));
	}
	public ArrayList<List<Outlay>> getChildList() {
		return child;
	}

	// method£º get detailed information about total income
	public ArrayList<Income> getIncomeStat(long timeStart, long timeEnd) {

		// declare variable
		ArrayList<Income> mArrayList = new ArrayList<Income>();
		Cursor myICursor;
		myICursor= dbHelper.GetIncomeByTime(timeStart, timeEnd+24*60*60*1000);
		// cursor is moving the first item and cycling
		myICursor.moveToFirst();
		totalIncome = 0.0;
		for (int j = 0; j < myICursor.getCount(); j++) {
			double totalmoney;
			//List<Income> incomes = null;
			// create an object
			Income income = new Income();
			// get data from database and initialize each members of object
			income.Name = myICursor.getString(1);
			totalmoney = Double.parseDouble(myICursor.getString(2));
			income.Money=totalmoney;
			income.Time = myICursor.getLong(3);
			income.ID=myICursor.getInt(0);
			myICursor.moveToNext();
			totalIncome += totalmoney;

			// add my outlay object to ArrayList
			mArrayList.add(income);

		}

		return mArrayList;

	}

	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		boolean exist = dbHelper.OutCategoryByName(name);
		return exist;
	}

	public void addCategory(String name, int out) {
		// TODO Auto-generated method stub
		dbHelper.Insetcategory(name, out);
	}

	public int getCatId(String name) {
		// TODO Auto-generated method stub
		return dbHelper.getCategoryIdByName(name);
	}

	public void addOutlay(String catName, double money, long time,
			String comment, int catId) {
		// TODO Auto-generated method stub
		dbHelper.Insetoutlay(catName, money, comment, time, catId);
	}

	public void addIncome(String CategoryName, double Money, long time, int CategoryId) {
		// TODO Auto-generated method stub
		dbHelper.Insetincome(CategoryName, Money, time, CategoryId);
	}

	public double getOutMoney() {
		// TODO Auto-generated method stub
		return totalOutlay;
	}

	public double getInMoney() {
		// TODO Auto-generated method stub
		return totalIncome;
	}
}
