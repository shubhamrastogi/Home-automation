package com.example.sakshi.retrieval;

/**
 * Created by sakshi on 22/3/17.
 */
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.IntDef;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringDef;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class  MainProfile  extends AppCompatActivity {
    Firebase myFirebaseRef;
    public static String mUserId;
    public String days;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    Firebase neft2;
    String value;
    public int[] day=new int[7];


    int k = 0;

    MainProfile( FragmentActivity f) {

    }

    public int genProfile(int a1,int a2) {
        int dayAvg = 0;
        day[0]=a1;
        day[1]=a2;
        for (int i = 0; i < 2; i++) {
            dayAvg += day[i];
        }
        dayAvg /= 2;
        if (day[1] >= 120) {
            return 1;
        } else {
            if (dayAvg >= 120 && day[1] >= 80)
                return 1;
        }
        return 0;
    }

    public int advProfile() {
        return 0;
    }

    private int getData(int i, int t, int c) {
        //  Firebase.setAndroidContext(this.getActivity());
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        days = "day" + Integer.toString(i);
        Firebase neft = myFirebaseRef.child(mUserId).child(days);
        if (c == 1) {
            if (t == 1) {
                neft2 = neft.child("9-12").child("ac");

              neft2.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                      value=dataSnapshot.getValue(String.class);
                   //   Log.e("value",value);

                  }

                  @Override
                  public void onCancelled(FirebaseError firebaseError) {

                  }
              });
            }
           /* if (t == 2) {
                Log.e("t",Integer.toString(t));
                neft2 = neft.child("9-12").child("fan1");
                neft2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       value = dataSnapshot.getValue(String.class);
                        k++;
                        Log.e("gh", value);
                        //Log.e("gh",array[1]);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
            if (t == 3) {
                Log.e("t",Integer.toString(t));
                neft2 = neft.child("9-12").child("fan2");
                neft2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue(String.class);
                        k++;
                        Log.e("gh", value);
                        //Log.e("gh",array[1]);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }


            //retrive data of previous 7 days
*/

        }

        return 0;
    }

}









