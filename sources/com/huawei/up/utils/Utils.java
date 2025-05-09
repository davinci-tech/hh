package com.huawei.up.utils;

import android.os.Build;
import com.huawei.common.util.EncryptUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kno;
import health.compact.a.HEXUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

/* loaded from: classes8.dex */
public class Utils {
    private static final int NUMBER_LENGTH = 16;
    private static final String TAG = "Utils";

    private Utils() {
    }

    public static String encrypter4Srv(String str) throws GeneralSecurityException, InvalidKeyException, UnsupportedEncodingException {
        String upServiceToken = EncryptUtil.getUpServiceToken();
        String ivStr = getIvStr();
        return ivStr + ":" + HEXUtils.a(kno.b(str.getBytes("UTF-8"), upServiceToken.getBytes("UTF-8"), ivStr));
    }

    public static String getTerminalType() {
        String str = Build.MODEL;
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("Utils", "exception e=", e.getMessage());
            return str;
        }
    }

    private static String getIvStr() {
        return HEXUtils.a(new SecureRandom().generateSeed(16));
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() < 1;
    }
}
