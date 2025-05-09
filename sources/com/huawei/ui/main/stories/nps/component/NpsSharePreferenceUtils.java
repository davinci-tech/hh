package com.huawei.ui.main.stories.nps.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.nps.npsstate.NativeConfigBean;
import com.huawei.ui.main.stories.nps.npsstate.NativeConfigMap;

/* loaded from: classes7.dex */
public class NpsSharePreferenceUtils {
    private static final String IS_NEW_BECOME_OLD = "new_become_old";
    private static final String NPS_CONFIG = "nps_config";
    private static final String TAG = "NpsSharePreferenceUtils";

    private NpsSharePreferenceUtils() {
    }

    public static String getNewUserNativeConfigStr(Context context, String str) {
        return getSharedConfig(context.getSharedPreferences(NPS_CONFIG, 0), str);
    }

    public static void setNewUserNativeConfig(Context context, String str, String str2) {
        context.getSharedPreferences(NPS_CONFIG, 0).edit().putString(str, str2).apply();
    }

    public static NativeConfigBean getOldUserNativeConfig(Context context, String str) {
        String sharedConfig = getSharedConfig(context.getSharedPreferences(NPS_CONFIG, 0), str);
        try {
            if (!TextUtils.isEmpty(sharedConfig)) {
                return (NativeConfigBean) HiJsonUtil.e(sharedConfig, NativeConfigBean.class);
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "JsonSyntaxException, e is ", e.getMessage());
        }
        return new NativeConfigBean();
    }

    public static void setOldUserNativeConfig(Context context, String str, String str2) {
        context.getSharedPreferences(NPS_CONFIG, 0).edit().putString(str, str2).apply();
    }

    public static String getUserType(Context context, String str) {
        return context.getSharedPreferences(NPS_CONFIG, 0).getString(str, "");
    }

    public static void setIsNewBecomeOld(Context context, String str, boolean z) {
        context.getSharedPreferences(NPS_CONFIG, 0).edit().putBoolean(IS_NEW_BECOME_OLD + str, z).apply();
    }

    public static boolean getIsNewBecomeOld(Context context, String str) {
        return context.getSharedPreferences(NPS_CONFIG, 0).getBoolean(IS_NEW_BECOME_OLD + str, false);
    }

    public static NativeConfigMap getNewUserNativeConfig(Context context, String str) {
        String sharedConfig = getSharedConfig(context.getSharedPreferences(NPS_CONFIG, 0), str);
        try {
            if (!TextUtils.isEmpty(sharedConfig)) {
                return (NativeConfigMap) HiJsonUtil.e(sharedConfig, NativeConfigMap.class);
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "JsonSyntaxException, e is ", e.getMessage());
        }
        return new NativeConfigMap();
    }

    private static String getSharedConfig(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getString(str, "");
        } catch (ClassCastException unused) {
            LogUtil.b(TAG, "getSharedConfig fetchNativeConfig data ClassCastException");
            return "";
        }
    }
}
