package com.best.parttimejobapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.best.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    View view1,view2,view4;
    List<View> viewList = new ArrayList<>();

   GuideAdapter adapter;

    RadioButton radio1,radio2,radio4;
    ImageButton actionMainActivity01;
    ImageButton actionMainActivity02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        view1 = LayoutInflater.from(this).inflate(R.layout.activity_step_view1,null);
        view2 = LayoutInflater.from(this).inflate(R.layout.activity_step_view2,null);

        view4 = LayoutInflater.from(this).inflate(R.layout.activity_step_view4,null);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);

        radio4 = (RadioButton) findViewById(R.id.radio4);
        actionMainActivity01 = (ImageButton) findViewById(R.id.tiaoguo);
        actionMainActivity02 = (ImageButton) view4.findViewById(R.id.actionMainActivity02);
        actionMainActivity01.setOnClickListener(this);
        actionMainActivity02.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radio1.setChecked(true);
                        break;
                    case 1:
                        radio2.setChecked(true);
                        break;

                    case 2:
                        radio4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view4);

      adapter = new GuideAdapter(this,viewList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if(bmobUser != null){
            Intent intent = new Intent(GuideActivity.this,MainActivity.class);
            startActivity(intent);
        }else {

            Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        GuideActivity.this.finish();
    }
}
