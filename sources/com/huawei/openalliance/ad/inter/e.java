package com.huawei.openalliance.ad.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ai;

/* loaded from: classes5.dex */
public class e extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            SafeIntent safeIntent = new SafeIntent(intent);
            String action = safeIntent.getAction();
            ho.b("HwAccountReceiver", "onReceive action: %s", action);
            if (!TextUtils.isEmpty(safeIntent.getPackage())) {
                ho.b("HwAccountReceiver", "duplicate broadcast to: %s", safeIntent.getPackage());
            } else if (Action.ACTION_HW_ACCOUNT_LOGIN.equalsIgnoreCase(action) || Action.ACTION_HW_ACCOUNT_LOGOUT.equalsIgnoreCase(action)) {
                ai.b(context.getApplicationContext());
            }
        } catch (Throwable th) {
            ho.c("HwAccountReceiver", "onReceive Exception: %s", th.getClass().getSimpleName());
        }
    }
}
