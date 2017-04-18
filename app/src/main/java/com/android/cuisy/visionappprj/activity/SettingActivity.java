package com.android.cuisy.visionappprj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.entity.Constants;

public class SettingActivity extends AppCompatActivity implements OnClickListener{
    private EditText ipEdit;
    private EditText portEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ipEdit = (EditText) findViewById(R.id.ip_edit_id);
        portEdit = (EditText) findViewById(R.id.port_edit_id);


        ipEdit.setText(Constants.SetIp);
        portEdit.setText(Constants.SetPort);
        View confirm_btn = findViewById(R.id.confirm_btn_id);
        View cancel_btn = findViewById(R.id.cancel_btn_id);

        confirm_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.confirm_btn_id) {
            Constants.SetIp = ipEdit.getText().toString();
            Constants.SetPort = portEdit.getText().toString();
            System.out.println(".....IP = " + Constants.SetIp);
            System.out.println(".....PORT = " + Constants.SetPort);
            if (Constants.SetIp.equals("")) {
                Toast.makeText(this, "IP地址不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Constants.SetPort.equals("")) {
                Toast.makeText(this, "端口不能为空",Toast.LENGTH_SHORT).show();
                return;
            }

//            Intent intent = new Intent(this, LoginActivity.class);
//            //Intent intent_port = new Intent(this, SettingActivity.class);
//            intent.putExtra("ip", ip);
//            intent.putExtra("port",port);
            //setResult(RESULT_OK, intent);
//            startActivity(intent);
            //setResult(RESULT_OK, intent_port);
            finish();
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.cancel_btn_id) {
            finish();
        }
    }
//    @Override
//    public void onBackPressed() {
//        String ip = ipEdit.getText().toString();
//        String port = portEdit.getText().toString();
//        Intent intent_ip = new Intent();
//        intent_ip.putExtra("ip", ip);
//        Intent intent_port = new Intent();
//        intent_port.putExtra("ip", port);
//        setResult(RESULT_OK, intent_ip);
//        setResult(RESULT_OK, intent_port);
//        finish();
//    }
}
