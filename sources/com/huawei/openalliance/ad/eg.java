package com.huawei.openalliance.ad;

import android.opengl.Matrix;

/* loaded from: classes9.dex */
public class eg {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f6836a;
    private final ef b;
    private float d;
    private float e;
    private float f;
    private float g;
    private int c = -1;
    private final float[] h = new float[16];
    private boolean i = false;
    private final float[] j = new float[16];

    public void b(float f, float f2) {
        this.f = f;
        this.g = f2;
        this.i = false;
    }

    public void a(eh ehVar, float[] fArr) {
        Matrix.multiplyMM(this.j, 0, fArr, 0, b(), 0);
        ehVar.a(new ei(this.j, this.b.a(), 0, this.b.c(), this.b.f(), this.b.d(), f6836a, this.b.b(), this.c, this.b.e()));
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(float f, float f2) {
        this.d = f;
        this.e = f2;
        this.i = false;
    }

    private float[] b() {
        if (!this.i) {
            a();
        }
        return this.h;
    }

    private void a() {
        float[] fArr = this.h;
        Matrix.setIdentityM(fArr, 0);
        Matrix.translateM(fArr, 0, this.f, this.g, 0.0f);
        Matrix.scaleM(fArr, 0, this.d, this.e, 1.0f);
        this.i = true;
    }

    public eg(ef efVar) {
        this.b = efVar;
    }

    static {
        float[] fArr = new float[16];
        f6836a = fArr;
        Matrix.setIdentityM(fArr, 0);
    }
}
