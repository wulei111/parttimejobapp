

package com.best.parttimejobapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

public class ValidateLoginActivity extends AppCompatActivity {
    int i = 10;
    boolean open = true;
    EditText phones;
    EditText yanzheng;
    private int recLen = 60;
    private static Button txtView;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validatelogin);
        phones = (EditText) findViewById(R.id.phonenum);
        yanzheng = (EditText) findViewById(R.id.yanzhengs);
        txtView = (Button)findViewById(R.id.anniu);
    }

    //    发送验证码
    public void fasong(View view){
        Toast.makeText(this,"denglu",Toast.LENGTH_SHORT).show();
        open = true;
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
    }
    private Handler hans = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if((int)msg.obj>0) {
                    txtView.setEnabled(false);
                    txtView.setText(String.valueOf(msg.obj + "s后重新发送"));
                }else {
                    txtView.setEnabled(true);
                    txtView.setText("发送验证码");
                }
            }
            super.handleMessage(msg);
        }
    };

    //    登录
    public void denglu(View view){
        String yanshangma = yanzheng.getText().toString();
        String shoujinum = phones.getText().toString();
        BmobUser.loginBySMSCode(this, shoujinum, yanshangma, new LogInListener<Object>() {

            @Override
            public void done(Object o, BmobException e) {
                if (o != null) {
                    Intent intent = new Intent(ValidateLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.i("asd",e+"");
                }
            }

        });

    }



}
