package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class kuz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("modifiedTime")
    private long f14619a;

    @SerializedName("endLocalDate")
    private String b;

    @SerializedName("dataType")
    private kun c;

    @SerializedName("startLocalDate")
    private String d;

    @SerializedName("endTime")
    private long e;

    @SerializedName("timeZone")
    private String g;

    @SerializedName("subUser")
    private String h;

    @SerializedName("startTime")
    private long i;

    public kun a() {
        return this.c;
    }

    public long d() {
        return this.i;
    }

    public String toString() {
        return "SampleDataBase{dataType=" + this.c + ", startLocalDate='" + this.d + "', endLocalDate='" + this.b + "', startTime=" + this.i + ", endTime=" + this.e + ", timeZone='" + this.g + "', modifiedTime=" + this.f14619a + ", subUser='" + this.h + "'}";
    }
}
