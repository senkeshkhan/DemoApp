package com.task.demoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by vivid on 15/9/17.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if (action == null) {

            System.out.println("111111111111111");
            Log.e("uninstall ", "Action==null!");
        }   else if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            Log.e("uninstall ", "action :"+action);
            System.out.println("2222222222222");
        }
    }
}