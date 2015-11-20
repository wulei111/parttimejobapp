package com.best.parttimejobapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;

public class MainActivity extends Activity implements View.OnClickListener{
    private SlideMenu slideMenu;
    TextView toolbartitle;
    ImageView touxiang;
    Button slide_wode_button = null,slide_fabu_button = null,slide_shezhi_button = null,slide_yijian_button = null,slide_about_button = null,slide_exit_button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slide_wode_button = (Button)findViewById(R.id.slide_wode_button);
        slide_fabu_button = (Button)findViewById(R.id.slide_fabu_button);
        slide_shezhi_button = (Button)findViewById(R.id.slide_shezhi_button);
        slide_yijian_button = (Button)findViewById(R.id.slide_yijian_button);
        slide_about_button = (Button) findViewById(R.id.slide_about_button);
        slide_exit_button = (Button) findViewById(R.id.slide_exit_button);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        //找到Toobar
        slide_wode_button.setOnClickListener(this);
        slide_fabu_button.setOnClickListener(this);
        slide_shezhi_button.setOnClickListener(this);
        slide_yijian_button.setOnClickListener(this);
        slide_about_button.setOnClickListener(this);
        slide_exit_button.setOnClickListener(this);
        touxiang.setOnClickListener(this);
        slide();

        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if(bmobUser == null){
            slide_exit_button.setVisibility(View.GONE);
        }
    }
   public void slide(){
       //主页面中toolbar按钮监听。。。。。
       slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
       Button toobarbutton = (Button) findViewById(R.id.toolbar_button);
       toobarbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId()) {
                   case R.id.toolbar_button:
                       if (slideMenu.isMainScreenShowing()) {
                           slideMenu.openMenu();
                       } else {
                           slideMenu.closeMenu();
                       }
                       break;
               }
           }
       });
       //侧滑边栏中切换求职和招聘
       RadioGroup radioGroupi = (RadioGroup) findViewById(R.id.slide_radiogroup);

       radioGroupi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RadioButton radioButton_qiuzhi = (RadioButton) findViewById(R.id.slide_qiuzhi_radiobutton);
               RadioButton radioButton_zhaopin = (RadioButton) findViewById(R.id.slide_zhaopin_radiobutton);
               switch (v.getId()) {
                   case R.id.slide_qiuzhi_radiobutton:
                       radioButton_zhaopin.setChecked(true);
                       radioButton_qiuzhi.setChecked(false);
                       break;
                   case R.id.slide_zhaopin_radiobutton:
                       radioButton_qiuzhi.setChecked(true);
                       radioButton_zhaopin.setChecked(false);
                       break;
               }
           }
       });
   }

    @Override
    public void onClick(View v) {

        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if(bmobUser != null){
            int button_id = v.getId();
            //侧边栏监听判断并反应
            if(button_id==R.id.slide_wode_button){
                Toast.makeText(this,"我的足迹",Toast.LENGTH_SHORT).show();
            }
            else if(button_id==R.id.slide_fabu_button){
                Toast.makeText(this,"我要发布",Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this,Activity_MyPublish.class);
//            startActivity(i);
                this.finish();
            }
            else if(button_id==R.id.slide_shezhi_button){
                Toast.makeText(this,"我的设置",Toast.LENGTH_SHORT).show();
            }
            else if(button_id==R.id.slide_yijian_button){
                Toast.makeText(this,"意见反馈",Toast.LENGTH_SHORT).show();
            }
            else if(button_id==R.id.slide_about_button){
                Toast.makeText(this,"关于我们",Toast.LENGTH_SHORT).show();
            }
            else if(button_id==R.id.slide_exit_button){
                BmobUser.logOut(this);   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(this); // 现在的currentUser是null了
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            //    侧边栏修改资料
            else if(button_id==R.id.touxiang){
                Intent intent = new Intent(this,UpdateUserActivity.class);
                startActivity(intent);
            }
        }else{
            Toast.makeText(this,"亲，还没登录哦！",Toast.LENGTH_SHORT).show();

        }


    }


}
