package defpackage;

/* loaded from: classes9.dex */
class kga {
    private String b;
    private String c;
    private String e;

    public kga(String str, String str2, String str3) {
        this.c = str;
        this.e = str2;
        this.b = str3;
    }

    public String d() {
        return this.b;
    }

    public String a() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String toString() {
        return "DeviceModeInfo{mDeviceModel='" + this.c + ", mDeviceId='" + this.e + ", mDeviceType='" + this.b + '}';
    }
}
