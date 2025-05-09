package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("id")
    private int f11816a;

    @SerializedName("rank")
    private int b;

    @SerializedName("userTimes")
    private int c;

    @SerializedName("people")
    private int d;

    @SerializedName("userWeekTimes")
    private int e;

    @SerializedName("weekNo")
    private int g;

    @SerializedName("weekRank")
    private int h;

    public int d() {
        return this.f11816a;
    }

    public int c() {
        return this.b;
    }

    public int a() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public String toString() {
        return "Stats{mId=" + this.f11816a + ", mRank=" + this.b + ", mPeople=" + this.d + ", mWeekNo=" + this.g + ", mWeekRank=" + this.h + ", mUserTimes=" + this.c + ", mUserWeekTimes=" + this.e + "}";
    }
}
