package com.huawei.hms.scankit.p;

import java.util.Map;

/* loaded from: classes9.dex */
public final class t1 {
    private z7 b = null;

    /* renamed from: a, reason: collision with root package name */
    private final p6 f5883a = new p6(o3.m);

    public w1 a(s sVar, Map<l1, ?> map) throws a {
        v vVar = new v(sVar);
        z7 a2 = vVar.a();
        this.b = a2;
        e1[] a3 = e1.a(vVar.b(), a2);
        int i = 0;
        for (e1 e1Var : a3) {
            i += e1Var.b();
        }
        byte[] bArr = new byte[i];
        int length = a3.length;
        for (int i2 = 0; i2 < length; i2++) {
            e1 e1Var2 = a3[i2];
            byte[] a4 = e1Var2.a();
            int b = e1Var2.b();
            a(a4, b);
            for (int i3 = 0; i3 < b; i3++) {
                bArr[(i3 * length) + i2] = a4[i3];
            }
        }
        return o1.a(bArr, map);
    }

    private void a(byte[] bArr, int i) throws a {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f5883a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (a unused) {
            throw a.a();
        }
    }

    public z7 a() {
        return this.b;
    }
}
