package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class exf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("devId")
    private String f12364a;

    @SerializedName("commonUse")
    private String c = "FAMILY_SPACE";

    @SerializedName("operation")
    private String d;

    @SerializedName("pushUdid")
    private String e;

    public exf() {
    }

    public exf(String str, String str2, String str3) {
        this.f12364a = str;
        this.d = str2;
        this.e = str3;
    }

    public String toString() {
        return "{devId=" + this.f12364a + ", commonUse=" + this.c + ", operation=" + this.d + ", pushUdid=" + this.e + '}';
    }
}
