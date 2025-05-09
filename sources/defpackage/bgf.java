package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;

/* loaded from: classes8.dex */
public class bgf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("uid")
    private String f354a;

    @SerializedName("appName")
    private String b;

    @SerializedName("appLogo")
    private String c;

    @SerializedName("status")
    private String d;

    @SerializedName(SdkCfgSha256Record.PKGNAME)
    private String e;

    public String d() {
        return this.f354a;
    }

    public void b(String str) {
        this.f354a = str;
    }

    public String a() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public void a(String str) {
        this.c = str;
    }

    public void e(String str) {
        this.d = str;
    }
}
