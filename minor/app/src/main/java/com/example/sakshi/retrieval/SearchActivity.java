package com.example.sakshi.retrieval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    Button btn;
    EditText edtxt;
    String s;
    int k=0;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mFirebaseUser;
    public String FIREBASE_URL = "https://retrieve-1fea7.firebaseio.com/users/";
    static String[] value = new String[1000];
    Firebase myFirebaseRef,neft2,neft3;
    static String[] fragments=new String[1000];
    ArrayList<String> names;
    String mUserId;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btn = (Button) findViewById(R.id.button4);
        edtxt = (EditText) findViewById(R.id.search_app);
        final TextView levTxt = (TextView)findViewById(R.id.LevTxt);
        names = new ArrayList<>();
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        neft2 = myFirebaseRef.child(mUserId).child("app");
        neft2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String value1 = postSnapshot.getKey();
                    value[count] = value1;
                    count++;

                }
                Log.e("count",String.valueOf(count));
                Log.e("val",value[0]);
                for(int i=0;i<count;i++) {
                    names.add(value[i]);
                    Log.e("val",value[i]);
                }

                edtxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        String str = edtxt.getText().toString();

                        int min =1000;
                        int index =0;
                        for (int in=0;in<count;in++){
                            int x ;
                            String string = names.get(in);
                            x= editDist(str, string, str.length(), min2(string.length(),str.length() +1));
                            if(x<min){
                                min = x;
                                index = in;
                            }
                        }
                        levTxt.setText("        "+names.get(index));
                        levTxt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                edtxt.setText(levTxt.getText());
                            }
                        });


                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s = edtxt.getText().toString();
                Log.e("ssss",s);
                int j = 0, i = 0,k=0;
for(i=0;i<8;i++) {
    j = 0;
    if (i == 0) {
        while (!(OneFragment.appliance[j].equals(s))) {
            Log.e("in loop", OneFragment.appliance[j]);

            j++;
        }

                        if (OneFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "9am-12pm";
                            k++;
                            Log.e("kkk", String.valueOf(k));

                        }
                    }


                   else if(i==1) {
                        while (!(TwoFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                            if (TwoFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "12pm-3pm";
                            k++;
                        }
                    }
                        else if(i==2) {
                        while (!(ThreeFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                        if (ThreeFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "3pm-6pm";
                            k++;
                        }
                    }
                        else if(i==3) {
                        while (!(FourFragment.appliance[j].equals(s))) {
                            Log.e("in loop", FourFragment.appliance[j]);

                            j++;
                        }

                        if (FourFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "6pm-9pm";
                            k++;
                        }
                    }
                        else if(i==4) {
                        while (!(FiveFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                        if (FiveFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "9pm-12am";
                            k++;
                        }
                    }
                        else if(i==5) {
                        while (!(SixFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                        if (SixFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "12am-3am";
                            k++;
                        }
                    }
                        else if(i==6) {
                        while (!(SevenFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                        if (SevenFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "3am-6am";
                            k++;
                        }
                    }
                        else if(i==7) {
                        while (!(EightFragment.appliance[j].equals(s))) {
                            Log.e("in loop", OneFragment.appliance[j]);

                            j++;
                        }

                        if (EightFragment.stat[j].equalsIgnoreCase("ON")) {
                            fragments[k] = "6am-9am";
                            k++;
                        }
                    }

    }
    for (i = 0; i < k; i++)
        Log.e("kkk", fragments[i]);
    Intent intent = new Intent(SearchActivity.this, ListFragment.class);
    intent.putExtra("size", k);
    startActivity(intent);
}




        });

    }

    private int min2(int length, int i) {
        if (length>i)
            return i;
        return length;

    }

    static int min(int x,int y,int z)
    {
        if (x<y && x<z) return x;
        if (y<x && y<z) return y;
        else return z;
    }

    static int editDist(String str1 , String str2 , int m ,int n)
    {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0) return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0) return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (str1.charAt(m-1) == str2.charAt(n-1))
            return editDist(str1, str2, m-1, n-1);

        // If last characters are not same, consider all three
        // operations on last character of first string, recursively
        // compute minimum cost for all three operations and take
        // minimum of three values.
        return 1 + min ( editDist(str1,  str2, m, n-1),    // Insert
                editDist(str1,  str2, m-1, n),   // Remove
                editDist(str1,  str2, m-1, n-1) // Replace
        );
    }

}
