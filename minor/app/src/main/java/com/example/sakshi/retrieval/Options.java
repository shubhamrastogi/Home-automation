package com.example.sakshi.retrieval;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.example.sakshi.retrieval.R;
import com.example.sakshi.retrieval.EightFragment;
import com.example.sakshi.retrieval.FiveFragment;
import com.example.sakshi.retrieval.OneFragment;
import com.example.sakshi.retrieval.TwoFragment;
import com.example.sakshi.retrieval.ThreeFragment;
import com.example.sakshi.retrieval.FourFragment;
import com.example.sakshi.retrieval.SixFragment;
import com.example.sakshi.retrieval.SevenFragment;
import com.example.sakshi.retrieval.NineFragment;
import com.example.sakshi.retrieval.TenFragment;
import com.firebase.client.Firebase;


import static com.example.sakshi.retrieval.R.id.info;
import static com.example.sakshi.retrieval.R.id.status_bar_latest_event_content;

public class Options extends AppCompatActivity {
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView img1;
    private int[] imageArray;
    private int currentIndex,strtIndex,endIndex;


    int hs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_basic_layout);
        View view =getLayoutInflater().inflate(R.layout.fragment_one,null);
        startService(view);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
     img1=(ImageView)findViewById(R.id.r1);
        imageArray=new int[10];
        imageArray[0]=R.drawable.i1;
        imageArray[1]=R.drawable.i2;
        imageArray[2]=R.drawable.i3;
        imageArray[3]=R.drawable.i4;
        imageArray[4]=R.drawable.i5;
        imageArray[5]=R.drawable.i6;
        imageArray[6]=R.drawable.i7;
        imageArray[7]=R.drawable.i8;
        imageArray[8]=R.drawable.i9;
        imageArray[9]=R.drawable.i10;
        strtIndex=0;
        endIndex=9;

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_gallery)
                {
                    Intent i=new Intent(Options.this,dummyActivity.class);
                    startActivity(i);

                } else if(id==R.id.nav_search)
                {
                    Intent i1=new Intent(Options.this,SearchActivity.class);
                    startActivity(i1);
                } else if(id==R.id.nav_manage)
                {
                    Intent i3=new Intent(Options.this,PieGraph.class);
                    startActivity(i3);
                }
                else if(id==R.id.nav_settings){
                    Intent i4=new Intent(Options.this,UserScreen.class);
                    startActivity(i4);
                }
                return false;
            }
        });
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        Calendar now2 = Calendar.getInstance();

        hs= now2.get(Calendar.HOUR_OF_DAY);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        nextImage();
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(v);
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(v);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(v);
            }
        });
    }
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "9am-12pm");
        adapter.addFrag(new TwoFragment(), "12pm-3pm");
      adapter.addFrag(new ThreeFragment(), "3pm-6pm");
       adapter.addFrag(new FourFragment(), "6pm-9pm");
        adapter.addFrag(new FiveFragment(), "9pm-12am");
        adapter.addFrag(new SixFragment(), "12am-3am");
        adapter.addFrag(new SevenFragment(), "3am-6am");
        adapter.addFrag(new EightFragment(), "6am-9am");
        viewPager.setAdapter(adapter);
     /*   new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish(){
                if(hs>=9 && hs<=11)
                    viewPager.setCurrentItem(0);
                if(hs>=12 && hs<=14)
                    viewPager.setCurrentItem(1);
                if(hs>=15 && hs<=17)
                    viewPager.setCurrentItem(2);
                if(hs>=18 && hs<=20)
                    viewPager.setCurrentItem(3);
                if(hs>=21 && hs<=23)
                    viewPager.setCurrentItem(4);
                if(hs>=00 && hs<=2)
                    viewPager.setCurrentItem(5);
                if(hs>=3 && hs<=5)
                    viewPager.setCurrentItem(6);
                if(hs>=6 && hs<=8)
                    viewPager.setCurrentItem(7);*/
        //    }
        //}.start();

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void onclick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:

                Intent intent = new Intent(Options.this,ShoppyMain.class);
                startActivity(intent);
                break;
            case R.id.fab2:

                Intent intent1 = new Intent(Options.this,BillBarInput.class);
                startActivity(intent1);
                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }
    public void nextImage()
    {
        img1.setImageResource(imageArray[currentIndex]);
        Animation rotateimage=AnimationUtils.loadAnimation(this,R.anim.custom_anim);
        img1.startAnimation(rotateimage);
        currentIndex++;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                if(currentIndex>endIndex){
                    currentIndex--;
                    prevImage();
                }else{
                    nextImage();
                }
            }
        },10000);

    }
    public void prevImage()
    {
        img1.setImageResource(imageArray[currentIndex]);
        Animation rotateimage=AnimationUtils.loadAnimation(this,R.anim.custom_anim);
        img1.startAnimation(rotateimage);
        currentIndex--;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                if(currentIndex<strtIndex){
                    currentIndex++;
                    nextImage();
                }else{
                    prevImage();
                }
            }
        },10000);

    }
}

