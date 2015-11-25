package com.best.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.Invite;
import com.best.parttimejobapp.R;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class IndexAdapter extends BaseAdapter{

    private Context context;
    private List<Invite> list;
    public IndexAdapter(Context context, List<Invite> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_discover_list,null);
        Invite invite = list.get(position);
        ImageView discoverlisticon,discoverlistjiaobiao;
        TextView discoverlisttitle,discoverlistaddress,discoverlistmoney,discoverlistdays;
        discoverlisticon = (ImageView) convertView.findViewById(R.id.discoverlisticon);
        discoverlistjiaobiao = (ImageView) convertView.findViewById(R.id.discoverlistjiaobiao);
        discoverlisttitle = (TextView) convertView.findViewById(R.id.discoverlisttitle);
        Log.i("gg",discoverlisttitle+"");
        discoverlistaddress = (TextView) convertView.findViewById(R.id.discoveraddressbut);
        discoverlistmoney = (TextView) convertView.findViewById(R.id.discoverlistmoney);
        discoverlistdays = (TextView) convertView.findViewById(R.id.discoverlistdays);
        discoverlisttitle.setText(invite.getInvite_title());
//        discoverlistaddress.setText(invite.getIncite_addressid().toString());
        discoverlistmoney.setText(invite.getInvite_money());
        discoverlistdays.setText(invite.getIncite_days()+"天");
        return convertView;
    }
}
