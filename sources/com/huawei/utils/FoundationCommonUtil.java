package com.huawei.utils;

import android.content.Context;
import android.provider.Settings;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.knl;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;

/* loaded from: classes.dex */
public class FoundationCommonUtil {
    private static final String COLON_STR = ":";
    private static final String EMPTY_STR = "";
    private static final String EQUAL_BAR = "=";
    private static final int HUAWEI_WATCH_LATONA = 34;
    private static final int INIT_VALUE = 0;
    private static final String KEY_WHETHER_TO_AUTH = "key_wether_to_auth";
    private static final int MAC_ENERY_LENGTH = 24;
    private static final int MAC_LENGTH = 12;
    private static final String PLUS_BAR = "+";
    private static final String REPLACE_STR = "A";
    private static final String SLASH_BAR = "/";
    private static final String TAG = "FoundationCommonUtil";
    private static String sCachedAndroidId = "";

    public static boolean isSystemInfoAuthorized(Context context) {
        return Boolean.parseBoolean(KeyValDbManager.b(context).e(KEY_WHETHER_TO_AUTH)) && !SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
    }

    public static String getAndroidId(Context context) {
        if (!isSystemInfoAuthorized(context)) {
            LogUtil.c(TAG, "enter getAndroidId unauthorized: ,return empty string.");
            return "";
        }
        if (StringUtils.i(sCachedAndroidId)) {
            return sCachedAndroidId;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        sCachedAndroidId = string;
        return string;
    }

    public static String getEncryptionLogMark(String str, String str2, int i) {
        String replace;
        if (str == null) {
            LogUtil.a(TAG, "getEncryptionLogMark sn is null");
            return "";
        }
        if (CloudUtils.d()) {
            replace = knl.d(str);
            if (replace.length() >= 24) {
                replace = getNewSn(replace);
            }
        } else if (!str.contains(":") && str.length() > 12) {
            replace = knl.d(str);
            if (replace.length() >= 24) {
                replace = getNewSn(replace);
            }
        } else {
            replace = str.replace(":", "");
        }
        if (CloudUtils.d() || i < 34) {
            return replace;
        }
        if (!str.equals(str2)) {
            return knl.a(str + str2);
        }
        return knl.a(str);
    }

    private static String getNewSn(String str) {
        return str == null ? "" : str.length() >= 24 ? str.replace("+", "A").replace("/", "A").replace("=", "A").substring(0, 24) : str;
    }

    public static boolean getAuthorizationStatus(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        boolean parseBoolean = Boolean.parseBoolean(KeyValDbManager.b(context).e(KEY_WHETHER_TO_AUTH));
        if (!parseBoolean) {
            ReleaseLogUtil.a(TAG, "getAuthorizationStatus key_wether_to_auth is false");
        }
        return parseBoolean;
    }

    public static boolean isSameTelephonyNetWorkAndSim(Context context) {
        if (getAuthorizationStatus(context)) {
            return CommonUtil.ae(context);
        }
        return false;
    }

    public static String getCountryStrByTelephonyMcc(Context context) {
        return !getAuthorizationStatus(context) ? "" : CommonUtil.i(context);
    }
}
