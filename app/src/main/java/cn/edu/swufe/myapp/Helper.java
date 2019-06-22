package cn.edu.swufe.myapp;

import android.util.Log;

public class Helper {
    String result;
    private final String TAG = "Helper";

    public Helper() {
        result = "";
    }
    public Helper(String res) {
        result = res;
    }


    public String getResult() {
        Log.i(TAG, "getResult: "+result);
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
