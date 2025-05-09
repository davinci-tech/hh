package com.autonavi.aps.amapapi.storage;

import com.amap.api.col.p0003sl.ja;
import com.amap.api.col.p0003sl.jb;
import com.amap.api.location.AMapLocation;

@ja(a = "c")
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    @jb(a = "a2", b = 6)
    private String f1638a;

    @jb(a = "a3", b = 5)
    private long b;

    @jb(a = "a4", b = 6)
    private String c;
    private AMapLocation d;

    public final AMapLocation a() {
        return this.d;
    }

    public final void a(AMapLocation aMapLocation) {
        this.d = aMapLocation;
    }

    public final String b() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final String c() {
        return this.f1638a;
    }

    public final void b(String str) {
        this.f1638a = str;
    }

    public final long d() {
        return this.b;
    }

    public final void a(long j) {
        this.b = j;
    }
}
