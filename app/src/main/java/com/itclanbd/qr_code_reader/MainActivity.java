package com.itclanbd.qr_code_reader;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultview;
    Button capture;
    static String result_String="Scane is not complete";
    int REQUEST_CODE_FOR_QR = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultview=(TextView)findViewById(R.id.result_show);
        capture=(Button)findViewById(R.id.cam_qr);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Result_activity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_QR);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_FOR_QR && resultCode == RESULT_OK && data!= null){
            result_String=data.getStringExtra(result_String);
            resultview.setText(result_String);
        }

    }
}
