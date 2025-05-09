package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.io.Serializable;

/* loaded from: classes8.dex */
public class cbh implements Serializable {
    private static final long serialVersionUID = 3401094552994390416L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planEndTime")
    private long f593a;

    @SerializedName("lastTimestamp")
    private long b;

    @SerializedName("lastSystolic")
    private int c;

    @SerializedName("lastDiastolic")
    private int d;

    @SerializedName("planId")
    private String e;

    @SerializedName(ParsedFieldTag.EXCHANGE_PROPORTION)
    private float f;

    @SerializedName("planLocalDate")
    private int i;

    @SerializedName("planStartTime")
    private long j;

    public String c() {
        return this.e;
    }

    public long i() {
        return this.j;
    }

    public long a() {
        return this.f593a;
    }

    public float f() {
        return this.f;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public long b() {
        return this.b;
    }

    public String toString() {
        return "DynamicBloodPressureStatus{mPlanId='" + this.e + "', mPlanStartTime=" + this.j + ", mPlanEndTime=" + this.f593a + ", mPlanLocalDate=" + this.i + ", mRatio=" + this.f + ", mLastSystolic=" + this.c + ", mLastDiastolic=" + this.d + ", mLastTimestamp=" + this.b + '}';
    }
}
