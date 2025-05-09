package com.huawei.openalliance.ad;

import android.content.Context;
import java.io.File;

/* loaded from: classes5.dex */
public class df {

    /* renamed from: a, reason: collision with root package name */
    private Context f6697a;
    private ga b;

    public void a() {
        try {
            long a2 = this.b.a("143");
            ho.a("DbSizeMonitor", "lastRptTime:%s", Long.valueOf(a2));
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - a2 < 86400000) {
                ho.a("DbSizeMonitor", "rpt once time a day");
                return;
            }
            this.b.a("143", currentTimeMillis);
            File databasePath = com.huawei.openalliance.ad.utils.x.i(this.f6697a).getDatabasePath("hiad_recd.db");
            if (databasePath.exists()) {
                new com.huawei.openalliance.ad.analysis.c(this.f6697a).a("hiad_recd", databasePath.length());
            }
        } catch (Throwable th) {
            ho.c("DbSizeMonitor", "check db size ex:%s", th.getClass().getSimpleName());
        }
    }

    public df(Context context) {
        this.f6697a = context;
        this.b = fe.a(context);
    }
}
