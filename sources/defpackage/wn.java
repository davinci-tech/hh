package defpackage;

import android.util.Log;
import com.huawei.algorithm.bodycomposition.HWAlgorithm;

/* loaded from: classes2.dex */
public class wn {

    /* renamed from: a, reason: collision with root package name */
    private float f17729a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private int n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private float v;
    private float w;
    private float y;

    public wn() {
    }

    public wn(float f, float f2, int i, int i2, int i3, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.d = f;
        this.f17729a = f2;
        this.n = i2;
        this.q = i;
        this.s = i3;
        this.r = 8;
        this.t = 1;
        this.h = f3;
        this.c = f4;
        this.g = f5;
        this.o = f6;
        this.l = f7;
        this.b = f8;
        this.i = 0.0f;
        this.f = 0.0f;
        this.j = 0.0f;
        this.m = 0.0f;
        this.k = 0.0f;
        this.e = 0.0f;
        this.p = f9;
        if (i2 >= 6 && i2 <= 17) {
            this.q = i + 3;
            this.n = (i2 * 10) + ((int) (((i3 / 12.0d) + 0.05d) * 10.0d));
        }
        if (f9 == 0.0f) {
            this.v = 0.0f;
            this.w = 0.0f;
            this.y = 0.0f;
        }
    }

    public wn(float f, float f2, int i, int i2, int i3, int i4, int i5, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.d = f;
        this.f17729a = f2;
        this.n = i2;
        this.q = i;
        this.s = i3;
        this.r = i4;
        this.t = i5;
        this.h = f3;
        this.c = f4;
        this.g = f5;
        this.o = f6;
        this.l = f7;
        this.b = f8;
        this.i = f9;
        this.f = f10;
        this.j = f11;
        this.m = f12;
        this.k = f13;
        this.e = f14;
        this.p = f15;
        if (i2 >= 6 && i2 <= 17) {
            this.q = i + 3;
            this.n = (i2 * 10) + ((int) (((i3 / 12.0d) + 0.05d) * 10.0d));
        }
        if (f15 == 0.0f) {
            this.v = 0.0f;
            this.w = 0.0f;
            this.y = 0.0f;
        }
    }

    public wn(float f, float f2, int i, int i2, int i3, float f3, float f4) {
        this.d = f;
        this.f17729a = f2;
        this.n = i2;
        this.q = i;
        this.s = i3;
        this.r = 4;
        this.t = 1;
        this.h = 0.0f;
        this.c = 0.0f;
        this.g = 0.0f;
        this.o = 0.0f;
        this.l = 0.0f;
        this.b = f3;
        this.i = 0.0f;
        this.f = 0.0f;
        this.j = 0.0f;
        this.m = 0.0f;
        this.k = 0.0f;
        this.e = 0.0f;
        this.p = f4;
        if (i2 >= 6 && i2 <= 17) {
            this.q = i + 3;
            this.n = (i2 * 10) + ((int) (((i3 / 12.0d) + 0.05d) * 10.0d));
        }
        if (f4 == 0.0f) {
            this.v = 0.0f;
            this.w = 0.0f;
            this.y = 0.0f;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a3, code lost:
    
        if (r6 <= 1500.0f) goto L74;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int e() {
        /*
            Method dump skipped, instructions count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wn.e():int");
    }

    public float am() {
        return new HWAlgorithm().getTW(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ao() {
        return new HWAlgorithm().getTWR(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float v() {
        return new HWAlgorithm().getMW(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float o() {
        return new HWAlgorithm().getIS(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float u() {
        return new HWAlgorithm().getOR(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float j() {
        return new HWAlgorithm().getFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float a() {
        return new HWAlgorithm().getBMI(this.d, this.f17729a);
    }

    public float ad() {
        return new HWAlgorithm().getRFMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float s() {
        return new HWAlgorithm().getLFMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ah() {
        return new HWAlgorithm().getRHMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float r() {
        return new HWAlgorithm().getLHMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float aj() {
        return new HWAlgorithm().getTMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ab() {
        return new HWAlgorithm().getRFFM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float q() {
        return new HWAlgorithm().getLFFM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ag() {
        return new HWAlgorithm().getRHFM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float t() {
        return new HWAlgorithm().getLHFM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ak() {
        return new HWAlgorithm().getTFM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ac() {
        return new HWAlgorithm().getRFFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float m() {
        return new HWAlgorithm().getLFFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float aa() {
        return new HWAlgorithm().getRHFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float p() {
        return new HWAlgorithm().getLHFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float an() {
        return new HWAlgorithm().getTFAP(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float i() {
        return new HWAlgorithm().getCAL(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ar() {
        return new HWAlgorithm().getWHR(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float ai() {
        return new HWAlgorithm().getSMM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float z() {
        return new HWAlgorithm().getRASM(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int as() {
        return new HWAlgorithm().getVFL(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int ae() {
        return new HWAlgorithm().getSCORE(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int b() {
        return new HWAlgorithm().getBODYAGE(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int f() {
        return new HWAlgorithm().getBODYTYPE(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int c() {
        return new HWAlgorithm().getBODYSHAPE(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int y() {
        return new HWAlgorithm().getMB(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public int k() {
        return new HWAlgorithm().getFB(this.d, this.f17729a, this.q, this.n, this.r, this.t, this.h, this.c, this.g, this.o, this.l, this.b, this.i, this.f, this.j, this.m, this.k, this.e, this.v, this.w, this.y);
    }

    public float x() {
        return new HWAlgorithm().getMWHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float aq() {
        return new HWAlgorithm().getTWRHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float n() {
        return new HWAlgorithm().getISHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float w() {
        return new HWAlgorithm().getORHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float l() {
        return new HWAlgorithm().getFAPHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float g() {
        return new HWAlgorithm().getCALHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public int ap() {
        return new HWAlgorithm().getVFLHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public int af() {
        return new HWAlgorithm().getSCOREHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public int d() {
        return new HWAlgorithm().getBODYAGEHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public float al() {
        return new HWAlgorithm().getSMMHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    public int h() {
        return new HWAlgorithm().getBODYTYPEHalf(this.d, this.f17729a, this.q, this.n, this.b, this.v, this.w, this.y);
    }

    static {
        System.loadLibrary("bodycomposition");
        Log.d("HWAlorithmUtil", "load lib success");
    }
}
