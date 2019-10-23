package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyforeService extends Service {
    public MyforeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

     return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyforeService","onCreat executed");

        //创建通知
        //1.针对Android.o以上版本创建通知通道Channel
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String channelId="ForegroundService";
            String channelName="前台服务";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel(channelId,channelName,importance);
            NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager!=null){
                manager.createNotificationChannel(channel);
            }
        }
        //2.创建通知对象
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this,"ForegroundService")
                .setContentTitle("服务通知")
                .setContentText("前台服务启动")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();

        //3.启动前台通知
         startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("MyforeService","onStartCommand executed"+i);
        }

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyforeService","onDestroy executed");
    }
}
