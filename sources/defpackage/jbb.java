package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class jbb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("appName")
    private String f13707a;

    @SerializedName("appId")
    private int b;

    @SerializedName("appAuditInfo")
    private jax c;

    @SerializedName("packageName")
    private String d;

    @SerializedName("mcp")
    private String e;

    @SerializedName("prodType")
    private int j;

    public String a() {
        return this.e;
    }

    public String toString() {
        return "AppInfo{appId=" + this.b + ", prodType=" + this.j + ", appName='" + this.f13707a + "', packageName='" + this.d + "', mcp='" + this.e + "', appAuditInfo=" + this.c + '}';
    }
}
