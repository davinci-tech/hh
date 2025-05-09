package com.huawei.wear.wallet.proxy.broadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.wallet.proxy.InitWalletProxy;
import defpackage.stq;

/* loaded from: classes9.dex */
public class ForegroundStatusChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a();
    }

    private void a() {
        try {
            stq.b("ForegroundStatusChangeReceiver", "doReceiver");
            try {
                InitWalletProxy c = InitWalletProxy.c();
                if (c != null) {
                    c.receiverData("101", HiAnalyticsConstant.KeyAndValue.NUMBER_01);
                }
            } catch (ClassNotFoundException unused) {
                stq.b("ForegroundStatusChangeReceiver", "onBind, not PluginAvailable");
            }
        } catch (Exception unused2) {
            stq.e("ForegroundStatusChangeReceiver", "onReceive exception");
        }
    }
}
