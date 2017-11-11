package com.example.sakshi.retrieval;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ShoppyMain extends AppCompatActivity {
    String scanned;
    String[] dost;
    AutoCompleteTextView pro1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppy_main);
        pro1 = (AutoCompleteTextView) findViewById(R.id.autoTextty);
        dost = getResources().getStringArray(R.array.YoNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dost);
        pro1.setAdapter(adapter);
        Button ok = (Button)findViewById(R.id.okBtn);
        Button scan = (Button)findViewById(R.id.QRbtn1);
        final Activity activity = this;

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCAN");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pro ;
                pro = pro1.getText().toString();
                if(pro.equals("")){
                    Toast.makeText(ShoppyMain.this,"please enter product code",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //Log.e("rrrr","fuccccccccccccccccccccccccccccccccccckkk");
                    Intent intent = new Intent(ShoppyMain.this, Shoppy2.class);
                    intent.putExtra("userInput1",pro);
                    Log.e("prrooo",pro);
                    startActivity(intent);

                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this,"You cancelled",Toast.LENGTH_SHORT).show();
            }
            else{
                scanned=result.getContents();

                Toast.makeText(this,scanned, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShoppyMain.this, Shoppy2.class);

                intent.putExtra("userInput1",scanned);
                startActivity(intent);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
