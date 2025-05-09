package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class omk {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("duration")
    private long f15786a;

    @SerializedName("resultCode")
    private String b;

    @SerializedName("resultDesc")
    private String d;

    @SerializedName("totalCount")
    private int e;

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public long a() {
        return this.f15786a;
    }

    public String toString() {
        return "UserAccumulatedPlayDurationRsp{resultCode='" + this.b + "', resultDesc='" + this.d + "', duration='" + this.f15786a + "', totalCount='" + this.e + "'}";
    }
}
