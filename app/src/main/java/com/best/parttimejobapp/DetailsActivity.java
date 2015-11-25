package com.best.parttimejobapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class DetailsActivity extends AppCompatActivity {
    TextView detailstitle,detailsaddress,detailssex,detailsnum,detailsmoney,detailsdate,detailsphone,detailscompyaddress,detailsbeizhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //详情页面
        setContentView(R.layout.activity_details);
        //报名按钮
        Button button = (Button) findViewById(R.id.detailsbut);
        //找到toolbar页面
        android.support.v7.widget.Toolbar toolbarview = (android.support.v7.widget.Toolbar) findViewById(R.id.details_toolbar);
        //找到toolbar的标题
        TextView toolvartitle = (TextView) toolbarview.findViewById(R.id.toolbartitle);
        //修改toolbar的标题
        toolvartitle.setText("详情");
        //找到toolbar的返回按钮
        ImageButton returnbutton = (ImageButton) toolbarview.findViewById(R.id.toolbarreturn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });
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
//        System.out.println("shoujihao:"+intent.getStringExtra("detailsphone"));
        detailsphone.setText(intent.getStringExtra("detailsphone"));
        detailscompyaddress.setText(intent.getStringExtra("detailscompyaddress"));
        detailsbeizhu.setText(intent.getStringExtra("detailsbeizhu"));
    }


}
