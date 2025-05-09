package defpackage;

import android.graphics.Bitmap;

/* loaded from: classes4.dex */
public class hlj {
    private float h = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private hjd f13236a = null;
    private float c = 0.0f;
    private Bitmap d = null;
    private float b = 0.0f;
    private boolean e = true;

    public float c() {
        return this.h;
    }

    public hlj d(float f) {
        this.h = f;
        return this;
    }

    public hjd b() {
        return this.f13236a;
    }

    public hlj e(hjd hjdVar) {
        this.f13236a = hjdVar;
        return this;
    }

    public float d() {
        return this.c;
    }

    public hlj a(float f) {
        this.c = f;
        return this;
    }

    public Bitmap bhR_() {
        return this.d;
    }

    public hlj bhS_(Bitmap bitmap) {
        this.d = bitmap;
        return this;
    }

    public float e() {
        return this.b;
    }

    public hlj c(float f) {
        this.b = f;
        return this;
    }

    public boolean h() {
        return this.e;
    }

    public hlj a(boolean z) {
        this.e = z;
        return this;
    }
}
