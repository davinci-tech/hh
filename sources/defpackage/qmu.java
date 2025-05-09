package defpackage;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class qmu {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16489a;
    private int b;
    private Date c;
    private boolean d;
    private CopyOnWriteArrayList<pvz> e;
    private double i;

    public CopyOnWriteArrayList<pvz> b() {
        return this.e;
    }

    public void b(CopyOnWriteArrayList<pvz> copyOnWriteArrayList) {
        this.e = copyOnWriteArrayList;
    }

    public boolean a() {
        return this.d;
    }

    public void d(boolean z) {
        this.d = z;
    }

    public double c() {
        return this.i;
    }

    public void a(double d) {
        this.i = d;
    }

    public boolean h() {
        return this.f16489a;
    }

    public void b(boolean z) {
        this.f16489a = z;
    }

    public Date e() {
        return (Date) jdy.d(this.c);
    }

    public void c(Date date) {
        this.c = (Date) jdy.d(date);
    }

    public int d() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }
}
