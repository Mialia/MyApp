package cn.edu.swufe.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KnownActivity extends AppCompatActivity {
    private  Button next;
    private  EditText ed_what;
    private  EditText ed_num;
    private  String TAG="KnownActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_known);


        ed_what=(EditText)findViewById(R.id.edit_what);
        ed_num=(EditText)findViewById(R.id.edit_num);

        next = (Button) findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View btn) {
                Log.i(TAG, "onClick: ");
                String str1 = ed_what.getText().toString();
                String str2 = ed_num.getText().toString();
                Log.i(TAG, "onClick: get what=" + str1+"num="+str2);

                Toast toast;
                int num;

                if(str1.length()>0 && str1.length()>0){
                    num=Integer.parseInt(str2);

                    Intent intent=new Intent();
                    intent.setClass(KnownActivity.this,DetailActivity.class);//前面是本页面，后面是下一页面
                    intent.putExtra("ed_what",str1);
                    intent.putExtra("ed_num",num);
                    Log.i(TAG, "onClick: ed_what=" + str1);
                    Log.i(TAG, "onClick: ed_num=" + str2);
                    startActivityForResult(intent,1);
                    //startActivity(intent);
                }else{
                    //用户没有输入内容
                    toast = Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
            }
        });
    }

    public void onClick(View view) {
    }

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
