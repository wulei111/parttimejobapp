package com.best.parttimejobapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.best.adapter.Spinner_item_Adapter;
import com.best.bean.Invite;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 创建人：朱芮成
 * 创建时间：11.19
 * 创建内容：发布招聘
 * */

public class RecruitmentActivity extends AppCompatActivity {
    EditText danwei_edittext,time_edittext,didian_edittext,xinzi_edittext,renshu_edittext,beizhu_edittext;
    Spinner leixing_spinner;
    int spinner_position = 0;
    int[] icon = new int[]{R.drawable.yuesao,R.drawable.zhongdiangongs,R.drawable.jiudain,R.drawable.baomu,
        R.drawable.it,R.drawable.liyi,R.drawable.lvyou,R.drawable.motes};
    String[] Classify = new String[]{"月嫂","钟点工","服务员","保姆","IT","礼仪","导游","模特"};
    int[] id = {0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
        danwei_edittext = (EditText) findViewById(R.id.edit_name);
        time_edittext = (EditText) findViewById(R.id.edit_date);
        didian_edittext = (EditText) findViewById(R.id.edit_place);
        xinzi_edittext = (EditText) findViewById(R.id.edit_money);
        renshu_edittext = (EditText) findViewById(R.id.edit_peopleNum);
        beizhu_edittext = (EditText) findViewById(R.id.edit_remark);
        leixing_spinner = (Spinner) findViewById(R.id.edit_classify);
        Spinner_item_Adapter _MyAdapter=new Spinner_item_Adapter(this,icon,Classify);
//绑定Adapter
        leixing_spinner.setAdapter(_MyAdapter);
        leixing_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = Classify[position];
                spinner_position = position;
                Toast.makeText(RecruitmentActivity.this, "你点击的是:" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        BmobQuery<Invite> query = new BmobQuery<Invite>();
//查询Invite_id
        query.addWhereNotEqualTo("Invite_id", "new_id");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
//执行查询方法
        query.findObjects(this, new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {
                for (int i = 0; i < list.size(); i++) {

                    if (id[0] < list.get(i).getInvite_id()) {
                        id[0] = list.get(i).getInvite_id();
                    } else {
                    }
                    Log.i("vfrvbrd", "aed" + list.get(i).getInvite_id() + ":ddddd" + id[0]);
                }
                Log.i("vfrvbrd", "aed" + ":ssssssssssss" + id[0]);
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }



    public void release_recruitment(View v){
        //          进度条
        ProgressDialog pd = null;

        pd = ProgressDialog.show(this,"","正在上传。。。。");
        final boolean[] commend = new boolean[1];
        String context = beizhu_edittext.getText()+"";
        String xinzi_edittexts = xinzi_edittext.getText()+"";
        String invite_times = time_edittext.getText()+"";
        String people_num = renshu_edittext.getText()+"";
        String danwei_edittexts = danwei_edittext.getText()+"";
        Log.i("isswitch","swss"+MainActivity.main_denglu_switch);
//        if(MainActivity.main_denglu_switch){
        Log.i("vfrvbrd","aed"+":ccccccccccccc"+id[0]+context+xinzi_edittexts+invite_times+danwei_edittexts);
        Invite invite = new Invite();
        invite.setClassify_id(spinner_position);
        invite.setInvite_id(id[0]+1);
        invite.setInvite_context(context);
        invite.setInvite_money(Integer.parseInt(xinzi_edittexts));
        invite.setInvite_time(invite_times);
        invite.setInvite_personNum(Integer.parseInt(people_num));
        invite.setInvite_title(danwei_edittexts);
        invite.save(getApplicationContext(), new SaveListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                commend[0] = true;
                id[0]++;
                Log.i("isscuessed", "issuccessed");
                //关闭进度条

            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                // 添加失败
                Log.i("isscuessed","nosuccessed           "+arg0);
                commend[0] = false;
                id[0] = 0;
            }

        });
            pd.dismiss();
//        }
//    else{

        }
//        if(commend[0]){
//        Toast.makeText(this,"发布成功！",Toast.LENGTH_SHORT).show();
//    }
//        else{
//        Toast.makeText(this,"发布失败！",Toast.LENGTH_SHORT).show();}
//    }

}
