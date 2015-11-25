package com.best.parttimejobapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.best.love_parttimejob_app.adapter.DialogListAdapter;
import com.best.love_parttimejob_app.adapter.DiscoverListAdapter;
import com.best.love_parttimejob_app.bean.Company;
import com.best.love_parttimejob_app.bean.Dialogs;
import com.best.love_parttimejob_app.bean.Invite;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Vonte on 2015/11/18.
 */
public class DiscoverActivity extends Activity implements View.OnClickListener{
    //分类按钮
    Button discovertimebut,discoverclassifybut,discoveraddressbut;
    //弹出框控件
    //标题
    TextView dialogtitle;
    //listview
    ListView dialoglistview,discoverlistview;
    //按钮
    Button dialognobut,dialogokbut;
    //选项listview
    List<Dialogs> list = new ArrayList<>();
    //adapter
    DialogListAdapter adapter;
    //自定义视图
    View myview;
    //弹出框
    Dialog dialog;
    //id 代表分类id
    int id;
    //string 代表分类
    String setText;
    //listview de adapter
    DiscoverListAdapter discoverListAdapter;
    //listview 的list01
    List<Invite> inviteList = new ArrayList<>();
    //listview 的list02
    List<Company> companyList = new ArrayList<>();
    //查询公司表的地点
    List<String> addresslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //分类页面
        setContentView(R.layout.activity_discover);
        //找到toolbar页面
//        Toolbar toolbarview = (Toolbar) findViewById(R.id.toolbar);
        //找到toolbar的标题
//        TextView toolvartitle = (TextView) toolbarview.findViewById(R.id.toolbartitle);
        //修改toolbar的标题
//        toolvartitle.setText("分类");
        //给listview添加list赋值
        fuzhi();
        //选择时间按钮
        discovertimebut = (Button) findViewById(R.id.discovertimebut);
        discovertimebut.setOnClickListener(this);
        //选择类型按钮
        discoverclassifybut = (Button) findViewById(R.id.discoverclassifybut);
        discoverclassifybut.setOnClickListener(this);
        //选择时间按钮
        discoveraddressbut = (Button) findViewById(R.id.discoveraddressbut);
        discoveraddressbut.setOnClickListener(this);
        //listview
        discoverlistview = (ListView) findViewById(R.id.discoverlistview);
        discoverlistview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Invite invite = inviteList.get(position);
                Intent intent = new Intent(DiscoverActivity.this,DetailsActivity.class);
                intent.putExtra("detailstitle",invite.getInvite_title());
                intent.putExtra("detailsaddress",invite.getInvite_address());
                intent.putExtra("detailssex",invite.getIncite_sex());
                intent.putExtra("detailsnum",invite.getInvite_personNum());
                intent.putExtra("detailsmoney",invite.getInvite_money());
                intent.putExtra("detailsdate",invite.getIncite_days());
                intent.putExtra("detailsphone",invite.getInvite_title());
                intent.putExtra("detailscompyaddress",invite.getInvite_title());
                intent.putExtra("detailsbeizhu",invite.getInvite_context());
//                intent.putExtra("createAt",invite.getCreateAt().getDate());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discoveraddressbut:
                load(v.getId());
                break;
            case R.id.discovertimebut:
                load(v.getId());
                break;
            case R.id.discoverclassifybut:
                load(v.getId());
                break;
            case R.id.dialognobut:
//                Toast.makeText(DiscoverActivity.this,"nonono",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
            case R.id.dialogokbut:
//                Toast.makeText(DiscoverActivity.this,"okokok",Toast.LENGTH_SHORT).show();
                setText="";
                int sum = 0;
                for(int i = 0;i<list.size();i++){
//                    System.out.println(list.get(i).getBool()+"````````````````````````");
                    if(list.get(i).getBool()){
                        if(i==0){
                            setText=list.get(i).getTitle();
                            break;
                        }
                        if(sum!=0){
                            setText+=",";
                        }
                        setText+=list.get(i).getTitle();
                        sum++;
                    }
                }
                switch (id){
                    case R.id.discoveraddressbut:
                        discoveraddressbut.setText(setText);
                        break;
                    case R.id.discovertimebut:
                        discovertimebut.setText(setText);
                        break;
                    case R.id.discoverclassifybut:
                        discoverclassifybut.setText(setText);
                        break;
                }
                dialog.dismiss();
                break;
        }
    }
    public void load(int id){
        this.id = id;
        //获取自定义视图
        myview=LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_dialog, null);
        //获取自定义视图控件
        dialogtitle = (TextView) myview.findViewById(R.id.dialogtv);
        dialoglistview = (ListView) myview.findViewById(R.id.dialoglistview);
        dialognobut = (Button) myview.findViewById(R.id.dialognobut);
        dialognobut.setOnClickListener(this);
        dialogokbut = (Button) myview.findViewById(R.id.dialogokbut);
        dialogokbut.setOnClickListener(this);
        //修改弹出框内容
        list.clear();
        list.add(new Dialogs("默认", true));
        //获取按钮文本，用于listview初始化
        String getText = null;
        switch (id){
            case R.id.discovertimebut:
                getText = discovertimebut.getText().toString();
                dialogtitle.setText("选择时间");
                list.add(new Dialogs("一天", false));
                list.add(new Dialogs("一周", false));
                list.add(new Dialogs("一季度", false));
                list.add(new Dialogs("一年",false));
                break;
            case R.id.discoverclassifybut:
                getText = discoverclassifybut.getText().toString();
                dialogtitle.setText("选择类型");
                list.add(new Dialogs("礼仪",false));
                list.add(new Dialogs("主持",false));
                list.add(new Dialogs("家教",false));
                list.add(new Dialogs("钟点工",false));
                list.add(new Dialogs("月嫂",false));
                list.add(new Dialogs("演出",false));
                list.add(new Dialogs("导游", false));
                list.add(new Dialogs("礼仪",false));
                list.add(new Dialogs("主持",false));
                list.add(new Dialogs("家教",false));
                list.add(new Dialogs("钟点工",false));
                list.add(new Dialogs("月嫂",false));
                list.add(new Dialogs("演出",false));
                list.add(new Dialogs("导游", false));
                list.add(new Dialogs("礼仪",false));
                list.add(new Dialogs("主持",false));
                list.add(new Dialogs("家教",false));
                list.add(new Dialogs("钟点工",false));
                list.add(new Dialogs("月嫂",false));
                list.add(new Dialogs("演出",false));
                list.add(new Dialogs("导游", false));
                break;
            case R.id.discoveraddressbut:
                getText = discoveraddressbut.getText().toString();
                dialogtitle.setText("选择区域");
                list.add(new Dialogs("河东", false));
                list.add(new Dialogs("兰山", false));
                list.add(new Dialogs("苍山", false));
                list.add(new Dialogs("罗庄", false));
                list.add(new Dialogs("临沭", false));
                list.add(new Dialogs("费县",false));
                break;
        }
        //初始化，判断是否选择过
        String[] getTexts = getText.split(",");
        int tm = 0;
        Boolean b;
        for(int i = 0;i<list.size();i++){
            b = false;
//            CheckBox checkBoxs = (CheckBox) dialoglistview.getChildAt(i).findViewById(R.id.dialoglistviewcb);
//            System.out.println(getTexts[tm]+"````````````"+list.get(i).getTitle());
            if(getTexts[tm].equals(list.get(i).getTitle())){
//                checkBoxs.setChecked(true);
                b = true;
                tm++;
                if(tm>=getTexts.length){
                    tm--;
                }
            }
            list.set(i, new Dialogs(list.get(i).getTitle(),b));
//            checkBoxs.setChecked(false);
        }
        //adapter赋值
        adapter = new DialogListAdapter(DiscoverActivity.this,list);
        //listview 设置监听事件
        dialoglistview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.dialoglistviewcb);
                checkBox.setChecked(!checkBox.isChecked());
                list.set(position, new Dialogs(list.get(position).getTitle(), checkBox.isChecked()));
                if(position==0){
                    int itemheight = dialoglistview.getChildAt(0).getHeight();
                    int listviewheight = dialoglistview.getHeight();
                    int num = list.size();
                    int nums = (listviewheight/itemheight)<num?(listviewheight/itemheight):num;
                    for(int j=1;j<nums;j++){
//                        System.out.println(j+"                                                 !!!!!!!!!!!!!!!!!!!!!!!     ");
                        CheckBox checkBoxs = (CheckBox) dialoglistview.getChildAt(j).findViewById(R.id.dialoglistviewcb);
                        checkBoxs.setChecked(false);
                        list.set(j,new Dialogs(list.get(j).getTitle(),false));
                    }
                }else if(checkBox.isChecked()){
                    checkBox = (CheckBox) dialoglistview.getChildAt(0).findViewById(R.id.dialoglistviewcb);
                    checkBox.setChecked(false);
                    list.set(0, new Dialogs(list.get(0).getTitle(), false));
                }else {
                    int nums = 0;
                    for(int j=1;j<list.size();j++){
//                        System.out.println(j+"                                                 !!!!!!!!!!!!!!!!!!!!!!!     ");
                        if(list.get(j).getBool()){
                            nums++;
                        }
                    }
                    if(nums==0){
                        checkBox = (CheckBox) dialoglistview.getChildAt(0).findViewById(R.id.dialoglistviewcb);
                        checkBox.setChecked(true);
                        list.set(0, new Dialogs(list.get(0).getTitle(), true));
                    }
                }
            }
        });
        //listview添加适配器
        dialoglistview.setAdapter(adapter);
        //弹出框
        dialog= new Dialog(DiscoverActivity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);//系统定义风格
        dialog.setContentView(myview);
        dialog.setCancelable(true);
        dialog.show();
    }
    private void fuzhi(){
        //清空地点list
        addresslist.clear();
        //连接网络
        Bmob.initialize(this, "e4472a3896b975ebe594e9669b07774d");
        BmobQuery<Invite> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(this, new FindListener<Invite>() {
            @Override
            public void onSuccess(List<Invite> list) {
//                Toast.makeText(DiscoverActivity.this, list.get(0).getInvite_title(), Toast.LENGTH_SHORT).show();
                for (Invite invite : list) {
                }
                inviteList = list;
                discoverListAdapter = new DiscoverListAdapter(DiscoverActivity.this,inviteList);
                discoverlistview.setAdapter(discoverListAdapter);
            }

            @Override
            public void onError(int i, String s) {
                Log.i("", "网络错误");
                Toast.makeText(DiscoverActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
