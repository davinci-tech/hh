package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.HiDateUtil;
import java.io.Serializable;

/* loaded from: classes8.dex */
public class gsc implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("title")
    private String f12902a;

    @SerializedName("preStartTime")
    private long b;

    @SerializedName("startTime")
    private long c;

    @SerializedName("endTime")
    private long d;

    @SerializedName("plan")
    private String e;

    @SerializedName("type")
    private int f;

    public int c() {
        return this.f;
    }

    public long e() {
        return this.d;
    }

    public String toString() {
        return "FastingLiteViewBean{mType=" + this.f + ", mTitle='" + this.f12902a + "', mPlan='" + this.e + "', mPreStartTime=" + HiDateUtil.b(this.b, "yyyy-MM-dd HH:mm:ss:SSS") + ", mStartTime=" + HiDateUtil.b(this.c, "yyyy-MM-dd HH:mm:ss:SSS") + ", mEndTime=" + HiDateUtil.b(this.d, "yyyy-MM-dd HH:mm:ss:SSS") + '}';
    }
}
