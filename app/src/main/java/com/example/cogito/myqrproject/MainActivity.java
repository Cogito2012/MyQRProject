package com.example.cogito.myqrproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvScanQRCode;
    private TextView tvScanResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScanQRCode = (TextView)findViewById(R.id.scan_qrcode);
        tvScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"开始扫描",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, QRScan.class);
                startActivityForResult(intent,0);
            }
        });

        tvScanResults = (TextView)findViewById(R.id.qrscan_results);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            String qrresults = data.getExtras().getString("qrcontent");
            tvScanResults.setText(qrresults);
        }
    }
}
