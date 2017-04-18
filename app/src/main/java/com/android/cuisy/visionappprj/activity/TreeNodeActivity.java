package com.android.cuisy.visionappprj.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.adapter.DepartAdapter;
import com.android.cuisy.visionappprj.timerService.Timer;
import com.android.cuisy.visionappprj.entity.Depart;
import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.util.HttpUtil;
import com.android.cuisy.visionappprj.xmlParse.ParseXml;

import java.util.ArrayList;
import java.util.List;


public class TreeNodeActivity extends AppCompatActivity {

    private ListView listView;
    private List<Depart> departList = new ArrayList<>();
    DepartAdapter adapter;

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
        setContentView(R.layout.activity_tree_list);
        initView();
        getNode();
        startService(new Intent(TreeNodeActivity.this, Timer.class));//启动定时服务
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_depart);
        adapter = new DepartAdapter(TreeNodeActivity.this, R.layout.activity_tree_node, departList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TreeNodeActivity.this,CameraListActivity.class);
                intent.putExtra("name",departList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    public void getNode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String TreeNodeInfo = HttpUtil.HttpGetTreeNode(Constants.u, "getdtrees", Constants.sessionId);
                List<Depart> departs = ParseXml.getTreeNodeByXml(TreeNodeInfo);
                departList.addAll(departs);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
