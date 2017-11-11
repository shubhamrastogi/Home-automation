package com.example.sakshi.retrieval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static android.R.attr.value;

public class BarGraphToggy extends AppCompatActivity {
    BarChart barChart;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private Firebase myFirebaseRef,neft;
    String mUserId,name;
    float[] data=new float[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph_toggy);
        barChart= (BarChart)findViewById(R.id.bar);
        Intent i=getIntent();
         name=i.getStringExtra("name");
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        neft = myFirebaseRef.child(mUserId).child("barGraph");


        fetchData();


    }

    private void fetchData() {
        neft.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int i=0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    float value= postSnapshot.getValue(float.class);
                    data[i] = value;
                    Log.e("bar",String.valueOf(data[i]));
                    i++;

                }
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(1,data[0]));
                barEntries.add(new BarEntry(2,data[1]));
                barEntries.add(new BarEntry(3,data[2]));
                barEntries.add(new BarEntry(4,data[3]));
                barEntries.add(new BarEntry(5,data[4]));
                barEntries.add(new BarEntry(6,data[5]));
                barEntries.add(new BarEntry(7,data[6]));




                BarDataSet barDataSet = new BarDataSet(barEntries,"Names");

                final ArrayList<String> names = new ArrayList<>();
                names.add("Shit");
                names.add("Pappu");
                names.add("Pum");
                names.add("Toggy");
                names.add("Sanya");
                names.add("Gabbar");



                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setPinchZoom(true);
                barChart.setScaleEnabled(true);

                barChart.invalidate();



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}



