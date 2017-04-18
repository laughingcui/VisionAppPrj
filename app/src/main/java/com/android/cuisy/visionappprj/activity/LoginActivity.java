package com.android.cuisy.visionappprj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.util.HttpUtil;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private static final int REQUEST_ACCOUNT = 1;//请求码，此值只要是唯一即可
    public EditText accountEdit;
    public EditText passwordEdit;
//    public static String account;//用户名
//    public static String password;//密码

    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = (EditText) findViewById(R.id.account_edit_id);
        passwordEdit = (EditText) findViewById(R.id.password_edit_id);

        View login_btn = findViewById(R.id.login_btn_id);
        View setting_btn = findViewById(R.id.setting_btn_id);
        //读数据库设置用户名和密码

        accountEdit.setText(Constants.username);
        passwordEdit.setText(Constants.password);

        login_btn.setOnClickListener(this);
        setting_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login_btn_id) {
            Constants.username = accountEdit.getText().toString();
            Constants.password = passwordEdit.getText().toString();

            if (Constants.username.equals("")) {
                Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (Constants.password.equals("")) {
                    Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            //登录
                            //String u = "http://" + Constants.SetIp + ":" + Constants.SetPort + "/";
                            Constants.sessionId = HttpUtil.HttpLogIn(Constants.u, "login", new String[]{"name", "password"}, Constants.username, Constants.password);
                            System.out.println("sessionid = " + Constants.sessionId);
//                            //启动定时器服务
//                            startService(new Intent(LoginActivity.this, Timer.class));
//
//                            //获取树节点
//                            String TreeNodeInfo = HttpUtil.HttpGetTreeNode(Constants.u, "getdtrees", Constants.sessionId);
//                            System.out.println("走到1");
//                            List<Depart> departs = ParseXml.getTreeNodeByXml(TreeNodeInfo);
//
//                            //获取树节点的具体元素的值
//                            for (int i = 0; i < departs.size(); i++) {
//                                System.out.println("depart = " + departs.get(i).getName());
//                                System.out.println("depart = " + departs.get(i).getDes());
//                                System.out.println("depart = " + departs.get(i).getStatus());
//
//                                //获取到摄像头节点
//                                String CameraNodeInfo = HttpUtil.HttpGetCameraNode(Constants.u,"getdevs", Constants.sessionId, departs.get(i).getName());
//                                List<Camera> camera = ParseXml.getCameraNodeByXml(CameraNodeInfo);
//                            }

                            Intent intent = new Intent(LoginActivity.this, TreeNodeActivity.class);
                            startActivity(intent);
                        }
                    }).start();
                }
            }
        }

        if (id == R.id.setting_btn_id) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivityForResult(intent, REQUEST_ACCOUNT);//用此方法启动活动，在活动销毁时，会将结果返回给上一个活动
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("mIp = " + Constants.SetIp);
        System.out.println("mPort = " + Constants.SetPort);
    }

//用于数据交流的回调函数
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Intent intent = getIntent();
//        mIp = intent.getStringExtra("ip");
//        System.out.println("1111" + mIp);
//        Log.d("LoginActivity",mIp);

        // 判断本次返回的数值的来源
//        switch (requestCode) {
//            case 1:
//                if (requestCode == REQUEST_ACCOUNT) {
//                    String returnedData_ip = data.getStringExtra("ip");
//                    String returnedData_port = data.getStringExtra("port");
//                    Log.d("LoginActivity", returnedData_ip);
//                    Log.d("LoginActivity", returnedData_port);
//                }
//                break;
//            default:
////        if (requestCode == REQUEST_ACCOUNT
////                && resultCode == Activity.RESULT_OK) {// 说明是从设置界面返回的ip和port
////            Bundle bundle = data.getExtras();
////            mIp = bundle.getString("ip");
////            mPort = bundle.getString("port");
////            Log.d("ip=",mIp);
////            Log.d("port=",mPort);
//
////            accountEdit.setText(mIp);
////            passwordEdit.setText(mPort);
//
//        }
//    }
}
