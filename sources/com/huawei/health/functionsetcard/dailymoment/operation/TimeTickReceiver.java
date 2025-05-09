package com.huawei.health.functionsetcard.dailymoment.operation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dnx;

/* loaded from: classes3.dex */
public class TimeTickReceiver extends BroadcastReceiver {
    private dnx d;

    public TimeTickReceiver(dnx dnxVar) {
        this.d = dnxVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null || intent.getAction() == null || !"android.intent.action.TIME_TICK".equals(intent.getAction())) {
            return;
        }
        long currentTimeMillis = (System.currentTimeMillis() / 1000) / 60;
        long j = currentTimeMillis / 60;
        if (this.d != null) {
            long j2 = currentTimeMillis % 60;
            if (j2 == 0 && (j + 8) % 24 == 0) {
                LogUtil.a("TimeTickReceiver", "another day");
                this.d.g();
            } else if (j2 == 0) {
                LogUtil.a("TimeTickReceiver", "another hour");
                this.d.j();
            }
        }
    }
}
