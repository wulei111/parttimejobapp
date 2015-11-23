package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.best.fragment.IndexFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView toolbartitle;
    String background_style = "night";
    android.support.v4.app.FragmentManager fm;
    RadioButton fenclass,index,seeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到Toobar
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        //seeat = (RadioButton)findViewById(R.id.seeat);
        //  fenclass = (RadioButton) findViewById(R.id.fenclass);
        index = (RadioButton) findViewById(R.id.index);
        index.setOnClickListener(this);
        fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            Log.i("vv",fm.beginTransaction()+"");
            android.support.v4.app.FragmentTransaction ftt = fm.beginTransaction();
            IndexFragment inf = new IndexFragment();
            ftt.add(R.id.fragment_parent, inf, "index");
            ftt.commit();
        }



        //侧边栏button监听事件
//        Button sildebar_shezhi_button = (Button) findViewById(R.id.slide_shezhi_button);
//        sildebar_shezhi_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,Activity_SetUp.class);
//                startActivity(i);
//            }
//        });
//
//
//        Button yijian = (Button) findViewById(R.id.slide_yijian_button);
//        yijian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }return super.onOptionsItemSelected(item);
    }


    public void slide_about_button(View v){
        Intent i = new Intent(this,Activity_About.class);
        startActivity(i);
        this.finish();
    }
    public void slide_yijian_button(View v){
        Intent i = new Intent(this,Activity_Information.class);
        startActivity(i);
        this.finish();
    }
    public void slide_setup_button(View v){
        Intent i = new Intent(this,Activity_SetUp.class);
        startActivity(i);
        this.finish();
    }
    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction ftt = fm.beginTransaction();
        if (fm.findFragmentByTag("index")!= null){
            ftt.hide(fm.findFragmentByTag("index"));
        }
        if (fm.findFragmentByTag("fenclass")!= null){
            ftt.hide(fm.findFragmentByTag("fenclass"));
        }
        if (fm.findFragmentByTag("index")!= null){
            ftt.hide(fm.findFragmentByTag("index"));
        }
        if (fm.findFragmentByTag("seeat")!= null){
            ftt.hide(fm.findFragmentByTag("seeat"));
        }
        int id = v.getId();
        if (id == R.id.index) {
            if (fm.findFragmentByTag("index") != null) {
                ftt.show(fm.findFragmentByTag("index"));
            } else {
                IndexFragment pf = new IndexFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, pf, "index");
            }
        }else if (id == R.id.fenclass){
            if (fm.findFragmentByTag("fenclass")!=null){
                ftt.show(fm.findFragmentByTag("fenclass"));
            }else{
                //  FenClassFragment af = new FenClassFragment();
                //add(父布局ID，Fragment，Tag);
                //   ftt.add(R.id.fragment_parent, af, "fenclass");
            }
        }else if (id == R.id.seeat){
            if (fm.findFragmentByTag("seeat")!=null){
                ftt.show(fm.findFragmentByTag("seeat"));
            }else{
                //    SeeAtFragment af = new SeeAtFragment();
                //add(父布局ID，Fragment，Tag);
                //    ftt.add(R.id.fragment_parent,af,"seeat");
            }
        }

        ftt.commit();
    }
}
