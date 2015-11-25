package com.best.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import com.best.adapter.IndexAdapter;
import com.best.bean.Company;
import com.best.bean.Invite;
import com.best.parttimejobapp.DetailsActivity;
import com.best.parttimejobapp.GuideActivity;
import com.best.parttimejobapp.ListViewForScrollView;
import com.best.parttimejobapp.R;
import com.best.parttimejobapp.XiangqingActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import droid.Activity01;

/**
 * 创建时间：2015-11-19
 *创建人：吴磊
 * 创建的动作：首页
 *
 * */
public class IndexFragment extends Fragment {
    public GridView gv;
    ImageView image;
    ListView listView;
    TextView textView;
    ViewPager bannerviewPager;
    //listview 的list01
    List<Invite> inviteList = new ArrayList<>();
    //listview 的list02
    IndexAdapter indexAdapter;
    List<Company> companyList = new ArrayList<>();
    View bannerview1,bannerview2,bannerview3,bannerview4;
    List<View> bannerviewList = new ArrayList<>();
    private ImageHandler handler = new ImageHandler(new WeakReference<IndexFragment>(this));
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        //listview
        listView = (ListViewForScrollView) view.findViewById(R.id.index);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Invite invite = inviteList.get(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("detailstitle", invite.getInvite_title());
                intent.putExtra("detailsaddress", invite.getInvite_address());
                intent.putExtra("detailssex", invite.getIncite_sex());
                intent.putExtra("detailsnum", invite.getInvite_personNum());
                intent.putExtra("detailsmoney", invite.getInvite_money());
                intent.putExtra("detailsdate", invite.getIncite_days());
//                System.out.println("shoujihaoma:"+invite.getInvite_phonenum());
                intent.putExtra("detailsphone", invite.getInvite_phonenum().toString());
                intent.putExtra("detailscompyaddress", invite.getInvite_addresss());
                intent.putExtra("detailsbeizhu", invite.getInvite_context());
//                System.out.println("createAt:"+invite.getCreateAt().getDate());
                intent.putExtra("createAt", invite.getInvite_date());
                startActivity(intent);
            }
        });
        //连接网络
        Bmob.initialize(getActivity(), "e4472a3896b975ebe594e9669b07774d");
        BmobQuery<Invite> u = new BmobQuery<>();
        u.findObjects(getActivity(), new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {

                inviteList = list;
                indexAdapter = new IndexAdapter(getActivity(), inviteList);
                Log.i("ggg","lkllllll");
                listView.setAdapter(indexAdapter);
            }
            @Override
            public void onError(int i, String s) {
                Log.i("cha", "onError brand数据库错误" + i + s);
            }
        });


        bannerviewPager = (ViewPager) view.findViewById(R.id.bannerviewpagers);
        //优化banner
        bannerviewPager.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        bannerview1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_index_banner_item1,null);
        bannerview2 = LayoutInflater.from(getContext()).inflate(R.layout.activity_index_banner_item2,null);
        bannerview3 = LayoutInflater.from(getContext()).inflate(R.layout.activity_index_banner_item3,null);
        //将banner加入到集合中
        bannerviewList.add(bannerview1);
        bannerviewList.add(bannerview2);
        bannerviewList.add(bannerview3);
        bannerviewPager.setAdapter(new MyViewPager());
        //找到布局中的gridView，传给gv
        gv = (GridView) view.findViewById(R.id.fenclassgridview);
        gv.setAdapter(new GridViewAdapter(getContext()));
        setListViewHeightBasedOnChildren(gv);
        //给gridView布局添加一个点击事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(),"你点击了"+position+"",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),XiangqingActivity.class);
                startActivity(intent);
                for(int i=0;i<=position;i++){


                }
            }
        });
        return view;
    }
    class GridViewAdapter extends BaseAdapter {
        //图片
        private int[] images = {R.drawable.yuesao,R.drawable.zhongdiangongs,R.drawable.jiudain,R.drawable.baomu
        ,R.drawable.it,R.drawable.liyi,R.drawable.lvyou,R.drawable.motes};
        //文字
        private String[] texts = {"月嫂","钟点工","服务员","保姆","IT","礼仪","导游","模特"};
        LayoutInflater inflater;
        public GridViewAdapter(Context context){
            inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            grid g;
            if(convertView==null)
            {
                g = new grid();
                convertView = inflater.inflate(R.layout.gridview_item, null);
                g.image = (ImageView)convertView.findViewById(R.id.gridview_image);
                g.text = (TextView)convertView.findViewById(R.id.gridview_text);
                convertView.setTag(g);

            }
                g= (grid) convertView.getTag();
                g.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                g.image.setPadding(8, 8, 8, 8);
                g.image.setImageResource(images[position]);
                g.text.setText(texts[position]);
                return convertView;
        }
    }
    class grid{
        ImageView image;
        TextView text;
    }
     //控制在scollView中添加listView 或 GridView 显示一行的问题
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        if(listView == null) return;


        GridViewAdapter listAdapter = (GridViewAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i <3; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
//            totalHeight = listItem.getMeasuredHeight();
            totalHeight += listItem.getMeasuredHeight();
            Log.i("aaa",listItem.getMeasuredHeight()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // totalHeight + (listView.getMeasuredHeight() * (listAdapter.getCount() - 1))
//        params.height =500;
        params.height = totalHeight- 70 + (listView.getMeasuredHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {

            return bannerviewList.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(bannerviewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = bannerviewList.get(position);
            container.addView(v, 0);
            return v;
        }
    }
        /**
     * 下面是ViewPager的轮播效果
     */
        private static class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE  = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT   = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT  = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED  = 4;
        /**
         * 轮播间隔时间
         */
        protected static final long MSG_DELAY = 3000;

        /**
         * 使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
         * */
        private WeakReference<IndexFragment> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<IndexFragment> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("LOG_TAG", "receive message " + msg.what);
            IndexFragment activity = weakReference.get();
            if (activity==null){
                /**
                 *  Activity已经回收，无需再处理UI了
                 * */
                return ;
            }
            /**
             * 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
             * */
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)){
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    if(currentItem >2){
                        currentItem=0;
                    }
                    activity.bannerviewPager.setCurrentItem(currentItem);
                    /**
                     * 准备下次播放
                     * */
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    /**
                     * 只要不发送消息就暂停了
                     * */
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    /**
                     * 记录当前的页号，避免播放的时候页面显示不正确。
                     * */
                    currentItem = msg.arg1;
                    break;
                    default:
                    break;
            }
        }
    }
}

