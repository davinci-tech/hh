package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.goj;

/* loaded from: classes8.dex */
public class LoginStatusReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("Login_LoginStatusReceiver", "receive the static BroadcastReceiver , logout");
        goj.a().aQB_(context, intent);
    }
}
