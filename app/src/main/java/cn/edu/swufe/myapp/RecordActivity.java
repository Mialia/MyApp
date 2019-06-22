package cn.edu.swufe.myapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordActivity extends ListActivity implements AdapterView.OnItemLongClickListener{

    private final String TAG = "RecordActivity";
    private List<HashMap<String, String>> mychoicelist;
    private MyChoAdapter adapter;
    private DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);

        //接收并保存数据
        mychoicelist=new ArrayList<HashMap<String, String>>();

        HashMap<String, String> m = new HashMap<String, String>();
        m.put("QUESTION", "  ");
        m.put("RESULT", "长按删除");
        m.put("TIME", "  ");
        mychoicelist.add(m);

        HashMap<String, String> ma = new HashMap<String, String>();
        ma.put("QUESTION", "问题");
        ma.put("RESULT", "选项");
        ma.put("TIME", "时间");
        mychoicelist.add(ma);

        dbManager = new DBManager(RecordActivity.this);
        for(Choices choices : dbManager.listAll()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("QUESTION", choices.getQuestion());
            map.put("RESULT", choices.getResult());
            map.put("TIME", choices.getTime());
            mychoicelist.add(map);
            Log.i(TAG,"问题："+ choices.getQuestion() + "选择： " + choices.getResult()+"时间： "+choices.getTime());
            Log.i(TAG,"id："+ choices.getId() );

        }



        adapter = new MyChoAdapter(this,R.layout.item_record,mychoicelist);
        this.setListAdapter(adapter);

        getListView().setOnItemLongClickListener(this);

        //获取ListView对象
        //listView = (ListView) findViewById(R.id.list_choice);
        //listView.setAdapter(adapter);

    }


    public void onClick(View v) {
        //Toast.makeText(DetailActivity.this, adapter.getParams()+"", Toast.LENGTH_SHORT).show();

        //String strs[]= adapter.getParams().toArray(new String[0]);



    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按列表项position="+position);
        //删除操作
        //listItems.remove(position);
        //listItemAdapter.notifyDataSetChanged();
        //构造对话框进行确认操作
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int[] ids=new int[mychoicelist.size()];
                int index=0;
                for(Choices choices : dbManager.listAll()){
                    ids[index]=choices.getId();
                    index++;


                }
                Log.i(TAG, "onClick: 对话框事件处理  position:"+position);
                Log.i(TAG,"ID："+ ids[position-2]);
                mychoicelist.remove(position);
                dbManager.delete(ids[position-2]);

                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();

        Log.i(TAG, "onItemLongClick: size="+mychoicelist.size());
        return true;//false：同时不屏蔽短按事件，true：同时屏蔽短按事件
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
