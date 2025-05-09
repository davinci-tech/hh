package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dry {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("configDescription")
    private String f11812a;

    @SerializedName("appName")
    private String b;

    @SerializedName("appType")
    private String c;

    @SerializedName("configData")
    private String d;

    @SerializedName("configName")
    private String e;

    @SerializedName("version")
    private long f;

    @SerializedName("type")
    private String g;

    @SerializedName("id")
    private String h;

    public String b() {
        return this.d;
    }

    public String toString() {
        return "GetUserSampleConfig{mType=" + this.g + ", mId=" + this.h + ", mConfigData=" + this.d + ", mConfigName=" + this.e + ", mConfigDescription=" + this.f11812a + ", mVersion=" + this.f + ", mAppName=" + this.b + ", mAppType=" + this.c + "}";
    }
}
