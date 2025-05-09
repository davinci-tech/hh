package com.alipay.apmobilesecuritysdk.otherid;

import android.content.Context;
import defpackage.ii;
import defpackage.mq;

/* loaded from: classes7.dex */
public class UmidSdkWrapper {
    public static final String UMIDTOKEN_FILE_NAME = "xxxwww_v2";
    public static final String UMIDTOKEN_KEY_NAME = "umidtk";
    public static volatile String cachedUmidToken = "";
    public static volatile boolean initUmidFinished = false;

    public static void updateLocalUmidToken(Context context, String str) {
        synchronized (UmidSdkWrapper.class) {
            if (mq.b(str)) {
                ii.d(context, UMIDTOKEN_FILE_NAME, UMIDTOKEN_KEY_NAME, str);
                cachedUmidToken = str;
            }
        }
    }

    public static String startUmidTaskSync(Context context, int i) {
        return "";
    }

    public static String getSecurityToken(Context context) {
        String str;
        synchronized (UmidSdkWrapper.class) {
            str = cachedUmidToken;
        }
        return str;
    }

    public static String compatUmidBug(Context context, String str) {
        if (!mq.e(str) && !mq.d(str, "000000000000000000000000")) {
            return str;
        }
        String utdid = UtdidWrapper.getUtdid(context);
        if (utdid != null && utdid.contains("?")) {
            utdid = "";
        }
        return mq.e(utdid) ? "" : utdid;
    }
}
