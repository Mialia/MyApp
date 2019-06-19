package cn.edu.swufe.myapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PageActivity extends AppCompatActivity {
    //private Button btn_page;
    private  String TAG="PageActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        ViewPager viewPager =findViewById(R.id.viewpager);
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //btn_page = (Button) findViewById(R.id.btn_enter);
        //btn_page.setOnClickListener(new View.OnClickListener(){

            //@Override
            //public void onClick(View v) {


            //}
       // });




    }

    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(PageActivity.this,TwoPActivity.class);//前面是本页面，后面是下一页面
        Log.i(TAG, "onClick: PageActivity");
        startActivity(intent);

    }
}