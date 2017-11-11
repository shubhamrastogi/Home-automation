package com.example.sakshi.retrieval;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;

public class pop_up extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

       // SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textView = (TextView)findViewById(R.id.textS);
        textView.setText("fffff");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .75), (int) (height * .3));
        //fetch_data();
    }

}

