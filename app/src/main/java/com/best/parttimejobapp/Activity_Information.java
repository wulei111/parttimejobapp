package com.best.parttimejobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Activity_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

    }
    public void back_menu(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        this.finish();
    }
}
