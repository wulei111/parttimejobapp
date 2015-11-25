package com.best.adapter;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.Invite;
import com.best.parttimejobapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孝帅 on 2015/11/23.
 */
public class SearchListAdapter extends BaseAdapter{

    Integer[] images = {R.drawable.yuesao,R.drawable.zhongdiangongs,R.drawable.baomu,
                     R.drawable.qiubaoyang,R.drawable.it,R.drawable.liyi,
                    R.drawable.lvyou,R.drawable.motes,R.drawable.jiudain
                    };

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
        ImageView imageclass = (ImageView) convertView.findViewById(R.id.image_class);
        ImageView imageji = (ImageView)convertView.findViewById(R.id.image_ji);
        TextView title = (TextView) convertView.findViewById(R.id.text_title);
        TextView txtshijian = (TextView)convertView.findViewById(R.id.text_shijian);
        TextView txtren = (TextView)convertView.findViewById(R.id.text_renshu);
        TextView txtqian = (TextView)convertView.findViewById(R.id.text_qian);
        TextView tian = (TextView)convertView.findViewById(R.id.text_tianshu);

        if (invite.getClassify_id()<images.length){
            imageclass.setImageResource(images[invite.getClassify_id()]);
        }else imageclass.setImageResource(R.drawable.qiubaoyang);

        if (invite.getInvite_urgent()==null){
            imageji.setVisibility(View.GONE);
        }
        title.setText(invite.getInvite_title().toString());
        txtshijian.setText(invite.getInvite_time().toString());
        txtren.setText(invite.getInvite_personNum().toString()+"人");
        txtqian.setText(invite.getInvite_money().toString()+"元/"+invite.getInvite_danwei());
        tian.setText(invite.getInvite_days()+"天");

        return convertView;
    }
}
