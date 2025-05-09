package com.huawei.operation.utils;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.GRSManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class CasLoginUtil {
    private static final String CAS_CUSTOMER_LOGIN = "/mcp/account/casLogin";
    private static final int INIT_STRING_BUILDER_MIDDLE_SIZE = 100;
    private static final String LOCAL_LOGIN_ERROR_URL_SUFFIX = "CAS/mobile/standard/wapLogin.html&themeName=red&countryCode=CN";
    private static final String TAG = "PluginOperation_CasLoginUtil";
    private static final String UP_WAP_LOGIN_PATH = "CAS/mobile/stLogin.html";

    private CasLoginUtil() {
    }

    public static String getCasLoginUrl(String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainOpenapiVmall");
        if (TextUtils.isEmpty(url)) {
            LogUtil.b(TAG, "getCasLoginUrl baseUrl is empty");
            return "";
        }
        LogUtil.c(TAG, "getCasLoginUrl baseUrl = ", url);
        if (!str.startsWith("https://")) {
            LogUtil.h(TAG, "url is error, not https url");
            return "";
        }
        return getRealCasLoginUrl(str.replace(url, ""), "portal=3", "lang=zh_CN", com.huawei.openalliance.ad.constant.Constants.THIRD_AD_INFO_CN, "version=20");
    }

    private static String getRealCasLoginUrl(String str, String str2, String str3, String str4, String str5) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(getLocalLoginUrl(str), "UTF-8");
        String encode2 = URLEncoder.encode("&" + str2 + "&" + str3 + "&" + str4 + "&" + str5, "UTF-8");
        String encode3 = URLEncoder.encode(getLocalLoginErrorUrl(), "UTF-8");
        StringBuilder sb = new StringBuilder("CAS/mobile/stLogin.html?service=");
        sb.append(encode);
        sb.append(encode2);
        sb.append("&loginChannel=26000002&reqClientType=26&loginUrl=");
        sb.append(encode3);
        String sb2 = sb.toString();
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHwidVmall");
        LogUtil.c(TAG, "getRealCasLoginUrl upUrl = ", url);
        return url + "/" + sb2;
    }

    private static String getLocalLoginUrl(String str) throws UnsupportedEncodingException {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainOpenapiVmall");
        if (TextUtils.isEmpty(url)) {
            LogUtil.b(TAG, "getLocalLoginUrl baseUrl is empty");
        }
        LogUtil.c(TAG, "getLocalLoginUrl baseUrl = ", url);
        StringBuilder sb = new StringBuilder(100);
        sb.append(url);
        sb.append("/mcp/account/casLogin");
        if (!TextUtils.isEmpty(str) && !str.startsWith("/mcp/account/casLogin")) {
            sb.append("?url=");
            sb.append(URLEncoder.encode(str, "UTF-8"));
        }
        return sb.toString();
    }

    private static String getLocalLoginErrorUrl() {
        StringBuilder sb = new StringBuilder(100);
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHwidVmall");
        LogUtil.c(TAG, "getLocalLoginErrorUrl upUrl = ", url);
        sb.append(url);
        sb.append("/CAS/mobile/standard/wapLogin.html&themeName=red&countryCode=CN");
        return sb.toString();
    }

    public static String getAutoLoginUrl() {
        return WebViewUtils.getUrl("autoLogin", BaseApplication.getContext().getPackageName() + "','" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002) + "','" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
    }
}
