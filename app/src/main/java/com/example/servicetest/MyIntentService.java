package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class MyIntentService extends IntentService {


    public MyIntentService() {

        super("MyIntentService");
    }



    @Override
    public void onCreate() {
        Log.i("MyIntentService","onCreate executed");
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i("MyIntentService","onHandleIntent executed");

        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.i("MyIntentService","onDestroy executed");
    }
}
