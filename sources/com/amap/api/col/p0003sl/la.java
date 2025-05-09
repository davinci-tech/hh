package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.kz;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class la extends lc {
    private static la c = new la(new kz.a().a("amap-global-threadPool").b());

    public static la a() {
        return c;
    }

    public static la a(kz kzVar) {
        return new la(kzVar);
    }

    private la(kz kzVar) {
        try {
            this.f1319a = new ThreadPoolExecutor(kzVar.a(), kzVar.b(), kzVar.d(), TimeUnit.SECONDS, kzVar.c(), kzVar);
            this.f1319a.allowCoreThreadTimeOut(true);
        } catch (Throwable th) {
            iv.c(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    @Deprecated
    public static la b() {
        la laVar;
        synchronized (la.class) {
            if (c == null) {
                c = new la(new kz.a().b());
            }
            laVar = c;
        }
        return laVar;
    }

    @Deprecated
    public static la c() {
        return new la(new kz.a().b());
    }
}
