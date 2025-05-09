package com.huawei.ui.homewear21.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class UpgradeMessageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("UpgradeMessageReceiver", "UpgradeMessageReceiver enter");
        if (intent == null || context == null) {
            LogUtil.a("UpgradeMessageReceiver", "UpgradeMessageReceiver null intent  return!");
            return;
        }
        String action = intent.getAction();
        if (action != null) {
            LogUtil.a("UpgradeMessageReceiver", "UpgradeMessageReceiver action = ", action);
        }
    }
}
