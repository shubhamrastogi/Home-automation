package com.example.sakshi.retrieval;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Arrays;

public class RetrieveData extends AppCompatActivity {
    private TextView text, text1, text2, text3, text4;
    private Firebase mref1, mref2, mref3, mref4, mref5;
    private Firebase mref6, mref7, mref8, mref9, mref10;
    private Firebase mref11, mref12, mref13, mref14, mref15;
    private Firebase mref16, mref17, mref18, mref19, mref20;
    private Firebase mref21, mref22, mref23, mref24, mref25;
    public ArrayList<String> array = new ArrayList<>();
    private ListView list;
    public String userI1;
    public String userI2;

    int flag1=0,flag2=0;
    //private EditText t;
    private long id = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  t=(EditText) findViewById(R.id.editText2);

        //  t.getText().toString();

       /* mref6=new Firebase("https://retrieve-1fea7.firebaseio.com/item/AC/ac001");
        mref7=new Firebase("https://retrieve-1fea7.firebaseio.com/item/AC/ac002");
        mref8=new Firebase("https://retrieve-1fea7.firebaseio.com/item/AC/ac003");
        mref9=new Firebase("https://retrieve-1fea7.firebaseio.com/item/AC/ac004");
        mref10=new Firebase("https://retrieve-1fea7.firebaseio.com/item/AC/ac005");
        mref11=new Firebase("https://retrieve-1fea7.firebaseio.com/item/Tv/tv001");
        mref12=new Firebase("https://retrieve-1fea7.firebaseio.com/item/Tv/tv002");
        mref13=new Firebase("https://retrieve-1fea7.firebaseio.com/item/Tv/tv003");
        mref14=new Firebase("https://retrieve-1fea7.firebaseio.com/item/Tv/tv004");
        mref15=new Firebase("https://retrieve-1fea7.firebaseio.com/item/Tv/tv005");
        mref16=new Firebase("https://retrieve-1fea7.firebaseio.com/refrigerators/fr001");
        mref17=new Firebase("https://retrieve-1fea7.firebaseio.com/item/refrigerators/fr002");
        mref18=new Firebase("https://retrieve-1fea7.firebaseio.com/item/refrigerators/fr003");
        mref19=new Firebase("https://retrieve-1fea7.firebaseio.com/item/refrigerators/fr004");
        mref20=new Firebase("https://retrieve-1fea7.firebaseio.com/item/refrigerators/fr005");
        mref21=new Firebase("https://retrieve-1fea7.firebaseio.com/item/washing machine/wm001");
        mref22=new Firebase("https://retrieve-1fea7.firebaseio.com/item/washing machine/wm002");
        mref23=new Firebase("https://retrieve-1fea7.firebaseio.com/item/washing machine/wm003");
        mref24=new Firebase("https://retrieve-1fea7.firebaseio.com/item/washing machine/wm004");
        mref25=new Firebase("https://retrieve-1fea7.firebaseio.com/item/washing machine/wm005");*/
        // list = (ListView) findViewById(R.id.listview);
        userI1 = (String) getIntent().getExtras().get("prod1");
        userI2 = (String) getIntent().getExtras().get("prod2");

        mref1 = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/fan001");
        mref2 = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/fan002");
        mref3 = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/fan003");
        mref4 = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/fan004");
        mref5 = new Firebase("https://retrieve-1fea7.firebaseio.com/item/fans/fan005");
        mref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                array.add(value);
                //adapter.notifyDataSetChanged();
                if(flag1==0) {
                if (array.contains(userI1) ) {

                        flag1 = 1;
                        Toast.makeText(RetrieveData.this, "congo,1st product exists", Toast.LENGTH_SHORT).show();
                    }

                }
                if(flag2==0)
                {
                    if (array.contains(userI2)) {

                        flag2 = 1;
                        Toast.makeText(RetrieveData.this, "congo,2nd product exists", Toast.LENGTH_SHORT).show();
                    }

                }
                if(flag1==1 && flag2==1)
                {
                    Intent intent=new Intent(RetrieveData.this,ShoppyFan.class);
                    intent.putExtra("pro1",userI1);
                    intent.putExtra("pro",userI2);
                    startActivity(intent);
                }


                //array.add("raksha");

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

       Log.e("aa","rakssssssssssssssssss");
        mref2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                array.add(value);
                if(flag1==0){
                if (array.contains(userI1)) {

                       Toast.makeText(RetrieveData.this, "congo! 1st product exists", Toast.LENGTH_SHORT).show();
                       flag1=1;
                   }

                }
                if(flag2==0)
                {
                if (array.contains(userI2)) {

                        Toast.makeText(RetrieveData.this, "congo,2nd product exists", Toast.LENGTH_SHORT).show();
                        flag2=1;
                    }

                }
                if(flag1==1 && flag2==1)
                {
                    Intent intent=new Intent(RetrieveData.this,ShoppyFan.class);
                    intent.putExtra("pro1",userI1);
                    intent.putExtra("pro",userI2);
                    startActivity(intent);
                    Log.e("goinfff","goinggggg");
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


       mref3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                array.add(value);

                //adapter.notifyDataSetChanged();
                if(flag1==0){
                    if (array.contains(userI1)) {

                        Toast.makeText(RetrieveData.this, "congo! 1st product exists", Toast.LENGTH_SHORT).show();
                        flag1=1;
                    }

                }
                if(flag2==0)
                {
                    if (array.contains(userI2)) {

                        Toast.makeText(RetrieveData.this, "congo,2nd product exists", Toast.LENGTH_SHORT).show();
                        flag2=1;
                    }

                }
                if(flag1==1 && flag2==1)
                {
                    Intent intent=new Intent(RetrieveData.this,ShoppyFan.class);
                    intent.putExtra("pro1",userI1);
                    intent.putExtra("pro",userI2);
                    startActivity(intent);
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


        mref4.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                array.add(value);

                if(flag1==0){
                    if (array.contains(userI1)) {

                        Toast.makeText(RetrieveData.this, "congo! 1st product exists", Toast.LENGTH_SHORT).show();
                        flag1=1;
                    }

                }
                if(flag2==0)
                {
                    if (array.contains(userI2)) {

                        Toast.makeText(RetrieveData.this, "congo,2nd product exists", Toast.LENGTH_SHORT).show();
                        flag2=1;
                    }

                }
                if(flag1==1 && flag2==1)
                {
                    Intent intent=new Intent(RetrieveData.this,ShoppyFan.class);
                    intent.putExtra("pro1",userI1);
                    intent.putExtra("pro",userI2);
                    startActivity(intent);
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


        mref5.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                array.add(value);

                //adapter.notifyDataSetChanged();
                if(flag1==0){
                    if (array.contains(userI1)) {

                        Toast.makeText(RetrieveData.this, "congo! 1st product exists", Toast.LENGTH_SHORT).show();
                        flag1=1;
                    }

                }
                if(flag2==0)
                {
                    if (array.contains(userI2)) {

                        Toast.makeText(RetrieveData.this, "congo,2nd product exists", Toast.LENGTH_SHORT).show();
                        flag2=1;
                    }

                }
                if(flag1==1 && flag2==1)
                {
                    Intent intent=new Intent(RetrieveData.this,ShoppyFan.class);
                    intent.putExtra("pro1",userI1);
                    intent.putExtra("pro",userI2);
                    startActivity(intent);
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


        // Log.e("dd","fucccccccccccccccccccccccccccccccccccccc");
        //Log.e("ddd",userI1);


    }
}


      /*  if(id==6) {
            mref6.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==7) {
            mref7.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==8){
            mref8.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==9) {
            mref9.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==10)
        {
            mref10.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value=dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
            });}



        if(id==11) {
            mref11.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==12) {
            mref12.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==13){
            mref13.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==14) {
            mref14.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==15)
        {
            mref15.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value=dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
            });}



        if(id==16) {
            mref16.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==17) {
            mref17.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==18){
            mref18.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==19) {
            mref19.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==20)
        {
            mref20.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value=dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
            });}



        if(id==21) {
            mref21.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==22) {
            mref22.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==23){
            mref23.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==24) {
            mref24.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
        if(id==25)
        {
            mref25.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value=dataSnapshot.getValue(String.class);
                    array.add(value);

                    adapter.notifyDataSetChanged();
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
            });}
*/


