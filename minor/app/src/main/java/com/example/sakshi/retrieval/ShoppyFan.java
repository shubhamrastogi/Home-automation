package com.example.sakshi.retrieval;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppyFan extends AppCompatActivity {
   // TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21;
    TextView text;
    private int i = 0,j=0,num=0,num1=0,n1=0,n2=0;
    String userI1, userI2;
    Firebase myFirebaseRef;
    public String[] array1 = new String[7];
    public String[] array2 = new String[7];
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String Value1,Value2,c,c1;
   Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppy_fan);
        b=(Button)findViewById(R.id.button3);
        final TextView text3=(TextView) findViewById(R.id.t4);
        final TextView text4=(TextView) findViewById(R.id.t21);
        final TextView text5=(TextView) findViewById(R.id.t6);
        final TextView text6=(TextView) findViewById(R.id.t7);
        final TextView text7=(TextView) findViewById(R.id.t9);
        final TextView text8=(TextView) findViewById(R.id.t10);
        final TextView text9=(TextView) findViewById(R.id.t12);
        final TextView text10=(TextView) findViewById(R.id.t13);
        final TextView text11=(TextView) findViewById(R.id.t15);
        final TextView text12=(TextView) findViewById(R.id.t16);
        final TextView text13=(TextView) findViewById(R.id.t18);
        final TextView text14=(TextView) findViewById(R.id.t19);
         text=(TextView) findViewById(R.id.textView4);
        Bundle extras1 = getIntent().getExtras();
        userI1 = extras1.getString("pro1");
        Bundle extras2 = getIntent().getExtras();
        userI2 = extras2.getString("pro");
        Log.e("userrrr", userI1);
        Log.e("dddddddddd",userI2);
       b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(ShoppyFan.this,ShoppyMain.class);
                startActivity(in);
            }});
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/");
        Query queryRef = myFirebaseRef.orderByChild("id").equalTo(userI1);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChild) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                     Value1 = postSnapshot.getValue(String.class);
                    Log.e("count","raksha");
                    array1[i]=Value1;


                    i++;

                    // here you can access to name property like university.name

                }
                try{
                    num=Integer.parseInt(array1[4]);
                }catch(NumberFormatException nfe){

                }

             c= calcCost(num);
                text3.setText(array1[0]);
                text5.setText(array1[2]);
                text7.setText(array1[3]);
                text9.setText(array1[4]);
                text11.setText(array1[1]);
                text13.setText(c);


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
        //Log.e("array",array1[1]);

        Query queryRef2 = myFirebaseRef.orderByChild("id").equalTo(userI2);
        queryRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChild) {
                Log.e("ssss","dddddddddddddddssssssssssssssssssss");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                     Value2 = postSnapshot.getValue(String.class);

                    array2[j]=Value2;
                    Log.e("sdd",array2[j]);
                   j++;
                    // here you can access to name property like university.name

                }
                try{
                    num1=Integer.parseInt(array2[4]);
                }catch(NumberFormatException nfe){

                }

                c1= calcCost(num1);
                text4.setText(array2[0]);
                text6.setText(array2[2]);
                text8.setText(array2[3]);
                text10.setText(array2[4]);
                text12.setText(array2[1]);
                text14.setText(c1);
                n1=Integer.parseInt(c);
                n2=Integer.parseInt(c1);
                if(n1>n2) {
                    text.setText("Go with 2nd product");
                }
                else
                {
                    text.setText("Go with 1st product");
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


    private String calcCost(int i) {
        int x = (int) (5 * i);          // On average a fan runs about 5000hrs per year in New Delhi. Power consumption = (no. of operating hours) * (power) / 1000
        //x = x*cost_per_unit
        return String.valueOf(x);

    }
}

