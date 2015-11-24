package com.best.adapter;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.bean.Invite;
import com.best.parttimejobapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孝帅 on 2015/11/23.
 */
public class SearchListAdapter extends BaseAdapter{

    Context context;
    List<Invite> inviteList;

    public SearchListAdapter(Context context,List<Invite> inviteList){
        this.context = context;
        this.inviteList = inviteList;
    }

    @Override
    public int getCount() {
        return inviteList.size();
    }

    @Override
    public Object getItem(int position) {
        return inviteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Invite invite = inviteList.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.listview_s,null);
        TextView title = (TextView) convertView.findViewById(R.id.text_title);

        title.setText(invite.getInvite_title().toString());

        return convertView;
    }
}
