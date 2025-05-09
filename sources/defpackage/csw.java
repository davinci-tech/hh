package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class csw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("psk")
    private String f11453a;

    @SerializedName("code")
    private String b;

    @SerializedName("devId")
    private String c;

    @SerializedName("cloudUrl")
    private String e;

    public void a(String str) {
        this.c = str;
    }

    public String e() {
        return this.c;
    }

    public void b(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.f11453a = str;
    }

    public String toString() {
        return "SoftApRegisterInfo{devId =" + cpw.d(this.c) + ", code ='" + cpw.d(this.b) + "', cloudUrl ='" + cpw.d(this.e) + "', psk ='" + cpw.d(this.f11453a) + "'}";
    }
}
