package com.huawei.appgallery.marketinstallerservice.impl.download;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.appgallery.marketinstallerservice.api.FailResultParam;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import defpackage.agh;
import defpackage.agr;

/* loaded from: classes3.dex */
public class MarketInstallReceiver extends SafeBroadcastReceiver {
    @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
    public void onReceiveMsg(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("callback_key");
        InstallCallback a2 = agh.a(stringExtra);
        MarketInfo d = agh.d(stringExtra);
        if (a2 == null) {
            agr.c("MarketInstallReceiver", "market install callback is null!");
            return;
        }
        int i = extras.getInt("android.content.pm.extra.STATUS");
        if (i == 0) {
            a2.onSuccess(d);
        } else {
            FailResultParam failResultParam = new FailResultParam();
            failResultParam.setResult(-2);
            failResultParam.setReason(i);
            failResultParam.setMarketInfo(d);
            a2.onFailed(failResultParam);
        }
        agh.e(stringExtra);
        agh.b(stringExtra);
    }
}
