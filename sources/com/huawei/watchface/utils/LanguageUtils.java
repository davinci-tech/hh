package com.huawei.watchface.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.environment.Environment;
import java.util.Locale;

/* loaded from: classes7.dex */
public final class LanguageUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11194a = "LanguageUtils";

    private LanguageUtils() {
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static boolean b() {
        return MLAsrConstants.LAN_ZH.equals(a());
    }

    public static boolean isNeedMirror() {
        String a2 = a();
        return "ar".equals(a2) || "fa".equals(a2) || "iw".equals(a2) || "ug".equals(a2) || Constants.URDU_LANG.equals(a2);
    }

    public static boolean c() {
        return "ar".equals(a());
    }

    public static String a(boolean z) {
        String str;
        Configuration configuration = Environment.getApplicationContext().getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        if ("my".equals(language) && "Qaag".equals(Locale.getDefault().getScript())) {
            country = "ZG";
        }
        if ("en".equals(language) && "Qaag".equals(Locale.getDefault().getScript())) {
            country = "GB";
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer(language);
            stringBuffer.append("_");
            stringBuffer.append(country);
            return stringBuffer.toString();
        }
        String script = configuration.locale.getScript();
        if (!TextUtils.isEmpty(script)) {
            if ("Hans".equalsIgnoreCase(script)) {
                str = "CN";
            } else if ("Hant".equalsIgnoreCase(script)) {
                str = "TW";
            } else {
                HwLog.w(f11194a, "script default");
            }
            country = str;
            language = MLAsrConstants.LAN_ZH;
        }
        StringBuffer stringBuffer2 = new StringBuffer(language);
        stringBuffer2.append("_");
        stringBuffer2.append(country);
        return stringBuffer2.toString();
    }

    public static String d() {
        return Environment.getApplicationContext().getResources().getConfiguration().locale.getScript();
    }

    public static boolean a(Context context) {
        if (context != null) {
            return "ar".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) || "iw".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) || "fa".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) || Constants.URDU_LANG.equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) || "ug".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
        }
        HwLog.w(f11194a, "isRTLLanguage() context is null");
        return false;
    }
}
