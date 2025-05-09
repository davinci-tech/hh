package com.huawei.health.h5pro.utils;

import android.content.Context;

/* loaded from: classes3.dex */
public class LanguageUtil {
    public static boolean isRtlLanguage(Context context) {
        if (context == null) {
            LogUtil.w("H5PRO_LanguageUtil", "isRTLLanguage() context is null");
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if ("ar".equalsIgnoreCase(language) || "iw".equalsIgnoreCase(language) || "fa".equalsIgnoreCase(language) || com.huawei.openalliance.ad.constant.Constants.URDU_LANG.equalsIgnoreCase(language)) {
            return true;
        }
        return "ug".equalsIgnoreCase(language);
    }
}
