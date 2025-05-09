package defpackage;

import android.os.Bundle;

/* loaded from: classes4.dex */
public class hae {
    private static volatile hae b;
    private static final Object c = new Object();
    private int h = 0;
    private long i = 0;
    private float d = 0.0f;
    private float g = 0.0f;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f13043a = 0;
    private boolean j = false;

    private hae() {
    }

    public static hae e() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new hae();
                }
            }
        }
        return b;
    }

    public void d(int i) {
        this.h = i;
    }

    public int h() {
        return this.h;
    }

    public void e(long j) {
        this.i = j;
    }

    public void e(float f) {
        this.d = f;
    }

    public float d() {
        return this.d;
    }

    public void c(float f) {
        this.g = f;
    }

    public void c(int i) {
        this.e = i;
    }

    public int b() {
        return this.e;
    }

    public void b(int i) {
        this.f13043a = i;
    }

    public int a() {
        return this.f13043a;
    }

    public void aXI_(Bundle bundle, int i, int i2, int i3) {
        if (hab.g()) {
            if (bundle != null) {
                d(bundle.getInt("sportState"));
                e(bundle.getInt("duration"));
                e(bundle.getInt("distance") / 1000.0f);
                c((bundle.getFloat("speed") * 3600.0f) / 1000.0f);
                c(bundle.getInt("calorie") / 1000);
            }
            if (this.j && this.h == 2) {
                gxd.a().e();
                gxd.a().d();
            }
            int i4 = this.h;
            if (i4 == 2 || i4 == 3) {
                this.j = false;
                return;
            }
            this.j = true;
            b(i);
            if (i > 0) {
                hac.a().d = i;
                hac.a().b.add(Integer.valueOf(i));
            }
            if (this.g > 0.0f) {
                hac.a().c(this.g);
                hac.a().a(this.g);
            }
            if (i2 > 0) {
                hac.a().b(i2, i3);
            }
            hac.a().b(this.i * 1000);
        }
    }

    public void c() {
        f();
    }

    private void f() {
        synchronized (c) {
            b = null;
        }
    }
}
