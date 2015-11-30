package com.kim.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.widget.Toast;

/**
 * Created by 伟阳 on 2015/11/29.
 */
public class HelloService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Starting", Toast.LENGTH_SHORT).show();
        Time time = new Time();
        time.setToNow();
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");
        Toast.makeText(HelloService.this, currentTime, Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Done", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
