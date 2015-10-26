package com.cloudtv.hahong.bindservicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by hahong on 15-10-21.
 */
public class MainActivity extends Activity {

    private IStudent student;
    public sendResultHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启查询名称的服务
        Intent service = new Intent(this,StudentService.class);
        bindService(service,new StudentConnection(),BIND_AUTO_CREATE);
        handler = new sendResultHandler();
        //延迟2s在显示查询的内容，不然开启服务也是需要时间的，如果不延迟一段时间的话,student对象为null;
        new Thread(new Runnable() {

            @Override
            public void run(){
                try {
                    Thread.sleep(2*1000);
                    //handler.sendEmptyMessage(0);
                    Looper.prepare();

                    Toast.makeText(getApplicationContext(), student.getNameByNumber(1), Toast.LENGTH_LONG).show();
                    Looper.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public class sendResultHandler extends Handler {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    Toast.makeText(getApplicationContext(), student.getNameByNumber(1), Toast.LENGTH_LONG).show();
                    break;



            }

        }
    }
    /**
     * 自定义的服务连接connection
     * @author weijiang204321
     *
     */
    private class StudentConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            student = (IStudent)service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    }
}
