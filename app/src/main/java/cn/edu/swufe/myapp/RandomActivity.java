package cn.edu.swufe.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    private final String TAG = "RandomActivity";
    private int ed_num;
    private String ed_what;
    private MySurfaceView mDial;
    private Button mBt;
    private MyPara paras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //接收数据
        Intent intent = getIntent();
        ed_what = intent.getStringExtra("ed_what");
        ed_num = intent.getIntExtra("ed_num", 0);
        Log.i(TAG, "onCreate: ed_what=" + ed_what);
        Log.i(TAG, "onCreate: ed_num=" + ed_num);
        String[] choice = new String[ed_num];
        for(int i=0;i<ed_num;i++){
            choice[i] = intent.getStringExtra("item"+i+1);
            Log.i(TAG, "接收:  选项:"+(i+1)+ "  内容: "+choice[i]);
        }

        //将数据传入全局变量
        paras=new MyPara();
        paras.setEd_num(ed_num);
        Log.i(TAG, "传入全局变量： 选项个数"+ed_num);
        paras.setEd_what(ed_what);
        Log.i(TAG, "传入全局变量：  what"+ed_what);
        paras.setItems(choice);
        Log.i(TAG, "传入全局变量：  选项列表"+choice.toString());



        setContentView(R.layout.activity_random);
        Log.i(TAG, "加载activity_random");
        mDial= (MySurfaceView) findViewById(R.id.lucky_view);
        Log.i(TAG, "加载lucky_view");


    }

    public void click(View view) {
        if (mDial.isRotating()) {
            view.setBackgroundResource(R.drawable.start);
            mDial.stopDial();
        } else {
            //如果点击了停止按钮，且转盘由于惯性还在旋转时，则不起作用
            if(!mDial.isShouldEndFlag()) {
                view.setBackgroundResource(R.drawable.end);
                mDial.startDial(1);
            }

        }
    }


}
