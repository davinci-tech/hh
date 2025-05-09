package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.kyx;
import defpackage.ppy;
import defpackage.qkc;

/* loaded from: classes3.dex */
public class DataSyncActivityCycle {
    private final BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.health.receiver.DataSyncActivityCycle.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("DataSyncActivityCycle", "Sleep action ", action);
            if (!"com.huawei.health.DataSyncActivityCycle.Sleep".equals(action)) {
                LogUtil.h("DataSyncActivityCycle", "onReceive intent action is not Sleep");
                return;
            }
            long longExtra = intent.getLongExtra("fallAsleepTime", 0L);
            long longExtra2 = intent.getLongExtra("wakeTime", 0L);
            int intExtra = intent.getIntExtra(JsUtil.SCORE, 0);
            if (longExtra > 0 && longExtra2 > 0) {
                LogUtil.a("DataSyncActivityCycle", "handleSleepEvent Start!");
                ppy.a(longExtra, longExtra2, intExtra);
                qkc.e(longExtra, longExtra2);
                LogUtil.a("DataSyncActivityCycle", "handleSleepEvent Stop!");
            }
            kyx.bTa_(this);
        }
    };
    private final BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.health.receiver.DataSyncActivityCycle.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("DataSyncActivityCycle", "Exercise action ", action);
            if (!"com.huawei.health.DataSyncActivityCycle.Exercise".equals(action)) {
                LogUtil.h("DataSyncActivityCycle", "onReceive intent action is not Exercise");
                return;
            }
            LogUtil.a("DataSyncActivityCycle", "handleSportBloodSugarEvent Start!");
            qkc.b();
            LogUtil.a("DataSyncActivityCycle", "handleSportBloodSugarEvent Stop!");
            kyx.bSZ_(this);
        }
    };

    public void registerDataSyncReceiver(int i) {
        LogUtil.a("DataSyncActivityCycle", "registerDataSyncReceiver type:", Integer.valueOf(i));
        if (i == 1) {
            kyx.bSY_(this.d);
        } else if (i == 2) {
            kyx.bSX_(this.c);
        } else {
            LogUtil.a("DataSyncActivityCycle", "registerDataSyncReceiver unknown action");
        }
    }
}
