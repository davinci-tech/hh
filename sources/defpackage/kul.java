package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class kul {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("endTime")
    private long f14606a;

    @SerializedName("endLocalDate")
    private String b;

    @SerializedName("dataSourceOptions")
    private kuj c;

    @SerializedName("startLocalDate")
    private String d;

    @SerializedName("startTime")
    private long e;

    @SerializedName("subUserOption")
    private e i;

    @SerializedName("timeZone")
    private String j;

    public String f() {
        return this.j;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.b;
    }

    public long e() {
        return this.e;
    }

    public long d() {
        return this.f14606a;
    }

    public kuj a() {
        return this.c;
    }

    public String toString() {
        return "DataRequest{startLocalDate='" + this.d + "', endLocalDate='" + this.b + "', startTime=" + this.e + ", endTime=" + this.f14606a + ", dataSourceOptions=" + this.c + ", subUserOption=" + this.i + '}';
    }

    class e {

        @SerializedName("subUser")
        private String d;

        @SerializedName("subUserOnly")
        private boolean e;

        public String toString() {
            return "SubUserOption{subUser='" + this.d + "', subUserOnly=" + this.e + '}';
        }
    }
}
