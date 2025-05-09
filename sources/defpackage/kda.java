package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kda {

    /* renamed from: a, reason: collision with root package name */
    private String f14298a;
    private List<kdd> b = new ArrayList(16);
    private String c;
    private int d;
    private long e;
    private int g;
    private String i;
    private int j;

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(long j) {
        this.e = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void d(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.g))).intValue();
    }

    public void c(int i) {
        this.g = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(String str) {
        this.f14298a = (String) jdy.d(str);
    }

    public List<kdd> d() {
        return (List) jdy.d(this.b);
    }

    public int b() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public String a() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }
}
