package com.best.parttimejobapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_SetUp extends AppCompatActivity {
    int wifi_condition,night_condition;
    public static boolean wifi_switch = false;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        boolean open = isNetworkAvailable(this);
        ImageButton wifi = (ImageButton) findViewById(R.id.setup_wifiopen_button);
        if(wifi_switch){
            wifi.setImageResource(R.drawable.no);
            wifi_condition=3;
            if(open){ LoginActivity.network_switch ="0";}
        }
        else if(!(wifi_switch)){
            wifi.setImageResource(R.drawable.off);
            wifi_condition=4;
            LoginActivity.network_switch ="e4472a3896b975ebe594e9669b07774d";
        }
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
        boolean open = isNetworkAvailable(this);
        ImageButton wifi = (ImageButton) findViewById(R.id.setup_wifiopen_button);
        if(wifi_condition%2==1){
            wifi.setImageResource(R.drawable.no);
            wifi_switch = true;
            Toast.makeText(this,"开启仅wifi联网",Toast.LENGTH_SHORT).show();
            if(open){ LoginActivity.network_switch ="0";}
            Log.i("svsdvsdr", "accccdceas"+open);
        }
        else if(wifi_condition%2==0){
            wifi.setImageResource(R.drawable.off);
            wifi_switch = false;
            Toast.makeText(this,"关闭仅wifi联网",Toast.LENGTH_SHORT).show();
            LoginActivity.network_switch ="e4472a3896b975ebe594e9669b07774d";
            Log.i("svsdvsdr", "adcewwwwas" + open);
        }
    }

    public void setup_nightstyle_button(View v){
        night_condition++;
        ImageButton night = (ImageButton) findViewById(R.id.setup_nightstyle_button);
        if(night_condition%2==1){
            night.setImageResource(R.drawable.no);
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("background_style", "during");
            startActivity(i);
        }
        else if(night_condition%2==0){
            night.setImageResource(R.drawable.off);
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("background_style", "night");
            startActivity(i);
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
