package com.example.navdrawer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class MyService extends Service {

    private final IBinder binder = new LocalBinder();
    private long secondsElapsed = 0;
    private Handler handler = new Handler();
    private Runnable runnable;

    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();
    }

    private void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                secondsElapsed += 5;
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnable);
    }

    public long getSecondsElapsed() {
        return secondsElapsed;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

}