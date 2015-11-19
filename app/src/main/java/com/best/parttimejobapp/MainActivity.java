package com.best.parttimejobapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
public class MainActivity extends Activity {

    TextView toolbartitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到Toobar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
    }

}
