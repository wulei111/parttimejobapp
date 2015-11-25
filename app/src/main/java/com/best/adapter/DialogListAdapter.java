package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.best.love_parttimejob_app.R;
import com.best.love_parttimejob_app.bean.Dialogs;

import java.util.List;

/**
 * Created by Vonte on 2015/11/20.
 */
public class DialogListAdapter extends BaseAdapter {
    Context context;
    List<Dialogs> list;
    //item中的控件
    //textview
    TextView textView;
    //checkbox
    CheckBox checkBox;
    public DialogListAdapter(Context context,List<Dialogs> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_dialog_list,null);
        textView  = (TextView) convertView.findViewById(R.id.dialoglistviewtv);
        checkBox = (CheckBox) convertView.findViewById(R.id.dialoglistviewcb);
        textView.setText(list.get(position).getTitle());
        checkBox.setChecked(list.get(position).getBool());
        return convertView;
    }
}
