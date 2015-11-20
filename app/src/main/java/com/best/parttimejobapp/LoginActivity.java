package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 创建时间：2015-11-18
 * 创建人：李广兴
 * 创建内容：LoginActivity
 * */

public class LoginActivity extends AppCompatActivity {

    public TextView duanxin;
    public Button denglu;
    public EditText zhanghaos;
    public EditText mimas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        duanxin = (TextView) findViewById(R.id.duanxin);
        denglu = (Button) findViewById(R.id.login );
        zhanghaos = (EditText) findViewById(R.id.zhanghao);
        mimas = (EditText) findViewById(R.id.mima);
    }

//    登录
    public void login(View view){
        String usernum = zhanghaos.getText().toString();
        String userpass = mimas.getText().toString();
    }

//    短信验证登录
    
    public void duanxinlogin(View view){
        Intent intent = new Intent(this,ValidateLoginActivity.class);
        startActivity(intent);
    }

//    注册
    public void registere(View view){
        Intent intent = new Intent(this,RegisteredActivity.class);
        startActivity(intent);
    }
}
