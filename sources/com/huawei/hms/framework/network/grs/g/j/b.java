package com.huawei.hms.framework.network.grs.g.j;

import android.os.SystemClock;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final Future<com.huawei.hms.framework.network.grs.g.d> f4548a;
    private final long b = SystemClock.elapsedRealtime();

    public boolean b() {
        return SystemClock.elapsedRealtime() - this.b <= 300000;
    }

    public Future<com.huawei.hms.framework.network.grs.g.d> a() {
        return this.f4548a;
    }

    public b(Future<com.huawei.hms.framework.network.grs.g.d> future) {
        this.f4548a = future;
    }
}
