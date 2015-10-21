package com.cloudtv.hahong.bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by hahong on 15-10-21.
 */
public class StudentService extends Service {

    //名称
    public static String[] nameAry = {"张飞","李小龙","赵薇"};

    /**
     * 通过no获取name
     * @param no
     * @return
     */
    private String getNameByNo(int no){
        if(no>0 && no<4)
            return nameAry[no-1];
        return null;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return new StudentBinder();
    }

    /**
     * 自定义的Binder对象
     * @author weijiang204321
     *
     */
    private class StudentBinder extends Binder implements IStudent{
        @Override
        public String getNameByNumber(int no) {
            return getNameByNo(no);
        }
    }


}
