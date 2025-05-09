package com.huawei.dnurse.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes3.dex */
class a extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1955a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z;
        Handler handler;
        int i;
        boolean z2;
        Log.i("HWHealthSDK", "Get event");
        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            if (intent.getIntExtra("state", 0) == 0 || intent.getIntExtra("microphone", 0) != 1) {
                z = this.f1955a.B;
                if (z) {
                    if (this.f1955a.f != 0) {
                        this.f1955a.c();
                        handler = this.f1955a.K;
                        Runnable runnable = this.f1955a.b;
                        i = this.f1955a.q;
                        handler.postDelayed(runnable, i);
                    }
                    this.f1955a.B = false;
                    Log.i("HWHealthSDK", "Headset removed");
                    return;
                }
                return;
            }
            z2 = this.f1955a.B;
            if (z2) {
                return;
            }
            Log.i("HWHealthSDK", "Model: " + Build.MODEL);
            Log.i("HWHealthSDK", "Ver: " + Build.VERSION.RELEASE);
            this.f1955a.B = true;
            this.f1955a.wakeupDevice();
        }
    }

    a(DnurseDeviceTest dnurseDeviceTest) {
        this.f1955a = dnurseDeviceTest;
    }
}
