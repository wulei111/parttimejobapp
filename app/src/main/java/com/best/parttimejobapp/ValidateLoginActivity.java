//package com.best.parttimejobapp;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.content.Intent;
//import android.widget.TextView;
//
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import cn.bmob.v3.BmobSMS;
//import cn.bmob.v3.BmobUser;
//import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.LogInListener;
//import cn.bmob.v3.listener.RequestSMSCodeListener;
//
//public class ValidateLoginActivity extends AppCompatActivity {
//    int i = 10;
//    boolean open = true;
//    EditText phones;
//    EditText yanzheng;
//    private int recLen = 5;
//    private Button txtView;
//    Timer timer = new Timer();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_validatelogin);
//        phones = (EditText) findViewById(R.id.phonenum);
//        yanzheng = (EditText) findViewById(R.id.yanzhengs);
//        txtView = (Button)findViewById(R.id.anniu);
//    }
//
////    发送验证码
//    public void fasong(View view){
//        if(i==0){
//            open=true;
//            kaishi(true);
//            txtView.setClickable(true);
//            i=10;
////        //    发送验证码
////            String shoujinum = phones.getText().toString();
////            BmobSMS.requestSMSCode(this, shoujinum, "手机登录模板", new RequestSMSCodeListener() {
////                @Override
////                public void done(Integer integer, BmobException e) {
////                    if (e == null) {//验证码发送成功
////                        Log.i("smile", "短信id：" + integer);//用于后续的查询本次短信发送状态
////                    } else {
////                        Log.i("smile", "短信id：" + integer);//用于后续的查询本次短信发送状态
////                    }
////                }
////            });
//
//        }
////        倒计时
//        else if(i>0){
//            txtView.setClickable(false);
//            Thread th = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    txtView.setFocusable(false);
//                    while (i>1){
//                        txtView.setFocusable(false);
//                        i--;
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.obj = i ;
//                        hans.sendMessage(msg);
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//            th.start();
//        }
//    }
//    public void kaishi(boolean isopen){
//        txtView.setFocusable(isopen);
//    }
//    private Handler hans = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//// TODO 接收消息并且去更新UI线程上的控件内容
//            if (msg.what == 1) {
//                txtView.setText(String.valueOf(msg.obj));
//                if(msg.obj.toString().equals("2")){
//                    txtView.setFocusable(true);}
//                if(msg.obj.toString().equals("1")){
//                    txtView.setText("点击，重新发送！");
//                }
//            }
//            super.handleMessage(msg);
//        }
//    };
//
////    登录
//    public void denglu(View view){
//        String yanshangma = yanzheng.getText().toString();
//        String shoujinum = phones.getText().toString();
//        BmobUser.loginBySMSCode(this, shoujinum, yanshangma, new LogInListener<Object>() {
//
//            @Override
//            public void done(Object o, BmobException e) {
//                if (o != null) {
//                    Intent intent = new Intent(ValidateLoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Log.i("asd",e+"");
//                }
//            }
//
//        });
//
//    }
//
//
//
//}
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
