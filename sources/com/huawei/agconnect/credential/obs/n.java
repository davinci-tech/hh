package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.common.api.RandomWrapper;
import com.huawei.secure.android.common.util.HexUtil;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class n {

    /* renamed from: a, reason: collision with root package name */
    private static final int f1777a = 16;

    public static SecretKey a(String str, String str2, String str3, int i) {
        return new SecretKeySpec(SecretKeyFactory.getInstance(str3).generateSecret(new PBEKeySpec(str.toCharArray(), HexUtil.hexStr2ByteArray(str2), i, 128)).getEncoded(), "AES");
    }

    public static String a(String str, String str2) {
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str2);
        if (hexStr2ByteArray == null || hexStr2ByteArray2 == null || hexStr2ByteArray.length != hexStr2ByteArray2.length) {
            return null;
        }
        int length = hexStr2ByteArray.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (hexStr2ByteArray[i] ^ hexStr2ByteArray2[i]);
        }
        return HexUtil.byteArray2HexStr(bArr);
    }

    public static String a(String str, int i) {
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        if (hexStr2ByteArray == null || hexStr2ByteArray.length == 0) {
            return null;
        }
        for (int i2 = 0; i2 < hexStr2ByteArray.length; i2++) {
            if (i < 0) {
                hexStr2ByteArray[i2] = (byte) (hexStr2ByteArray[i2] << (-i));
            } else {
                hexStr2ByteArray[i2] = (byte) (hexStr2ByteArray[i2] >> i);
            }
        }
        return HexUtil.byteArray2HexStr(hexStr2ByteArray);
    }

    static String a(int i) {
        return HexUtil.byteArray2HexStr(RandomWrapper.generateSecureRandom(i / 2));
    }
}
