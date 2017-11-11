package com.example.sakshi.retrieval;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PopUp1 extends Activity {

    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up1);
        Log.e("ffff","hereeee3w33333333333");
        ok = (Button) findViewById(R.id.button5);
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textView = (TextView)findViewById(R.id.textSeek);
        textView.setText("");
        seekBar.setMax(168);
        seekBar.setProgress(24);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textView.getText().toString();
                Intent intent = getIntent();
                intent.putExtra("msg",str);
                setResult(2,intent);
                finish();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .75), (int) (height * .3));
        //fetch_data();
    }

}

