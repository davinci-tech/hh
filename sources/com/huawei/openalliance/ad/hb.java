package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes5.dex */
public class hb {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6895a = new byte[0];
    private static hb b;
    private Context c;
    private BroadcastReceiver d;

    public void b() {
        if (this.d != null) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.hb.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ho.b("LinkedAdStatusHandler", "unregisterPpsReceiver");
                        hb.this.c.unregisterReceiver(hb.this.d);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        }
    }

    public void a(final BroadcastReceiver broadcastReceiver) {
        ho.a("LinkedAdStatusHandler", "registerPpsReceiver ");
        if (this.d != null) {
            b();
        }
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.hb.1
            @Override // java.lang.Runnable
            public void run() {
                IntentFilter intentFilter = new IntentFilter("com.huawei.hms.pps.action.LINKED_AD_STATUS_CHANGED");
                intentFilter.addAction("com.huawei.hms.pps.action.AD_DETAIL_CLOSED");
                hb.this.d = broadcastReceiver;
                com.huawei.openalliance.ad.utils.ao.a(hb.this.c, hb.this.d, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
                ho.b("LinkedAdStatusHandler", "registerPpsReceiver");
            }
        });
    }

    public void a() {
        a(new a());
    }

    private static hb b(Context context) {
        hb hbVar;
        synchronized (hb.class) {
            synchronized (f6895a) {
                if (b == null) {
                    b = new hb(context);
                }
                hbVar = b;
            }
        }
        return hbVar;
    }

    public static hb a(Context context) {
        return b(context);
    }

    static class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if ("com.huawei.hms.pps.action.LINKED_AD_STATUS_CHANGED".equals(intent.getAction())) {
                    boolean booleanExtra = intent.getBooleanExtra("linked_ad_played_in_linked", false);
                    int intExtra = intent.getIntExtra("linked_ad_play_progress", 0);
                    ho.b("LinkedAdStatusHandler", "LinkedAdBroadcastReceiver playProgress " + intExtra);
                    gz gzVar = new gz();
                    gzVar.b(booleanExtra);
                    gzVar.a(intExtra);
                    ha.a(gzVar);
                }
            } catch (Throwable th) {
                ho.c("LinkedAdStatusHandler", "LinkedAdBroadcastReceiver error: %s", th.getClass().getSimpleName());
            }
        }

        private a() {
        }
    }

    private hb(Context context) {
        if (context != null) {
            this.c = context.getApplicationContext();
        }
    }
}
