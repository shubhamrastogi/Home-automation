package com.example.sakshi.retrieval;


//import android.icu.text.DateFormat;
//import android.icu.text.SimpleDateFormat;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import com.firebase.client.Config;
import com.firebase.client.FirebaseError;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.firebase.client.Firebase;

public class dummyActivity extends AppCompatActivity {
    protected TextView text;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mFirebaseUser;
    public String FIREBASE_URL = "https://retrieve-1fea7.firebaseio.com/users/";
    private Firebase ref, neft;
    private String mUserId;
    Date start;
    Date stop;
    long x1;
    static int t;
    String start1;
    String stop1;
    static String[] value = new String[1000];
    String[] duration= new String[1000];
    int hour1, hour2, min1, min2,hs;
    long ms, me;
    int i = 0;
    long dur, h, m, s;
    Firebase myFirebaseRef,neft2,neft3;
    private static final int SECOND = 1000;
    int flag2=0;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    public static final String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    private int Num_row = 3,sec;
    public static int count;
    private int Num_col = 3;
    String[][] yesno;
    Integer[] array2=new Integer[1000];
    int newWidth=2,newHeight=2;
    static String[] name=new String[1000];
static Integer[] valpie=new Integer[1000];
    ToggleButton[][] buttons=new ToggleButton[6][6];
    static Boolean switchState;
    String[] status=new String[10000];
    SharedPreferences mPrefs;
    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy2);
        Firebase.setAndroidContext(this);
        Calendar now2 = Calendar.getInstance();

        hs= now2.get(Calendar.HOUR_OF_DAY);
        min1 = now2.get(Calendar.MINUTE);
        hour1=hs;
        ms = System.currentTimeMillis();

        if(hs>=9 && hs<=11) {
            status = OneFragment.stat;
        }
        if(hs>=12 && hs<=14)
          status=TwoFragment.stat;
        if(hs>=15 && hs<=17)
          status=ThreeFragment.stat;
        if(hs>=18 && hs<=20) {
            status=FourFragment.stat;
        }
        if(hs>=21 && hs<=23) {
           status=FiveFragment.stat;
        }
        if(hs>=00 && hs<=2)
           status=SixFragment.stat;
        if(hs>=3&& hs<=5)
           status=SevenFragment.stat;
        if(hs>=6&& hs<=8) {
            status = EightFragment.stat;
        }
        int mf = now2.get(Calendar.MINUTE);
        /*if(hs==9 && mf==0)
        {
            getValuet(c);
            t++;
            neft.child("day").setValue(t);
        }*/
        int hr =0 ,min = 0;
        int thr = 8, tmin =59;


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            hr = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
        }

        if (hr>thr){
            hr = hr - thr - 1;
            min = tmin - min;
        }
        else {
            hr = thr - hr - 1;
            min = tmin - min;
        }

        min = (hr * 60) + min;
       sec = min * 60;

        myFirebaseRef = new Firebase("https://retrieve-1fea7.firebaseio.com/users/");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        neft = myFirebaseRef.child(mUserId);
         neft2 = myFirebaseRef.child(mUserId).child("app");
        neft3=myFirebaseRef.child(mUserId).child("day2");
        neft2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String value1 = postSnapshot.getKey();
                    value[i] = value1;
                    duration[i]=postSnapshot.getValue(String.class);
                    i++;

                }
               populateButtons(i);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


    }

    public void populateButtons(int i) {
        count=i;
        int c = 0, flag = 0,l=0;

        if (i % 3 != 0)
            Num_row = (i / 3) + 1;
        else
            Num_row = i / 3;

       // buttons = new ToggleButton[Num_row][Num_col];
        TableLayout table = (TableLayout) findViewById(R.id.tableforbuttons);
        for (int row = 0; row < Num_row; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int col = 0; col < Num_col; col++) {
                c++;
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                ToggleButton button = new ToggleButton(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeState(FINAL_ROW, FINAL_COL, v);
                    }
                });

                button.setTextOff(value[l]+"\r\noff");

                button.setTextOn(value[l]+"\r\non");
                l++;

               // button.setBackgroundResource(R.drawable.images);
                tableRow.addView(button);
                buttons[row][col] = button;

                if (c == i) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        changeStateApp(status,i);

    }


    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
    public void startAlert() {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ (sec * 1000), pendingIntent);
       // Toast.makeText(this, "Alarm after " + sec + " seconds",Toast.LENGTH_LONG).show();
    }
    public void updateDate()
    {

    }
   public  int getValuet(final int c)
    {
        Log.e("hereeeeeeeee","ttttttttttttttt");
        neft.child("day").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                t=  dataSnapshot.getValue(int.class);
                Log.e("durtttt",String.valueOf(t));
                long dur=getValueOfBar(c,t);
                dur=dur+x1;
                Log.e("durccc",String.valueOf(c));
                Log.e("dur",String.valueOf(dur));
                Log.e("durtttt",value[c]);
                neft.child("barGraph").child(value[c]).child(String.valueOf(t)).setValue(dur);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
return t;
    }

    public void changeState(int x, int y, View view) {

        Boolean checked = ((ToggleButton) view).isChecked();
        if (checked) {

    buttons[x][y].setBackgroundColor(Color.GREEN);
            Calendar now = Calendar.getInstance();

            hour1 = now.get(Calendar.HOUR_OF_DAY);
            min1 = now.get(Calendar.MINUTE);
            start = parseDate(hour1 + ":" + min1);
            ms = System.currentTimeMillis();

        } else {
            buttons[x][y].setBackgroundColor(Color.RED);
            Calendar now = Calendar.getInstance();
            hour2 = now.get(Calendar.HOUR_OF_DAY);
            min2 = now.get(Calendar.MINUTE);
            stop = parseDate(hour2 + ":" + min2);
            Log.e("in off","offffffffffffffff");
            me = System.currentTimeMillis();
            x1 = caldur(me, ms);
            c=x*3+y;
            Log.e("c",String.valueOf(c));
          t=getValuet(c);
            Log.e("ccccccccc",String.valueOf(c));
            if (hour1 >= 9 && hour2 <= 11) {

                        neft.child("day3").child("9am-12pm").child(value[c]).setValue(x1);



            }

                    if (hour1 >= 12 && hour2 <= 14) {
                        neft.child("day3").child("12pm-3pm").child(value[c]).setValue(x1);

                    }

                    if (hour1 >= 15 && hour2 <= 17) {
                        Log.e("sd", "here");
                        neft.child("day3").child("3pm-6pm").child(value[c]).setValue(x1);

                    }

                    if (hour1 >= 18 && hour2 <= 20) {
                        neft.child("day3").child("6pm-9pm").child(value[c]).setValue(x1);


                    }

                    if (hour1 >= 21 && hour2 <= 23) {
                        neft.child("day3").child("9pm-12am").child(value[c]).setValue(x1);



                    }

                    if (hour1 >= 00 && hour2 <= 2) {
                        neft.child("day3").child("12am-3am").child(value[c]).setValue(x1);
                        Log.e("x1",String.valueOf(x1));
                     /*   if(hour2==23 && min2==54)
                        {
                            switchVal1();
                            getDataOfPie();
                            neft.child("piechart").child(value[c]).setValue();
                        }

*/

                    }

                    if (hour1 >= 3 && hour2 <= 5) {
                        neft.child("day3").child("3am-6am").child(value[c]).setValue(x1);

                    }

                    if (hour1 >= 6 && hour2 <= 8) {
                        neft.child("day3").child("6am-9am").child(value[c]).setValue(x1);



                    }
                }
            }
            long getValueOfBar(int c,int t)
            {  final long[] days=new long[8];
                  i=0;
                Log.e("c",String.valueOf(c));
                neft.child("barGraph").child(value[c]).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                            long z=postSnapshot.getValue(long.class);
                            days[i]=z;
                            i++;
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                return days[t-1];
              //return 0;
            }
            void getDataOfPie()
            {
                neft.child("piechart").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        i=0;

                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                            Integer z=postSnapshot.getValue(Integer.class);
                            valpie[i]=z;
                            Log.e("valpie",String.valueOf(valpie[i]));
                            i++;
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }



            void switchVal2()
            {
                neft.child("day3").child("12am-3am").addValueEventListener(new ValueEventListener() {
                   int h1;

                                                                               @Override
                                                                               public void onDataChange(DataSnapshot snapshot) {
                                                                                   i=0;
                                                                                   for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                       Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                       array2[i] = Value2;
                                                                                       name[i]=postSnapshot.getKey();
                                                                                            neft.child("bargraph").child(name[i]).child(String.valueOf(c)).setValue(array2[i]);
                                                                                       h1=array2[i]/60;
                                                                                       valpie[i]=valpie[i]+h1;

                                                                                       neft.child("piechart").child(name[i]).setValue(valpie[i]);
                                                                                       i++;

                                                                                   }

                                                                                   for(i=0;i<count;i++)
                                                                                   {
                                                                                       neft.child("day2").child("12am-3am").child(name[i]).setValue(array2[i]);
                                                                                   }
                                                                               }

                                                                               @Override
                                                                               public void onCancelled(FirebaseError firebaseError) {

                                                                               }
                                                                           }
                );

                neft.child("day3").child("12pm-3pm").addValueEventListener(new ValueEventListener() {
                                                                               @Override
                                                                               public void onDataChange(DataSnapshot snapshot) {
                                                                                   i=0;
                                                                                   for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                       Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                       array2[i] = Value2;
                                                                                       name[i]=postSnapshot.getKey();
                                                                                       int h1=array2[i]/60;
                                                                                       valpie[i]=valpie[i]+h1;
                                                                                       neft.child("piechart").child(name[i]).setValue(valpie[i]);
                                                                                       i++;

                                                                                   }
                                                                                   for(i=0;i<count;i++)
                                                                                   {
                                                                                       neft.child("day2").child("12pm-3pm").child(name[i]).setValue(array2[i]);
                                                                                   }
                                                                               }

                                                                               @Override
                                                                               public void onCancelled(FirebaseError firebaseError) {

                                                                               }
                                                                           }
                );

               neft.child("day3").child("3am-6am").addValueEventListener(new ValueEventListener() {
                                                                              @Override
                                                                              public void onDataChange(DataSnapshot snapshot) {
                                                                                  i=0;
                                                                                  for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                      Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                      array2[i] = Value2;
                                                                                      name[i]=postSnapshot.getKey();

                                                                                      int h1=array2[i]/60;
                                                                                      valpie[i]=valpie[i]+h1;
                                                                                      Log.e("valll",String.valueOf(valpie[i]));
                                                                                      neft.child("piechart").child(name[i]).setValue(valpie[i]);

                                                                                      i++;

                                                                                  }
                                                                                  for(i=0;i<count;i++)
                                                                                  {
                                                                                      neft.child("day2").child("3am-6am").child(name[i]).setValue(array2[i]);
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void onCancelled(FirebaseError firebaseError) {

                                                                              }
                                                                          }
                );

                neft.child("day3").child("3pm-6pm").addValueEventListener(new ValueEventListener() {
                                                                              @Override
                                                                              public void onDataChange(DataSnapshot snapshot) {
                                                                                  i=0;
                                                                                  for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                      Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                      array2[i] = Value2;
                                                                                      name[i]=postSnapshot.getKey();

                                                                                      int h1=array2[i]/60;
                                                                                      valpie[i]=valpie[i]+h1;
                                                                                      Log.e("valll1111",String.valueOf(valpie[i]));
                                                                                      neft.child("piechart").child(name[i]).setValue(valpie[i]);
                                                                                      i++;

                                                                                  }
                                                                                  for(i=0;i<count;i++)
                                                                                  {
                                                                                      neft.child("day2").child("3pm-6pm").child(name[i]).setValue(array2[i]);
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void onCancelled(FirebaseError firebaseError) {

                                                                              }
                                                                          }
                );

              neft.child("day3").child("6am-9am").addValueEventListener(new ValueEventListener() {
                                                                              @Override
                                                                              public void onDataChange(DataSnapshot snapshot) {
                                                                                  i=0;
                                                                                  for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                      Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                      array2[i] = Value2;
                                                                                      name[i]=postSnapshot.getKey();

                                                                                      int h1=array2[i]/60;
                                                                                      valpie[i]=valpie[i]+h1;
                                                                                      Log.e("valll",String.valueOf(valpie[i]));
                                                                                      neft.child("piechart").child(name[i]).setValue(valpie[i]);
                                                                                      i++;

                                                                                  }
                                                                                  for(i=0;i<count;i++)
                                                                                  {
                                                                                      neft.child("day2").child("6am-9am").child(name[i]).setValue(array2[i]);
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void onCancelled(FirebaseError firebaseError) {

                                                                              }
                                                                          }
                );

                neft.child("day3").child("6pm-9pm").addValueEventListener(new ValueEventListener() {
                                                                              @Override
                                                                              public void onDataChange(DataSnapshot snapshot) {
                                                                                  i=0;
                                                                                  for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                      Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                      array2[i] = Value2;
                                                                                      name[i]=postSnapshot.getKey();
                                                                                      int h1=array2[i]/60;
                                                                                      valpie[i]=valpie[i]+h1;
                                                                                      c++;
                                                                                      Log.e("vall2222222l",String.valueOf(valpie[i]));
                                                                                      neft.child("piechart").child(name[i]).setValue(valpie[i]);
                                                                                      i++;

                                                                                  }
                                                                                  for(i=0;i<count;i++)
                                                                                  {
                                                                                      neft.child("day2").child("6pm-9pm").child(name[i]).setValue(array2[i]);
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void onCancelled(FirebaseError firebaseError) {

                                                                              }
                                                                          }
                );

             neft.child("day3").child("9am-12pm").addValueEventListener(new ValueEventListener() {
                                                                               @Override
                                                                               public void onDataChange(DataSnapshot snapshot) {
                                                                                   i=0;
                                                                                   for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                       Log.e("raksha","rrrrrrrrrrrrrrr");
                                                                                       Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                       array2[i] = Value2;
                                                                                       name[i]=postSnapshot.getKey();
                                                                                       int h1=array2[i]/60;
                                                                                       valpie[i]=valpie[i]+h1;
                                                                                       Log.e("valll333333333",String.valueOf(valpie[i]));
                                                                                       neft.child("piechart").child(name[i]).setValue(valpie[i]);


                                                                                       i++;

                                                                                   }
                                                                                   for(i=0;i<count;i++)
                                                                                   {
                                                                                       neft.child("day2").child("9am-12pm").child(name[i]).setValue(array2[i]);
                                                                                   }
                                                                               }

                                                                               @Override
                                                                               public void onCancelled(FirebaseError firebaseError) {

                                                                               }
                                                                           }
                );


            }


    void switchVal1()
    {
        neft.child("day2").child("12am-3am").addValueEventListener(new ValueEventListener() {
                                                                       @Override
                                                                       public void onDataChange(DataSnapshot snapshot) {
                                                                           i=0;
                                                                           for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                               Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                               array2[i] = Value2;
                                                                               name[i]=postSnapshot.getKey();


                                                                               i++;

                                                                           }
                                                                           for(i=0;i<count;i++)
                                                                           {
                                                                               neft.child("day1").child("12am-3am").child(name[i]).setValue(array2[i]);
                                                                           }
                                                                       }

                                                                       @Override
                                                                       public void onCancelled(FirebaseError firebaseError) {

                                                                       }
                                                                   }
        );

        neft.child("day2").child("12pm-3pm").addValueEventListener(new ValueEventListener() {
                                                                       @Override
                                                                       public void onDataChange(DataSnapshot snapshot) {
                                                                           i=0;
                                                                           for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                               Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                               array2[i] = Value2;
                                                                               name[i]=postSnapshot.getKey();


                                                                               i++;

                                                                           }
                                                                           for(i=0;i<count;i++)
                                                                           {
                                                                               neft.child("day1").child("12-3").child(name[i]).setValue(array2[i]);
                                                                           }
                                                                       }

                                                                       @Override
                                                                       public void onCancelled(FirebaseError firebaseError) {

                                                                       }
                                                                   }
        );

        neft.child("day2").child("3am-6am").addValueEventListener(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(DataSnapshot snapshot) {
                                                                          i=0;
                                                                          for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                              Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                              array2[i] = Value2;
                                                                              name[i]=postSnapshot.getKey();


                                                                              i++;

                                                                          }
                                                                          for(i=0;i<count;i++)
                                                                          {
                                                                              neft.child("day1").child("3am-6am").child(name[i]).setValue(array2[i]);
                                                                          }
                                                                      }

                                                                      @Override
                                                                      public void onCancelled(FirebaseError firebaseError) {

                                                                      }
                                                                  }
        );

        neft.child("day2").child("3pm-6pm").addValueEventListener(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(DataSnapshot snapshot) {
                                                                          i=0;
                                                                          for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                              Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                              array2[i] = Value2;
                                                                              name[i]=postSnapshot.getKey();


                                                                              i++;

                                                                          }
                                                                          for(i=0;i<count;i++)
                                                                          {
                                                                              neft.child("day1").child("3-6").child(name[i]).setValue(array2[i]);
                                                                          }
                                                                      }

                                                                      @Override
                                                                      public void onCancelled(FirebaseError firebaseError) {

                                                                      }
                                                                  }
        );

        neft.child("day2").child("6am-9am").addValueEventListener(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(DataSnapshot snapshot) {
                                                                          i=0;
                                                                          for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                              Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                              array2[i] = Value2;
                                                                              name[i]=postSnapshot.getKey();


                                                                              i++;

                                                                          }
                                                                          for(i=0;i<count;i++)
                                                                          {
                                                                              neft.child("day1").child("6am-9am").child(name[i]).setValue(array2[i]);
                                                                          }
                                                                      }

                                                                      @Override
                                                                      public void onCancelled(FirebaseError firebaseError) {

                                                                      }
                                                                  }
        );

        neft.child("day2").child("6pm-9pm").addValueEventListener(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(DataSnapshot snapshot) {
                                                                          i=0;
                                                                          for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                              Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                              array2[i] = Value2;
                                                                              name[i]=postSnapshot.getKey();



                                                                              i++;

                                                                          }
                                                                          for(i=0;i<count;i++)
                                                                          {
                                                                              neft.child("day1").child("6-9").child(name[i]).setValue(array2[i]);
                                                                          }
                                                                      }

                                                                      @Override
                                                                      public void onCancelled(FirebaseError firebaseError) {

                                                                      }
                                                                  }
        );

        neft.child("day2").child("9am-12pm").addValueEventListener(new ValueEventListener() {
                                                                       @Override
                                                                       public void onDataChange(DataSnapshot snapshot) {
                                                                           i=0;
                                                                           for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                               Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                               array2[i] = Value2;
                                                                               name[i]=postSnapshot.getKey();

                                                                               neft.child("day1").child("9-12").child(name[i]).setValue(array2[i]);
                                                                               i++;

                                                                           }

                                                                       }

                                                                       @Override
                                                                       public void onCancelled(FirebaseError firebaseError) {

                                                                       }
                                                                   }
        );

                neft.child("day2").child("9pm-12am").addValueEventListener(new ValueEventListener() {
                                                                               @Override
                                                                               public void onDataChange(DataSnapshot snapshot) {
                                                                                   i=0;
                                                                                   for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                                       Integer  Value2 = postSnapshot.getValue(Integer.class);
                                                                                       array2[i] = Value2;
                                                                                       name[i]=postSnapshot.getKey();

                                                                                        neft.child("day1").child("9pm-12am").child(name[i]).setValue(array2[i]);
                                                                                      // neft3.child("9pm-12am").child(name[i]).setValue(array2[i]);
                                                                                       i++;

                                                                                   }

                                                                               }

                                                                               @Override
                                                                               public void onCancelled(FirebaseError firebaseError) {

                                                                               }
                                                                        }
                );


    }

                public long caldur(long me, long ms) {
        Log.e("incal","duration");
        dur = me - ms;
        if (dur > HOUR) {
            h = dur / HOUR;
            dur %= HOUR;
        }

        if (dur > MINUTE) {
            m = dur / MINUTE;
            dur %= MINUTE;
        }
        if (dur > SECOND) {
            s = dur / SECOND;
            dur %= SECOND;
        }
        m = m + h * 60 + (m / 60);
        return m;

    }




    public void changeStateApp(String[] yesno, int size) {
        Log.e("dummm","dummmmy");
       // buttons=populateButtons(size);
        for (i = 0; i < size; i++) {
            int num = i + 1, c = 0, flag1 = 0, x = 0, y = 0;
            for (int k = 0; k < Num_row; k++) {
                for (int j = 0; j < 3; j++) {
                    c++;
                    if (c == num) {
                        flag1 = 1;
                        y = j;
                        break;
                    }
                }
                if (flag1 == 1) {
                    x = k;
                    break;
                }
            }


                ToggleButton button = buttons[x][y];
                switchState = button.isChecked();
              if (yesno[i].equalsIgnoreCase("OFF")) {
                  if (switchState) {


                        button.toggle();
                    }

                } else {
                    if (!switchState)

                        button.toggle();
                }
            }

        }

    }








