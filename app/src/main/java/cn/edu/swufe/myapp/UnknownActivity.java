package cn.edu.swufe.myapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class UnknownActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Vibrator vibrator;

    private static  String strs[]={"工作","跑步","遛狗","看电影","听音乐","购物","剪头发","健身","喝咖啡",
            "旅行","K歌","约会","聚会","玩游戏","睡觉","甜点","思考","阅读","运动"};
    private static  int pics[]={R.mipmap.p1_work,R.mipmap.p2_running,R.mipmap.p3_walkdog,R.mipmap.p4_movie,R.mipmap.p5_music,
            R.mipmap.p6_shopping,R.mipmap.p7_haircut,R.mipmap.p8_fitness,R.mipmap.p9_coffee,R.mipmap.p10_travel,R.mipmap.p11_ktv,
            R.mipmap.p12_date,R.mipmap.p13_party,R.mipmap.p14_games,R.mipmap.p15_sleep,R.mipmap.p16_dessert,R.mipmap.p17_think,
            R.mipmap.p18_reading,R.mipmap.p19_sports};

    private TextView tv;
    private ImageView tutu;

    private static final  String TAG="UnknownActivity";
    private static final  int SENSOR_SHAKE=10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown);

        tv=(TextView)findViewById(R.id.tv);
        tutu=(ImageView) findViewById(R.id.tutu);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);


    }

    @Override
    protected void onResume() {

        super.onResume();
        if(sensorManager != null){
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
            //1.Listener 2.传感器类型 3.获取传感器信息的频率
        }
    }
    protected void onStop() {

        super.onStop();
        if(sensorManager != null){//取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }




    /**重力感应监听*/
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器信息改变时执行
            float[] values=event.values;
            float x=values[0];//x轴方向的重力加速度，向右为正
            float y=values[1];//y轴方向的重力加速度，向前为正
            float z=values[2];//z轴方向的重力加速度，向上为正
            Log.i(TAG, " x["+x+"] y["+y+"] z["+z+"]");
            //一般这三个方向达到40就达到了摇晃手机的状态
            int medumValues=10;//不同厂商可能不同
            if(Math.abs(x)>medumValues||Math.abs(y)>medumValues||Math.abs(z)>medumValues){
                vibrator.vibrate(200);
                Message msg=new Message();
                msg.what=SENSOR_SHAKE;
                handler.sendMessage(msg);

            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case SENSOR_SHAKE:
                    //Toast.makeText()

                    Log.i(TAG, "handleMessage: 检测到摇晃，执行操作");
                    java.util.Random r=new java.util.Random();
                    int num =Math.abs(r.nextInt())%19;
                    tv.setText(strs[num]);
                    tutu.setImageResource(pics[num]);
                    break;
            }
        }
    };




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_index){
            Intent list= new Intent(this,TwoPActivity.class);
            startActivity(list);
        }
        else if(item.getItemId()==R.id.menu_choice){
            //打开列表窗口
            Intent list= new Intent(this,RecordActivity.class);
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
