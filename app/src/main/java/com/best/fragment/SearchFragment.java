package com.best.fragment;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
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
import android.widget.EditText;
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
    //进度条
    ProgressDialog pd = null;
    //搜索条件,时间，人数，薪资,搜索关键字
    public EditText edit_search;
    public String search_main=null;
    public Integer search_time = null,search_people = null,search_money1 = null,search_money2=null;
    public BmobQuery<Invite> bq_main;
    public BmobQuery<Invite> bq_time;
    public BmobQuery<Invite> bq_people;
    public BmobQuery<Invite> bq_money1;
    public BmobQuery<Invite> bq_money2;
    //搜索按钮
    Button button_search;
    TextView txt_jieguo;

    //全局布局
    public View view;
    ImageView imageview01,imageview02,imageview03;
    RelativeLayout fu1,fu2,fu3;//父列表
    Boolean IsF1 = false;
    Boolean IsF2 = false;
    Boolean IsF3 = false;
    LinearLayout zi1,zi2,zi3;//子列表
    //选择时间 单选按钮
    RadioGroup grp_s1,grp_s2;
    //选择人数
    RadioGroup grp_r1,grp_r2;
    //选择薪资
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
        txt_jieguo = (TextView) view.findViewById(R.id.text_jieguo);
        edit_search = (EditText)view.findViewById(R.id.edit_search);
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
                search_main = edit_search.getText().toString();
                pd = ProgressDialog.show(getContext(),"","玩命搜索中。。。。");
                searchKu();

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
//                radioButton01 = (RadioButton) view.findViewById(checkedId);
                //设置搜索条件
                if (checkedId==grp_s1.getChildAt(0).getId()){
                    search_time = 1;
                }else if (checkedId==grp_s1.getChildAt(1).getId()){
                    search_time = 7;
                }else if (checkedId==grp_s1.getChildAt(2).getId()){
                    search_time = 30;
                }

            }else if (group == grp_s2){
                grp_s1.clearCheck();
                //1和2是同一个组
                //设置搜索条件
                if (checkedId==grp_s2.getChildAt(0).getId()){
                    search_time = 90;
                }else if (checkedId==grp_s2.getChildAt(1).getId()){
                    search_time = 180;
                }else if (checkedId==grp_s2.getChildAt(2).getId()){
                    search_time = 181;//以上
                }
            }
            if (group == grp_r1){
                grp_r2.clearCheck();
                //设置搜索条件
                if (checkedId==grp_r1.getChildAt(0).getId()){
                    search_people = 10;
                }else if (checkedId==grp_r1.getChildAt(1).getId()){
                    search_people = 20;
                }else if (checkedId==grp_r1.getChildAt(2).getId()){
                    search_people = 50;
                }
            }else if (group == grp_r2){
                grp_r1.clearCheck();
                //设置搜索条件
                if (checkedId==grp_r2.getChildAt(0).getId()){
                    search_people = 100;
                }else if (checkedId==grp_r2.getChildAt(1).getId()){
                    search_people = 200;
                }else if (checkedId==grp_r2.getChildAt(2).getId()){
                    search_people = 201;//以上
                }
            }
            if (group == grp_x1){
                grp_x2.clearCheck();
                //设置搜索条件
                if (checkedId==grp_x1.getChildAt(0).getId()){
                    search_money1 = 1;
                    search_money2 = 100;
                }else if (checkedId==grp_x1.getChildAt(1).getId()){
                    search_money1 = 100;
                    search_money2 = 200;
                }else if (checkedId==grp_x1.getChildAt(2).getId()){
                    search_money1 = 200;
                    search_money2 = 500;
                }
            }else if (group == grp_x2){
                grp_x1.clearCheck();
                //设置搜索条件
                if (checkedId==grp_x2.getChildAt(0).getId()){
                    search_money1 = 500;
                    search_money2 = 1000;
                }else if (checkedId==grp_x2.getChildAt(1).getId()){
                    search_money1 = 1000;
                    search_money2 = 3000;
                }else if (checkedId==grp_x2.getChildAt(2).getId()){
                    search_money1 = 3001;//以上
                }
            }
            changeGroup = false;
        }

    }
    //查询方法
    public void searchKu(){
        //添加数据
        Bmob.initialize(getContext(), "e4472a3896b975ebe594e9669b07774d");
        //and 并列搜索
        if (search_main!=null){
            bq_main = new BmobQuery<Invite>();
            bq_main.addWhereContains("invite_title",search_main);
        }
        if(search_time!=null){
            bq_time = new BmobQuery<Invite>();
            if (search_time==181){
                bq_time.addWhereGreaterThanOrEqualTo("invite_days",search_time);
            }else{
                bq_time.addWhereLessThanOrEqualTo("invite_days",search_time);
            }
        }
        if (search_people!=null){
            bq_people = new BmobQuery<Invite>();
            if (search_people==201){
                bq_people.addWhereGreaterThanOrEqualTo("invite_personNum",search_people);
            }else {
                bq_people.addWhereLessThanOrEqualTo("invite_personNum",search_people);
            }
        }
        if (search_money1!=null){
            bq_money1 = new BmobQuery<Invite>();
            bq_money2 = new BmobQuery<Invite>();
            if (search_money1==3001){
                bq_money1.addWhereGreaterThanOrEqualTo("invite_money",search_money1);
            }else {
                bq_money1.addWhereGreaterThanOrEqualTo("invite_money",search_money1);
                bq_money2.addWhereLessThanOrEqualTo("invite_money",search_money2);
            }
        }

        //组装成完整的条件
        List<BmobQuery<Invite>> andQuerys = new ArrayList<BmobQuery<Invite>>();
        if (search_main!=null) andQuerys.add(bq_main);
        if (search_time!=null) andQuerys.add(bq_time);
        if (search_people!=null) andQuerys.add(bq_people);
        if(search_money1!=null) andQuerys.add(bq_money1);
        if (search_money2!=null) andQuerys.add(bq_money2);

        //查询and条件
        BmobQuery<Invite> inviteBmobQuery = new BmobQuery<Invite>();
        inviteBmobQuery.and(andQuerys);

//        返回50条数据，如果不加上这条语句，默认返回10条数据
//        query.setLimit(50);
        inviteBmobQuery.findObjects(getContext(), new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {
                invites.clear();
                for (Invite b : list) {
                    invites.add(new Invite(b.getInvite_id(), b.getClassify_id(), b.getUser_id(), b.getInvite_title(), b.getInvite_time(),
                            b.getInvite_address(), b.getInvite_money(), b.getInvite_personNum(), b.getInvite_context(), b.getInvite_check(),
                            b.getInvite_urgent(), b.getInvite_sex(), b.getInvite_days(), b.getInvite_addressid(),b.getInvite_danwei()));
                }
                //关闭进度条
                pd.dismiss();
                //点击查询取消单选按钮的选中
                search_time = null;
                search_people = null;
                search_money1 = null;
                search_money2=null;
                grp_s1.clearCheck();
                grp_s2.clearCheck();
                grp_r1.clearCheck();
                grp_r2.clearCheck();
                grp_x1.clearCheck();
                grp_x2.clearCheck();
                //点击查询关闭子菜单
                imageview01.setImageResource(R.drawable.btn_browser);
                zi1.setVisibility(View.GONE);
                IsF1 = false;
                imageview02.setImageResource(R.drawable.btn_browser);
                zi2.setVisibility(View.GONE);
                IsF2 = false;
                imageview03.setImageResource(R.drawable.btn_browser);
                zi3.setVisibility(View.GONE);
                IsF3 = false;

                //listview添加
                txt_jieguo.setVisibility(View.VISIBLE);
                txt_jieguo.setText("爱兼职（全部约"+invites.size()+"个结果）");
                searchListAdapter = new SearchListAdapter(getContext(), invites);
                listView.setAdapter(searchListAdapter);
            }

            @Override
            public void onError(int i, String s) {
                Log.i("cha", "onError brand数据库错误" + i + s);
                pd.dismiss();
                Toast.makeText(getContext(),"网络不可用，请检查您的网络连接",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
