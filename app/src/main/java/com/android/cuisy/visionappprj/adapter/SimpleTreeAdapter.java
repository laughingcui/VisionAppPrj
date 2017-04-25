package com.android.cuisy.visionappprj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.cuisy.visionappprj.R;
import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;


import java.util.List;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T>
{

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
                             int defaultExpandLevel) throws IllegalArgumentException,
            IllegalAccessException
	{
		super(mTree, context, datas, defaultExpandLevel);
	}

	@Override
	public View getConvertView(Node node , int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_label);
			convertView.setTag(viewHolder);

		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

//		Log.d("node.getIcon()= ",node.getIcon()+"");
//		if (node.getIcon() == -1)
//		{
//			viewHolder.icon.setVisibility(View.INVISIBLE);
//		} else
//		{
//			viewHolder.icon.setVisibility(View.VISIBLE);
//			viewHolder.icon.setImageResource(node.getIcon());
//		}
//		viewHolder.label.setText(node.getName());
		Log.d("node.getIcon()= ", node.getIcon() + "");
		if (node.getIcon() == 2130837506) {
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(R.drawable.tree_ex);
		} else if (node.getIcon() == 2130837505){
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(R.drawable.tree_ec);
		}else {
			viewHolder.icon.setVisibility(View.INVISIBLE);
		}
		viewHolder.label.setText(node.getName());
		return convertView;
	}

	private final class ViewHolder
	{
		ImageView icon;
		TextView label;
	}

}
