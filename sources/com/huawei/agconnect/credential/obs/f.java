package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.agconnect.common.api.Logger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes8.dex */
final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1768a = "SHA-256";
    private static final char[] b = "0123456789ABCDEF".toCharArray();

    static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            char[] cArr = b;
            sb.append(cArr[(b2 >> 4) & 15]);
            sb.append(cArr[b2 & BaseType.Obj]);
        }
        return sb.toString();
    }

    static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            throw new RuntimeException("encrypt failed");
        }
    }

    static byte[] a(String str) {
        return a(str.getBytes(Charset.defaultCharset()));
    }

    static String a(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "SHA-256";
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return b(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            Logger.e("", "", e);
            return null;
        }
    }

    f() {
    }
}
