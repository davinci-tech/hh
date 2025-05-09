package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class enj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("timeZone")
    private String f12110a;

    @SerializedName("motionPathStartTime")
    private Long b;

    @SerializedName("hotPathOperationInfo")
    private enf e;

    public Long d() {
        return this.b;
    }

    public enf a() {
        return this.e;
    }

    public String toString() {
        return "HotPathParticipateHistory{motionPathStartTime=" + this.b + ", timeZone='" + this.f12110a + "', hotPathOperationInfo=" + this.e + '}';
    }
}
