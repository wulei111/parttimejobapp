package com.best.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public class GuideAdapter extends PagerAdapter {
    Context context;
    List<View> list;
    public GuideAdapter(Context context,List<View> list){
        this.context = context;
        this.list = list;
//        for(View v:list){
//            System.out.println(v.toString()+"000000000000000000000000000000000000000000000");
//        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        //xiaohui
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //
        View v = list.get(position);
        container.addView(v,0);
        return v;
    }
}
