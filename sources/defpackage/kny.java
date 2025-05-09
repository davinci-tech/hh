package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kny implements Serializable {
    private static final long serialVersionUID = -5886031711168296004L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("heartRateIndex")
    private int f14443a;

    @SerializedName("heartRateEnd")
    private float b;

    @SerializedName("heartRateLow")
    private float c;

    @SerializedName("heartRateHigh")
    private float d;

    @SerializedName("heartRateStart")
    private float e;

    public kny(int i, float f, float f2, float f3, float f4) {
        this.f14443a = i;
        this.e = f;
        this.d = f2;
        this.b = f3;
        this.c = f4;
    }

    public float b() {
        return this.d;
    }

    public float a() {
        return this.c;
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.b;
    }

    public String toString() {
        return "tp=h_r_a;index=" + this.f14443a + ";start=" + this.e + ";high=" + this.d + ";end=" + this.b + ";low=" + this.c + ";" + System.lineSeparator();
    }
}
