package com.best.parttimejobapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import com.best.bean.Myuser;

import com.best.bean.User;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.squareup.picasso.Picasso;

import android.os.AsyncTask;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import me.nereo.imagechoose.MultiImageSelectorActivity;

public class UpdateUserActivity extends AppCompatActivity {


    EditText names;
    EditText phone;
    EditText email;
    EditText qqs;
    String newname;
    String newphone;
    String newqq;
    String newemail;
    String ID;
    String imageurl;
    private static final int REQUEST_IMAGE = 2;
    private String mFilePath = null;
    ImageView touxiangs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        BmobUser bu = BmobUser.getCurrentUser(this);
        ID = bu.getObjectId();
        Myuser mu = new Myuser();
//        mu.setUser_image();
        String userimage = mu.getUser_image();
        BmobQuery<Myuser> query = new BmobQuery<Myuser>();
        query.getObject(this, ID, new GetListener<Myuser>() {

            @Override
            public void onSuccess(Myuser object) {
                // TODO Auto-generated method stub

                //获得playerName的信息
                imageurl = object.getUser_image();
                Log.i("chaxun", imageurl + "aaaaaaaaaaaaaaaaaaaaaa");
                Picasso.with(getApplication()).load(object.getUser_image()).into(touxiangs);
                Log.i("coe", object.getUser_image().toString());

                //获得数据的objectId信息
                object.getObjectId();
                //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                object.getCreatedAt();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                Log.i("chaxun", "查询失败：" + arg0);
            }
        });
        Log.i("asd", ID + "URL" + userimage);
        String username = bu.getUsername();
        String userphone = bu.getMobilePhoneNumber();
        touxiangs = (ImageView) findViewById(R.id.touxiang);
        String useremail = bu.getEmail();
        names = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phones);
        email = (EditText) findViewById(R.id.email);
        Log.i("asd", username + "");
        names.setText(username);
        phone.setText(userphone);
        email.setText(useremail);
//        qqs.setText(userqq);
    }

    //    修改
    public void wancheng(View view){
        touxiang();
        BmobUser newUser = new BmobUser();
        Myuser mu = new Myuser();
        newname = names.getText().toString();
        newphone = phone.getText().toString();
//        newqq = qqs.getText().toString();
        newemail=email.getText().toString();
        newUser.setUsername(newname);
        newUser.setMobilePhoneNumber(newphone);
//        mu.setUser_qq(newqq);
        newUser.setEmail(newemail);

        BmobUser bmobUser = BmobUser.getCurrentUser(this);

        newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {

            @Override
            public void onSuccess() {
                chenggong();
            }

            @Override
            public void onFailure(int i, String s) {
                shibai();
                Log.i("shibai", s);
            }
        });
    }




    //    上传头像
    public  void touxiang(){
        //图片
        Bmob.initialize(this, "e4472a3896b975ebe594e9669b07774d");
        BmobProFile.getInstance(getApplication()).upload(mFilePath, new UploadListener() {

            @Override
            public void onError(int i, String s) {
                Log.i("asd", s);
            }

            @Override
            public void onSuccess(String s, String s1, BmobFile bmobFile) {
                Myuser user = new Myuser();
                Log.i("dizhi", bmobFile.getUrl());
                user.setUser_image(bmobFile.getUrl());
                user.update(getApplication(), ID, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.i("asd","成共");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.i("shibai",s);
                    }
                });
            }
            @Override
            public void onProgress(int i) {
            }
        });
    }

    //  修改头像
    public void genghuan(View view){
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);

        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        Log.i("xuanze", "zoudaole");

        startActivityForResult(intent, REQUEST_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                mFilePath = path.get(0);
                Log.i("dizhi",mFilePath);
                touxiangs.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
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

    //    修改密码
    public void xiugaimima(View view){
        Intent intent = new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void fanhui(View view){
        this.finish();
    }
}



