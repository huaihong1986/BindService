package com.cloudtv.hahong.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 生成该类的对象，并调用execute方法之后
 * 首先执行的是onProExecute方法
 * 其次执行doInBackgroup方法
 *
 */
public class ProgressBarAsyncTask extends AsyncTask<String, Integer, String> {

    private TextView textView;
    private ProgressBar progressBar;


    public ProgressBarAsyncTask(TextView textView, ProgressBar progressBar) {
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }


    /**
     * 这里的String参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的控件进行设置和修改
     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
     */
    @Override
    protected String doInBackground(String... params) {
        NetOperator netOperator = new NetOperator();
        int i = 0;
        for (i = 10; i <= 100; i+=10) {
            netOperator.operator();
            publishProgress(i,i+1);
        }
        Log.v("ProgressBarAsyncTask",i + params[0].toString() + "   " + params[1].toString());
        return i + params[0].toString() + "";
    }


    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String result) {
        textView.setText("异步操作执行结束" + result);
    }


    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
        textView.setText("开始执行异步线程");
    }


    /**
     * 这里的Intege参数对应AsyncTask中的第二个参数
     * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
     * onProgressUpdate是在UI线程中执行，所有可以对UI控件进行操作
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int vlaue = values[0];
        Log.v("ProgressBarAsyncTask",values[0]+ "  " + values[1]);
        progressBar.setProgress(vlaue);
    }





}
