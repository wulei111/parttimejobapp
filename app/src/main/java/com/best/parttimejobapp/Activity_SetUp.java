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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            wifi_switch = true;
            Toast.makeText(this,"开启仅wifi联网",Toast.LENGTH_SHORT).show();
            setMobileDataStatus(this, wifi_switch);
            boolean open = isNetworkAvailable(this);
            Log.i("svsdvsdr", "accccdceas"+open);
        }
        else if(wifi_condition%2==0){
            wifi.setImageResource(R.drawable.off);
            wifi_switch = false;
            Toast.makeText(this,"关闭仅wifi联网",Toast.LENGTH_SHORT).show();
            setMobileDataStatus(this, wifi_switch);
            boolean open = isNetworkAvailable(this);
            Log.i("svsdvsdr", "adcewwwwas" + open);
        }
    }
    //联网控制
    public void setMobileDataStatus(Context context, boolean enabled)

    {

        ConnectivityManager conMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // ConnectivityManager类
        Class<?> conMgrClass = null;
        // ConnectivityManager类中的字段
        Field iConMgrField = null;
        // IConnectivityManager类的引用
        Object iConMgr = null;
        // IConnectivityManager类
        Class<?> iConMgrClass = null;
        // setMobileDataEnabled方法
        Method setMobileDataEnabledMethod = null;
        try {
            // 取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            // 取得ConnectivityManager类中的对象Mservice
            iConMgrField = conMgrClass.getDeclaredField("mService");
            // 设置mService可访问
            iConMgrField.setAccessible(true);
            // 取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            // 取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());
            // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
            setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
                    "setMobileDataEnabled", Boolean.TYPE);
            // 设置setMobileDataEnabled方法是否可访问
            setMobileDataEnabledMethod.setAccessible(true);
            // 调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConMgr, enabled);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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
