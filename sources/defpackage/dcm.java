package defpackage;

import java.io.Serializable;

/* loaded from: classes8.dex */
public class dcm implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f11584a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String h;
    private int j;

    public void b(int i) {
        this.j = i;
    }

    public void j(String str) {
        this.h = str;
    }

    public void a(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.f11584a = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public String toString() {
        return "NemoDeviceInfo{deviceType=" + this.j + ", deviceVersion='" + this.h + "', deviceSoftVersion='" + this.c + "', deviceSn='" + this.d + "', deviceModel='" + this.e + "', deviceOtaPackageName='" + this.f11584a + "', deviceSubModelId='" + this.b + "'}";
    }
}
