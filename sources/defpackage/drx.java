package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class drx {

    @SerializedName("id")
    private int b;

    @SerializedName("endTime")
    private int c;

    @SerializedName("timestamp")
    private long d;

    @SerializedName("startTime")
    private int e;

    public int c() {
        return this.b;
    }

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int b() {
        return this.c;
    }

    public String toString() {
        return "HealthLifeChallengeBean{mId=" + this.b + ", mStartTime=" + this.e + ", mEndTime=" + this.c + ", mTimestamp=" + this.d + "}";
    }
}
