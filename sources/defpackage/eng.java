package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class eng implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("huid")
    private String f12108a;

    @SerializedName("num")
    private int b;

    @SerializedName("timeZone")
    private String c;

    @SerializedName("motionPathStartTime")
    private long d;

    @SerializedName("minTime")
    private long e;

    public long a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public String e() {
        return this.f12108a;
    }

    public String toString() {
        return "ParticipateUserInfo{timeZone='" + this.c + "', motionPathStartTime=" + this.d + ", minTime=" + this.e + ", num=" + this.b + ", huid=" + this.f12108a + '}';
    }
}
