package com.huawei.health.weight.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class WeightTargetDifferences {

    @SerializedName("targetDifferences")
    private double b;

    @SerializedName("maintainTargetWeightRange")
    private int c;

    @SerializedName("targetFinishDate")
    private long d;

    @SerializedName("targetBeginDate")
    private long e;

    /* loaded from: classes4.dex */
    public enum WeightTargetType {
        WEIGHT_GAIN,
        WEIGHT_LOSS,
        WEIGHT_KEE
    }

    public WeightTargetDifferences(double d, long j, long j2, int i) {
        e(d);
        this.e = j;
        this.d = j2;
        this.c = i;
    }

    public void d(double d) {
        e(d);
    }

    private void e(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return;
        }
        this.b = d;
    }

    public double a() {
        return this.b;
    }

    public long b() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return "WeightTargetDifferences{mTargetDifferences=" + this.b + ", mTargetBeginDate=" + this.e + ", mTargetFinishDate=" + this.d + ", mMaintainTargetWeightRange=" + this.c + "}";
    }

    public WeightTargetType d() {
        double d = this.b;
        if (d == 0.0d) {
            return WeightTargetType.WEIGHT_KEE;
        }
        if (d < 0.0d) {
            return WeightTargetType.WEIGHT_GAIN;
        }
        return WeightTargetType.WEIGHT_LOSS;
    }
}
