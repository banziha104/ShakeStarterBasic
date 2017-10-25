package com.veryworks.iyeongjun.shakestarterbasic;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.seismic.ShakeDetector;
import static com.veryworks.iyeongjun.shakestarterbasic.StaticStatus.isServiceRan;

public class ShakeDetectService extends Service implements DisplayReceiver.ShakeController, ShakeDetector.Listener{
    DisplayReceiver displayReceiver;
    SensorManager sensorManager;
    ShakeDetector sd;
    private final String TAG = "Service";
    public ShakeDetectService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"ServiceCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null) addDisplayReceiver();
        isServiceRan = true;
        Log.d(TAG,"ServiceOnStartCommand");
        startForeground(1, new Notification());
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"ServiceOnDestroy");
        isServiceRan = false;
        super.onDestroy();
    }

    private void addDisplayReceiver(){
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        displayReceiver = new DisplayReceiver();
        displayReceiver.setShakeServiceContext(this);
        registerReceiver(displayReceiver, filter);
        addDisplayReceiver();
        startShakeListener();
        Log.d(TAG,"BroadCastReceiverStart");
    }

    @Override
    public void onShakeDetector() {
        startShakeListener();
    }

    @Override
    public void offShakeDetector() {
        stopShakeListener();
    }

    @Override
    public void hearShake() {
        startBannerActivity();
    }

    private void startShakeListener(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(this);
        sd.start(sensorManager);
        Log.d(TAG,"onShakeDetector");
    }
    private void stopShakeListener(){
        sd.stop();
        Log.d(TAG,"offShakeDetector");
    }

    private void startBannerActivity(){
        Intent intent = new Intent(this,BannerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
