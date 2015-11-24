package com.best.parttimejobapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 创建时间：2015-11-18
 * 创建人：吴磊
 * 创建的内容：启动页面
 * */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //启动一个线程，控制启动页的秒数
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //睡眠三秒
                    Thread.sleep(3000);
                    //intent的跳转
                    Intent intent = new Intent(StartActivity.this,GuideActivity.class);
                    startActivity(intent);
                    StartActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
