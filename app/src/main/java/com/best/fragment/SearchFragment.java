package com.best.fragment;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.best.adapter.QQListAdapter;
import com.best.parttimejobapp.R;
import com.best.util.QQListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人：张孝帅
 * 创建时间：11.19
 * 创建内容：搜索
 */
public class SearchFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

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
    boolean changeGroup = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search,container,false);

        imageview01 = (ImageView) view.findViewById(R.id.image01);
        imageview02 = (ImageView) view.findViewById(R.id.image02);
        imageview03 = (ImageView) view.findViewById(R.id.image03);
        fu1 = (RelativeLayout) view.findViewById(R.id.choose_fu01);
        fu2 = (RelativeLayout) view.findViewById(R.id.choose_fu02);
        fu3 = (RelativeLayout) view.findViewById(R.id.choose_fu03);
        zi1 = (LinearLayout) view.findViewById(R.id.choose_zi01);
        zi2 = (LinearLayout) view.findViewById(R.id.choose_zi02);
        zi3 = (LinearLayout) view.findViewById(R.id.choose_zi03);
        //获取单选按钮
        grp_s1 = (RadioGroup) view.findViewById(R.id.radioshijian01);
        grp_s2 = (RadioGroup) view.findViewById(R.id.radioshijian02);
        grp_r1 = (RadioGroup) view.findViewById(R.id.radioren01);
        grp_r2 = (RadioGroup) view.findViewById(R.id.radioren02);
        grp_x1 = (RadioGroup) view.findViewById(R.id.radioxin01);
        grp_x2 = (RadioGroup) view.findViewById(R.id.radioxin02);

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
                if (IsF1){
                    //隐藏子列表
                    imageview01.setImageResource(R.drawable.btn_browser);
                    zi1.setVisibility(View.GONE);
                    IsF1 = false;
                }else {
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
                if (IsF2){
                    //隐藏子列表
                    imageview02.setImageResource(R.drawable.btn_browser);
                    zi2.setVisibility(View.GONE);
                    IsF2 = false;
                }else {
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
                if (IsF3){
                    //隐藏子列表
                    imageview03.setImageResource(R.drawable.btn_browser);
                    zi3.setVisibility(View.GONE);
                    IsF3 = false;
                }else {
                    //显示子列表
                    imageview03.setImageResource(R.drawable.btn_browser2);
                    zi3.setVisibility(View.VISIBLE);
                    IsF3 = true;
                }
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
            }else if (group == grp_s2){
                grp_s1.clearCheck();
            }
            if (group == grp_r1){
                grp_r2.clearCheck();
            }else if (group == grp_r2){
                grp_r1.clearCheck();
            }
            if (group == grp_x1){
                grp_x2.clearCheck();
            }else if (group == grp_x2){
                grp_x1.clearCheck();
            }
            changeGroup = false;
        }

    }

}
