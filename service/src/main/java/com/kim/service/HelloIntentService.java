package com.kim.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 伟阳 on 2015/11/28.
 */
public class HelloIntentService extends IntentService {

    private String currentTime;

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Time time = new Time();
        time.setToNow();
        currentTime = time.format("%Y-%m-%d %H:%M:%S");
        Log.d("currentTime", currentTime);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Service Done", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
