package com.android.cuisy.visionappprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.entity.Camera;

import java.util.List;

/**
 * Created by cuisy on 2017/4/11.
 */

public class CameraAdapter extends ArrayAdapter<Camera>{
    private int resourceId;
    private List<Camera> cameras;
    public CameraAdapter(Context context, int textViewResourceId, List<Camera> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        cameras = objects;

    }
    @Override
    public Camera getItem(int position) {
        return cameras == null?null:cameras.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Camera camera = getItem(position); //获取当前项的Camera实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView cameraImage = (ImageView) view.findViewById(R.id.camera_image);
        TextView cameraName = (TextView) view.findViewById(R.id.camera_name);
        cameraImage.setImageResource(R.drawable.dayou_logo3);
        cameraName.setText(camera.getDes());
        return view;
    }


}
