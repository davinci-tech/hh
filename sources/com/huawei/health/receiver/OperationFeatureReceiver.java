package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.MainActivityHandlerMsg;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class OperationFeatureReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static OperationFeatureReceiver f2952a;
    private Handler d;

    public OperationFeatureReceiver(Handler handler) {
        this.d = handler;
    }

    public static void aur_(Handler handler) {
        if (f2952a == null) {
            f2952a = new OperationFeatureReceiver(handler);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.plugin.operation.action_jumt_to_fearture_page");
            LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(f2952a, intentFilter);
        }
    }

    public static void c() {
        if (f2952a != null) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(f2952a);
            f2952a.d = null;
            f2952a = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Handler handler;
        if (intent == null || (handler = this.d) == null) {
            LogUtil.c("OperationFeatureReceiver", "Broadcast triggered, but intent or handler is null...");
        } else {
            handler.sendEmptyMessage(MainActivityHandlerMsg.ON_OPERATION_FEATURE_BROADCAST);
        }
    }
}
