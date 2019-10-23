package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    public MusicService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this,R.raw.sleepaway);
        player.setOnCompletionListener(this);
    }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (player!=null){
                player.release();
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
        }

         public int onStartCommand(Intent intent,int flags,int startId){
        if (player==null){
            player =MediaPlayer.create(this,R.raw.sleepaway);
        }
        int status=intent.getIntExtra("status",0);
        switch (status){
            case 0:
                player=MediaPlayer.create(this,R.raw.sleepaway);
                player.start();
                break;
            case 1:
                if (player.isPlaying()){
                    player.pause();
                }
                break;
            case 2:
                if (!player.isPlaying()){
                    player.start();
                }
                break;
            case 3://停止
                if (player.isPlaying()){
                    player.stop();
                    try {
                        //stop后下次重新播放要首先进入prepared的状态，并将播放时间设置到0
                        player.prepare();
                        player.seekTo(0);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
        return super.onStartCommand(intent,flags,startId);
         }

    }
