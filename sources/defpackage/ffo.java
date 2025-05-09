package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class ffo {

    @SerializedName("mTime")
    private long e = -1;

    @SerializedName("mCadence")
    private int d = -1;

    @SerializedName("mDeltaCircle")
    private long c = -1;

    @SerializedName("mSumCircle")
    private long b = -1;

    public void a(long j) {
        this.e = j;
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public long c() {
        return this.e;
    }

    public String toString() {
        return "RidePostureDataInfo {mCadence = " + this.d + "mDeltaCircle = " + this.c + "mSumCircle = " + this.b + "}";
    }
}
