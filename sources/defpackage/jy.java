package defpackage;

import com.alipay.sdk.m.h.b;
import com.alipay.sdk.m.h.e;

/* loaded from: classes7.dex */
public class jy extends jq {
    public static final /* synthetic */ boolean e = true;

    public jy(byte[] bArr) {
        super(bArr);
    }

    public static jy a() {
        try {
            return c("EX", 0L, new jw(""), (short) 0, new kc());
        } catch (Exception unused) {
            return null;
        }
    }

    public static jy c(String str, long j, b bVar, short s, e eVar) throws Exception {
        byte[] d = ju.d((byte) 1);
        boolean z = e;
        if (!z && d.length != 1) {
            throw new AssertionError();
        }
        byte[] b = ju.b(str.charAt(0), str.charAt(1));
        if (!z && b.length != 2) {
            throw new AssertionError();
        }
        byte[] d2 = ju.d(j);
        if (!z && d2.length != 8) {
            throw new AssertionError();
        }
        byte[] e2 = ju.e();
        if (!z && e2.length != 2) {
            throw new AssertionError();
        }
        bVar.a();
        byte[] d3 = ju.d(bVar.f859a);
        if (!z && d3.length != 1) {
            throw new AssertionError();
        }
        byte[] d4 = ju.d(bVar.b);
        if (!z && d4.length != 1) {
            throw new AssertionError();
        }
        byte[] bArr = (byte[]) bVar.c.clone();
        if (!z && bArr.length != (bVar.b & 255)) {
            throw new AssertionError();
        }
        byte[] c = ju.c(s);
        if (!z && c.length != 2) {
            throw new AssertionError();
        }
        byte[] e3 = ju.e();
        if (!z && e3.length != 2) {
            throw new AssertionError();
        }
        eVar.a();
        byte[] d5 = ju.d(eVar.f860a);
        if (!z && d5.length != 1) {
            throw new AssertionError();
        }
        byte[] bArr2 = (byte[]) eVar.b.clone();
        if (!z && bArr2.length != (eVar.f860a & 255)) {
            throw new AssertionError();
        }
        byte[] a2 = ju.a();
        if (z || a2.length == 4) {
            return new jy(ju.b(d, b, d2, e2, d3, d4, bArr, c, e3, d5, bArr2, a2));
        }
        throw new AssertionError();
    }
}
