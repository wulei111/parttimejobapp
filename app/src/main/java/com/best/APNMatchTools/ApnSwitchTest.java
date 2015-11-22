package com.best.APNMatchTools;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class ApnSwitchTest extends Activity
{

    Uri uri = Uri.parse("content://telephony/carriers/preferapn");

    // 开启APN
    public void openAPN()
    {
        List<APN> list = getAPNList();
        for (APN apn : list)
        {
            ContentValues cv = new ContentValues();

            // 获取及保存移动或联通手机卡的APN网络匹配
            cv.put("apn", APNMatchTools.matchAPN(apn.apn));
            cv.put("type", APNMatchTools.matchAPN(apn.type));

            // 更新系统数据库，改变移动网络状态
            getContentResolver().update(uri, cv, "_id=?", new String[]
                    {
                            apn.id
                    });
        }

    }

    // 关闭APN
    public void closeAPN()
    {
        List<APN> list = getAPNList();
        for (APN apn : list)
        {
            // 创建ContentValues保存数据
            ContentValues cv = new ContentValues();
            // 添加"close"匹配一个错误的APN，关闭网络
            cv.put("apn", APNMatchTools.matchAPN(apn.apn) + "close");
            cv.put("type", APNMatchTools.matchAPN(apn.type) + "close");

            // 更新系统数据库，改变移动网络状态
            getContentResolver().update(uri, cv, "_id=?", new String[]
                    {
                            apn.id
                    });
        }
    }

    public static class APN
    {
        String id;
        String apn;
        String type;
    }

    public List<APN> getAPNList()
    {
        // current不为空表示可以使用的APN
        String projection[] =
                {
                        "_id, apn, type, current"
                };
        // 查询获取系统数据库的内容
        Cursor cr = getContentResolver().query(uri, projection, null, null, null);

        // 创建一个List集合
        List<APN> list = new ArrayList<>();

        while (cr != null && cr.moveToNext())
        {

            Log.d("ApnSwitch", "id" + cr.getString(cr.getColumnIndex("_id")) + " \n" + "apn"
                    + cr.getString(cr.getColumnIndex("apn")) + "\n" + "type"
                    + cr.getString(cr.getColumnIndex("type")) + "\n" + "current"
                    + cr.getString(cr.getColumnIndex("current")));

            APN a = new APN();
            a.id = cr.getString(cr.getColumnIndex("_id"));
            a.apn = cr.getString(cr.getColumnIndex("apn"));
            a.type = cr.getString(cr.getColumnIndex("type"));
            list.add(a);
        }

        if (cr != null)
            cr.close();
        return list;
    }

}
