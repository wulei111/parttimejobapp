package com.best.parttimejobapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText old;
    EditText newmima;
    EditText newmimas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        old = (EditText) findViewById(R.id.oldpsw);
        newmima = (EditText) findViewById(R.id.newpsw);
        newmimas = (EditText) findViewById(R.id.newpsws);
    }

//    点击完成
    public void wancheng(View view){
        String jiumima = old.getText().toString();
        String xinmima = newmima.getText().toString();
        String xinmimas = newmimas.getText().toString();
        if(jiumima.length()==0){
            Toast.makeText(this,"旧密码不能为空",Toast.LENGTH_LONG).show();
        }else{
            if(xinmima.length()==0){
                Toast.makeText(this,"新密码不能为空",Toast.LENGTH_LONG).show();
            }else{
                if(xinmimas.length()==0){
                    Toast.makeText(this,"重复新密码不能为空",Toast.LENGTH_LONG).show();
                }else{
                    if(xinmima.equals(xinmimas)){
                        if(xinmimas.length()>=6 && xinmimas.length()<=18){
                            if(jiumima.equals(xinmima)){
                                Toast.makeText(this,"不能与旧密码一致",Toast.LENGTH_LONG).show();
                            }else{
                                xiugai();
                            }

                        }else{
                            Toast.makeText(this,"密码长度必须大于6位，小于18位",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"密码前后不一致，请重新输入",Toast.LENGTH_LONG).show();
                    }

                }
            }
        }
    }


//    修改密码
    public void xiugai(){
        String jiumima = old.getText().toString();
        String xinmima = newmima.getText().toString();
        BmobUser.updateCurrentUserPassword(this, jiumima, xinmima, new UpdateListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Log.i("smile", "密码修改成功，可以用新密码进行登录啦");
                chenggong();
                Intent intent = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Log.i("smile", "密码修改失败：" + msg + "(" + code + ")");
                if("old password incorrect.".equals(msg)){
                    cuowu();
                }
            }
        });
    }


//    修改成功
    public void chenggong(){
        Toast.makeText(this,"密码修改成功，可以用新密码进行登录啦",Toast.LENGTH_LONG).show();
    }

//    旧密码错误
    public void cuowu(){
        Toast.makeText(this,"旧密码输入错误",Toast.LENGTH_LONG).show();
    }

//    返回
    public void fanhui(View view){
        this.finish();
    }

}
