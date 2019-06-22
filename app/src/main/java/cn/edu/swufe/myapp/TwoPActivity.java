package cn.edu.swufe.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TwoPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_p);


    }

    public void onClick(View btn) {
        Intent intent=new Intent();
        if(btn.getId()==R.id.known){//转到  已知选项
            intent.setClass(TwoPActivity.this,KnownActivity.class);//前面是本页面，后面是下一页面
        }
        else if(btn.getId()==R.id.unknown){//转到  未知选项
            intent.setClass(TwoPActivity.this,UnknownActivity.class);//前面是本页面，后面是下一页面
        }
        else{//转到  我的记录
            intent.setClass(TwoPActivity.this,RecordActivity.class);//前面是本页面，后面是下一页面
        }
        startActivity(intent);
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
