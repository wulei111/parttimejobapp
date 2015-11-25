package com.best.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.parttimejobapp.R;

/**
 * Created by Administrator on 2015/11/25.
 */
public class Spinner_item_Adapter extends BaseAdapter{
    Context context;
    int[] icon = new int[10];
    String[] Classify = new String[10];
    public Spinner_item_Adapter(Context context,int[] icon,String[] Classify){
        this.context = context;
        this.icon = icon;
        this.Classify = Classify;
    }
    @Override
    public int getCount() {
        return icon.length;
    }

    @Override
    public Object getItem(int position) {
        return icon[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.from(context).inflate(R.layout.spinner_list_item,null);
        TextView  textView = (TextView) convertView.findViewById(R.id.spinner_item_label);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.spinner_item_icon_image);
        textView.setText(Classify[position]);
        imageView.setImageResource(icon[position]);
        return convertView;
    }
}
