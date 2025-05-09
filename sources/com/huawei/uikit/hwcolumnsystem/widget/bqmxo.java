package com.huawei.uikit.hwcolumnsystem.widget;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import defpackage.slf;

/* loaded from: classes7.dex */
public abstract class bqmxo {
    protected static final String f = "HwColumnDelegate";
    private static final float g = 1.0E-6f;
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 2;

    /* renamed from: a, reason: collision with root package name */
    public int f10622a;
    public int b;
    public int c = 1;
    private float d;
    private float e;

    public bqmxo(Context context) {
    }

    private void p() {
        float f2 = this.d;
        float f3 = this.f10622a;
        if (f2 < f3) {
            this.c = 0;
        } else if (f2 < f3 || f2 >= this.b) {
            this.c = 2;
        } else {
            this.c = 1;
        }
    }

    public int a(int i2) {
        return a(i2, this.e);
    }

    protected abstract Pair<Integer, Integer> a(float f2);

    protected abstract Pair<Integer, String> a(int i2, String[] strArr, float f2);

    protected abstract slf a();

    protected abstract slf a(int i2, int i3, int i4);

    protected abstract slf a(int i2, Pair<Integer, Integer> pair, int i3, int i4, float f2);

    protected abstract int b(int i2, int i3, int i4);

    protected abstract slf b();

    void b(int i2, float f2) {
        if (f2 != 0.0f) {
            this.e = f2;
            this.d = i2 / f2;
            p();
        }
    }

    protected abstract boolean b(int i2);

    protected abstract slf c();

    protected abstract slf c(int i2, int i3, int i4);

    protected abstract slf d();

    protected abstract slf d(int i2, int i3, int i4);

    protected abstract slf e();

    protected abstract slf f();

    protected abstract slf g();

    protected abstract slf h();

    protected abstract slf i();

    protected abstract slf j();

    protected abstract slf k();

    protected abstract slf l();

    protected abstract slf m();

    protected abstract slf n();

    protected abstract int o();

    public int a(int i2, float f2) {
        if (f2 != 0.0f) {
            return (int) (i2 * f2);
        }
        throw new IllegalStateException("HwColumnDelegate constructor did not call superclass implementation");
    }

    public boolean a(float f2, float f3) {
        return Math.abs(f2 - f3) < g;
    }

    void a(int[] iArr) {
        int i2;
        if (iArr != null && iArr.length >= 2) {
            int i3 = iArr[0];
            if (i3 <= 0 || (i2 = iArr[1]) <= 0) {
                throw new IllegalArgumentException("Break points value cannot be zero!");
            }
            if (i3 == i2) {
                Log.w(f, "Break points cannot be the same.");
                return;
            }
            this.f10622a = Math.min(i3, i2);
            this.b = Math.max(iArr[0], iArr[1]);
            p();
            return;
        }
        throw new IllegalArgumentException("Break points cannot be null or length less than 2!");
    }
}
