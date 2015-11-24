package com.best.parttimejobapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.best.bean.Myuser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class RegisteredActivity extends AppCompatActivity {

    public EditText username;
    public EditText userphone;
    public EditText userpassword;
    public EditText userpasswords;
    public EditText yanzheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        Bmob.initialize(this, "e4472a3896b975ebe594e9669b07774d");

        username = (EditText) findViewById(R.id.name);
        userphone = (EditText) findViewById(R.id.phone);
        userpassword = (EditText) findViewById(R.id.pass);
        userpasswords = (EditText) findViewById(R.id.passw);
        yanzheng = (EditText) findViewById(R.id.yanzhengs);

    }

    public void zhuce(View view){
        //        获取输入的值
        String phone = userphone.getText().toString();
        String names = username.getText().toString();
        String pass = userpassword.getText().toString();
        String passw = userpasswords.getText().toString();
        String yanzhengs = yanzheng.getText().toString();

//        判断密码书否为空
        if(pass.length()!=0){
            //        判断密码输入是否一致
            if(pass.equals(passw)){

//            判断密码位数
                if(pass.length()>=6 && pass.length()<=18){


//                判断用户名是否为空
                    if(names.length()!=0){
                        if(names.length()>=2){
                            zhuces();
                        }else{
                            Toast.makeText(this,"姓名必须两个字以上",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"姓名不能为空",Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(this,"密码长度必须大于6位，小于18位",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this,"密码前后不一致，请重新输入",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
        }

    }

    //    注册
    public void zhuces(){
        String phone = userphone.getText().toString();
        String names = username.getText().toString();
        String pass = userpassword.getText().toString();
        String passw = userpasswords.getText().toString();
        String yanzhengs = yanzheng.getText().toString();
        BmobUser mu = new BmobUser();
        Myuser mm = new Myuser();
        mu.setUsername(names);
        mu.setPassword(passw);
//            mu.setEmail(phone);
        mu.setMobilePhoneNumber(phone);
        Log.i("ceshi",names+""+passw);
//注意：不能用save方法进行注册
        mu.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
//                toast("注册成功:");
                chenggong();
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
//                toast("注册失败:"+msg);
                Log.i("ceshi", msg);
                if("mobilePhoneNumber Must be valid mobile number".equals(msg)){
                    phones();
                    return;
                }
                shibai();
            }
        });
    }

    //    注册成功
    public void chenggong(){
        Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    //    注册失败
    public void shibai(){
        Toast.makeText(this,"失败",Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
    }

    public void phones(){
        Toast.makeText(this,"请填写有效手机号码", Toast.LENGTH_LONG).show();
    }

}
