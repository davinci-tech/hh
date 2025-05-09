package com.huawei.health.device.kit.hwwsp.hagrid.bean;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cgb;
import defpackage.cgf;
import defpackage.cgh;
import defpackage.cvx;

/* loaded from: classes3.dex */
public class MultiPackageDataRecLogic {

    /* renamed from: a, reason: collision with root package name */
    private MultiPackageCb f2224a;
    private byte[] b;
    private String c;
    private String d;
    private int e = 0;

    public interface MultiPackageCb {
        void onDataReceiveDone(byte[] bArr);
    }

    public MultiPackageDataRecLogic(String str, MultiPackageCb multiPackageCb, String str2, Context context) {
        this.c = str;
        this.f2224a = multiPackageCb;
        this.d = str2;
    }

    public void c(byte[] bArr) {
        cgb d = cgf.d(bArr);
        if (d == null) {
            this.f2224a.onDataReceiveDone(null);
            this.b = null;
            this.e = 0;
            LogUtil.b(this.c, "recv multi package error.");
            return;
        }
        int e = (d.e() + 1) * 15;
        if (this.b == null) {
            this.b = new byte[e];
        }
        if (d.a() != this.e) {
            LogUtil.a(this.c, "recv seq error");
            this.f2224a.onDataReceiveDone(null);
            this.b = null;
            this.e = 0;
            return;
        }
        LogUtil.a(this.c, "recv ", Integer.valueOf(d.a()), "/", Integer.valueOf(d.e()));
        int a2 = d.a() * 15;
        byte[] d2 = d.d();
        if (d2 != null && d2.length + a2 <= e) {
            e(this.b, a2, d.d());
            this.e++;
            c(d);
        } else {
            LogUtil.a(this.c, "recv data length error");
            this.f2224a.onDataReceiveDone(this.b);
            this.b = null;
            this.e = 0;
        }
    }

    private void c(cgb cgbVar) {
        if (cgbVar == null) {
            return;
        }
        byte[] bArr = new byte[16];
        if (cgbVar.a() >= cgbVar.e()) {
            if (cgbVar.b() != 15) {
                int e = (cgbVar.e() * 15) + cgbVar.b();
                byte[] bArr2 = new byte[e];
                c(bArr2, 0, this.b, e);
                this.b = bArr2;
                if (cgbVar.c() == -51) {
                    System.arraycopy(this.b, 0, bArr, 0, 16);
                    LogUtil.a(this.c, "recv data mOnePackage", cvx.d(this.b));
                    LogUtil.a(this.c, "recv data ivinfo", cvx.d(bArr));
                    byte[] bArr3 = this.b;
                    int length = bArr3.length - 16;
                    byte[] bArr4 = new byte[length];
                    System.arraycopy(bArr3, 16, bArr4, 0, length);
                    LogUtil.a(this.c, "recv data playload", cvx.d(bArr4));
                    byte[] a2 = cgh.a(bArr4, cgh.d(this.d), bArr);
                    this.b = a2;
                    LogUtil.a(this.c, "recv data desEncrypt mOnePackage", cvx.d(a2));
                }
            }
            String str = this.c;
            Object[] objArr = new Object[3];
            objArr[0] = "recv data finished(";
            byte[] bArr5 = this.b;
            objArr[1] = bArr5 != null ? Integer.valueOf(bArr5.length) : " mOnePackage is null ";
            objArr[2] = " Bytes)";
            LogUtil.a(str, objArr);
            MultiPackageCb multiPackageCb = this.f2224a;
            if (multiPackageCb != null) {
                multiPackageCb.onDataReceiveDone(this.b);
            }
            this.b = null;
            this.e = 0;
        }
    }

    private static int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr == null || bArr2 == null || i < 0 || i + i2 > bArr.length) {
            return 0;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = bArr2[i3];
        }
        return i2;
    }

    private static int e(byte[] bArr, int i, byte[] bArr2) {
        return c(bArr, i, bArr2, bArr2 != null ? bArr2.length : 0);
    }
}
