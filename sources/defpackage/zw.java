package defpackage;

import java.util.List;

/* loaded from: classes8.dex */
public class zw {

    /* renamed from: a, reason: collision with root package name */
    private long f17779a;
    private List<zx> b;
    private int c = -1;
    private long d;
    private long e;
    private String g;
    private String h;

    public long a() {
        return this.e;
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(String str) {
        this.g = str;
    }

    public List<zx> b() {
        return this.b;
    }

    public void b(long j) {
        this.e = j;
    }

    public void b(long j, long j2) {
        this.e = j;
        this.d = j2;
        this.f17779a = j2 - j;
    }

    public long c() {
        return this.d - this.e;
    }

    public void c(int i) {
        this.c = i;
    }

    public void c(String str) {
        this.h = str;
    }

    public String d() {
        return this.h;
    }

    public void d(List<zx> list) {
        this.b = list;
    }

    public long e() {
        return this.d;
    }

    public String f() {
        return this.g;
    }
}
