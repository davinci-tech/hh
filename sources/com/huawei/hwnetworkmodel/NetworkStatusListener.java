package com.huawei.hwnetworkmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public abstract class NetworkStatusListener extends BroadcastReceiver {
    private static final String TAG = "NetworkStatusListener";
    private boolean mIsConnected;

    public abstract void onConnected();

    public abstract void onDisconnected();

    public NetworkStatusListener(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "NetworkStatusListener null");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        updateNetworkStatus(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            LogUtil.h(TAG, "onReceive null");
        } else {
            LogUtil.a(TAG, "mNonLocalBroadcastReceiver()  intent : ", intent.getAction());
            updateNetworkStatus(context);
        }
    }

    public void unregister(Context context) {
        if (context == null) {
            return;
        }
        context.unregisterReceiver(this);
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }

    private void updateNetworkStatus(Context context) {
        ConnectivityManager connectivityManager = context.getSystemService("connectivity") instanceof ConnectivityManager ? (ConnectivityManager) context.getSystemService("connectivity") : null;
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                onConnected();
                this.mIsConnected = true;
                return;
            } else {
                onDisconnected();
                this.mIsConnected = false;
                return;
            }
        }
        onDisconnected();
        this.mIsConnected = false;
        LogUtil.b(TAG, "cm.getActiveNetworkInfo() return null");
    }
}
