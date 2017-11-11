package com.example.sakshi.retrieval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListFragment extends AppCompatActivity {
String[] frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment);
        int k=getIntent().getIntExtra("size",0);
        frag=new String[k];
        for(int i=0;i<k;i++)
        {
            frag[i]=SearchActivity.fragments[i];
        }
      ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,frag);
       ListView lv=(ListView)findViewById(R.id.list9);
       lv.setAdapter(adapter);
    }
}
