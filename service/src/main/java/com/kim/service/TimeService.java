package com.kim.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 伟阳 on 2015/11/29.
 */
public class TimeService extends Service {
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer(true);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "timer start", Toast.LENGTH_SHORT).show();
        super.onStart(intent, startId);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String ns = Context.NOTIFICATION_SERVICE;
                Intent intent1 = new Intent(TimeService.this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(TimeService.this, 0, intent1, 0);
                NotificationManager manager = (NotificationManager) getSystemService(ns);
                Notification.Builder builder = new Notification.Builder(TimeService.this);
                builder.setContentIntent(contentIntent);
                builder.setContentTitle("注意休息！");
                builder.setContentText("你已经使用1分钟了！");
                builder.setTicker("通知来了！");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setDefaults(Notification.DEFAULT_ALL);
                builder.setAutoCancel(true);
                Notification notification = builder.build();
                manager.notify(0, notification);
                TimeService.this.stopSelf();
            }
        }, 6000);
    }
}
