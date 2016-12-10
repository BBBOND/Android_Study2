package com.kim.media;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by 伟阳 on 2015/11/28.
 */
public class VideoActivity extends AppCompatActivity {

    private VideoView video;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        video = (VideoView) findViewById(R.id.video_view);

        File file = new File("/storage/sdcard0/html.mp4");
        MediaController mc = new MediaController(VideoActivity.this);
        if (file.exists()) {
            video.setVideoPath(file.getAbsolutePath());
            video.setMediaController(mc);
            video.requestFocus();
            try {
                video.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(VideoActivity.this, "播放完毕", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(VideoActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
