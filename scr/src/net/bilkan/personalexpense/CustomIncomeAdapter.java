package net.bilkan.personalexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CustomIncomeAdapter extends BaseExpandableListAdapter {
	private PackageManager m_pkgMgr;
	private Context m_context;
	private ArrayList<Income> m_groups;
	private ArrayList<List<Income>> m_children;

	public CustomIncomeAdapter(Context context, ArrayList<Income> groups,
			ArrayList<List<Income>> children) {
		m_context = context;
		m_pkgMgr = m_context.getPackageManager();
		m_groups = groups;
		m_children = children;
	}

	public Object getChild(int groupPos, int childPos) {
		return 0;// m_children.get(groupPos).get(childPos);
	}

	public long getChildId(int groupPos, int childPos) {
		return 0;//childPos;
	}

	public int getChildrenCount(int groupPos) {
		return 0;//m_children.get(groupPos).size();
	}

	public View getChildView(int groupPos, int childPos, boolean isLastChild,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(m_context);
			convertView = inflater.inflate(R.layout.itemlistrow,
					null);
		}
		
		TextView txtName = (TextView) convertView
				.findViewById(R.id.tvCat);
		TextView txtMoney = (TextView) convertView
				.findViewById(R.id.tvMoney);
		TextView txtTime = (TextView) convertView
				.findViewById(R.id.tvTime);
			//txtName.setText(m_children.get(groupPos).get(childPos).Name);
		//	txtMoney.setText("" + m_children.get(groupPos).get(childPos).Money);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
			//txtTime.setText(sdf.format(m_children.get(groupPos).get(childPos).Time));
			
			
		convertView.setFocusableInTouchMode(true);
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
			convertView = inflater.inflate(R.layout.itemlistrow,
					null);
		}
		
		TextView txtName = (TextView) convertView
				.findViewById(R.id.tvCat);
		TextView txtMoney = (TextView) convertView
				.findViewById(R.id.tvMoney);
		TextView txtPercentage = (TextView) convertView
				.findViewById(R.id.tvTime);
		int itemId=m_groups.get(groupPos).ID;
		txtName.setText(m_groups.get(groupPos).Name);
		txtMoney.setText("" + m_groups.get(groupPos).Money);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
		txtPercentage.setText(sdf.format( m_groups.get(groupPos).Time));
		
		return convertView;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPos, int childPos) {
		return true;
	}
}