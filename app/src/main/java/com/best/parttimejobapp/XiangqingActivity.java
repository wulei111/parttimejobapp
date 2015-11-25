package com.best.parttimejobapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.best.adapter.IndexAdapter;
import com.best.bean.Invite;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class XiangqingActivity extends AppCompatActivity {
    ListView lists;
    List<Invite> inviteList = new ArrayList<>();
    IndexAdapter indexAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        lists = (ListView) findViewById(R.id.xiangqing);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Invite invite = inviteList.get(position);
                Intent intent = new Intent(XiangqingActivity.this, DetailsActivity.class);
                intent.putExtra("detailstitle", invite.getInvite_title());
                intent.putExtra("detailsaddress", invite.getInvite_address());
                intent.putExtra("detailssex", invite.getIncite_sex());
                intent.putExtra("detailsnum", invite.getInvite_personNum());
                intent.putExtra("detailsmoney", invite.getInvite_money());
                intent.putExtra("detailsdate", invite.getIncite_days());
//                System.out.println("shoujihaoma:"+invite.getInvite_phonenum());
                intent.putExtra("detailsphone", invite.getInvite_phonenum().toString());
                intent.putExtra("detailscompyaddress", invite.getInvite_addresss());
                intent.putExtra("detailsbeizhu", invite.getInvite_context());
//                System.out.println("createAt:"+invite.getCreateAt().getDate());
                intent.putExtra("createAt", invite.getInvite_date());
                startActivity(intent);
            }
        });
        //连接网络
        Bmob.initialize(this, "e4472a3896b975ebe594e9669b07774d");
        BmobQuery<Invite> u = new BmobQuery<>();
        u.findObjects(this, new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {
                inviteList = list;
                indexAdapter = new IndexAdapter(XiangqingActivity.this, inviteList);
                Log.i("ggg", "lkllllll");
                lists.setAdapter(indexAdapter);
            }

            @Override
            public void onError(int i, String s) {
                Log.i("cha", "onError brand数据库错误" + i + s);
            }
        });
    }
}
