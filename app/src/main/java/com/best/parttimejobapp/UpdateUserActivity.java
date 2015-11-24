package com.best.parttimejobapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.best.bean.Myuser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateUserActivity extends AppCompatActivity {


    EditText names;
    EditText phone;
    String newname;
    String newphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        BmobUser bu = BmobUser.getCurrentUser(this);
//        Myuser mu = (Myuser) Myuser.getCurrentUser(this);
        String username = bu.getUsername();
        String userphone = bu.getMobilePhoneNumber();
        names = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phones);
        Log.i("asd", username + "");
        names.setText(username);
        phone.setText(userphone);

    }

//    修改
    public void wancheng(View view){
        BmobUser newUser = new BmobUser();
        newname = names.getText().toString();
        newphone = phone.getText().toString();
        newUser.setUsername(newname);
        newUser.setMobilePhoneNumber(newphone);
//        newUser.setEmail("xxx@163.com");
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {

            @Override
            public void onSuccess() {
                chenggong();
            }

            @Override
            public void onFailure(int i, String s) {
                shibai();
                Log.i("shibai",s);
            }
        });
    }

    //    修改成功
    public void chenggong(){
        Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    //    修改失败
    public void shibai(){
        Toast.makeText(this,"失败",Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
    }

    public void fanhui(View view){
        this.finish();
    }
}
