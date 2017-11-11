package com.example.sakshi.retrieval;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ramotion.foldingcell.FoldingCell;

public class ShowMonthlyHours extends Activity {

    ListView listView;
    int appSelected;
    TextView textView;
    Firebase myFirebaseRef, ref;
    public static String mUserId;
    public int[] images = new int[6];
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String Value1;
    public String[] array1 = new String[15];
    int i = 0;
    String[] name = new String[6];
    int[] lastBill = new int[6];

    int[] billDueApp;
    Double[] hourlyCost = new Double[6];
    int[] hourUsage = new int[6];
    public ListView lv;
    public int billLeftAmt;
    int mSum = 0, count = 0,j=0;
    int essentialDevicesCost = 0, totalBillEst = 0;
    String[] appliances = new String[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_monthly_hours);
        Firebase.setAndroidContext(this);
        final int bill = getIntent().getIntExtra("bill", 500);
        billLeftAmt=bill;
        lv = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.totalBill);
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        Firebase neft = myFirebaseRef.child("users").child(mUserId).child("Appliances");
        ref = myFirebaseRef.child("users").child(mUserId).child("app");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String value1 = postSnapshot.getKey();
                    name[i] = value1;
                    if(name[i]=="ac")
                        images[i]=R.drawable.ac;
                    else if(name[i]=="fan" ||name[i]=="fan2")
                        images[i]=R.drawable.fan;
                    else if(name[i]=="motor")
                        images[i]=R.drawable.motor;
                    else if(name[i]=="tv")
                        images[i]=R.drawable.tv;
                    else if(name[i]=="geyser")
                        images[i]=R.drawable.geyser;
                    else if(name[i]=="wm")
                        images[i]=R.drawable.wm;
                    else if(name[i]=="light" || name[i]=="light2")
                        images[i]=R.drawable.bulb1;
                    else if(name[i]=="fridge")
                        images[i]=R.drawable.fridge;
                    else
                        images[i]=R.drawable.back;
                    i++;

                }
                Log.e("rakhsa",String.valueOf(i));
                count=i;
                billDueApp=new int[count];
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        neft.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                i=0;
                Log.e("count", String.valueOf(count));
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Value1 = postSnapshot.getValue(String.class);
                    if (i < count) {
                        hourlyCost[i]=Double.parseDouble(Value1);
                        i++;
                    }
                    else {
                        lastBill[j] = Integer.parseInt(Value1);
                        Log.e("horrr", String.valueOf(lastBill[j]));
                        j++;
                        i++;
                    }
                }


                for(i=0;i<count;i++) {
                    if(name[i]!="fan" && name[i]!="fridge")
                        mSum = mSum + lastBill[i];
                    else
                        essentialDevicesCost = essentialDevicesCost + lastBill[i];
                }
                for(i=0;i<count;i++) {
                    Log.e("billnnnnnnnnnnnnnnn",name[i]);
                    try {
                        if(name[i]=="fan"|| name[i]=="fridge") {
                            hourUsage[i] = (int) (lastBill[i] / hourlyCost[i]);
                            billLeftAmt = bill - essentialDevicesCost;
                        }
                        else {
                            hourUsage[i] = (int) ((((lastBill[i] * billLeftAmt) / mSum) / hourlyCost[i]));
                            Log.e("bill", String.valueOf(billLeftAmt));
                        }
                    } catch (Exception e) {

                    }



                    billDueApp[i] = (int) (hourUsage[i] * hourlyCost[i]);
                    Log.e("bill",String.valueOf(hourUsage[i]));
                    totalBillEst =  totalBillEst + billDueApp[i];
                    Log.e("bill",String.valueOf(totalBillEst));
                    appliances[i]=Integer.toString(hourUsage[i]);

                }
                Log.e("count",String.valueOf(count));
                textView.setText("" + totalBillEst);


                /****************Electricity rates = Rs 4 per unit ****************************/

                Log.e("count111111111",String.valueOf(count));
                for(i=0;i<count;i++)
                {   Log.e("hour",String.valueOf(hourUsage[i]));
                    if(hourUsage[i]>0)
                    {
                        if(hourUsage[i]>168) {
                            hourUsage[i] = 168;
                            appliances[i] = Integer.toString(hourUsage[i]);
                        }
                        else
                            appliances[i] = Integer.toString(hourUsage[i]);
                        Log.e("hour222",String.valueOf(hourUsage[i]));

                    }
                    else
                    {
                        hourUsage[i]=0;
                        appliances[i] = Integer.toString(hourUsage[i]);
                    }
                }

                Adapter adapter= new adapter(ShowMonthlyHours.this,appliances,name,images);

                lv.setAdapter((ListAdapter) adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ShowMonthlyHours.this,PopUp1.class);
                        appSelected = i;
                        startActivityForResult(intent,2);
                        Log.e("ffff","hereeee");
                        //Toast.makeText(Act3.this,"WE are back",Toast.LENGTH_SHORT).show();
                        // notifyAll();
                    }
                });



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("The read failed: ", firebaseError.getMessage());
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==2){
            String msg = data.getStringExtra("msg").toString();
            Log.e("msgggggggggggggg",msg);
            String prev = appliances[appSelected];
            appliances[appSelected] = msg;
            Adapter adapter= new adapter(ShowMonthlyHours.this,appliances,name,images);

            lv.setAdapter((ListAdapter) adapter);

            int prevI = Integer.parseInt(prev);
            int pres = Integer.parseInt(msg);
            int diff = prevI - pres;
            Log.e("eeeeee       EEEEEEEE",String.valueOf(hourlyCost[appSelected]));
            int pDiff = (int) (diff * hourlyCost[appSelected]);
            String Price = textView.getText().toString();
            int price = Integer.parseInt(Price);
            price -= pDiff;
            textView.setText(String.valueOf(price));


            // notifyAll();

        }
    }
}










