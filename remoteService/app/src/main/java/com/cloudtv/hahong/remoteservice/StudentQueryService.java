package com.cloudtv.hahong.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
public class StudentQueryService extends Service {
    //姓名名称
    private String[] names = {"张飞", "李静", "赵薇"};

    private IBinder binder = new StudentQueryBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    /**
     * 服务中定义的访问方法
     * @param number
     * @return
     */
    private String query(int number){
        if(number > 0 && number < 4){
            return names[number - 1];
        }
        return null;
    }

    /**
     * 定义Binder，这里需要继承StudentQuery.Stub
     * StudentQuery是我们定义的AIDL
     * @author hahong
     *
     */
    private final class StudentQueryBinder extends StudentQuery.Stub{
        public String queryStudent(int number) throws RemoteException {
            return query(number);
        }
    }
}
