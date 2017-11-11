package com.example.sakshi.retrieval;

/**
 * Created by sakshi on 30/4/17.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.snapshot.StringNode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

/**
 * Created by TutorialsPoint7 on 8/23/2016.
 */

public class MyService extends Service {
    dummyActivity ob=new dummyActivity();
    Firebase myFirebaseRef,neft;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mFirebaseUser;
  String mUserId;
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        neft=myFirebaseRef.child(mUserId);
        // Let it continue running until it is stopped.
        Calendar now2 = Calendar.getInstance();

     int   hs= now2.get(Calendar.HOUR_OF_DAY);
        int mf = now2.get(Calendar.MINUTE);
        if(hs==17 && mf==38)
            Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
           /* neft.child("day").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   int t=  dataSnapshot.getValue(int.class);

                    t=t+1;
                    Log.e("durtttt",String.valueOf(t));
                    neft.child("day").setValue(t);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });*/



        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}

