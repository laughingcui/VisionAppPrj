package com.android.cuisy.visionappprj.timerService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.android.cuisy.visionappprj.entity.Constants;
import com.android.cuisy.visionappprj.util.HttpUtil;

/**
 * Created by cuisy on 2017/4/8.
 */

public class Timer extends Service {

    private boolean isLoop = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //每隔10s钟，定时执行用户登录刷新
            String u = "http://" + Constants.SetIp + ":" + Constants.SetPort + "/";
            String result_refresh = HttpUtil.HttpLogInRefresh(u,"refresh",Constants.sessionId);
            System.out.println("sessionId = " + Constants.sessionId);
            System.out.println("定时执行登录刷新:result_refresh = " + result_refresh);
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new MyThread()).start();
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            while (isLoop) {
                try {
                    Thread.sleep(10000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }
}
