package com.cloudtv.hahong.aidlparcelable;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
public class MainActivity extends ActionBarActivity {
    private IPersonService personService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        this.bindService(new Intent("com.cloudtv.hahong.aidlparcelable.service"),this.serviceConnection, BIND_AUTO_CREATE);//绑定到服务
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(serviceConnection);//解除服务
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            personService =IPersonService.Stub.asInterface(service);
            try {
                personService.save(new Person(56,"liming"));
            } catch(RemoteException e) {
                Log.e("MainActivity", e.toString());
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            personService =null;
        }
    };
}
