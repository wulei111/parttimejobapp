package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText phonenum;
    EditText yanzhengnum;
    int i = 10;
    boolean open = true;
    private int recLen = 60;
    private static Button yanzhengs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        phonenum = (EditText) findViewById(R.id.phone);
        yanzhengnum = (EditText) findViewById(R.id.yanzheng);
        yanzhengs = (Button) findViewById(R.id.yanzhenbutton);
    }
    //    发送验证码
    public void fasong(View view){

        open = true;
        //        判断有效手机号
        String shoujinum = phonenum.getText().toString();
        if(shoujinum.length()==0){
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_LONG).show();
        }else{
            if(shoujinum.length()==11){
                yanzheng();
                recLen=60;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (open){
                            try {
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = recLen ;
                                if(recLen>0) {
                                    Thread.sleep(1000);
                                    hans.sendMessage(msg);
                                }else{
                                    hans.sendMessage(msg);
                                    open = false;
                                }
                                recLen--;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }else{
                Toast.makeText(this,"请填写有效手机号码",Toast.LENGTH_LONG).show();
            }
        }

    }
    private Handler hans = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if ((int) msg.obj > 0) {
                    yanzhengs.setEnabled(false);
                    yanzhengs.setText(String.valueOf(msg.obj + "s后重新发送"));
                    yanzhengs.setBackgroundResource(R.drawable.morens);
                } else {
                    yanzhengs.setEnabled(true);
                    yanzhengs.setText("发送验证码");
                    yanzhengs.setBackgroundResource(R.drawable.anniu01);
                    yanzheng();

                }
            }
            super.handleMessage(msg);
        }
    };
    //    发送验证码
    public void yanzheng(){
        String shoujinum = phonenum.getText().toString();
        BmobSMS.requestSMSCode(this, shoujinum, "手机登录模板", new RequestSMSCodeListener() {

            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    Log.i("smile", "短信id：" + integer);//用于后续的查询本次短信发送状态
                } else {
                    Log.i("smile", "短信id：" + integer);//用于后续的查询本次短信发送状态
                }
            }
        });

    }

    //    登录
    public void denglu(View view){
        final String yanshangma = yanzhengnum.getText().toString();
        String shoujinum = phonenum.getText().toString();
        BmobUser.loginBySMSCode(this, shoujinum, yanshangma, new LogInListener<Object>() {

            @Override
            public void done(Object o, BmobException e) {
                if (o != null) {
                    Intent intent = new Intent(ForgetPasswordActivity.this, ChangePasswordActivity.class);
                    startActivity(intent);
                } else {
                    if (yanshangma.length() == 0) {
                        nonull();
                    } else {
                        cuowu();
                    }

                }
            }

        });

    }
    public void nonull(){
        Toast.makeText(this,"验证码不能为空",Toast.LENGTH_LONG).show();
    }
    public void cuowu(){
        Toast.makeText(this,"验证码输入错误",Toast.LENGTH_LONG).show();
    }


}
