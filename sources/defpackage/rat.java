package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class rat {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("sn")
    private String f16685a;

    @SerializedName("udid")
    private String c;

    @SerializedName("deviceName")
    private String d;

    @SerializedName("devType")
    private String e;

    public String a() {
        return this.e;
    }

    public String e() {
        return this.c;
    }

    public String toString() {
        return "DevInfo{devType=" + this.e + ", deviceName=" + this.d + ", udid=" + this.c + ", sn=" + this.f16685a + '}';
    }
}
