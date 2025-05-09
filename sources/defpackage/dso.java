package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dso {

    @SerializedName("isCreatedPlan")
    private boolean b;

    @SerializedName("resultCode")
    private int c;

    @SerializedName("latestJoinTime")
    private long d;

    @SerializedName("currentTime")
    private long e;

    public int a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }

    public boolean e() {
        return this.b;
    }

    public String toString() {
        return "InterventionPlanInfoResponse{mResultCode=" + this.c + ", mLatestJoinTime=" + this.d + ", mCurrentTime=" + this.e + ", mIsCreatedPlan=" + this.b + '}';
    }
}
