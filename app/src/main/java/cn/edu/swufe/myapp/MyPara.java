package cn.edu.swufe.myapp;

import android.app.Application;
import android.util.Log;


public class MyPara extends Application {
    private final String TAG = "MyPara";

    private int ed_num;
    private String ed_what;
    private String ed_time;
    private String[] items;
    private  String[] we ={"wait"};

    //一定要在onCreate方法里给变量赋值
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        setItems(we);
        setEd_num(1);
        setEd_what("");
        setEd_time("");
        Log.i(TAG, "onCreate：初始化赋值");

    }
    //存入、获取ed_num
    public int getEd_num(){
        Log.i(TAG, "onCreate：getEd_num");
        return ed_num;

    }

    public void setEd_num(int ed_num) {
        Log.i(TAG, "onCreate：setEd_num"+ed_num);
        this.ed_num = ed_num;
    }
    //存入、获取ed_what
    public String getEd_what() {
        Log.i(TAG, "onCreate：getEd_what");
        return ed_what;
    }

    public void setEd_what(String ed_what) {
        Log.i(TAG, "onCreate：setEd_what"+ed_what);
        this.ed_what = ed_what;
    }
    //存入、获取time

    public String getEd_time() {
        Log.i(TAG, "onCreate：getEd_time");
        return ed_time;
    }

    public void setEd_time(String ed_time) {
        Log.i(TAG, "onCreate：setEd_time"+ed_time);
        this.ed_time = ed_time;
    }

    //存入、获取所有选项
    public String[] getItems() {
        Log.i(TAG, "onCreate：getItems");
        return items;
    }
    public void setItems(String[] items) {
        Log.i(TAG, "onCreate：setItems"+items.toString());
        this.items = items;
    }

}
