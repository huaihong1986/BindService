package com.cloudtv.hahong.asynctask;

/**
 * Created by hahong on 15-10-26.
 */
//模拟网络环境
public class NetOperator {

    public void operator(){
        try {
            //休眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
