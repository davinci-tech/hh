package defpackage;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class juz {

    /* renamed from: a, reason: collision with root package name */
    private String f14107a;
    private String b;
    private String d;
    private String e;

    public String d() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String e() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String b() {
        return this.f14107a;
    }

    public void c(String str) {
        this.f14107a = str;
    }

    public String a() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public boolean c() {
        return TextUtils.isEmpty(this.e) || TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.f14107a) || TextUtils.isEmpty(this.b);
    }

    public String toString() {
        return "EphemerisUrlSonyEntity{ver='" + this.e + "', downloadUrl='" + this.d + "', signature='" + this.f14107a + "', fileId='" + this.b + "'}";
    }
}
