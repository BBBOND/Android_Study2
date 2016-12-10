package com.kim.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.widget.Toast;

/**
 * Created by 伟阳 on 2015/11/29.
 */
public class MessengerService extends Service {

    static final int HELLO_WORLD = 1;
    static final int CURRENT_TIME = 2;

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HELLO_WORLD:
                    Toast.makeText(MessengerService.this, "Hello World!", Toast.LENGTH_SHORT).show();
                    break;
                case CURRENT_TIME:
                    Time time = new Time();
                    time.setToNow();
                    String currentTime = time.format("%Y-%m-%d %H:%M:%S");
                    Toast.makeText(MessengerService.this, currentTime, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger messenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Binding", Toast.LENGTH_SHORT).show();
        return messenger.getBinder();
    }
}
