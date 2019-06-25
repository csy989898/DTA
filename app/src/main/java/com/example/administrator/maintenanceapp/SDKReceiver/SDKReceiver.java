package com.example.administrator.maintenanceapp.SDKReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2019/5/23.
 */

public class SDKReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            //key验证失败，做相应处理

        } else if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
            //key验证成功，做相应处理
            /*//key验证事件监听,---activity
            IntentFilter iFilter = new IntentFilter();
            iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
            iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
            mReceiver = new SDKReceiver();
            registerReceiver(mReceiver, iFilter);*/
        }
    }
}