package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Utils;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes9.dex */
public class x {
    public static final String e = "DnsInfo";

    /* renamed from: a, reason: collision with root package name */
    public int f5563a;
    public long b = 0;
    public long c = 0;
    public final AtomicLong d = new AtomicLong(0);

    public long e() {
        return this.d.incrementAndGet();
    }

    public int d() {
        return this.f5563a;
    }

    public long c() {
        if (this.c == 0) {
            this.c = Utils.getCurrentTime(true);
        }
        return this.c;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(int i) {
        this.f5563a = i;
    }

    public void a() {
        this.d.set(0L);
        this.c = 0L;
    }
}
