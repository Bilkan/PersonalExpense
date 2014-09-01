package net.bilkan.personalexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
	private PackageManager m_pkgMgr;
	private Context m_context;
	private ArrayList<TotalOutlay> m_groups;
	private ArrayList<List<Outlay>> m_children;

	public CustomExpandableListAdapter(Context context,
			ArrayList<TotalOutlay> groups, ArrayList<List<Outlay>> children) {
		m_context = context;
		m_pkgMgr = m_context.getPackageManager();
		m_groups = groups;
		m_children = children;
	}

	public Object getChild(int groupPos, int childPos) {
		return m_children.get(groupPos).get(childPos);
	}

	public long getChildId(int groupPos, int childPos) {
		return childPos;
	}

	public int getChildrenCount(int groupPos) {
		return m_children.get(groupPos).size();
	}

	@SuppressLint("ResourceAsColor")
	public View getChildView(int groupPos, int childPos, boolean isLastChild,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(m_context);
			convertView = inflater.inflate(R.layout.itemlistrow, null);
			Drawable dr=m_context.getResources().getDrawable(R.drawable.background);
			
			convertView.setBackgroundResource(R.drawable.child_back);
		}

		TextView txtName = (TextView) convertView.findViewById(R.id.tvCat);
		TextView txtMoney = (TextView) convertView.findViewById(R.id.tvMoney);
		TextView txtTime = (TextView) convertView.findViewById(R.id.tvTime);
		txtName.setText(m_children.get(groupPos).get(childPos).Comment);
		txtMoney.setText("" + m_children.get(groupPos).get(childPos).Money);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
		txtTime.setText(sdf.format(new Date(m_children.get(groupPos).get(childPos).Time)));
		txtMoney.setPadding(0, 5, 0, 10);
		
		
		return convertView;
	}

	public Object getGroup(int groupPos) {
		return m_groups.get(groupPos);
	}

	public int getGroupCount() {
		return m_groups.size();
	}

	public long getGroupId(int groupPos) {
		return groupPos;
	}

	public View getGroupView(int groupPos, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(m_context);
			convertView = inflater.inflate(R.layout.itemlistrow, null);

		}

		TextView txtName = (TextView) convertView.findViewById(R.id.tvCat);
		TextView txtMoney = (TextView) convertView.findViewById(R.id.tvMoney);
		TextView txtPercentage = (TextView) convertView
				.findViewById(R.id.tvTime);

		txtName.setText(m_groups.get(groupPos).Name);
		txtName.setTextSize(25);
		txtMoney.setText("" + m_groups.get(groupPos).tMoney);
		if (m_groups.get(groupPos).percentage >= 0) {
			txtPercentage.setText("" + m_groups.get(groupPos).percentage+"%");

		} else {
			txtPercentage.setText("" + 0+"%");
		}
		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPos, int childPos) {
		return true;
	}
}