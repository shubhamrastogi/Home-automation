package com.example.sakshi.retrieval;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.List;


public class TwoFragment extends Fragment {
    MainProfile obj = new MainProfile(getActivity());
    dummyActivity ob=new dummyActivity();
    Firebase myFirebaseRef;
    public static String mUserId;
    public static String[] appliance;
    String[] arraydup=new String[1000];
    public static String[] stat;
    private int[] images;
    String[][] yesno;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    Firebase neft2, neft3;
    public String[] array1 ;
    public String[] array2;
    int i = 0, j = 0, x, y, t,f=0,count=0;
    String value;
    int hs;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("sss", "fjkefjdlskfs");
        //Firebase.setAndroidContext(this);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Calendar now2 = Calendar.getInstance();

        hs= now2.get(Calendar.HOUR_OF_DAY);

        Firebase.setAndroidContext(this.getActivity());
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        Firebase neft = myFirebaseRef.child(mUserId);
        //neft3=neft.child("app");
        neft2 = neft.child("day2").child("12pm-3pm");
        Firebase neft4= neft.child("day1").child("12-3");


        neft4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("neft4","neft33333333333334");
                j=0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    value = postSnapshot.getValue(String.class);
                    arraydup[j]=value;
                    j++;
                }
                array1=new String[j];
                array2=new String[j];
                appliance=new String[j];
                for(i=0;i<j;i++)
                    array1[i]=arraydup[i];


            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        neft2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                i=0;
                count=0;
                Log.e("22","neft222");
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    String val=postSnapshot.getValue(String.class);
                    appliance[i]=postSnapshot.getKey();

                    array2[i]=val;

                    i++;
                    count++;
                }
                images=new int[count];
                yesno=new String[count][2];
                stat=new String[count];
                for (i = 0; i < count; i++) {
                    Log.e("oiii",String.valueOf(i));
                    yesno[i][0] = appliance[i];
                    if(appliance[i]=="ac")
                        images[i]=R.drawable.ac;
                    else if(appliance[i]=="fan" ||appliance[i]=="fan2")
                        images[i]=R.drawable.fan;
                    else if(appliance[i]=="motor")
                        images[i]=R.drawable.motor;
                    else if(appliance[i]=="tv")
                        images[i]=R.drawable.tv;
                    else if(appliance[i]=="geyser")
                        images[i]=R.drawable.geyser;
                    else if(appliance[i]=="wm")
                        images[i]=R.drawable.wm;
                    else if(appliance[i]=="light" || appliance[i]=="light2")
                        images[i]=R.drawable.bulb1;
                    else if(appliance[i]=="fridge")
                        images[i]=R.drawable.fridge;
                    else
                        images[i]=R.drawable.back;
                }
                callFun();
               // populateList();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





        Log.e("ra","intwo");







        Log.e("aha","hatt");
        ///
        return view;
    }

    private void callFun()
    {
        i=0;
        j=0;
        Log.e("count",String.valueOf(count));
        while(i!=count && j!=count) {
            x = Integer.parseInt(array1[i]);
            y = Integer.parseInt(array2[j]);
            t = obj.genProfile(x, y);
            if (t == 1)
                yesno[i][1] = "ON";
            else
                yesno[i][1] = "OFF";
            i++;
            j++;
        }

        for(f=0;f<count;f++)
        {   appliance[f]=yesno[f][0];
            stat[f]=yesno[f][1];

        }
          populateList();
    }
    private void populateList() {
        Log.e("aha","hatt");
        ListView lv=(ListView)getActivity().findViewById(R.id.list2);

        Adapter adapter= new adapter(getActivity(),appliance,stat,images);

        lv.setAdapter((ListAdapter) adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView txt=(TextView) view.findViewById(R.id.textView6);
                String txt2=txt.getText().toString();
                Log.e("ttttttttttttt","fightttttttttttttttt");
                if(txt2.equalsIgnoreCase("ON")) {
                    stat[position]="OFF";
                    txt.setText("OFF");
                }
                else {
                    stat[position] = "ON";
                    txt.setText("ON");
                }
            }
        });


    }
    private void callDummy()
    {
        Intent intent=new Intent(getActivity(),dummyActivity.class);
        intent.putExtra("onORoff",stat);
        startActivity(intent);
    }
}




