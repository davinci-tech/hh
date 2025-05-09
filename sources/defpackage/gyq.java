package defpackage;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class gyq implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private int f13007a;
    private int b;
    private int c;
    private int d;
    private emj e;
    private int j;

    public gyq() {
        this.b = 1;
        this.c = 0;
        this.f13007a = 0;
        this.j = 0;
        this.d = 0;
    }

    public gyq(int i) {
        this.b = 1;
        this.c = 0;
        this.f13007a = 0;
        this.j = 0;
        this.d = i;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int b() {
        return this.f13007a;
    }

    public emj a() {
        return this.e;
    }

    public void e(emj emjVar) {
        this.e = emjVar;
    }

    public void a(int i) {
        this.b = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.f13007a = i;
    }

    public int i() {
        return this.j;
    }

    public void d(int i) {
        this.j = i;
    }

    public int e() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public boolean j() {
        return this.f13007a == 0 && this.c == 0;
    }
}
