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
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Shoppy2 extends AppCompatActivity {
    public static RetrieveData obj=new RetrieveData();
    public String userI11,scanned1;
    String[] dost;
    AutoCompleteTextView pro1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fff","sdddddddddddddd");
        setContentView(R.layout.activity_shoppy_main);
        Log.e("ninni","niiiii");
      //  final EditText pro1 = (EditText)findViewById(R.id.pro1);
        pro1 = (AutoCompleteTextView) findViewById(R.id.autoTextty);
        dost = getResources().getStringArray(R.array.YoNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dost);
        pro1.setAdapter(adapter);

        Button ok = (Button)findViewById(R.id.okBtn);
        Button scan = (Button)findViewById(R.id.QRbtn1);
        final Activity activity = this;
        userI11 = (String) getIntent().getExtras().get("userInput1");
        Log.e("user",userI11);
        //final String pro;
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



        //      tv.setText(p1);



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pro;
                pro = pro1.getText().toString();
                if(pro.equals("")){
                    Toast.makeText(Shoppy2.this,"please enter product code",Toast.LENGTH_SHORT).show();
                }
                else
                {


                    Intent intent = new Intent(Shoppy2.this,RetrieveData.class);

                    intent.putExtra("prod1",userI11);
                    intent.putExtra("prod2",pro);

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
                scanned1=result.getContents();
                Toast.makeText(this,scanned1, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Shoppy2.this,RetrieveData.class);
                intent.putExtra("prod1",userI11);
                intent.putExtra("prod2",scanned1);
                startActivity(intent);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}