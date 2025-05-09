package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class kvb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("endTime")
    private long f14622a;

    @SerializedName("startTime")
    private long d;

    public long b() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public long e() {
        return this.f14622a;
    }

    public void c(long j) {
        this.f14622a = j;
    }

    public String toString() {
        return "BaseUiData{startTime=" + this.d + ", endTime=" + this.f14622a + '}';
    }
}
