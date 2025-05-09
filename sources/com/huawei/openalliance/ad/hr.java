package com.huawei.openalliance.ad;

import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class hr extends hm {
    private final ht b;
    private final Executor c = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new com.huawei.openalliance.ad.utils.n("PPS-FileLog"));

    @Override // com.huawei.openalliance.ad.ht
    public void a(final hv hvVar, final int i, final String str) {
        this.c.execute(new Runnable() { // from class: com.huawei.openalliance.ad.hr.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    hr.this.b.a(hvVar, i, str);
                } catch (Throwable th) {
                    Log.w("HiAdLog", "log err: " + th.getClass().getSimpleName());
                }
            }
        });
        if (this.f6914a != null) {
            this.f6914a.a(hvVar, i, str);
        }
    }

    @Override // com.huawei.openalliance.ad.ht
    public ht a(final String str, final String str2) {
        this.c.execute(new Runnable() { // from class: com.huawei.openalliance.ad.hr.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    hr.this.b.a(str, str2);
                } catch (Throwable th) {
                    Log.w("HiAdLog", "init err: " + th.getClass().getSimpleName());
                }
            }
        });
        if (this.f6914a != null) {
            this.f6914a.a(str, str2);
        }
        return this;
    }

    public hr(ht htVar) {
        this.b = htVar;
    }
}
