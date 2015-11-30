package com.kim.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Time;

import java.util.Random;

/**
 * Created by 伟阳 on 2015/11/29.
 */
public class BinderService extends Service {

    private final IBinder binder = new LocalBinder();
    private final Random generator = new Random();

    public class LocalBinder extends Binder {
        BinderService getsService() {
            return BinderService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return generator.nextInt(100);
    }

    public String getCurrentTime() {
        Time time = new Time();
        time.setToNow();
        return time.format("%Y-%m-%d %H:%M:%S");
    }
}