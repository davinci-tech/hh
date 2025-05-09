package defpackage;

import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;

/* loaded from: classes3.dex */
class cdj {

    /* renamed from: a, reason: collision with root package name */
    private DeviceDownloadSourceInfo f643a;
    private cvc b;
    private String c;
    private String d;
    private String e;
    private String i;

    public cdj() {
    }

    public cdj(String str, String str2, String str3, cvc cvcVar) {
        this.c = str;
        this.i = str2;
        this.d = str3;
        this.b = cvcVar;
    }

    public String a() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public String g() {
        return this.i;
    }

    public cvc d() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public DeviceDownloadSourceInfo b() {
        return this.f643a;
    }

    public void c(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.f643a = deviceDownloadSourceInfo;
    }
}
