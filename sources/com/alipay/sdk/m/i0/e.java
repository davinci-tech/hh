package com.alipay.sdk.m.i0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.agconnect.credential.obs.c;
import defpackage.kb;
import defpackage.ke;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public final class e extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("openIdNotifyFlag", 0);
        ke.c("shouldUpdateId, notifyFlag : ".concat(String.valueOf(intExtra)));
        if (intExtra == 1) {
            if (!TextUtils.equals(intent.getStringExtra("openIdPackage"), context.getPackageName())) {
                return;
            }
        } else if (intExtra == 2) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("openIdPackageList");
            if (stringArrayListExtra == null || !stringArrayListExtra.contains(context.getPackageName())) {
                return;
            }
        } else if (intExtra != 0) {
            return;
        }
        String stringExtra = intent.getStringExtra("openIdType");
        ke b = ke.b();
        kb kbVar = "oaid".equals(stringExtra) ? b.f14312a : "vaid".equals(stringExtra) ? b.f : c.f1765a.equals(stringExtra) ? b.b : "udid".equals(stringExtra) ? b.c : null;
        if (kbVar == null) {
            return;
        }
        kbVar.e();
    }
}
