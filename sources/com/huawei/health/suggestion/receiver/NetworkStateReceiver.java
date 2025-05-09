package com.huawei.health.suggestion.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.asc;
import health.compact.a.CommonUtil;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("Suggestion_NetworkStateReceiver", "Receive network status change broadcast");
        if (intent == null) {
            LogUtil.h("Suggestion_NetworkStateReceiver", "intent == null");
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && CommonUtil.aa(context)) {
            LogUtil.a("Suggestion_NetworkStateReceiver", "Network status changed, and there is network, start data synchronization service");
            asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.receiver.NetworkStateReceiver.5
                @Override // java.lang.Runnable
                public void run() {
                    SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
                    if (sportServiceApi == null) {
                        LogUtil.h("Suggestion_NetworkStateReceiver", "onReceive sportServiceApi is null.");
                    } else {
                        sportServiceApi.syncData();
                        LogUtil.a("Suggestion_NetworkStateReceiver", "syncData() end");
                    }
                }
            });
        }
    }
}
