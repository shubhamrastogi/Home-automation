package com.example.sakshi.retrieval;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BillBarInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_bar_input);

        final EditText estBill = (EditText) findViewById(R.id.estBill);

        Button estBtn = (Button) findViewById(R.id.estBtn);

        estBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bill = Integer.valueOf(estBill.getText().toString());
                Intent intent = new Intent(BillBarInput.this, ShowMonthlyHours.class);
                intent.putExtra("bill", bill);
                startActivity(intent);
            }
        });

    }




}

