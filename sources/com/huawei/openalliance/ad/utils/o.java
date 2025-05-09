package com.huawei.openalliance.ad.utils;

import okio.Utf8;

/* loaded from: classes5.dex */
public abstract class o {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f7714a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] b = new byte[256];

    public static String a(byte[] bArr, int i) {
        boolean z;
        char[] cArr = new char[((i + 2) / 3) * 4];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = (bArr[i2] & 255) << 8;
            int i5 = i2 + 1;
            boolean z2 = true;
            if (i5 < i) {
                i4 |= bArr[i5] & 255;
                z = true;
            } else {
                z = false;
            }
            int i6 = i4 << 8;
            int i7 = i2 + 2;
            if (i7 < i) {
                i6 |= bArr[i7] & 255;
            } else {
                z2 = false;
            }
            char[] cArr2 = f7714a;
            int i8 = 64;
            cArr[i3 + 3] = cArr2[z2 ? i6 & 63 : 64];
            int i9 = i6 >> 6;
            if (z) {
                i8 = i9 & 63;
            }
            cArr[i3 + 2] = cArr2[i8];
            cArr[i3 + 1] = cArr2[(i6 >> 12) & 63];
            cArr[i3] = cArr2[(i6 >> 18) & 63];
            i2 += 3;
            i3 += 4;
        }
        return new String(cArr);
    }

    public static String a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    static {
        for (int i = 0; i < 256; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) (i3 - 71);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) (i4 + 4);
        }
        byte[] bArr = b;
        bArr[43] = 62;
        bArr[45] = Utf8.REPLACEMENT_BYTE;
    }
}
