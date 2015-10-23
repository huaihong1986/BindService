package com.cloudtv.hahong.aidlparcelable;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class PersonService extends Service {
    private ServiceBinder serviceBinder = new ServiceBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }
    public class ServiceBinder extends IPersonService.Stub {
        @Override
        public void save(Person person) throws RemoteException {
            Log.i("PersonService", person.getId() + "=" + person.getName());

        }
    }
}
