package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.HiDateUtil;

/* loaded from: classes7.dex */
public class qux {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("type")
    private int f16602a;

    @SerializedName("endTime")
    private long b;

    @SerializedName("startTime")
    private long e;

    public void a(int i) {
        this.f16602a = i;
    }

    public void a(long j) {
        this.e = j;
    }

    public void d(long j) {
        this.b = j;
    }

    public String toString() {
        return "FastingLiteFaData{mType=" + this.f16602a + ", mStartTime=" + HiDateUtil.b(this.e, "yyyy-MM-dd HH:mm:ss:SSS") + ", mEndTime=" + HiDateUtil.b(this.b, "yyyy-MM-dd HH:mm:ss:SSS") + '}';
    }
}
