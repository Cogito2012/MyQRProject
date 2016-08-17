package com.example.cogito.myqrproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.chzz.qrcode.core.QRCodeView;
import org.chzz.qrcode.zxing.QRCodeDecoder;
import org.chzz.qrcode.zxing.ZXingView;

import java.io.File;

public class QRScan extends AppCompatActivity implements QRCodeView.Delegate {
    private static final String TAG = QRScan.class.getSimpleName();
    private QRCodeView mQRCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpotAndShowRect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        Intent intent = new Intent();
        intent.putExtra("qrcontent",result);
        setResult(RESULT_OK,intent);
        finish();
//        mQRCodeView.startSpot();
    }
    @Override
    public void onScanQRCodeOpenCameraError() {

        Log.e(TAG, "打开相机出错");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

    }
}
