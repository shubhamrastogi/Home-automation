package com.example.sakshi.retrieval;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sakshi on 26/3/17.
 */

public class Adapter1 extends ArrayAdapter<String> {
    int[] imgs=new int[1000];

    String[] statuses=new String[1000];
    String[] names=new String[1000];
    Context c;
    LayoutInflater inflater;


    public Adapter1(Context context,String[] onOroff,String[] names,int[] imgs) {
        super(context, R.layout.model,names);
        this.c=context;
        this.statuses=onOroff;
        this.imgs=imgs;
        this.names=names;
        Log.e("a","incons");
    }
    public class ViewHolder
    {

        TextView textView6;
        ImageView imageViewm;
        TextView appliance;

    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        if(convertView==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.model,null);

        }

        final ViewHolder holder=new ViewHolder();
        Log.e("i","ingetView");

        holder.textView6=(TextView)convertView.findViewById(R.id.textView6);
        holder.appliance=(TextView)convertView.findViewById(R.id.appliance);
        Log.e("i","inViezzzzzzzzzw");
        holder.imageViewm=(ImageView)convertView.findViewById(R.id.imageViewm);
        Log.e("ij","pppp");

        holder.imageViewm.setImageResource(imgs[position]);
        Log.e("aa","ggggggggg");



        holder.textView6.setText(statuses[position]);
        holder.appliance.setText(names[position]);

        Log.e("i","insssssssssssssssssssssssssView");

        return convertView;
    }



}
