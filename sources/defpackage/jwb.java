package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class jwb {

    /* renamed from: a, reason: collision with root package name */
    private int f14143a;
    private int f;
    private boolean j = false;
    private int i = 0;
    private String e = "";
    private int k = -1;
    private int l = -1;
    private long g = -1;
    private int m = -1;
    private int d = -1;
    private int c = -1;
    private int o = 0;
    private List<byte[]> b = Collections.synchronizedList(new ArrayList(16));
    private List<Integer> h = new ArrayList(16);

    public boolean k() {
        return this.j;
    }

    public void e(boolean z) {
        this.j = z;
    }

    public int g() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public String b() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public void g(int i) {
        this.l = i;
    }

    public int l() {
        return this.l;
    }

    public void d(long j) {
        this.g = j;
    }

    public long j() {
        return this.g;
    }

    public void j(int i) {
        this.m = i;
    }

    public int n() {
        return this.m;
    }

    public void b(int i) {
        this.d = i;
    }

    public int e() {
        return this.d;
    }

    public void i(int i) {
        this.k = i;
    }

    public int h() {
        return this.k;
    }

    public void d(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public void c(int i) {
        this.f = i;
    }

    public int f() {
        return this.f;
    }

    public void a(int i) {
        this.f14143a = i;
    }

    public int c() {
        return this.f14143a;
    }

    public void f(int i) {
        this.o = i;
    }

    public int o() {
        return this.o;
    }

    public List<byte[]> d() {
        return this.b;
    }

    public List<Integer> i() {
        return this.h;
    }
}
