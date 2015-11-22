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
public class SearchFragment extends Fragment {

    TextView toolbartitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search,container,false);
        //找到Toobar
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        toolbartitle.setText("搜索");

        //添加仿qq列表数据,一级容器，二级容器
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

        Map<String, String> group = new HashMap<String, String>();
        group.put("g", "选择时间");
        groups.add(group);
        group.put("g", "选择地点");
        groups.add(group);
        group.put("g", "选择人数");
        groups.add(group);
        group.put("g", "选择薪资");
        groups.add(group);

        List<Map<String, String>> child = new ArrayList<Map<String, String>>();

        for (int j = 0; j < 3; j++) {
            Map<String, String> childdata = new HashMap<String, String>();
            childdata.put("c", "用户");
            child.add(childdata);
        }

        childs.add(child);


        /**
         * 创建ExpandableList的Adapter容器 参数: 1.上下文 2.一级集合 3.一级样式文件 4. 一级条目键值
         * 5.一级显示控件名 6. 二级集合 7. 二级样式 8.二级条目键值 9.二级显示控件名
         *
         */
        QQListView exList = (QQListView)view.findViewById(R.id.home_expandableListView);
        exList.setHeaderView(inflater.inflate(R.layout.group_header,exList,false));
//getLayoutInflater()备份
        QQListAdapter adapter = new QQListAdapter(
                getContext(),exList, groups, R.layout.group, new String[] { "g" },
                new int[] { R.id.groupto }, childs, R.layout.child,
                new String[] { "c" }, new int[] { R.id.childto });
//      setListAdapter(adapter);
        exList.setAdapter(adapter);

        

        return view;
    }



}
