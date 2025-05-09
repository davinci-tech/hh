package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class TriggerLoginReceiver extends BroadcastReceiver {
    private static TriggerLoginReceiver d;
    private Handler b;

    public TriggerLoginReceiver(Handler handler) {
        this.b = handler;
    }

    public static void aus_(Handler handler) {
        d = new TriggerLoginReceiver(handler);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.trigger.checklogin");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(d, intentFilter);
        ObserverManagerUtil.c("com.huawei.plugin.trigger.checklogin", new Object[0]);
    }

    public static void b() {
        if (d != null) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(d);
            d = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.c("TriggerLoginReceiver", "Broadcast triggered, but intent is null...");
        } else {
            this.b.sendEmptyMessage(6007);
        }
    }
}
