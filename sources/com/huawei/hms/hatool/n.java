package com.huawei.hms.hatool;

import android.util.Pair;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public class n {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f4605a = Charset.forName("UTF-8");

    public static String b(String str, String str2) {
        return HexUtil.byteArray2HexStr(AesCbc.encrypt(str.getBytes(f4605a), HexUtil.hexStr2ByteArray(str2)));
    }

    public static String a(byte[] bArr, String str) {
        String str2;
        if (bArr == null || bArr.length == 0 || str == null) {
            str2 = "cbc encrypt(byte) param is not right";
        } else {
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
            if (hexStr2ByteArray.length >= 16) {
                return HexUtil.byteArray2HexStr(AesGcm.encrypt(bArr, hexStr2ByteArray));
            }
            str2 = "key length is not right";
        }
        v.b("AesCipher", str2);
        return "";
    }

    public static String a(String str, String str2) {
        Pair<byte[], String> a2 = a(str, 32);
        return new String(AesCbc.decrypt(HexUtil.hexStr2ByteArray((String) a2.second), HexUtil.hexStr2ByteArray(str2), (byte[]) a2.first), f4605a);
    }

    public static Pair<byte[], String> a(String str, int i) {
        if (str == null || str.length() < i) {
            return new Pair<>(new byte[0], str);
        }
        String substring = str.substring(0, i);
        return new Pair<>(HexUtil.hexStr2ByteArray(substring), str.substring(i));
    }
}
