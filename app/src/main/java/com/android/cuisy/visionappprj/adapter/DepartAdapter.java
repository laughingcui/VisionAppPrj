package com.android.cuisy.visionappprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.entity.Depart;

import java.util.List;

/**
 * Created by cuisy on 2017/4/11.
 */

public class DepartAdapter extends ArrayAdapter<Depart>{
    private int resourceId;
    public DepartAdapter(Context context, int textViewResourceId, List<Depart> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Depart depart = getItem(position);//获取当前项的Depart实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView departImage = (ImageView) view.findViewById(R.id.depart_image);
        TextView departName = (TextView) view.findViewById(R.id.depart_name);
        departImage.setImageResource(R.drawable.dayou_logo3);
        departName.setText(depart.getDes());
        return view;
    }


}
