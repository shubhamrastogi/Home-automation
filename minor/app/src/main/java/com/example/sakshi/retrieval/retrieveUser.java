package com.example.sakshi.retrieval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class retrieveUser extends AppCompatActivity {
    Firebase myFirebaseRef;
    public static String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String Value1;
    public String[] array1 = new String[15];
    public String[] array2 = new String[7];
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_user);
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");
        Firebase.setAndroidContext(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        Firebase neft = myFirebaseRef.child(mUserId).child("Appliances");
        neft.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChild) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Value1 = postSnapshot.getValue(String.class);
                    Log.e("count", "raksha");
                    array1[i] = Value1;

                    Log.e("user",array1[i]);
                    i++;

                    // here you can access to name property like university.name

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}