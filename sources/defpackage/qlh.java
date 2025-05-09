package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.HiDateUtil;
import java.io.Serializable;

/* loaded from: classes.dex */
public class qlh implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("plan")
    private String f16473a;

    @SerializedName("title")
    private String b;

    @SerializedName("preStartTime")
    private long c;

    @SerializedName("startTime")
    private long d;

    @SerializedName("endTime")
    private long e;

    @SerializedName("type")
    private int f;

    public int b() {
        return this.f;
    }

    public void d(int i) {
        this.f = i;
    }

    public void c(String str) {
        this.b = str;
    }

    public void a(String str) {
        this.f16473a = str;
    }

    public long d() {
        return this.c;
    }

    public void c(long j) {
        this.c = j;
    }

    public long e() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public long a() {
        return this.e;
    }

    public void e(long j) {
        this.e = j;
    }

    public String toString() {
        return "FastingLiteViewBean{mType=" + this.f + ", mTitle='" + this.b + "', mPlan='" + this.f16473a + "', mPreStartTime=" + HiDateUtil.b(this.c, "yyyy-MM-dd HH:mm:ss:SSS") + ", mStartTime=" + HiDateUtil.b(this.d, "yyyy-MM-dd HH:mm:ss:SSS") + ", mEndTime=" + HiDateUtil.b(this.e, "yyyy-MM-dd HH:mm:ss:SSS") + '}';
    }
}
