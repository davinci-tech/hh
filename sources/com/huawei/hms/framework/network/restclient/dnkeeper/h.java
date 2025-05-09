package com.huawei.hms.framework.network.restclient.dnkeeper;

import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class h {
    private Future c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4572a = false;
    private long b = 0;
    private DnsResult d = new DnsResult();

    public boolean d() {
        return this.f4572a;
    }

    public long c() {
        return this.b;
    }

    public Future b() {
        return this.c;
    }

    public void a(boolean z) {
        this.f4572a = z;
    }

    public void a(Future future) {
        this.c = future;
    }

    public void a(DnsResult dnsResult) {
        this.d = dnsResult;
    }

    public void a(long j) {
        this.b = j;
    }

    public DnsResult a() {
        return this.d;
    }
}
