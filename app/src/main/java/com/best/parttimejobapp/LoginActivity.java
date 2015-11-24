package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.Myuser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

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
        Bmob.initialize(this, "e4472a3896b975ebe594e9669b07774d");
    }

//    登录
    public void login(View view){
        String userphone = zhanghaos.getText().toString();
        String userpass = mimas.getText().toString();
        BmobUser.loginByAccount(this, userphone, userpass, new LogInListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if(o!=null){
                    chenggong();
                }else{
                    shibai();
                    Log.i("asd",e+"");
                }
            }
        } );
    }


    public void chenggong(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void shibai(){
        Toast.makeText(this,"账号或密码错误",Toast.LENGTH_LONG).show();
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

//    随便看看

    public void look(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
