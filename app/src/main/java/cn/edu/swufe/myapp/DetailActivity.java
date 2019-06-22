package cn.edu.swufe.myapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = "DetailActivity";
    private int ed_num;
    private String ed_what;
    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //接收数据
        Intent intent = getIntent();
        ed_what = intent.getStringExtra("ed_what");
        ed_num = intent.getIntExtra("ed_num", 0);
        Log.i(TAG, "onCreate: ed_what=" + ed_what);
        Log.i(TAG, "onCreate: ed_num=" + ed_num);


        //获取ListView对象
        listView = (ListView) findViewById(R.id.mylist);
        adapter = new ListViewAdapter(this, initData());
        listView.setAdapter(adapter);

    }


    public void onClick(View btn) {
        //Toast.makeText(DetailActivity.this, adapter.getParams()+"", Toast.LENGTH_SHORT).show();

        String strs[]= adapter.getParams().toArray(new String[0]);

        Intent intent=new Intent();
        intent.setClass(DetailActivity.this,RandomActivity.class);//前面是本页面，后面是下一页面
        intent.putExtra("ed_what",ed_what);
        intent.putExtra("ed_num",ed_num);
        Log.i(TAG, "onClick: 存入ed_what=" + ed_what);
        Log.i(TAG, "onClick: 存入ed_num=" + ed_num);
        for(int i=0;i<ed_num;i++){
            if(strs.length>0){
            intent.putExtra("item"+(i+1),strs[i]);
            Log.i(TAG, "onClick:选项 "+(i+1)+ "内容"+strs[i]);

        }}
        startActivityForResult(intent,1);

    }
    private List<String> initData(){
        List<String> dataList = new ArrayList<>();
        for (int i = 1; i < ed_num+1; i++) {
            dataList.add("选项"+i);
        }
        return dataList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return true;
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
