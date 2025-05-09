package defpackage;

import health.compact.a.LogUtil;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class izf {
    private int b;
    private byte[] d;
    private boolean g;
    private int h;
    private String j;
    private boolean f = true;
    private int c = -1;

    /* renamed from: a, reason: collision with root package name */
    private int f13678a = 1;
    private int i = 0;
    private int e = 0;

    public void e(String str) {
        if (str == null) {
            return;
        }
        this.j = (String) bmb.d(str);
    }

    public String h() {
        return (String) bmb.d(this.j);
    }

    public void e(int i) {
        this.b = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) bmb.d(Integer.valueOf(this.b))).intValue();
    }

    public void e(byte[] bArr) {
        if (bArr != null) {
            this.d = (byte[]) bmb.d(Arrays.copyOf(bArr, bArr.length));
        } else {
            LogUtil.c("BTDeviceCommand", "dataContent is null");
        }
    }

    public byte[] d() {
        byte[] bArr = this.d;
        if (bArr != null) {
            return (byte[]) bmb.d(Arrays.copyOf(bArr, bArr.length));
        }
        return null;
    }

    public void b(int i) {
        this.h = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int j() {
        return ((Integer) bmb.d(Integer.valueOf(this.h))).intValue();
    }

    public void e(boolean z) {
        this.g = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean f() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.g))).booleanValue();
    }

    public void c(int i) {
        this.c = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int c() {
        return ((Integer) bmb.d(Integer.valueOf(this.c))).intValue();
    }

    public void a(boolean z) {
        this.f = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean i() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.f))).booleanValue();
    }

    public void a(int i) {
        this.f13678a = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) bmb.d(Integer.valueOf(this.f13678a))).intValue();
    }

    public void i(int i) {
        this.i = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int g() {
        return ((Integer) bmb.d(Integer.valueOf(this.i))).intValue();
    }

    public void d(int i) {
        this.e = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int a() {
        return ((Integer) bmb.d(Integer.valueOf(this.e))).intValue();
    }

    public String toString() {
        return "BTDeviceCommand{mServiceID='" + blq.b(this.i) + "', mCommandID='" + blq.b(this.e) + "', mDataLen='" + this.b + "', mDataContent= " + blq.d(this.d) + '}';
    }
}
