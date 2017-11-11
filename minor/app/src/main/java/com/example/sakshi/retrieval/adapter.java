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

public class adapter extends ArrayAdapter<String> {
    int[] imgs=new int[60];
    String[] app=new String[1000];
    String[] statuses=new String[1000];
    Context c;
    LayoutInflater inflater;


    public adapter(Context context, String[] appliances,String[] onOroff,int[] imgs) {
        super(context, R.layout.model,appliances);
        this.c=context;
        this.app=appliances;
        this.statuses=onOroff;
        this.imgs=imgs;

    }
    public class ViewHolder
    {

        TextView appliance;
        TextView textView6;
        ImageView imageViewm;

    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

         if(convertView==null)
         {
             inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView=inflater.inflate(R.layout.model,null);

         }

         final ViewHolder holder=new ViewHolder();
        holder.appliance=(TextView)convertView.findViewById(R.id.appliance);
        holder.textView6=(TextView)convertView.findViewById(R.id.textView6);
        holder.imageViewm=(ImageView)convertView.findViewById(R.id.imageViewm);

        holder.imageViewm.setImageResource(imgs[position]);;

        holder.appliance.setText(app[position]);

        holder.textView6.setText(statuses[position]);



        return convertView;
    }



}
