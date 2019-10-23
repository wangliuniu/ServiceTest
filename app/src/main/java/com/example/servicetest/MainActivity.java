package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 private MyBindService.MyBinder myBinder;
 private ServiceConnection connection=new ServiceConnection() {
     @Override
     public void onServiceConnected(ComponentName name, IBinder service) {
     myBinder=(MyBindService.MyBinder)service;
     myBinder.startDownload();
     myBinder.getProgress();
     }

     @Override
     public void onServiceDisconnected(ComponentName name) {

     }
 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPause=findViewById(R.id.btn_pause);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_service://启动服务
                Intent intent=new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
               intent=new Intent(this,MyService.class);
                stopService(intent);//停止
                break;
            case R.id.btn_bind_service:
                intent=new Intent(this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);//绑定
                break;
            case R.id.btn_unbind_service:
                intent=new Intent(this,MyService.class);
                unbindService(connection);//解绑
                break;
            case R.id.btn_foreground_service:
                intent=new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_foreground_service:
                intent=new Intent(this,MyService.class);
                stopService(intent);
                break;
            case R.id.btn_intent_service:
                intent=new Intent(this,MyIntentService.class);
                startService(intent);
                break;
            case R.id.btn_start:
                intent=new Intent(this,MusicService.class);
                intent.putExtra("status",0);
                startService(intent);
                break;
            case R.id.btn_pause:
                intent=new Intent(this,MusicService.class);
                if (!flag){
                    intent.putExtra("status",1);
                    btnPause.setText("继续");
                }else {
                    intent.putExtra("status",2);
                    btnPause.setText("暂停");
                }
                flag=!flag;
                startService(intent);
                break;
            case R.id.btn_stop:
                intent=new Intent(this,MusicService.class);
                 intent.putExtra("status",3);
                 startService(intent);
                break;
            default:
                break;
        }
    }
   private Button  btnPause;
    private boolean flag=false;
}
