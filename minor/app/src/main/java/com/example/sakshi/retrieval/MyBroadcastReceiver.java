package com.example.sakshi.retrieval;

/**
 * Created by sakshi on 4/5/17.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
        MediaPlayer mp;
    dummyActivity ob=new dummyActivity();
        @Override
        public void onReceive(Context context, Intent intent) {
          //  mp=MediaPlayer.create(context, R.raw.alrm	);
           // mp.start();
           // Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
            ob.updateDate();
        }


    }

