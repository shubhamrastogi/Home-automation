package com.example.sakshi.retrieval;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;

public class PieGraph extends AppCompatActivity {
    private float[] yData = new float[1000];
    private String[] xData = new String[1000];
    //public int[] valpie=new int[1000];
    String[] name=new String[1000];
    PieChart pieChart;
    Firebase neft,myFirebaseRef;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        pieChart = (PieChart)findViewById(R.id.pie);
neft=myFirebaseRef.child(mUserId).child("piechart");
      getDataOfPie();


        pieChart.setContentDescription("Appliance Analysis");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("WATTS_UP");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);


    }



    void getDataOfPie()
    {Log.e("here11111111111","ddddddddddddddddd");
        neft.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              int  i=0;Log.e("here","ddddddddddddddddd");

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    int z=postSnapshot.getValue(int.class);
                    name[i]=postSnapshot.getKey();
yData[i]=(float) z;
                   Log.e("valpie",String.valueOf(yData[i]));


                        xData[i]=name[i];
                        //yData[i]=  valpie[i];

                    i++;

                }
                addDataSet();

                pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        Log.e("toggy",e.toString());

                        int pos1 = e.toString().indexOf("y: ");
                        String score = e.toString().substring(pos1+3);

                        for (int i = 0;i<yData.length; i++){
                            if (yData[i] == Float.parseFloat(score)){
                                pos1 = i;
                                break;
                            }
                        }

                        String name = xData[pos1];
                        Toast.makeText(PieGraph.this,name + "  " + score, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PieGraph.this,BarGraphToggy.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



    private void addDataSet() {
Log.e("y",String.valueOf(yData[0]));
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();

        for (int i = 0;i < yData.length; i++){
            yEntry.add(new PieEntry(yData[i], i));
        }

        for (int i = 0;i < xData.length; i++){
            xEntry.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntry,"colors");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);

        //int[] colors = {Color.CYAN,Color.YELLOW,Color.RED,Color.GRAY,Color.GREEN};

        pieDataSet.setColors(colors);


        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}


