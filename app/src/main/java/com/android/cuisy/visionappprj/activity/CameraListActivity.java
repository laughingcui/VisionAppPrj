package com.android.cuisy.visionappprj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.adapter.CameraAdapter;
import com.android.cuisy.visionappprj.entity.Camera;
import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.util.HttpUtil;
import com.android.cuisy.visionappprj.xmlParse.ParseXml;

import java.util.ArrayList;
import java.util.List;

public class CameraListActivity extends AppCompatActivity {

    private String name;
    private ListView listView;
    private List<Camera> cameraList = new ArrayList<>();
    CameraAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_list);
        name = getIntent().getStringExtra("name");
        initCamera();
        getCameras(name);
    }
    private void initCamera() {
        listView = (ListView) findViewById(R.id.list_camera);
        adapter = new CameraAdapter(CameraListActivity.this, R.layout.activity_camera_node,cameraList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CameraListActivity.this, CameraDetailActivity.class);
                intent.putExtra("name",cameraList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    //获取到摄像头节点
    public void getCameras(final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String CameraNodeInfo = HttpUtil.HttpGetCameraNode(Constants.u, "getdevs", Constants.sessionId, name);
                List<Camera> camera = ParseXml.getCameraNodeByXml(CameraNodeInfo);
                cameraList.addAll(camera);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
