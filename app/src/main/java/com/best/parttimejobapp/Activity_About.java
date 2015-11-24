package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public class Activity_About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activlty_about);
    }
    public void back_menu(View v){
        Intent i = new Intent(Activity_About.this,MainActivity.class);
        startActivity(i);
        this.finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getRepeatCount() == 0) {
            Intent i = new Intent(Activity_About.this,MainActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
