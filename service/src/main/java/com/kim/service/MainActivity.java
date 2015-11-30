package com.kim.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startService;
    private Button startIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.start_service);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelloService.class);
                startService(intent);
            }
        });

        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelloIntentService.class);
                startService(intent);
            }
        });

        Intent intent = new Intent(MainActivity.this, TimeService.class);
        startService(intent);
    }

    private BinderService binderService;
    private boolean bound = false;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BinderService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        bindService(new Intent(this, MessengerService.class), connection1, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
        if (bound1) {
            unbindService(connection1);
            bound1 = false;
        }
    }

    public void onButtonClick(View v) {
        if (bound) {
            int num = binderService.getRandomNumber();
            Toast.makeText(this, "获得随机数:" + num, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, binderService.getCurrentTime(), Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BinderService.LocalBinder binder = (BinderService.LocalBinder) service;
            binderService = binder.getsService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    Messenger messenger = null;
    boolean bound1;
    private ServiceConnection connection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            bound1 = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            bound1 = false;
        }
    };

    public void sayHello(View v) {
        if (!bound1)
            return;
        Message msg = Message.obtain(null, MessengerService.HELLO_WORLD, 0, 0);
        Message msg1 = Message.obtain(null, MessengerService.CURRENT_TIME, 0, 0);
        try {
            messenger.send(msg);
            messenger.send(msg1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
