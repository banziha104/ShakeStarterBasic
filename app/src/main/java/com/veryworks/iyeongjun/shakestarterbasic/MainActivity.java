package com.veryworks.iyeongjun.shakestarterbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    DisplayReceiver displayReceiver;
    boolean isRan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startShakeDetect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startShakeDetect(){
        Intent intent = new Intent(MainActivity.this, ShakeDetectService.class);
        startService(intent);
    }
}
