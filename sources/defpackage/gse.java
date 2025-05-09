package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class gse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("lastCaloriesGapWeight")
    private double f12904a;

    @SerializedName("fatInfo")
    private gsg b;

    @SerializedName("subCategory")
    private int c;

    @SerializedName("startDay")
    private int d;

    @SerializedName("category")
    private int e;

    @SerializedName("updateTime")
    private long f;

    @SerializedName("weightManagerType")
    private int g;

    public gsg b() {
        return this.b;
    }

    public long c() {
        return this.f;
    }

    public String toString() {
        return "GoalDetail{mCategory=" + this.e + ", mSubCategory=" + this.c + ", mLastCaloriesGapWeight=" + this.f12904a + ", mWeightManagerType=" + this.g + ", mStartDay=" + this.d + ", mSimpleFatInfo=" + this.b + ", mUpdateTime=" + this.f + '}';
    }
}
