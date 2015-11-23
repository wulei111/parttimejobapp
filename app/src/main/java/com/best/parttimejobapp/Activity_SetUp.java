package com.best.parttimejobapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.best.APNMatchTools.ApnSwitchTest;

public class Activity_SetUp extends AppCompatActivity {
    public static final String CMWAP = "cmwap";   //中国移动cmwap
    public static final String CMNET = "cmnet";   //中国移动cmnet
    public static final String GWAP_3 = "3gwap";  //中国联通3gwap
    public static final String GNET_3 = "3gnet";  //中国联通3gnet
    public static final String UNIWAP = "uniwap"; //中国联通uni wap
    public static final String UNINET = "uninet"; //中国联通uni net

    int wifi_condition,night_condition;
    static boolean wifi_switch;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

    }
    public void setup_back_menu(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        this.finish();
    }

    //判断联网后的状态------------------是否wifi联网------------
    public static boolean checkNetworkConnection(Context context)
    {
        final ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi =connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile =connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isAvailable())  //getState()方法是查询是否连接了数据网络
            return true;
        else
            return false;
    }

    //控制联网权限

    public void setup_wifiopen_button(View v){
        wifi_condition++;
        ImageButton wifi = (ImageButton) findViewById(R.id.setup_wifiopen_button);
        if(wifi_condition%2==1){
            wifi.setImageResource(R.drawable.no);
            wifi_switch = false;
            Toast.makeText(this,"关闭仅wifi联网",Toast.LENGTH_SHORT).show();
            ApnSwitchTest ast = new ApnSwitchTest();//关闭移动网络
            ast.getAPNList();
            ast.closeAPN();
        }
        else if(wifi_condition%2==0){
            wifi.setImageResource(R.drawable.off);
            wifi_switch = true;
            Toast.makeText(this,"开启仅wifi联网",Toast.LENGTH_SHORT).show();
            ApnSwitchTest ast = new ApnSwitchTest();//开启移动网络
            ast.getAPNList();
            ast.openAPN();
        }
    }
    public void setup_nightstyle_button(View v){
        night_condition++;
        ImageButton night = (ImageButton) findViewById(R.id.setup_nightstyle_button);
        if(night_condition%2==1){
            night.setImageResource(R.drawable.no);
            Log.i("opensss","sss"+night_condition);
        }
        else if(night_condition%2==0){
            night.setImageResource(R.drawable.off);
            Log.i("opensss", "fff" + night_condition);
        }
    }

}
