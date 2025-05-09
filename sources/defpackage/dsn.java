package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dsn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultCode")
    private int f11822a;

    @SerializedName("resultDesc")
    private String c;

    @SerializedName("healthWeekReport")
    private dsb e;

    public int c() {
        return this.f11822a;
    }

    public dsb d() {
        return this.e;
    }

    public String toString() {
        return "HealthReportResponse{mResultCode=" + this.f11822a + ", mResultDescription=" + this.c + ", mHealthWeekReport=" + this.e + "}";
    }
}
