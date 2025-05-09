package com.huawei.hwdevice.phoneprocess.util;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kkj;

/* loaded from: classes.dex */
public class LanguageChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.h("LanguageChangedReceiver", "device language change receiver intent is none, operation ignored");
            return;
        }
        LogUtil.a("LanguageChangedReceiver", "device language change receiver, context:", context, " intent:", intent.getAction());
        if (context == null || !"android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.a("LanguageChangedReceiver", "switch not on, not need start service!");
            return;
        }
        kkj.d(null);
        try {
            Intent intent2 = new Intent(context, (Class<?>) HwUpdateService.class);
            intent2.setAction("action_band_manual_change_language");
            context.startService(intent2);
        } catch (IllegalStateException unused) {
            LogUtil.b("LanguageChange enter HwUpdateService IllegalStateException", new Object[0]);
        }
    }
}
