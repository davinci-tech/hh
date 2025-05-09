package com.huawei.common.util;

import android.content.Context;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ContentProviderUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Locale;

/* loaded from: classes.dex */
public class VersionIsCloud {
    private static final String SELECT_COUNTRY = "select_country";
    private static final String STR_INVALIED_PARAM = "-1";
    private static final String TAG = "Login_VersionIsCloud";

    private VersionIsCloud() {
    }

    public static String checkCloudState(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "checkCloudState invalied param");
            return "-1";
        }
        if (CommonUtil.cg()) {
            LogUtil.a(TAG, "checkCloudState:isTestThirdDeviceVersion");
            return "0";
        }
        String country = context.getResources().getConfiguration().locale.getCountry();
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if (StorageIsCloud.getIf1Login(context)) {
            String str = StorageIsCloud.isChinaSite() ? "0" : "1";
            LogUtil.a(TAG, "Statelogied cloud state : ", str);
            return str;
        }
        if ("cn".equalsIgnoreCase(country) && MLAsrConstants.LAN_ZH.equalsIgnoreCase(language)) {
            LogUtil.a(TAG, "If it is china, save ", "1");
            return "1";
        }
        LogUtil.a(TAG, "If it is not china, save ", "0");
        return "0";
    }

    public static String checkLoginState(Context context) {
        LogUtil.a(TAG, "accountmigrate: isAllowedLogin() enter");
        if (StorageIsCloud.getIf1Login(context)) {
            LogUtil.a(TAG, "accountmigrate: isAllowedLogin() ifAllowLogin true");
            return "1";
        }
        return getIfAllowLoginByMcc(context);
    }

    public static String getIfAllowLoginByMcc(Context context) {
        if (FoundationCommonUtil.isSameTelephonyNetWorkAndSim(context)) {
            LogUtil.a(TAG, "isSameTelephonyNetWorkAndSim!");
            setIfNeedShowAreaAlert(context, "0");
            String i = CommonUtil.i(context);
            if (TextUtils.isEmpty(i)) {
                LogUtil.a(TAG, "mcc not in CountryCodes!");
                return setIfAllowLoginByAreaAlert(context);
            }
            SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), SELECT_COUNTRY, i, (StorageParams) null);
            SharedPreferenceUtil.updateLastCountryCode(i);
            if (LoginInit.getCloudapi().isOverseaJudgeByCountry(i)) {
                LogUtil.a(TAG, "judgeIfInAccountArea set allowedLogin!");
                HuaweiLoginManager.setIsAllowedLoginValueToDB(context, "1");
                if ("cn".equalsIgnoreCase(i)) {
                    HuaweiLoginManager.setCloudVersion("1", null);
                } else {
                    HuaweiLoginManager.setCloudVersion("0", null);
                }
                return "1";
            }
            LogUtil.a(TAG, "judgeIfInAccountArea set notAllowedLogin!");
            HuaweiLoginManager.setIsAllowedLoginValueToDB(context, "0");
            HuaweiLoginManager.setCloudVersion("0", null);
            ContentProviderUtil.getInstance(context).setCountryCode(i, null);
            return "0";
        }
        LogUtil.a(TAG, "sim and network is not same!");
        return setIfAllowLoginByAreaAlert(context);
    }

    public static String setIfAllowLoginByAreaAlert(Context context) {
        LogUtil.a(TAG, "enter needShowAreaAlert.");
        setIfNeedShowAreaAlert(context, "1");
        if (CommonUtil.u(context) || LoginInit.getCloudapi().isOverseaJudgeByCountry("") || "CN".equalsIgnoreCase(Locale.getDefault().getCountry())) {
            LogUtil.a(TAG, "needShowAreaAlert: isAllowedLogin() true");
            return "1";
        }
        LogUtil.a(TAG, "needShowAreaAlert: isAllowedLogin() false");
        return "0";
    }

    public static void setIfNeedShowAreaAlert(Context context, String str) {
        SharedPreferenceManager.e(context, Integer.toString(10015), "key_ui_if_show_area_select_alert", str, new StorageParams(0));
    }

    public static String getIfNeedShowAreaAlert(Context context) {
        return SharedPreferenceManager.b(context, Integer.toString(10015), "key_ui_if_show_area_select_alert");
    }
}
