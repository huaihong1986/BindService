package com.cloudtv.hahong.remoteserviceclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudtv.hahong.remoteservice.StudentQuery;

/**
 * 客户端的测试代码
 * @author weijiang204321
 *
 */
public class MainActivity extends Activity {
    //定义控件
    private EditText numberText;
    private TextView resultView;
    private Button   connService;
    private StudentQuery studentQuery;

    //定义一个连接
    private StudentConnection conn = new StudentConnection();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity","onCreate");
        numberText = (EditText) this.findViewById(R.id.number);
        resultView = (TextView) this.findViewById(R.id.resultView);
        connService = (Button)  this.findViewById(R.id.get_service);
        //这里开启一个Service使用隐式意图action的名称必须和remoteService中AndroidMainfest.xml中定义的服务的action的name一样
        Intent service = new Intent("com.cloudtv.hahong.remoteservice.student.query");
        bindService(service, conn, BIND_AUTO_CREATE);
        connService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    queryStudent();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 给按钮定义点击事件
     */
    private void queryStudent() throws RemoteException {
        String number = numberText.getText().toString();
        int num = Integer.valueOf(number);
        Log.v("MainActivity","queryStudent"+studentQuery);
        resultView.setText(studentQuery.queryStudent(num));
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    /**
     * 自定义StudentConnection实现了ServiceConnection
     * @author hahong
     *
     */
    private final class StudentConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            //这里就用到了Stub类中的asInterface方法，将IBinder对象转化成接口
            studentQuery = StudentQuery.Stub.asInterface(service);
            Log.v("MainActivity", "onServiceConnected");
            Toast.makeText(MainActivity.this, "Service connected",
                    Toast.LENGTH_SHORT).show();
        }
        public void onServiceDisconnected(ComponentName name) {
            studentQuery = null;
        }
    }
}
