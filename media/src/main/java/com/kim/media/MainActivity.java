package com.kim.media;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 伟阳 on 2015/11/27.
 */
public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private Button play;
    private Button pause;
    private Button stop;

    private SoundPool soundPool;
    private Button play1;
    Map<Integer, Integer> map;

    private Button video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        initMediaPlayer();
        initSoundPool();
        initVideoView();
    }

    public void initMediaPlayer() {
        player = MediaPlayer.create(this, R.raw.music);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!player.isPlaying())
                    player.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });
    }

    public void initSoundPool() {
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 0);
        map = new HashMap<>();
        map.put(1, soundPool.load(this, R.raw.music3, 1));

        play1 = (Button) findViewById(R.id.play1);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(map.get(1), 1, 1, 0, 0, 1);
            }
        });
    }

    public void initVideoView() {
        video = (Button) findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        soundPool.play(map.get(1), 1, 1, 0, 0, 1);
        return true;
    }
}
