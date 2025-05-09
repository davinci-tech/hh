package com.huawei.ads.adsrec;

import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes2.dex */
public abstract class w0 {
    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            HiAdLog.e("Sha256Util", "sha256 NoSuchAlgorithmException");
            return new byte[0];
        }
    }

    public static String a(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        try {
            return b0.a(a(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            HiAdLog.e("Sha256Util", "get hex sha256 error");
            return "";
        }
    }
}
