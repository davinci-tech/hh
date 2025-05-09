package defpackage;

import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;

/* loaded from: classes6.dex */
public class nbw extends AbsPairedDevice {

    /* renamed from: a, reason: collision with root package name */
    private String f15239a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String g;
    private String h;
    private int i;

    public void d(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int j() {
        return ((Integer) jdy.d(Integer.valueOf(this.i))).intValue();
    }

    public void b(String str) {
        this.c = (String) jdy.d(str);
    }

    public String c() {
        return (String) jdy.d(this.c);
    }

    public void f(String str) {
        this.h = (String) jdy.d(str);
    }

    public String g() {
        return (String) jdy.d(this.h);
    }

    public String i() {
        return (String) jdy.d(this.g);
    }

    public void e(String str) {
        this.e = str;
    }

    public String b() {
        return this.e;
    }

    public void c(String str) {
        this.d = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.d);
    }

    public String d() {
        return (String) jdy.d(this.f15239a);
    }

    public void a(String str) {
        this.f15239a = (String) jdy.d(str);
    }

    public String e() {
        return (String) jdy.d(this.b);
    }

    public void d(String str) {
        this.b = (String) jdy.d(str);
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public int getPairedIDType() {
        return j();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getPairedID() {
        return b();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getPairedDeviceName() {
        return a();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalId() {
        return c();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalModel() {
        return d();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalSwVersion() {
        return e();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalMsisdn() {
        return i();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalEid() {
        return b();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public lnk getSecondaryDeviceId() {
        lnk lnkVar = new lnk();
        lnkVar.b(c());
        lnkVar.a(g());
        return lnkVar;
    }

    public String toString() {
        return "deviceinfo type=" + this.i + ",name=" + this.d;
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalVendor() {
        return "";
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice
    public String getTerminalIccid() {
        return "";
    }
}
