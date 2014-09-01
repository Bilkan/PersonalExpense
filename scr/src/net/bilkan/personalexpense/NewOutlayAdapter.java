package net.bilkan.personalexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewOutlayAdapter extends BaseAdapter {

	Context ctx;
	ArrayList<Outlay> outlays;
	LayoutInflater inflater;

	public NewOutlayAdapter(Context context, ArrayList<Outlay> array) {
		ctx = context;
		outlays = array;
		inflater = LayoutInflater.from(context);
	}

	public NewOutlayAdapter(OnClickListener onClickListener,
			ArrayList<Outlay> newOutlays) {
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return outlays.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return outlays.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.itemlistrow, null);
		}
		
		TextView cat = (TextView)v.findViewById(R.id.tvCat);
		TextView money = (TextView)v.findViewById(R.id.tvMoney);
		TextView time = (TextView)v.findViewById(R.id.tvTime);
		int itemId=(outlays.get(position).ID);
		cat.setText(outlays.get(position).Name);
		cat.setPadding(0, 10, 0, 0);
		money.setText("" + outlays.get(position).Money);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM");
		time.setText(format.format(new Date(outlays.get(position).Time)));
		money.setPadding(0, 0, 0, 10);
		return v;
	}

}
