package com.best.parttimejobapp;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/11/22.
 */
public class Background_style {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    public Background_style( RelativeLayout relativeLayout){
        this.relativeLayout = relativeLayout;
//        this.linearLayout = linearLayout;
    }
    public void onChange_background(String command){
        if(command.equals("night")){
            relativeLayout.setBackgroundResource(R.drawable.bitmap_book_read_chapterlist_repeat);
//            linearLayout.setBackgroundResource(R.drawable.bitmap_book_read_chapterlist_repeat);
        }
        else if(command.equals("during")){
            relativeLayout.setBackgroundResource(0);
            linearLayout.setBackgroundResource(0);
        }
    }  
}
