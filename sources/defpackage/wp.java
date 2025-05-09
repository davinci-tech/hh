package defpackage;

import android.util.Log;
import com.huawei.algorithm.peeranalysis.PeerPercentage;

/* loaded from: classes2.dex */
public class wp {

    /* renamed from: a, reason: collision with root package name */
    private float f17731a;
    private float b;
    private int c;
    private float d;
    private float e;
    private int f;
    private float g;
    private float h;
    private int i;
    private float j;

    public wp() {
    }

    public wp(int i, int i2, int i3, float f, float f2, float f3, int i4, float f4, float f5) {
        this.e = f;
        this.c = i;
        this.f = i2;
        this.f17731a = i + (i2 / 12.0f);
        this.i = i3;
        this.b = f2;
        this.d = f3;
        this.g = i4;
        this.h = f4;
        this.j = f5;
    }

    public int o() {
        int i;
        double d = this.e;
        if (d < 0.5d || d > 2.5d) {
            return -3;
        }
        int i2 = this.c;
        if (i2 < 6 || i2 > 80 || (i = this.f) < 0 || i > 11) {
            return -4;
        }
        if (this.i > 1) {
            return -5;
        }
        float f = this.b;
        if (f < 10.0f || f > 45.0f) {
            return -7;
        }
        float f2 = this.d;
        if (f2 > 1.0d || f2 < 0.0f) {
            return -7;
        }
        float f3 = this.g;
        if (f3 > 50.0f || f3 < 0.0f || this.h < 0.0f) {
            return -7;
        }
        float f4 = this.j;
        return (f4 < 4.0f || f4 > 20.0f) ? -7 : 0;
    }

    public int n() {
        int i;
        double d = this.e;
        if (d < 0.5d || d > 2.5d) {
            return -3;
        }
        int i2 = this.c;
        if (i2 < 6 || i2 > 80 || (i = this.f) < 0 || i > 11) {
            return -4;
        }
        if (this.i > 1) {
            return -5;
        }
        float f = this.b;
        if (f < 10.0f || f > 45.0f) {
            return -7;
        }
        float f2 = this.d;
        if (f2 > 1.0d || f2 < 0.0f) {
            return -7;
        }
        float f3 = this.g;
        return (f3 > 50.0f || f3 < 0.0f || this.h < 0.0f) ? -7 : 0;
    }

    public float b() {
        return new PeerPercentage().GetBMIMean(this.f17731a, this.i);
    }

    public float a() {
        return new PeerPercentage().GetBFRMean(this.f17731a, this.i);
    }

    public float g() {
        return new PeerPercentage().GetVFLMean(this.f17731a, this.i);
    }

    public float i() {
        return new PeerPercentage().GetSMMMean(this.f17731a, this.i, this.e);
    }

    public float e() {
        return new PeerPercentage().GetRASMMean(this.f17731a, this.i);
    }

    public float d() {
        return new PeerPercentage().GetBMIPeerPercent(this.c, this.i, this.b);
    }

    public float c() {
        return new PeerPercentage().GetBFRPeerPercent(this.c, this.i, this.d);
    }

    public float j() {
        return new PeerPercentage().GetVFLPeerPercent(this.c, this.i, this.g);
    }

    public float h() {
        return new PeerPercentage().GetSMMPeerPercent(this.c, this.i, this.e, this.h);
    }

    public float f() {
        return new PeerPercentage().GetRASMPeerPercent(this.c, this.i, this.j);
    }

    static {
        System.loadLibrary("peeranalysis");
        Log.d("PeerPercentageUtil", "load lib success");
    }
}
