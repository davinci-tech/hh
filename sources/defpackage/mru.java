package defpackage;

import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;

/* loaded from: classes.dex */
public class mru {

    /* renamed from: a, reason: collision with root package name */
    private DeviceDownloadSourceInfo f15135a;
    private String b;
    private msc c;
    private String d;
    private String e;
    private String i;

    public mru() {
    }

    public mru(String str, String str2, String str3, msc mscVar) {
        this.e = str;
        this.i = str2;
        this.b = str3;
        this.c = mscVar;
    }

    public String d() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public String c() {
        return this.e;
    }

    public String j() {
        return this.i;
    }

    public msc a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public DeviceDownloadSourceInfo e() {
        return this.f15135a;
    }

    public void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.f15135a = deviceDownloadSourceInfo;
    }
}
