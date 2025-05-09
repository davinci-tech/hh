package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class njf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("encourageType")
    private int f15326a;

    @SerializedName("currentDayAchievable")
    private int b;

    @SerializedName("encourageDate")
    private int c;

    @SerializedName("achieveDays")
    private int d;

    @SerializedName("needAchieveDays")
    private int e;

    public int a() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int b() {
        return this.f15326a;
    }

    public boolean e() {
        return this.b == 1;
    }
}
