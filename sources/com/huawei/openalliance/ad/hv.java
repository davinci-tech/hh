package com.huawei.openalliance.ad;

import android.os.Process;
import java.text.SimpleDateFormat;

/* loaded from: classes5.dex */
public class hv {

    /* renamed from: a, reason: collision with root package name */
    private String f6928a;
    private String b;
    private int c;
    private String e;
    private int f;
    private long d = 0;
    private final StringBuilder g = new StringBuilder();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        b(sb);
        return sb.toString();
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        b(sb);
        return sb.toString();
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public <T> hv a(T t) {
        this.g.append(t);
        return this;
    }

    private hv c() {
        this.d = System.currentTimeMillis();
        this.e = Thread.currentThread().getName();
        this.f = Process.myPid();
        return this;
    }

    private StringBuilder b(StringBuilder sb) {
        sb.append(' ');
        sb.append((CharSequence) this.g);
        return sb;
    }

    private StringBuilder a(StringBuilder sb) {
        SimpleDateFormat a2 = com.huawei.openalliance.ad.utils.ao.a("yyyy-MM-dd HH:mm:ss.SSS");
        sb.append('[');
        sb.append(a2.format(Long.valueOf(this.d)));
        String a3 = hs.a(this.c);
        sb.append(' ');
        sb.append(a3);
        sb.append('/');
        sb.append(this.f6928a);
        sb.append('/');
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.f);
        sb.append(':');
        sb.append(this.e);
        sb.append(']');
        return sb;
    }

    hv(String str, int i, String str2) {
        this.f6928a = null;
        this.b = "HA";
        this.c = 0;
        this.f6928a = str;
        this.c = i;
        if (str2 != null) {
            this.b = str2;
        }
        c();
    }
}
