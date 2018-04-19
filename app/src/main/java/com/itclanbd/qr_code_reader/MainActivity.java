package com.itclanbd.qr_code_reader;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.itclanbd.qr_code_reader.barcode.BarcodeCaptureActivity;

public class MainActivity extends AppCompatActivity {

    TextView resultview;
    Button capture;
    static String result_String="Scane is not complete";
    int REQUEST_CODE_FOR_QR = 1;
    int BARCODE_READER_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultview=(TextView)findViewById(R.id.result_show);
        capture=(Button)findViewById(R.id.cam_qr);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(getApplicationContext(), Result_activity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_QR);*/
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        /*if(requestCode == REQUEST_CODE_FOR_QR && resultCode == RESULT_OK && data!= null){
            result_String=data.getStringExtra(result_String);
            resultview.setText(result_String);
        }*/
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    //statusMessage.setText(R.string.barcode_success);
                    resultview.setText(barcode.displayValue);
                    //Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    resultview.setText(R.string.no_barcode_captured);
                }
            } else {
                resultview.setText(String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
