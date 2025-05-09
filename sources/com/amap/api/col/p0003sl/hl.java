package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class hl {

    /* renamed from: a, reason: collision with root package name */
    private static int f1129a = 6;

    private static byte[] a() {
        try {
            return a("16,99,86,77,511,98,86,97,511,99,86,77,511,18,48,97,511,99,86,77,511,58,601,77,511,58,48,77,511,58,86,87,511,18,48,97,511,58,86,87,511,18,48,97,511,98,48,87,511,98,48,97,511,99,86,77,511,58,221,77,511,98,601,87");
        } catch (Throwable th) {
            iv.c(th, "AMU", "grk");
            return null;
        }
    }

    private static byte[] b() {
        try {
            return a("16,18,86,97,511,18,48,97,511,18,86,97,511,58,86,77,511,18,86,97,511,58,48,77,511,18,86,97,511,58,601,77,511,18,86,97,511,58,221,77,511,18,86,97,511,58,86,87,511,18,86,97,511,58,48,87,511,18,86,97,511,58,601,87");
        } catch (Throwable th) {
            iv.c(th, "AMU", "giv");
            return null;
        }
    }

    private static byte[] a(String str) {
        try {
            String[] split = new StringBuffer(str).reverse().toString().split(",");
            int length = split.length;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(id.a(new String(bArr)), "UTF-8")).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            iv.c(th, "AMU", "rcs");
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            return hs.b(a(), bArr, b());
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return hs.a(a(), bArr, b());
        } catch (Exception unused) {
            return new byte[0];
        }
    }
}
