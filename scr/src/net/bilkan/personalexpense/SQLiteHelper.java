package net.bilkan.personalexpense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.inputmethodservice.Keyboard.Row;

/**
 * 閻庡湱鍋熼獮鍥╋拷绾懌锟介柣銊ュ閸ㄥ崬顕欓幁鎺炴嫹闁哄洤鐡ㄩ弻濠囧Υ娴ｇ缍侀柡鍥ㄦ綑閸亪宕ュ鍡樻儥濞达綇鎷�* @author
 * ytm0220@163.com
 * 
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	Calendar calendar;
	public static final String TB_outlay = "outlay";
	public static final String TB_income = "income";
	public static final String TB_category = "category";
	public static final String DB_NAME = "personalexpense";
	public static final int DB_VERSION = 1;

	public SQLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 闁告帗绋戠紓鎾诲棘閹峰被锟�
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_income + "("
				+ "id integer primary key autoincrement,"
				+ "CategoryName TEXT," + "Mony MONEY," + "Time DATATIME,"
				+ "CategoryId integer" + " )");

		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_outlay + "("
				+ "id integer primary key autoincrement,"
				+ "CategoryName TEXT," + "Mony MONEY," + "Comment text,"
				+ "Time DATATIME," + "CategoryId integer" + " )");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_category + "("
				+ "id integer primary key autoincrement,"
				+ "CategoryName TEXT," + "out integer" + " )");
	}

	/**
	 * 鐟滅増鎸婚ˉ鎴澝圭�鈺冪憿闁告挸绉崇粩鏉戔枎閳ュ啿鐏＄�銈囧劋閺嗙喖骞戦鑲╂皑闁绘鐗婂﹢鐗堢▔瀹ュ嫮顏遍柡宥夋敱濡炲倿鏁嶇仦钘夊弗闁告帞濞�▍
	 * 搴ｆ偘閵娿儱鏅欓柛鎺撶☉缂傛捇寮幏灞伙拷
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_outlay);
		db.execSQL("DROP TABLE IF EXISTS " + TB_income);
		db.execSQL("DROP TABLE IF EXISTS " + TB_category);
		onCreate(db);
	}

	/**
	 * 闁告瑦蓱濞插潡宕氬Δ锟藉�
	 * 
	 * @param db
	 * @param oldColumn
	 * @param newColumn
	 * @param typeColumn
	 */

	public void DeleteOutlay(int id) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		db.delete(TB_outlay, "id=" + id, null);

	}
	
	public void DeleteIncome(int id) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		db.delete(TB_income, "id=" + id, null);
	}

	public Cursor OutOutlayNew() {
		SQLiteDatabase db;
		db = getWritableDatabase();
		Cursor cursor = db.query(TB_outlay, null, null, null, null, null,
				"time desc", null);//"20");

		return cursor;
	}

	public Cursor OutOutlay() {
		SQLiteDatabase db;
		db = getWritableDatabase();
		Cursor cursor = db.query(TB_outlay, null, null, null, null, null,
				"time desc");

		return cursor;

	}

	public Cursor GetCategorybyid(int out) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		Cursor cursor = db.query(TB_category, null, "out=" + out, null, null,
				null, null);

		return cursor;

	}

	public boolean OutCategoryByName(String CategoryName) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		Cursor cursor = db.query(TB_category, null, "CategoryName=?",
				new String[] { CategoryName }, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public int getCategoryIdByName(String CategoryName) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		Cursor cursor = db.query(TB_category, null, "CategoryName=?",
				new String[] { CategoryName }, null, null, null);
		cursor.moveToFirst();
		int catId = cursor.getInt(0);
		cursor.close();

		return catId;

	}

	public Cursor GetOutlayByttimeId(int id, long time, long time1) {

		SQLiteDatabase db;
		// String sqljumla;
		// sqljumla="SELECT * FROM `outlay` WHERE `id`=id and `time`  BETWEEN time AND time2 ";
		// db.execSQL(sqljumla);
		db = getReadableDatabase();
		Cursor cursor = db.query(TB_outlay, null, "CategoryId=" + id, null,
				null, null, null);

		return cursor;

	}

	public void lastOutlayDate(long time, long current_time) {
		SQLiteDatabase db;
		db = getReadableDatabase();
		int thisYear = calendar.get(Calendar.YEAR);
		int thisMonth = calendar.get(Calendar.MONTH) + 1;
		Cursor cursor = db.query(TB_outlay, null, null, null, null, null,
				"time desc");
		cursor.moveToFirst();
		Long latestTime = cursor.getLong(4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		sdf.format(latestTime);
	}

	public Cursor GetIncomeByTime(long time, long time1) {

		SQLiteDatabase db;
		// String sqljumla;
		// sqljumla="SELECT * FROM `outlay` WHERE `id`=id and `time`  BETWEEN time AND time2 ";
		// db.execSQL(sqljumla);
		db = getReadableDatabase();

		String sql = "SELECT * FROM " + TB_income + " WHERE time > " + time
				+ " AND time < " + time1;
		Cursor cursor = db.rawQuery(sql, null);

		return cursor;

	}

	public Cursor GetOutlayByTime(int id, long time, long time1) {

		SQLiteDatabase db;
		// String sqljumla;
		// sqljumla="SELECT * FROM `outlay` WHERE `id`=id and `time`  BETWEEN time AND time2 ";
		// db.execSQL(sqljumla);
		db = getReadableDatabase();

		String sql = "SELECT * FROM " + TB_outlay + " WHERE CategoryId = " + id
				+ " AND time between  " + time + "  AND " + time1;
		Cursor cursor = db.rawQuery(sql, null);

		return cursor;

	}

	public void Insetoutlay(String CategoryName, double Mony, String Comment,
			long time, int CategoryId) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("CategoryName", CategoryName);
		values.put("Mony", Mony);
		values.put("Comment", Comment);
		values.put("Time", time);
		values.put("CategoryId", CategoryId);
		long rowid = db.insert(TB_outlay, null, values);

	}

	public void Insetincome(String CategoryName, double Mony, long time,
			int CategoryId) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		ContentValues values = new ContentValues();
		values.put("CategoryName", CategoryName);
		values.put("Mony", Mony);
		values.put("time", time);
		values.put("CategoryId", CategoryId);
		long rowid = db.insert(TB_income, null, values);

	}

	public void Insetcategory(String CategoryName, int CategoryId) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("CategoryName", CategoryName);
		values.put("out", CategoryId);
		long rowid = db.insert(TB_category, null, values);

	}

	public int ChechCat(String str) {
		SQLiteDatabase db;

		db = getWritableDatabase();
		Cursor cursor = db.query(TB_outlay, new String[] { "CategoryName" },
				"CategoryName=?", new String[] { str }, null, null, null);
		int i = cursor.getCount();
		close();
		return i;

	}

	public void DeleteOutlayCat(String str) {
		SQLiteDatabase db;
		db = getWritableDatabase();
		db.delete(TB_category, "CategoryName=?", new String[] { str });
		close();
	}
}