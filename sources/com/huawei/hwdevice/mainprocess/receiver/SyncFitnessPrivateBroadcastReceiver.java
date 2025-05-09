package com.huawei.hwdevice.mainprocess.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class SyncFitnessPrivateBroadcastReceiver extends BroadcastReceiver {
    private static boolean c = false;

    public static boolean b() {
        return c;
    }

    public static void c(boolean z) {
        c = z;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!CommonUtil.ce()) {
            LogUtil.h("SyncFitnessPrivateBroadcastReceiver", "onReceive: Not Support WearProduct.");
            return;
        }
        if (intent == null || context == null) {
            LogUtil.h("SyncFitnessPrivateBroadcastReceiver", "onReceive: Context or Intent Is Null.");
            return;
        }
        String action = intent.getAction();
        LogUtil.a("SyncFitnessPrivateBroadcastReceiver", "onReceive: action: ", action);
        if ("com.huawei.bone.action.open_gps".equals(action)) {
            c(true);
        } else {
            LogUtil.h("SyncFitnessPrivateBroadcastReceiver", "onReceive: unknown action, no need to handle.");
        }
    }
}
