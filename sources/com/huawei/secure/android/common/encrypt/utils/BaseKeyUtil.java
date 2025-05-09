package com.huawei.secure.android.common.encrypt.utils;

import com.huawei.secure.android.common.encrypt.hash.PBKDF2;

/* loaded from: classes6.dex */
public class BaseKeyUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8600a = "BaseKeyUtil";
    private static final int b = 16;
    private static final int c = 16;
    private static final int d = 10000;
    private static final int e = 32;
    private static final int f = 1;

    private static int a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    private static boolean a(int i) {
        return i >= 16;
    }

    private static boolean a(int i, byte[] bArr) {
        return b(i) & a(bArr);
    }

    private static boolean b(int i) {
        return i >= 16;
    }

    public static String exportHexRootKey(String str, String str2, String str3, byte[] bArr, int i, boolean z) {
        return HexUtil.byteArray2HexStr(exportRootKey(str, str2, str3, bArr, i, z));
    }

    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 16, z);
    }

    public static byte[] exportRootKey32(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 32, z);
    }

    public static byte[] exportRootKey32Iteration1(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 1, 32, z);
    }

    public static byte[] exportRootKeyIteration1(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 1, 16, z);
    }

    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, int i, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 10000, i, z);
    }

    private static boolean a(byte[] bArr) {
        return bArr.length >= 16;
    }

    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, int i, int i2, boolean z) {
        if (!a(i2)) {
            b.b(f8600a, "exportLen length must be more than 128bit.");
            return new byte[0];
        }
        if (!a(i2, str)) {
            b.b(f8600a, "material(first) length must be Greater than or equal to export length.");
            return new byte[0];
        }
        if (!a(i2, str2)) {
            b.b(f8600a, "material(second) length must be Greater than or equal to export length.");
            return new byte[0];
        }
        if (!a(i2, str3)) {
            b.b(f8600a, "material(third) length must be Greater than or equal to export length.");
            return new byte[0];
        }
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str2);
        byte[] hexStr2ByteArray3 = HexUtil.hexStr2ByteArray(str3);
        int a2 = a(hexStr2ByteArray.length, hexStr2ByteArray2.length, hexStr2ByteArray3.length);
        if (a(a2, bArr)) {
            char[] cArr = new char[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                cArr[i3] = (char) ((hexStr2ByteArray[i3] ^ hexStr2ByteArray2[i3]) ^ hexStr2ByteArray3[i3]);
            }
            if (!z) {
                b.c(f8600a, "exportRootKey: sha1");
                return PBKDF2.pbkdfBottom(cArr, bArr, i, i2 * 8, false);
            }
            b.c(f8600a, "exportRootKey: sha256");
            return PBKDF2.pbkdfBottom(cArr, bArr, i, i2 * 8, true);
        }
        throw new IllegalArgumentException("key length must be more than 128bit.");
    }

    private static boolean a(int i, String str) {
        return str != null && str.length() >= i;
    }

    public static byte[] exportRootKey(String str, String str2, String str3, String str4, int i, boolean z) {
        return exportRootKey(str, str2, str3, HexUtil.hexStr2ByteArray(str4), i, z);
    }
}
