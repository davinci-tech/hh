package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class pvy {
    private List<qkg> c;
    private long d;
    private String f = "--";
    private String g = "--";
    private String h = "--";
    private String j = "--";
    private int e = -1;
    private long i = 0;
    private long b = 0;

    /* renamed from: a, reason: collision with root package name */
    private List<qkg> f16287a = new ArrayList();

    public void c(long j) {
        this.d = j;
    }

    public long c() {
        return this.d;
    }

    public String d() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public String e() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String i() {
        return this.j;
    }

    public void e(String str) {
        this.j = str;
    }

    public int a() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public String toString() {
        return "HeartRate [mHeartRate=" + this.c + "]";
    }
}
