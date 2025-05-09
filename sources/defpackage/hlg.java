package defpackage;

import android.graphics.Bitmap;
import android.util.Pair;

/* loaded from: classes4.dex */
public class hlg {
    private String c;
    private boolean f;
    private boolean h;
    private boolean i;
    private int l;
    private Bitmap j = null;
    private hjd g = null;
    private Pair<Float, Float> b = new Pair<>(Float.valueOf(0.5f), Float.valueOf(1.0f));
    private float e = 90.0f;

    /* renamed from: a, reason: collision with root package name */
    private float f13233a = 0.0f;
    private float d = 0.0f;
    private double n = 1.0d;
    private boolean o = true;

    public hlg() {
        this.i = false;
        this.l = 10;
        this.f = false;
        this.i = false;
        this.l = 10;
        this.f = false;
    }

    public Pair<Float, Float> bhN_() {
        return this.b;
    }

    public hlg e(float f, float f2) {
        this.b = new Pair<>(Float.valueOf(f), Float.valueOf(f2));
        return this;
    }

    public hlg bhP_(Pair<Float, Float> pair) {
        if (pair == null) {
            return this;
        }
        this.b = new Pair<>((Float) pair.first, (Float) pair.second);
        return this;
    }

    public boolean h() {
        return this.i;
    }

    public Bitmap bhO_() {
        return this.j;
    }

    public hlg bhQ_(Bitmap bitmap) {
        this.j = bitmap;
        return this;
    }

    public hjd e() {
        return this.g;
    }

    public hlg d(hjd hjdVar) {
        this.g = hjdVar;
        return this;
    }

    public int d() {
        return this.l;
    }

    public hlg a(int i) {
        this.l = i;
        return this;
    }

    public boolean g() {
        return this.f;
    }

    public hlg b(boolean z) {
        this.f = z;
        return this;
    }

    public hlg c(boolean z) {
        this.h = z;
        return this;
    }

    public void e(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public void e(double d) {
        this.n = d;
    }

    public boolean f() {
        return this.o;
    }

    public hlg a(boolean z) {
        this.o = z;
        return this;
    }
}
