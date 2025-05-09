package com.huawei.operation.utils;

import android.content.res.Configuration;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.util.Locale;

/* loaded from: classes5.dex */
public class HelpCustomerOperate {
    private static final String DOMESTIC_HELP_URL_FORMAT = "/hwtips/help/health_help_all/%s/index.html%s";
    private static final String EUROPE_HELP_URL_FORMAT = "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=health_help_all";
    private static final String TAG = "HelpCustomerOperate";

    private HelpCustomerOperate() {
    }

    public static String getHelpCustomerUrl(String str, String str2) {
        String format;
        LogUtil.d(TAG, "getHelpCustomerUrl");
        if (CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) == 1) {
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = getDomesticLanguageTag();
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            format = String.format(locale, DOMESTIC_HELP_URL_FORMAT, objArr);
        } else {
            format = String.format(Locale.ENGLISH, EUROPE_HELP_URL_FORMAT, getCurrentLanguageTag());
        }
        String str3 = str + format;
        LogUtil.b(TAG, "getHelpCustomerUrl: url -> ", str3);
        return str3;
    }

    private static String getDomesticLanguageTag() {
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        String str = ("ZH".equalsIgnoreCase(language) || "BO".equalsIgnoreCase(language) || "UG".equalsIgnoreCase(language)) ? "zh-CN" : "en-US";
        LogUtil.b(TAG, "getDomesticLanguageTag: languageTag -> ", str);
        return str;
    }

    private static String getCurrentLanguageTag() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String str = configuration.locale.getLanguage() + com.huawei.openalliance.ad.constant.Constants.LINK + configuration.locale.getCountry();
        LogUtil.b(TAG, "getLanguageTag: currentLanguageTag -> ", str);
        return str;
    }
}
