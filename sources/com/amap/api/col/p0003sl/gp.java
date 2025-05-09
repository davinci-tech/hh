package com.amap.api.col.p0003sl;

/* loaded from: classes8.dex */
public final class gp {

    /* renamed from: a, reason: collision with root package name */
    private static int f1070a = 4;

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 0) {
            return bArr;
        }
        int length = bArr.length;
        int i = f1070a;
        int i2 = ((length / i) + 1) * i;
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        for (int i3 = length; i3 < i2; i3++) {
            bArr3[i3] = (byte) (i - (length % i));
        }
        return a(a(a(bArr3), a(bArr2)));
    }

    private static int[] a(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int i = length - 1;
        if (i <= 0) {
            return iArr;
        }
        if (iArr2.length < 4) {
            int[] iArr3 = new int[4];
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
            iArr2 = iArr3;
        }
        int i2 = iArr[i];
        int i3 = 0;
        for (int i4 = (52 / length) + 6; i4 > 0; i4--) {
            i3 -= 1640531527;
            int i5 = (i3 >>> 2) & 3;
            int i6 = 0;
            while (i6 < i) {
                int i7 = i6 + 1;
                int i8 = iArr[i7];
                i2 = ((((i2 >>> 5) ^ (i8 << 2)) + ((i8 >>> 3) ^ (i2 << 4))) ^ ((i8 ^ i3) + (i2 ^ iArr2[(i6 & 3) ^ i5]))) + iArr[i6];
                iArr[i6] = i2;
                i6 = i7;
            }
            int i9 = iArr[0];
            i2 = ((((i2 >>> 5) ^ (i9 << 2)) + ((i9 >>> 3) ^ (i2 << 4))) ^ ((i9 ^ i3) + (i2 ^ iArr2[i5 ^ (i6 & 3)]))) + iArr[i];
            iArr[i] = i2;
        }
        return iArr;
    }

    private static int[] a(byte[] bArr) {
        int[] iArr = new int[bArr.length >>> 2];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = i >>> 2;
            iArr[i2] = iArr[i2] | ((bArr[i] & 255) << ((i & 3) << 3));
        }
        return iArr;
    }

    private static byte[] a(int[] iArr) {
        int length = iArr.length << 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) ((iArr[i >>> 2] >>> ((i & 3) << 3)) & 255);
        }
        return bArr;
    }
}
