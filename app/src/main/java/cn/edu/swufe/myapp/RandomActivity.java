package cn.edu.swufe.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;

public class RandomActivity extends AppCompatActivity {
    private final String TAG = "RandomActivity";
    private int ed_num;
    private String ed_what;
    private LuckPan pan;
    private ImageView imgStart;
    private TextView d_what;
    private String final_choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //helper=new Helper();
        //final_choice="";
        //接收数据
        Intent intent = getIntent();
        ed_what = intent.getStringExtra("ed_what");
        ed_num = intent.getIntExtra("ed_num", 0);
        Log.i(TAG, "onCreate: ed_what=" + ed_what);
        Log.i(TAG, "onCreate: ed_num=" + ed_num);
        String[] choice = new String[ed_num];
        for(int i=0;i<ed_num;i++){
            choice[i] = intent.getStringExtra("item"+(i+1));
            Log.i(TAG, "接收:  选项:"+(i+1)+ "  内容: "+choice[i]);
        }
        setContentView(R.layout.activity_random);
        d_what=(TextView)findViewById(R.id.d_what);
        d_what.setText(ed_what);

        pan = (LuckPan) findViewById(R.id.pan);
        imgStart = (ImageView) findViewById(R.id.img_start);
        //pan.setItems(mItemStrs);
        pan.setItems(choice);
        Log.i(TAG, "onCreate: choice:"+choice);
        Random random=new Random(2);
        pan.setLuckNumber(pan.getmLuckNum());
        pan.setLuckPanAnimEndCallBack(new LuckPanAnimEndCallBack() {
            @Override
            public void onAnimEnd(String str) {
                Toast.makeText(RandomActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pan.startAnim();
                //Helper helper=new Helper();
                final_choice=pan.getResult();
                Log.i(TAG, "onCreate: getResult"+final_choice);
                String date = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
                DBManager dbManager = new DBManager(RandomActivity.this);
                Choices cho=new Choices(ed_what,final_choice,date);
                dbManager.add(cho);
                Log.i(TAG, "onCreate: 添加数据库成功：question:"+ed_what+"result:"+final_choice+"date"+date);

            }
        });
    }
    public void click(View view) {
        Intent list= new Intent(this,RecordActivity.class);
        startActivity(list);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_index){
            Intent list= new Intent(this,BoringActivity.class);
            startActivity(list);
        }
        else if(item.getItemId()==R.id.menu_choice){
            //打开列表窗口
            Intent list= new Intent(this,TwoPActivity.class);
            startActivity(list);
        }
        else if(item.getItemId()==R.id.menu_record){
            //打开列表窗口
            Intent list= new Intent(this,RecordActivity.class);
            startActivity(list);
        }
        else{
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

}
