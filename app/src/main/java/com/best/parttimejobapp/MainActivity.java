package com.best.parttimejobapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.best.fragment.IndexFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView toolbartitle;
    android.support.v4.app.FragmentManager fm;
    RadioButton fenclass,index,seeat;
    TextView Ttv;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
//设置是否返回地址信息（默认返回地址信息）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到Toobar
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        //seeat = (RadioButton)findViewById(R.id.seeat);
        //  fenclass = (RadioButton) findViewById(R.id.fenclass);
        index = (RadioButton) findViewById(R.id.index);
        /**
         * 定位
         *
         * */
        Ttv = (TextView) findViewById(R.id.address);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        Log.i("vv","fffffffff");
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    Log.i("vv","走了");
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        Log.i("vv", amapLocation.getLatitude() + "");
                        amapLocation.getLatitude();//获取经度
                        Log.i("vv", amapLocation.getLongitude() + "");
                        amapLocation.getLongitude();//获取纬度
                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        //  mLocationOption.setNeedAddress(true);
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        amapLocation.getCity();//城市信息
                        Log.i("vv", amapLocation.getCity() + "");
                        amapLocation.getDistrict();//城区信息
                        amapLocation.getRoad();//街道信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        Log.i("AmapError", amapLocation.getDistrict() + df.format(date) + amapLocation.getCity());
                        String s = amapLocation.getCity();
                        Ttv.setText(s);
                    } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                      Log.i("AmapError", "location Error, ErrCode:"
                             + amapLocation.getErrorCode() + ", errInfo:"
                             + amapLocation.getErrorInfo());
                      }
                }
            }
        });
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(10000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
//         seeat.setOnClickListener(this);
     //   fenclass.setOnClickListener(this);
        index.setOnClickListener(this);
        fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            Log.i("vv",fm.beginTransaction()+"");
            android.support.v4.app.FragmentTransaction ftt = fm.beginTransaction();
            IndexFragment inf = new IndexFragment();
            ftt.add(R.id.fragment_parent, inf, "index");
            ftt.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction ftt = fm.beginTransaction();
        if (fm.findFragmentByTag("index")!= null){
            ftt.hide(fm.findFragmentByTag("index"));
        }
        if (fm.findFragmentByTag("fenclass")!= null){
            ftt.hide(fm.findFragmentByTag("fenclass"));
        }
        if (fm.findFragmentByTag("index")!= null){
            ftt.hide(fm.findFragmentByTag("index"));
        }
        if (fm.findFragmentByTag("seeat")!= null){
            ftt.hide(fm.findFragmentByTag("seeat"));
        }
        int id = v.getId();
        if (id == R.id.index) {
            if (fm.findFragmentByTag("index") != null) {
                ftt.show(fm.findFragmentByTag("index"));
            } else {
                IndexFragment pf = new IndexFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, pf, "index");
            }
        }else if (id == R.id.fenclass){
            if (fm.findFragmentByTag("fenclass")!=null){
                ftt.show(fm.findFragmentByTag("fenclass"));
            }else{
              //  FenClassFragment af = new FenClassFragment();
                //add(父布局ID，Fragment，Tag);
             //   ftt.add(R.id.fragment_parent, af, "fenclass");
            }
        }else if (id == R.id.seeat){
            if (fm.findFragmentByTag("seeat")!=null){
                ftt.show(fm.findFragmentByTag("seeat"));
            }else{
            //    SeeAtFragment af = new SeeAtFragment();
                //add(父布局ID，Fragment，Tag);
            //    ftt.add(R.id.fragment_parent,af,"seeat");
            }
        }

        ftt.commit();
    }
}
