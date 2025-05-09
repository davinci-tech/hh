package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import health.compact.a.CommonUtil;

/* loaded from: classes7.dex */
public class csi {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("conflictFlag")
    private int f11435a;

    @SerializedName("fat")
    private double b;

    @SerializedName("dateTime")
    private long c;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int d;

    @SerializedName("age")
    private int e;

    @SerializedName("Z34")
    private double f;

    @SerializedName("height")
    private int g;

    @SerializedName("Z13")
    private double h;

    @SerializedName("hr")
    private double i;

    @SerializedName("Z34_H")
    private double j;

    @SerializedName("Z13_H")
    private double k;

    @SerializedName("Z14_H")
    private double l;

    @SerializedName("Z12_H")
    private double m;

    @SerializedName("Z14")
    private double n;

    @SerializedName("Z12")
    private double o;

    @SerializedName("Z24_H")
    private double p;

    @SerializedName("Z24")
    private double q;

    @SerializedName("Z23_H")
    private double r;

    @SerializedName("uid")
    private String s;

    @SerializedName("Z23")
    private double t;

    public String s() {
        return this.s;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.g;
    }

    public double o() {
        return this.o;
    }

    public double i() {
        return this.h;
    }

    public double l() {
        return this.n;
    }

    public double n() {
        return this.t;
    }

    public double t() {
        return this.q;
    }

    public double j() {
        return this.f;
    }

    public double k() {
        return this.m;
    }

    public double g() {
        return this.k;
    }

    public double m() {
        return this.l;
    }

    public double q() {
        return this.r;
    }

    public double r() {
        return this.p;
    }

    public double f() {
        return this.j;
    }

    public double e() {
        return this.b;
    }

    public double h() {
        return this.i;
    }

    public int a() {
        return this.f11435a;
    }

    public String toString() {
        return "ConflictUserInfo{mUid='" + CommonUtil.l(this.s) + "', mGender=" + this.d + ", mAge=" + this.e + ", mHeight=" + this.g + ", mResLhrh=" + this.o + ", mResLhlf=" + this.h + ", mResLhrf=" + this.n + ", mResRhlf=" + this.t + ", mResRhrf=" + this.q + ", mResLfrf=" + this.f + ", mResLhrhHf=" + this.m + ", mResLhlfHf=" + this.k + ", mResLhrfHf=" + this.l + ", mResRhlfHf=" + this.r + ", mResRhrfHf=" + this.p + ", mResLfrfHf=" + this.j + ", mFat=" + this.b + ", mHr=" + this.i + ", mConflictFlag=" + this.f11435a + ", mDateTime=" + this.c + '}';
    }
}
