package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class m0 {
    public static final String h = "DomainResult";
    public static final String i = "A";
    public static final String j = "AAAA";
    public static final String k = "ALL";
    public static final long l = 86400000;
    public static final int m = 0;
    public static final int n = 1;
    public static final int o = 2;
    public static final String p = "CNAME";
    public int f;

    /* renamed from: a, reason: collision with root package name */
    public String f5372a = "";
    public String b = "A";
    public List<String> c = new ArrayList();
    public long d = System.currentTimeMillis();
    public long e = w.a();
    public int g = -1;

    public String toString() {
        StringBuilder sb = new StringBuilder("{'");
        sb.append(this.b);
        sb.append('\'');
        sb.append(j() ? ", not exist" : ", exist");
        sb.append(", createTime=");
        sb.append(this.d);
        sb.append(", ");
        sb.append(t.m().a(this.f));
        sb.append(", ");
        sb.append(this.g);
        sb.append('}');
        return sb.toString();
    }

    public boolean j() {
        return this.c.isEmpty();
    }

    public String i() {
        return this.b;
    }

    public long h() {
        return this.e;
    }

    public int g() {
        long abs = Math.abs(System.currentTimeMillis() - this.d);
        Logger.v(h, "getStatus:time is %d,ttl is %d", Long.valueOf(abs), Long.valueOf(this.e));
        if (abs < this.e) {
            return 0;
        }
        return abs < 86400000 ? 1 : 2;
    }

    public int f() {
        return this.f;
    }

    public int e() {
        long abs = Math.abs(System.currentTimeMillis() - this.d);
        Logger.v(h, "getReportStatus:time is %d,ttl is %d", Long.valueOf(abs), Long.valueOf(this.e));
        long j2 = this.e;
        if (j2 <= 0) {
            return -1;
        }
        return (int) (abs / j2);
    }

    public List<String> d() {
        return new ArrayList(this.c);
    }

    public void c(String str) {
        this.b = str;
    }

    public String c() {
        return this.f5372a;
    }

    public void b(List<String> list) {
        if (list == null) {
            this.c = new ArrayList();
        } else {
            this.c = list;
        }
    }

    public void b(String str) {
        this.f5372a = str;
    }

    public void b(long j2) {
        this.e = j2;
    }

    public void b(int i2) {
        this.f = i2;
    }

    public long b() {
        return this.d;
    }

    public boolean a(m0 m0Var) {
        return m0Var == null || m0Var.j() || this.f >= m0Var.f();
    }

    public void a(List<String> list) {
        this.c.addAll(list);
    }

    public void a(String str) {
        this.c.add(str);
    }

    public void a(long j2) {
        this.d = j2;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public int a() {
        return this.g;
    }
}
