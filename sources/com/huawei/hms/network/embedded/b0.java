package com.huawei.hms.network.embedded;

import java.util.Date;

/* loaded from: classes9.dex */
public class b0 {

    /* renamed from: a, reason: collision with root package name */
    public String f5167a;
    public String b;
    public long c;
    public long d;
    public int e;

    public String toString() {
        return "Address{domain='" + this.f5167a + "', ip=" + this.b + ", ttl=" + this.c + ", createTime=" + this.d + ", source=" + t.m().a(this.e) + '}';
    }

    public boolean a() {
        long b = b() - this.d;
        return b > 86400000 || b < -86400000;
    }

    private long b() {
        return new Date().getTime();
    }

    public b0(String str, String str2, long j) {
        w.a();
        this.f5167a = str;
        this.b = str2;
        this.c = j;
    }

    public b0(String str, String str2) {
        this.c = w.a();
        this.f5167a = str;
        this.b = str2;
    }

    public b0() {
        this.c = w.a();
    }
}
