package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cty {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f11476a;

    public static void b() {
        byte[] e = ctv.e();
        if (e == null || e.length == 0) {
            return;
        }
        int i = e[0];
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = e[((i2 + i) * 8) - 2];
        }
        for (int i3 = 0; i3 < i / 2; i3++) {
            byte b = bArr[i3];
            int i4 = (i - 1) - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = b;
        }
        int length = ctv.d().length;
        byte[] bArr2 = new byte[length];
        for (int i5 = 0; i5 < ctv.d().length; i5++) {
            bArr2[i5] = ctv.d()[(ctv.d().length - 1) - i5];
        }
        byte[] d = ctv.d(new StringBuilder("53B848A734F834571DF6DF096ACD1A84A47D150FA8").reverse().toString());
        int length2 = ctu.d().length;
        byte[] bArr3 = new byte[length2];
        for (int i6 = 0; i6 < ctu.d().length; i6++) {
            bArr3[i6] = ctu.d()[(ctu.d().length - 1) - i6];
        }
        byte[] bArr4 = new byte[d.length + length + length2];
        byte[] bArr5 = new byte[16];
        byte[] bArr6 = new byte[32];
        System.arraycopy(bArr2, 0, bArr4, 0, length);
        System.arraycopy(d, 0, bArr4, length, d.length);
        System.arraycopy(bArr3, 0, bArr4, length + d.length, length2);
        System.arraycopy(bArr4, 0, bArr5, 0, 16);
        System.arraycopy(bArr4, 16, bArr6, 0, 32);
        try {
            f11476a = ctu.b(knl.c(bArr, bArr5, bArr6));
        } catch (Exception unused) {
            LogUtil.b("CommonPskUtil", "getPskFromSo get mPskData is exception");
            f11476a = ctu.b(new byte[0]);
        }
    }

    public static byte[] c() {
        byte[] bArr = f11476a;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }
}
