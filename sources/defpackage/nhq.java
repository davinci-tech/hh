package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class nhq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("goalMetCount")
    private int f15288a;

    @SerializedName("lastValue")
    private int b;

    @SerializedName("mean")
    private int c;

    @SerializedName("currentValue")
    private int d;

    @SerializedName("difference")
    private int e;

    @SerializedName("unit")
    private String f;

    @SerializedName("meanSys")
    private int g;

    @SerializedName("textID")
    private int h;

    @SerializedName("meanDia")
    private int i;

    public int e() {
        return this.h;
    }

    public int d() {
        return this.f15288a;
    }

    public int c() {
        return this.c;
    }

    public int b() {
        return this.g;
    }

    public int a() {
        return this.i;
    }

    public String toString() {
        return "Data{mUnit=" + this.f + ", mTextId=" + this.h + ", mDifference=" + this.e + ", mLastValue=" + this.b + ", mCurrentValue=" + this.d + ", mGoalMetCount=" + this.f15288a + ", mMean=" + this.c + '}';
    }
}
