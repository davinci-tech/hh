package com.huawei.secure.android.common.encrypt.rootkey;

import com.huawei.secure.android.common.encrypt.exception.RootKeyGenException;
import com.huawei.secure.android.common.encrypt.exception.RootKeyParamException;
import com.huawei.secure.android.common.encrypt.hash.PBKDF2;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import com.huawei.secure.android.common.encrypt.utils.b;

/* loaded from: classes9.dex */
public class RootKey {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8596a = "RootKey";
    private static final int b = 16;
    private static final int c = 16;
    private static final int d = 32;

    private static int a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    private static void a(RootKeyConfig rootKeyConfig) throws RootKeyParamException {
        if (rootKeyConfig == null) {
            b.b(f8596a, "rootKeyConfig is null.");
            throw new RootKeyParamException("rootKeyConfig is null.");
        }
        if (!a(rootKeyConfig.getExportLen())) {
            b.b(f8596a, "exportLen length must be more than 128bit.");
            throw new RootKeyParamException("exportLen length must be more than 128bit.");
        }
        if (rootKeyConfig.getExportLen() < 32) {
            b.e(f8596a, "It is recommended that the exported length be greater than or equal to 256 bits.");
        }
        if (!a(rootKeyConfig.getExportLen(), rootKeyConfig.getFirst())) {
            b.b(f8596a, "material(first) length must be Greater than or equal to export length.");
            throw new RootKeyParamException("material(first) length must be Greater than or equal to export length.");
        }
        if (!a(rootKeyConfig.getExportLen(), rootKeyConfig.getSecond())) {
            b.b(f8596a, "material(second) length must be Greater than or equal to export length.");
            throw new RootKeyParamException("material(second) length must be Greater than or equal to export length.");
        }
        if (a(rootKeyConfig.getExportLen(), rootKeyConfig.getThird())) {
            return;
        }
        b.b(f8596a, "material(third) length must be Greater than or equal to export length.");
        throw new RootKeyParamException("material(third) length must be Greater than or equal to export length.");
    }

    private static boolean a(int i) {
        return i >= 16;
    }

    private static boolean b(int i) {
        return i >= 16;
    }

    public static byte[] exportRootKey(RootKeyConfig rootKeyConfig) throws RootKeyParamException, RootKeyGenException {
        byte[] pbkdfBottom;
        a(rootKeyConfig);
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(rootKeyConfig.getFirst());
        byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(rootKeyConfig.getSecond());
        byte[] hexStr2ByteArray3 = HexUtil.hexStr2ByteArray(rootKeyConfig.getThird());
        int a2 = a(hexStr2ByteArray.length, hexStr2ByteArray2.length, hexStr2ByteArray3.length);
        if (!a(a2, rootKeyConfig.getSalt())) {
            throw new RootKeyParamException("key length must be more than 128bit.");
        }
        char[] cArr = new char[a2];
        for (int i = 0; i < a2; i++) {
            cArr[i] = (char) ((hexStr2ByteArray[i] ^ hexStr2ByteArray2[i]) ^ hexStr2ByteArray3[i]);
        }
        if (rootKeyConfig.isSha256()) {
            b.c(f8596a, "exportRootKey: sha256");
            pbkdfBottom = PBKDF2.pbkdfBottom(cArr, rootKeyConfig.getSalt(), rootKeyConfig.getIteration(), rootKeyConfig.getExportLen() * 8, true);
        } else {
            b.c(f8596a, "exportRootKey: sha1");
            pbkdfBottom = PBKDF2.pbkdfBottom(cArr, rootKeyConfig.getSalt(), rootKeyConfig.getIteration(), rootKeyConfig.getExportLen() * 8, false);
        }
        if (pbkdfBottom == null || pbkdfBottom.length == 0) {
            throw new RootKeyGenException("Failed to generate the rootkey.");
        }
        return pbkdfBottom;
    }

    private static boolean a(int i, byte[] bArr) {
        return b(i) & a(bArr);
    }

    private static boolean a(byte[] bArr) {
        return bArr.length >= 16;
    }

    private static boolean a(int i, String str) {
        return str != null && str.length() >= i;
    }
}
