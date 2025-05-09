package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class brm {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("recordName")
    private String f482a;

    @SerializedName("examinationInstitution")
    private String b;

    @SerializedName("extendData")
    private String c;

    @SerializedName("examinationReport")
    private String d;

    @SerializedName("name")
    private String e;

    @SerializedName("remarks")
    private String i;

    public void a(String str) {
        this.f482a = str;
    }

    public String b() {
        return this.f482a;
    }

    public String e() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public void g(String str) {
        this.i = str;
    }

    public String h() {
        return this.i;
    }

    public String d() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String c() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public String a() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }
}
