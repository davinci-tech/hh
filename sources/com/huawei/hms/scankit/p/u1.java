package com.huawei.hms.scankit.p;

import java.util.Map;

/* loaded from: classes9.dex */
public final class u1 {

    /* renamed from: a, reason: collision with root package name */
    private final p6 f5888a = new p6(o3.l);

    public w1 a(s sVar, Map<l1, ?> map) throws a {
        t tVar = new t(sVar);
        try {
            try {
                return a(tVar, map);
            } catch (a unused) {
                throw a.a();
            }
        } catch (a unused2) {
            tVar.e();
            tVar.a(true);
            tVar.d();
            tVar.c();
            tVar.a();
            w1 a2 = a(tVar, map);
            a2.a(new l6(true));
            return a2;
        }
    }

    private w1 a(t tVar, Map<l1, ?> map) throws a {
        a8 d = tVar.d();
        c3 a2 = tVar.c().a();
        c1[] a3 = c1.a(tVar.b(), d, a2);
        int i = 0;
        for (c1 c1Var : a3) {
            i += c1Var.b();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (c1 c1Var2 : a3) {
            byte[] a4 = c1Var2.a();
            int b = c1Var2.b();
            a(a4, b);
            int i3 = 0;
            while (i3 < b) {
                bArr[i2] = a4[i3];
                i3++;
                i2++;
            }
        }
        return p1.a(bArr, d, a2, map);
    }

    private void a(byte[] bArr, int i) throws a {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f5888a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (a unused) {
            throw a.a();
        }
    }
}
