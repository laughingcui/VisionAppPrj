package com.android.cuisy.visionappprj.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.activity.LoginActivity;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                GuideActivity.this.finish();
            }
        }, 2000);

        String VersionName;
        try {
            VersionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e) {
            VersionName = "1.0";
        }

        TextView txtVersion = (TextView) findViewById(R.id.txt_version);
        txtVersion.setText(String.format("v%s", VersionName));

    }
}
