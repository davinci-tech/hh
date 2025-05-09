package com.huawei.hms.scankit.p;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class b6 {
    private static final Object r = new Object();
    private int c;
    private long d;
    private boolean e;
    private float f;
    private int[] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private Path n;
    private PathMeasure o;

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList<w5> f5744a = new ArrayList<>();
    private final ArrayList<w5> b = new ArrayList<>();
    private boolean m = false;
    private x5 p = new x5();
    private z5 q = new z5();

    public b6(int i, long j) {
        a(i, j);
        a((Bitmap) null);
    }

    private void a(g4 g4Var) {
        if (this.q == null) {
            this.q = new z5();
        }
        this.q.a(g4Var);
    }

    public b6 b(float f, float f2) {
        a(new a6(f, f2));
        return this;
    }

    public List<w5> c() {
        List<w5> unmodifiableList;
        synchronized (r) {
            unmodifiableList = Collections.unmodifiableList(this.b);
        }
        return unmodifiableList;
    }

    public void b(long j) {
        boolean z = this.e;
        float f = this.f;
        float f2 = j;
        ArrayList arrayList = new ArrayList();
        synchronized (r) {
            while (z) {
                if (this.f5744a.isEmpty() || this.h >= f * f2) {
                    break;
                } else {
                    a(j);
                }
            }
            Iterator<w5> it = this.b.iterator();
            while (it.hasNext()) {
                w5 next = it.next();
                if (!next.a(j)) {
                    it.remove();
                    arrayList.add(next);
                }
            }
        }
        this.f5744a.addAll(arrayList);
    }

    public b6 a(int i, int i2, long j, long j2, Interpolator interpolator) {
        a(new j5(i, i2, j, j2, interpolator));
        return this;
    }

    private void a(f4 f4Var) {
        if (this.p == null) {
            this.p = new x5();
        }
        this.p.a(f4Var);
    }

    private void a(int i, long j) {
        this.g = new int[2];
        this.c = i;
        this.d = j;
    }

    private void a(Bitmap bitmap) {
        for (int i = 0; i < this.c; i++) {
            this.f5744a.add(new w5(bitmap));
        }
    }

    public void a(Rect rect, int i) {
        a(rect);
        a(i);
    }

    private void a(int i) {
        synchronized (r) {
            this.h = 0;
        }
        this.f = i / 1000.0f;
        this.e = true;
    }

    private void b() {
        ArrayList arrayList;
        synchronized (r) {
            arrayList = new ArrayList(this.b);
        }
        this.f5744a.addAll(arrayList);
    }

    private void a(Rect rect) {
        int i = rect.left - this.g[0];
        this.j = i;
        this.i = i + rect.width();
        int i2 = rect.top - this.g[1];
        this.l = i2;
        this.k = i2 + rect.height();
    }

    private void a(long j) {
        PathMeasure pathMeasure;
        w5 remove = this.f5744a.remove(0);
        this.q.a(remove);
        if (this.m && (pathMeasure = this.o) != null) {
            float[] a2 = a(0.0f, pathMeasure.getLength());
            remove.a(this.d, (int) a2[0], (int) a2[1], j, this.p);
        } else {
            remove.a(this.d, a(this.j, this.i), a(this.l, this.k), j, this.p);
        }
        synchronized (r) {
            this.b.add(remove);
            this.h++;
        }
    }

    private int a(int i, int i2) {
        if (i == i2) {
            return i;
        }
        if (i < i2) {
            return n6.a(i2 - i) + i;
        }
        return n6.a(i - i2) + i2;
    }

    private float[] a(float f, float f2) {
        if (Float.compare(f, f2) <= 0) {
            f2 = n6.a(f2 - f);
        } else {
            f = n6.a(f - f2);
        }
        float f3 = f2 + f;
        if (this.o == null) {
            this.o = new PathMeasure(this.n, true);
        }
        float[] fArr = new float[2];
        this.o.getPosTan(f3, fArr, null);
        float f4 = fArr[0];
        int[] iArr = this.g;
        fArr[0] = f4 - iArr[0];
        fArr[1] = fArr[1] - iArr[1];
        return fArr;
    }

    public void a() {
        b();
    }
}
