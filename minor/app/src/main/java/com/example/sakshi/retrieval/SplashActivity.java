package com.example.sakshi.retrieval;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences pref=getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed",false))
        {
            Intent i=new Intent(this,Options.class);
            startActivity(i);
            finish();
        }else{
            Intent i=new Intent(this,LogInActivity.class);
            startActivity(i);
            finish();
        }
    }
}
