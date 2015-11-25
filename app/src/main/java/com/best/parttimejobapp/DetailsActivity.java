package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView detailstitle,detailsaddress,detailssex,detailsnum,detailsmoney,detailsdate,detailsphone,detailscompyaddress,detailsbeizhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //详情页面
        setContentView(R.layout.activity_details);
        //找到toolbar页面
        android.support.v7.widget.Toolbar toolbarview = (android.support.v7.widget.Toolbar) findViewById(R.id.details_toolbar);
        //找到toolbar的标题
        TextView toolvartitle = (TextView) toolbarview.findViewById(R.id.toolbartitle);
        //修改toolbar的标题
        toolvartitle.setText("详情");
        //标题
        detailstitle = (TextView) findViewById(R.id.detailstitle);
        detailsaddress = (TextView) findViewById(R.id.detailsaddress);
        detailssex = (TextView) findViewById(R.id.detailssex);
        detailsnum = (TextView) findViewById(R.id.detailsnum);
        detailsmoney = (TextView) findViewById(R.id.detailsmoney);
        detailsdate = (TextView) findViewById(R.id.detailsdate);
        detailsphone = (TextView) findViewById(R.id.detailsphone);
        detailscompyaddress = (TextView) findViewById(R.id.detailscompyaddress);
        detailsbeizhu = (TextView) findViewById(R.id.detailsbeizhu);
        Intent intent = getIntent();
        detailstitle.setText(intent.getStringExtra("detailstitle"));
        detailsaddress.setText(intent.getStringExtra("detailsaddress"));
        detailssex.setText(intent.getStringExtra("detailssex"));
        detailsnum.setText(intent.getStringExtra("detailsnum"));
        detailsmoney.setText(intent.getStringExtra("detailsmoney"));
        detailsdate.setText(intent.getStringExtra("createAt"));
        detailsphone.setText(intent.getStringExtra("detailsphone"));
        detailscompyaddress.setText(intent.getStringExtra("detailscompyaddress"));
        detailsbeizhu.setText(intent.getStringExtra("detailsbeizhu"));
    }


}
