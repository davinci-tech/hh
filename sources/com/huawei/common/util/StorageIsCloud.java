package com.huawei.common.util;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.VersionDbManager;

/* loaded from: classes.dex */
public class StorageIsCloud {
    private static final String TAG = "Login_StorageIsCloud";

    private StorageIsCloud() {
    }

    public static void storageIsCloud(Context context) {
        String e = VersionDbManager.e(BaseApplication.getContext()).e("have_cloud_or_not");
        LogUtil.a(TAG, "Health APP VersionDbManager = ", e);
        String e2 = VersionDbManager.e(BaseApplication.getContext()).e("local_country_code");
        String e3 = VersionDbManager.e(BaseApplication.getContext()).e("local_language_code");
        if (TextUtils.isEmpty(e2)) {
            e2 = BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
            VersionDbManager.e(context).a("local_country_code", e2, null);
            LogUtil.a(TAG, "get the countryCode and save ", e2);
        }
        if (TextUtils.isEmpty(e3)) {
            e3 = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
            VersionDbManager.e(context).a("local_language_code", e3, null);
            LogUtil.a(TAG, "get the languageCode and save ", e3);
        }
        if (TextUtils.isEmpty(e)) {
            if (getIf1Login(context)) {
                String str = isChinaSite() ? "0" : "1";
                LogUtil.a(TAG, "logied cloud state : ", str);
                HuaweiLoginManager.setCloudVersion(str, null);
                LogUtil.a(TAG, "If have login save ", str);
                return;
            }
            if ("cn".equalsIgnoreCase(e2) && MLAsrConstants.LAN_ZH.equalsIgnoreCase(e3)) {
                HuaweiLoginManager.setCloudVersion("1", null);
                LogUtil.a(TAG, "If it is china, save ", "1");
                return;
            } else {
                HuaweiLoginManager.setCloudVersion("0", null);
                LogUtil.a(TAG, "If it is not china, save ", "0");
                return;
            }
        }
        if (TextUtils.isEmpty(CommonUtil.w())) {
            CommonUtil.ab(e);
            LogUtil.a(TAG, "CommonUtil.setIsNoCloud = ", e);
        }
    }

    public static boolean getIf1Login(Context context) {
        boolean isLogined = LoginInit.getInstance(context).getIsLogined();
        if ("0".equals(LoginInit.getInstance(context).getAccountInfo(1011))) {
            isLogined = false;
        }
        LogUtil.a(TAG, "getIf1login is loged ", Boolean.valueOf(isLogined));
        return isLogined;
    }

    public static boolean isChinaSite() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        LogUtil.a(TAG, "site is ", Integer.valueOf(m));
        return 1 != m;
    }

    public static void isAllowedLogin(Context context) {
        LogUtil.a(TAG, "accountmigrate: isAllowedLogin() enter");
        String e = VersionDbManager.e(BaseApplication.getContext()).e("allow_login_or_not");
        LogUtil.a(TAG, "accountmigrate: ifAllowLogin = ", e);
        if (TextUtils.isEmpty(e) && getIf1Login(context)) {
            HuaweiLoginManager.setIsAllowedLoginValueToDB(context, "1");
            LogUtil.a(TAG, "accountmigrate: isAllowedLogin() ifAllowLogin true");
        }
    }
}
