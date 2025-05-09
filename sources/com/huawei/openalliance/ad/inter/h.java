package com.huawei.openalliance.ad.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bf;
import com.huawei.openalliance.ad.utils.m;

/* loaded from: classes5.dex */
public class h extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static volatile h f7064a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (Constants.KIT_PRELOAD_ACTION.equalsIgnoreCase(new SafeIntent(intent).getAction())) {
                ho.a("KitPreloadReceiver", "onReceive kit preload");
                bf.a(context.getApplicationContext());
            }
        } catch (Throwable th) {
            ho.c("KitPreloadReceiver", "onReceive Exception: %s", th.getClass().getSimpleName());
        }
    }

    public static void a(final Context context) {
        m.g(new Runnable() { // from class: com.huawei.openalliance.ad.inter.h.1
            @Override // java.lang.Runnable
            public void run() {
                if (context != null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(Constants.KIT_PRELOAD_ACTION);
                    if (h.f7064a == null) {
                        h unused = h.f7064a = new h();
                    }
                    ao.a(context, h.f7064a, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
                }
            }
        });
    }
}
