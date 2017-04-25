package com.android.cuisy.visionappprj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.adapter.DepartAdapter;
import com.android.cuisy.visionappprj.adapter.SimpleTreeAdapter;
import com.android.cuisy.visionappprj.bean.FileBean;
import com.android.cuisy.visionappprj.entity.Camera;
import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.entity.Depart;
import com.android.cuisy.visionappprj.slide.SlideMenu;
import com.android.cuisy.visionappprj.timerService.Timer;
import com.android.cuisy.visionappprj.util.HttpUtil;
import com.android.cuisy.visionappprj.xmlParse.ParseXml;
import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;
import com.zhy.tree.bean.TreeListViewAdapter.OnTreeNodeClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 先把MainActivity代码移植到这个类
public class TreeNodeActivity extends AppCompatActivity implements View.OnClickListener {

    private SlideMenu slideMenu;
    private ListView listView;
    private List<Depart> departList = new ArrayList<>();
    private List<Camera>[] cameras ;

    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;

    private final int GET_CAMERA = 101;
    private final int INIT_DATA = 102;
    private long exitTime = 0;
    private static Boolean isQuit = false;
    Timer timer = new Timer();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if (msg.what == GET_CAMERA) {
               cameras = new List[departList.size()];
                getCameras();
            } else if (msg.what == INIT_DATA) {
                initData();
                Log.d("notifyData", "notifyDataSetChanged");
                initAdapter();
            }
        }
    };

    public TreeNodeActivity(TreeListViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public TreeNodeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTree = (ListView) findViewById(R.id.id_tree);
        initAdapter();
        getNode();
        startService(new Intent(TreeNodeActivity.this, Timer.class));//启动定时服务
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_menu_btn:
                if (slideMenu.isMainScreenShowing()) {
                    slideMenu.openMenu();
                } else {
                    slideMenu.closeMenu();
                }
                break;
        }
    }

    //点击返回键两次，退出APP
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return true;
    }

    private void initAdapter() {
        try {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);
            mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int i) {
                    if (node.isLeaf()) {
                        Toast.makeText(getApplicationContext(), node.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mTree.setAdapter(mAdapter);
            if (mDatas.size() >0){
                for (int i = 0;i < departList.size();i++){
                    mAdapter.expandOrCollapse(i);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void getNode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                departList.clear();
                String TreeNodeInfo = HttpUtil.HttpGetTreeNode(Constants.u, "getdtrees", Constants.sessionId);
                List<Depart> departs = ParseXml.getTreeNodeByXml(TreeNodeInfo);
                departList.addAll(departs);
                handler.sendEmptyMessage(GET_CAMERA);
            }
        }).start();
    }

    public void getCameras() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < departList.size(); i++) {
                    String CameraNodeInfo = HttpUtil.HttpGetCameraNode(Constants.u, "getdevs", Constants.sessionId, departList.get(i).getName());
                    Log.d("index ", i + "");
                    cameras[i] = ParseXml.getCameraNodeByXml(CameraNodeInfo);
                }
                handler.sendEmptyMessage(INIT_DATA);
            }
        }).start();

    }

    private void initData() {
        mDatas.clear();
        Log.d("departList.size = ", "" + departList.size());
        for (int i = 0; i < departList.size(); i++) {
            Log.d("node.name = ", "i = " + i + " parentId = " + 0 + " name = " + departList.get(i).getDes());
            mDatas.add(new FileBean(i + 1, 0, departList.get(i).getDes()));
        }
        for (int j = 0; j < cameras.length; j++) {
            Log.d("camera.length", cameras.length + "");
            for (int z = 0; z < cameras[j].size(); z++) {
                Log.d("camera.name", cameras[j].get(z).getDes());
                mDatas.add(new FileBean(departList.size() + j + z + 1, j + 1, cameras[j].get(z).getDes()));
            }
        }
        for (int i = 0; i < mDatas.size(); i++) {
            System.out.println(mDatas.get(i).get_id() + "|" + mDatas.get(i).getParentId() + "|" + mDatas.get(i).getName());
        }
    }
}
