package com.huawei.hms.scankit.p;

import java.util.Map;

/* loaded from: classes9.dex */
public final class v1 {

    /* renamed from: a, reason: collision with root package name */
    private final p6 f5895a = new p6(o3.l);

    public w1 a(s sVar, Map<l1, ?> map) throws a {
        u uVar = new u(sVar);
        try {
            return a(uVar, map);
        } catch (a e) {
            try {
                uVar.e();
                uVar.a(true);
                uVar.d();
                uVar.c();
                uVar.a();
                w1 a2 = a(uVar, map);
                a2.a(new i6(true));
                return a2;
            } catch (a unused) {
                throw e;
            }
        }
    }

    private w1 a(u uVar, Map<l1, ?> map) throws a {
        b8 d = uVar.d();
        b3 b = uVar.c().b();
        d1[] a2 = d1.a(uVar.b(), d, b);
        int i = 0;
        for (d1 d1Var : a2) {
            i += d1Var.b();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (d1 d1Var2 : a2) {
            byte[] a3 = d1Var2.a();
            int b2 = d1Var2.b();
            a(a3, b2);
            int i3 = 0;
            while (i3 < b2) {
                bArr[i2] = a3[i3];
                i3++;
                i2++;
            }
        }
        return r1.a(bArr, d, b, map);
    }

    private void a(byte[] bArr, int i) throws a {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f5895a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (a unused) {
            throw a.a();
        }
    }
}
