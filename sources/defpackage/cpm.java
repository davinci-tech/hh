package defpackage;

import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cpm {
    private int b;
    private String c;
    private int d;
    private String e;
    private boolean f;
    private String g;
    private long h;
    private int j;
    private int l;
    private int n;
    private String o;

    /* renamed from: a, reason: collision with root package name */
    private String f11403a = "";
    private int m = -1;
    private boolean i = true;

    public void a(String str) {
        this.g = str;
    }

    public boolean l() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public int j() {
        return this.n;
    }

    public void b(int i) {
        this.n = i;
    }

    public String d() {
        return (String) cpt.d(this.c);
    }

    public void e(String str) {
        this.c = str;
    }

    public int e() {
        return ((Integer) cpt.d(Integer.valueOf(this.b))).intValue();
    }

    public void a(int i) {
        this.b = i;
    }

    public int b() {
        return ((Integer) cpt.d(Integer.valueOf(this.d))).intValue();
    }

    public void c(int i) {
        this.d = i;
    }

    public int i() {
        return this.j;
    }

    public void d(int i) {
        this.j = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public String f() {
        return (String) cpt.d(this.o);
    }

    public void d(String str) {
        this.o = str;
    }

    public String a() {
        return (String) cpt.d(this.f11403a);
    }

    public void b(String str) {
        this.f11403a = str;
    }

    public int m() {
        return ((Integer) cpt.d(Integer.valueOf(this.l))).intValue();
    }

    public void e(int i) {
        this.l = i;
    }

    public String toString() {
        return "[mDeviceName = " + this.c + ",mDeviceConnectState = " + this.b + "]";
    }

    public void a(DeviceInfo deviceInfo, int i, int i2) {
        if (deviceInfo == null) {
            LogUtil.h("DeviceInfoForWear", "setData deviceInfo is null");
            return;
        }
        if ("PORSCHE DESIGN".equals(deviceInfo.getDeviceName())) {
            d(String.valueOf(R.mipmap._2131821260_res_0x7f1102cc));
            e(R.mipmap._2131821260_res_0x7f1102cc);
        } else {
            d(String.valueOf(i2));
            e(i);
        }
        a(deviceInfo.getDeviceConnectState());
        c(deviceInfo.getDeviceActiveState());
        b(deviceInfo.getDeviceIdentify());
        a(deviceInfo.getHiLinkDeviceId());
        b(deviceInfo.getPowerSaveModel());
        f(deviceInfo.getAutoDetectSwitchStatus());
    }

    public int o() {
        return ((Integer) cpt.d(Integer.valueOf(this.m))).intValue();
    }

    public void f(int i) {
        this.m = i;
    }

    public long h() {
        return this.h;
    }

    public void d(long j) {
        this.h = j;
    }

    public boolean g() {
        return this.f;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public String c() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }
}
