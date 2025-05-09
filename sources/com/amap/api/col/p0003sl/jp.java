package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class jp {

    /* renamed from: a, reason: collision with root package name */
    private static int f1219a = 4;

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 0) {
            return bArr;
        }
        int length = bArr.length;
        int i = f1219a;
        int i2 = ((length / i) + 1) * i;
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        for (int i3 = length; i3 < i2; i3++) {
            bArr3[i3] = (byte) (i - (length % i));
        }
        int[] a2 = a(bArr3);
        int[] a3 = a(bArr2);
        int length2 = a2.length;
        int i4 = length2 - 1;
        if (i4 > 0) {
            if (a3.length < 4) {
                int[] iArr = new int[4];
                System.arraycopy(a3, 0, iArr, 0, a3.length);
                a3 = iArr;
            }
            int i5 = a2[i4];
            int i6 = 0;
            for (int i7 = (52 / length2) + 6; i7 > 0; i7--) {
                i6 -= 1640531527;
                int i8 = (i6 >>> 2) & 3;
                int i9 = 0;
                while (i9 < i4) {
                    int i10 = i9 + 1;
                    int i11 = a2[i10];
                    i5 = ((((i5 >>> 5) ^ (i11 << 2)) + ((i11 >>> 3) ^ (i5 << 4))) ^ ((i11 ^ i6) + (i5 ^ a3[(i9 & 3) ^ i8]))) + a2[i9];
                    a2[i9] = i5;
                    i9 = i10;
                }
                int i12 = a2[0];
                i5 = ((((i5 >>> 5) ^ (i12 << 2)) + ((i12 >>> 3) ^ (i5 << 4))) ^ ((i12 ^ i6) + (i5 ^ a3[i8 ^ (i9 & 3)]))) + a2[i4];
                a2[i4] = i5;
            }
        }
        int length3 = a2.length << 2;
        byte[] bArr4 = new byte[length3];
        for (int i13 = 0; i13 < length3; i13++) {
            bArr4[i13] = (byte) ((a2[i13 >>> 2] >>> ((i13 & 3) << 3)) & 255);
        }
        return bArr4;
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
}
