package defpackage;

import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import health.compact.a.CommonLibUtil;

/* loaded from: classes3.dex */
public class csu {

    /* renamed from: a, reason: collision with root package name */
    private int f11452a;
    private int b;
    private String c;
    private byte[] d;
    private String e;
    private String f;
    private int g;
    private ScanFilter h;
    private byte[] i;
    private String j;
    private String l;
    private String o;

    public String i() {
        return this.o;
    }

    public String h() {
        return this.j;
    }

    public void a(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.f11452a = i;
    }

    public void f(String str) {
        this.l = str;
    }

    public byte[] g() {
        byte[] bArr = this.i;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.i = (byte[]) bArr.clone();
        }
    }

    public String d() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public byte[] a() {
        byte[] bArr = this.d;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            this.d = (byte[]) bArr.clone();
        }
    }

    public int b() {
        return this.g;
    }

    public String e() {
        return this.f;
    }

    public ScanFilter f() {
        return this.h;
    }

    public void d(ScanFilter scanFilter) {
        this.h = scanFilter;
    }

    public void e(String str) {
        this.o = (String) cpt.d(str);
    }

    public void b(String str) {
        this.j = (String) cpt.d(str);
    }

    public void d(String str) {
        this.c = (String) cpt.d(str);
    }

    public void e(int i) {
        this.g = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public void a(String str) {
        this.f = (String) cpt.d(str);
    }

    public String c() {
        return (String) cpt.d(this.c);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("MulticastInfo{mSsid =");
        stringBuffer.append(this.o);
        stringBuffer.append("mPassword =").append(cpw.d(this.j));
        stringBuffer.append("mAppIpAddress =").append(cpw.d(String.valueOf(this.b)));
        stringBuffer.append("mAppPort =").append(this.f11452a);
        stringBuffer.append("mToken =").append(this.l);
        stringBuffer.append("mDeviceSn =").append(this.c);
        stringBuffer.append("mHuaweiKeyIv =").append(CommonLibUtil.b(this.i));
        stringBuffer.append("mAesKey =").append(this.e);
        stringBuffer.append("mAesKeyIv =").append(CommonLibUtil.b(this.d));
        stringBuffer.append("mEncryptMode =").append(this.g);
        stringBuffer.append("mDeviceSsid =").append(this.f);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
