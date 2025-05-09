package com.huawei.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hms.framework.network.grs.GrsApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Locale;

/* loaded from: classes.dex */
public class Utils {
    private static final int HWID_KEY_VERSION_CODE_254302 = 20504302;
    private static final String SERVICE_SUFFIX;
    private static final String TAG = "PLGLOGIN_Utils";

    static {
        SERVICE_SUFFIX = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : "";
    }

    private Utils() {
    }

    public static String getUrl(String str, String str2) {
        GrsClient initGrs = initGrs(getCountryCode(), BaseApplication.getContext());
        if (initGrs == null) {
            LogUtil.h(TAG, "get SyncUrl mContext is null");
            return "";
        }
        return initGrs.synGetGrsUrl(str + SERVICE_SUFFIX, str2);
    }

    public static GrsClient initGrs(String str, Context context) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setAppName("healthcloud" + SERVICE_SUFFIX);
        grsBaseInfo.setCountrySource(str);
        grsBaseInfo.setSerCountry(str);
        grsBaseInfo.setRegCountry(str);
        grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
        try {
            return new GrsClient(context, grsBaseInfo);
        } catch (NullPointerException unused) {
            LogUtil.b(TAG, "context or grsBaseInfo is null");
            return null;
        }
    }

    public static void initGrsOld(String str, Context context) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setAppName("healthcloud" + SERVICE_SUFFIX);
        grsBaseInfo.setCountrySource(str);
        grsBaseInfo.setSerCountry(str);
        grsBaseInfo.setRegCountry(str);
        grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
        GrsApi.grsSdkInit(context, grsBaseInfo);
    }

    public static String getCountryCode() {
        Context context = BaseApplication.getContext();
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        if (!TextUtils.isEmpty(accountInfo)) {
            LogUtil.a(TAG, "getCommonCountryCode from account");
            return accountInfo;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        LogUtil.a(TAG, "getCommonCountryCode from service area");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String f = CommonUtil.f(context);
        if (!TextUtils.isEmpty(f)) {
            LogUtil.a(TAG, "getCommonCountryCode from MCC");
            return f;
        }
        String i = CommonUtil.i(context);
        if (!TextUtils.isEmpty(i)) {
            LogUtil.a(TAG, "getCommonCountryCode from SIM");
            return i;
        }
        return Locale.getDefault().getCountry();
    }

    public static String getCountryBeforeLogin() {
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        if (TextUtils.isEmpty(b)) {
            b = CommonUtil.f(context);
        }
        if (TextUtils.isEmpty(b)) {
            b = CommonUtil.i(context);
        }
        return TextUtils.isEmpty(b) ? Locale.getDefault().getCountry() : b;
    }

    public static String getHmsVersionName() {
        String hMSPackageName = HMSPackageManager.getInstance(BaseApplication.getContext()).getHMSPackageName();
        String str = "";
        if (TextUtils.isEmpty(hMSPackageName)) {
            LogUtil.a(TAG, "not install hms");
            return "";
        }
        try {
            str = BaseApplication.getContext().getPackageManager().getPackageInfo(hMSPackageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b(TAG, "getHmsVersionName NameNotFoundException error :", e.getMessage());
        }
        LogUtil.a(TAG, "getHmsVersionName versionName= ", str);
        return str;
    }

    public static int getHwidVersionCode(Context context) {
        String hMSPackageName = HMSPackageManager.getInstance(context).getHMSPackageName();
        if (TextUtils.isEmpty(hMSPackageName)) {
            return 0;
        }
        try {
            int i = context.getPackageManager().getPackageInfo(hMSPackageName, 0).versionCode;
            LogUtil.a(TAG, "getHwIDVersionCode versionCode ", Integer.valueOf(i));
            return i;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b(TAG, "getHwIDVersionCode NameNotFoundException error :", e.getMessage());
            return 0;
        }
    }

    public static boolean isSupportAuthAppList() {
        return HWID_KEY_VERSION_CODE_254302 <= getHwidVersionCode(BaseApplication.getContext());
    }
}
