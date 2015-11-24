package com.best.fragment;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.best.adapter.QQListAdapter;
import com.best.adapter.SearchListAdapter;
import com.best.bean.Invite;
import com.best.parttimejobapp.R;
import com.best.util.QQListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 创建人：张孝帅
 * 创建时间：11.19
 * 创建内容：搜索
 */
public class SearchFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    //适配器
    public SearchListAdapter searchListAdapter;
    ListView listView;
    //查询数据,
    List<Invite> invites = new ArrayList<>();
    //搜索条件,时间，人数，薪资,搜索关键字
    public String search_time = null,search_people = null,search_money = null,search_main=null;
    //搜索按钮
    Button button_search;

    //全局布局
    public View view;
    ImageView imageview01,imageview02,imageview03;
    RelativeLayout fu1,fu2,fu3;//父列表
    Boolean IsF1 = false;
    Boolean IsF2 = false;
    Boolean IsF3 = false;
    LinearLayout zi1,zi2,zi3;//子列表
    //选择时间 单选按钮
    RadioButton btn_s1,btn_s2,btn_s3,btn_s4,btn_s5,btn_s6;
    RadioGroup grp_s1,grp_s2;
    //选择人数
    RadioButton btn_r1,btn_r2,btn_r3,btn_r4,btn_r5,btn_r6;
    RadioGroup grp_r1,grp_r2;
    //选择薪资
    RadioButton btn_x1,btn_x2,btn_x3,btn_x4,btn_x5,btn_x6;
    RadioGroup grp_x1,grp_x2;
    //选中的按钮
    RadioButton radioButton01,radioButton02,radioButton03;
    boolean changeGroup = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search,container,false);

        imageview01 = (ImageView) view.findViewById(R.id.image01);
        imageview02 = (ImageView) view.findViewById(R.id.image02);
        imageview03 = (ImageView) view.findViewById(R.id.image03);
        fu1 = (RelativeLayout) view.findViewById(R.id.choose_fu01);
        fu2 = (RelativeLayout) view.findViewById(R.id.choose_fu02);
        fu3 = (RelativeLayout) view.findViewById(R.id.choose_fu03);
        zi1 = (LinearLayout) view.findViewById(R.id.choose_zi01);
        zi2 = (LinearLayout) view.findViewById(R.id.choose_zi02);
        zi3 = (LinearLayout) view.findViewById(R.id.choose_zi03);
        listView = (ListView) view.findViewById(R.id.listview_search);
        //获取单选按钮
        grp_s1 = (RadioGroup) view.findViewById(R.id.radioshijian01);
        grp_s2 = (RadioGroup) view.findViewById(R.id.radioshijian02);
        grp_r1 = (RadioGroup) view.findViewById(R.id.radioren01);
        grp_r2 = (RadioGroup) view.findViewById(R.id.radioren02);
        grp_x1 = (RadioGroup) view.findViewById(R.id.radioxin01);
        grp_x2 = (RadioGroup) view.findViewById(R.id.radioxin02);
        button_search = (Button) view.findViewById(R.id.but_search);

        //radiogroup的合并
        grp_s1.setOnCheckedChangeListener(this);
        grp_s2.setOnCheckedChangeListener(this);
        grp_r1.setOnCheckedChangeListener(this);
        grp_r2.setOnCheckedChangeListener(this);
        grp_x1.setOnCheckedChangeListener(this);
        grp_x2.setOnCheckedChangeListener(this);


        //第1个父列表的监听
        fu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsF1) {
                    //隐藏子列表
                    imageview01.setImageResource(R.drawable.btn_browser);
                    zi1.setVisibility(View.GONE);
                    IsF1 = false;
                } else {
                    //显示子列表
                    imageview01.setImageResource(R.drawable.btn_browser2);
                    zi1.setVisibility(View.VISIBLE);
                    IsF1 = true;
                }
            }
        });
        //第2个父列表的监听
        fu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsF2) {
                    //隐藏子列表
                    imageview02.setImageResource(R.drawable.btn_browser);
                    zi2.setVisibility(View.GONE);
                    IsF2 = false;
                } else {
                    //显示子列表
                    imageview02.setImageResource(R.drawable.btn_browser2);
                    zi2.setVisibility(View.VISIBLE);
                    IsF2 = true;
                }
            }
        });
        //第3个父列表的监听
        fu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsF3) {
                    //隐藏子列表
                    imageview03.setImageResource(R.drawable.btn_browser);
                    zi3.setVisibility(View.GONE);
                    IsF3 = false;
                } else {
                    //显示子列表
                    imageview03.setImageResource(R.drawable.btn_browser2);
                    zi3.setVisibility(View.VISIBLE);
                    IsF3 = true;
                }
            }
        });

        //点击搜索
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKu();
                Toast.makeText(getContext(), "查询了" + invites.size() + "个数据" + search_time + "--" + search_people + "--" + search_money, Toast.LENGTH_SHORT).show();

//listview添加
                searchListAdapter = new SearchListAdapter(getContext(), invites);
                listView.setAdapter(searchListAdapter);
            }
        });


        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (!changeGroup){
            changeGroup = true;
            if (group == grp_s1){
                grp_s2.clearCheck();
                //得到选中的按钮
                radioButton01 = (RadioButton) view.findViewById(checkedId);
                search_time = radioButton01.getText().toString();
            }else if (group == grp_s2){
                grp_s1.clearCheck();
                //1和2是同一个组
                radioButton01 = (RadioButton) view.findViewById(checkedId);
                search_time = radioButton01.getText().toString();
            }
            if (group == grp_r1){
                grp_r2.clearCheck();
                radioButton02 = (RadioButton) view.findViewById(checkedId);
                search_people = radioButton02.getText().toString();
            }else if (group == grp_r2){
                grp_r1.clearCheck();
                radioButton02 = (RadioButton) view.findViewById(checkedId);
                search_people = radioButton02.getText().toString();
            }
            if (group == grp_x1){
                grp_x2.clearCheck();
                radioButton03 = (RadioButton) view.findViewById(checkedId);
                search_money = radioButton03.getText().toString();
            }else if (group == grp_x2){
                grp_x1.clearCheck();
                radioButton03 = (RadioButton) view.findViewById(checkedId);
                search_money = radioButton03.getText().toString();
            }
            changeGroup = false;
        }

    }
    //查询方法
    public void searchKu(){
        //添加数据
        Bmob.initialize(getContext(), "e4472a3896b975ebe594e9669b07774d");
        BmobQuery<Invite> inviteBmobQuery = new BmobQuery<Invite>();
//        查询playerName叫“比目”的数据
//        query.addWhereEqualTo("playerName", "比目");
//        返回50条数据，如果不加上这条语句，默认返回10条数据
//        query.setLimit(50);
        inviteBmobQuery.findObjects(getContext(), new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {
                invites.clear();
                for (Invite b : list) {
                    invites.add(new Invite(b.getInvite_id(),b.getClassify_id(),b.getUser_id(),b.getInvite_title(),b.getInvite_time(),
                            b.getInvite_address(),b.getInvite_money(),b.getInvite_personNum(),b.getInvite_context(), b.getInvite_check(),
                            b.getIncite_urgent(),b.getIncite_sex(),b.getIncite_days(),b.getIncite_addressid()));
                }
            }
            @Override
            public void onError(int i, String s) {
                Log.i("cha", "onError brand数据库错误" + i + s);
            }
        });
    }


}
