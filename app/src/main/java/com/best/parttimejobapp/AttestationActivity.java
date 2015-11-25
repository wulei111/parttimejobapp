package com.best.parttimejobapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vonte on 2015/11/19.
 */
public class AttestationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //公司认证页面
        setContentView(R.layout.activity_attestation);
        //找到toolbar页面
        Toolbar toolbarview = (Toolbar) findViewById(R.id.toolbar);
        //找到toolbar的标题
        TextView toolvartitle = (TextView) toolbarview.findViewById(R.id.toolbartitle);
        //修改toolbar的标题
        toolvartitle.setText("公司页面");
        Toast.makeText(getApplicationContext(),"aaaaaaa",Toast.LENGTH_SHORT).show();
    }
}
