package com.huawei.operation.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.adapter.SendCurrentUrlCallback;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.h5pro.H5MiniProgramSecurityHelper;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.tencent.connect.common.Constants;
import defpackage.fdu;
import defpackage.jei;
import defpackage.koq;
import defpackage.moh;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class OperationUtils {
    private static final String AI_MARKETING_TWO_COUNTRIES_LOCAL_LIST = "marketing/ai_marketing_two_countries_local_list.json";
    private static final String AI_MARKETING_TWO_MODULE = "ai-marketing-two-001";
    private static final String AI_VMALL_MODULE = "ai-vmall-001";
    private static final String ALL_VERSION = "0";
    private static final int APP_SECRET_TYPE = 1;
    private static final int BYTE_LENGTH = 1024;
    private static final String COUNTRY_LIST_COLON_REGEX = ":";
    private static final String COUNTRY_LIST_REGEX = ",";
    private static final int INTERFACE_VERSION = 2;
    private static final String JSON_KEY_ABROAD_COUNTRY_LIST = "abroad_oversea_list";
    private static final String NOT_RELEASE_VERSION = "1";
    private static final String PARAMS = "params";
    private static final int REQUEST_CODE = 2001;
    private static final String SP_NAME = "30004";
    private static final int SUCCESS_RESULT_CODE = 0;
    private static final String TAG = "PluginOperation_OperationUtils";
    private static final int TIME_MILLIS_UNIT = 1000;
    private static final String UTF_8_ENCODE = "UTF-8";
    private String mHuaweiHost;
    private WhiteBoxManager mWhiteBoxUtil;

    public static int getInterfaceVersion() {
        return 2;
    }

    private OperationUtils() {
        this.mWhiteBoxUtil = WhiteBoxManager.d();
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainWwwHuawei");
        this.mHuaweiHost = url;
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "OperationUtils mHuaweiHost is empty");
        }
        LogUtil.c(TAG, "OperationUtils mHuaweiHost = ", this.mHuaweiHost);
    }

    public static OperationUtils getInstance() {
        return Instance.INSTANCE;
    }

    /* loaded from: classes9.dex */
    static class Instance {
        public static final OperationUtils INSTANCE = new OperationUtils();

        private Instance() {
        }
    }

    public static String getAppId(Context context) {
        return context != null ? LoginInit.getInstance(context).isLoginedByWear() ? "com.huawei.bone" : context.getPackageName() : "";
    }

    public static String getSysVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getTokenType() {
        return String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue());
    }

    public static long getUtc() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return calendar.getTimeInMillis();
    }

    public static void share(Context context, fdu fduVar, boolean z) {
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        fduVar.c(z);
        socialShareCenterApi.exeShare(fduVar, context);
    }

    public static final <T> T fromJson(String str, TypeToken<T> typeToken) {
        try {
            return (T) new Gson().fromJson(str, typeToken.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.a(TAG, "Gson to List error");
            return null;
        }
    }

    public static TitleBean getTitleBean(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getTitleBean: url is empty");
            return null;
        }
        TitleBean titleBean = new TitleBean();
        titleBean.configSetUrl(str);
        titleBean.configSetLeftBtn(str2);
        titleBean.configSetRightBtn(str3);
        titleBean.configSetShoppingCartUrl("");
        titleBean.configSetFeatureUrl("");
        titleBean.configSetOrderManagerUrl("");
        return titleBean;
    }

    private String getScopeByType(int i) {
        switch (i) {
            case 3:
                return this.mHuaweiHost + UriConstants.READ_SLEEP_DATA;
            case 4:
                return this.mHuaweiHost + UriConstants.READ_BLOOD_SUGAR;
            case 5:
                return this.mHuaweiHost + UriConstants.READ_BLOOD_PRESSURE;
            case 6:
            default:
                return "";
            case 7:
                return this.mHuaweiHost + UriConstants.READ_HEART_RATE;
            case 8:
                return this.mHuaweiHost + UriConstants.READ_WEIGHT;
            case 9:
                return this.mHuaweiHost + UriConstants.READ_SLEEP_DATA;
            case 10:
                return this.mHuaweiHost + UriConstants.READ_PRESSURE;
        }
    }

    public static long getUtcTime() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }

    private HashMap<String, String> initParamsRequest(Context context, String str) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (LoginInit.getInstance(context).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(context)));
        hashMap.put("upDeviceType", LoginInit.getInstance(context).getDeviceType());
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b(TAG, "token encode Exception ", e.toString());
            }
        }
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", "1");
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("wearType", "");
        hashMap.put("ts", String.valueOf(getUtcTime()));
        hashMap.put("language", "language");
        hashMap.put("accessToken", str);
        return hashMap;
    }

    private boolean isHuidValid(Context context, String str) {
        if (context == null) {
            return false;
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        HashMap hashMap = new HashMap(16);
        hashMap.put("x-huid", accountInfo);
        hashMap.put("x-version", CommonUtil.c(context));
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthcommonHicloud");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "isHuidValid urlAuthInfoDomain is empty");
            return false;
        }
        String b = jei.b(url + Constants.URL_AUTH_TOKEN, initParamsRequest(context, str), hashMap);
        if (b == null) {
            LogUtil.a(TAG, "The result is null! ");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(b);
            int i = jSONObject.getInt("resultCode");
            if (i != 0) {
                LogUtil.a(TAG, "Error! The result resCode is ", Integer.valueOf(i));
                return false;
            }
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.RESPONSE_AUTH_INFO);
                if (!jSONObject2.isNull("uid") && jSONObject2.getString("uid").equals(accountInfo)) {
                    return true;
                }
                LogUtil.a(TAG, "Error! The uid is illegal!");
                return false;
            } catch (JSONException e) {
                LogUtil.a(TAG, "parse param json fail! AuthInfo exception is ", e.getMessage());
                return false;
            }
        } catch (JSONException e2) {
            LogUtil.a(TAG, "parse param json fail! e is ", e2.getMessage());
            return false;
        }
    }

    private HashMap<String, String> initParam(String str) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(Constants.JumpUrlConstants.URL_KEY_OPENID, "OPENID");
        hashMap.put(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN, str);
        return hashMap;
    }

    private HashMap<String, String> initHeader(String str) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("Content-Type", "text/xml");
        hashMap.put("Host", str);
        hashMap.put("Connection", "Keep-Alive");
        hashMap.put("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
        return hashMap;
    }

    public boolean doAccessTokenCheck(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(TAG, "(accessTokenParam is null) || (scope is null)");
            return false;
        }
        try {
            String encode = URLEncoder.encode(str.trim(), "UTF-8");
            if (!isHuidValid(context, encode)) {
                LogUtil.a(TAG, "The token does not suit!");
                return false;
            }
            String tokenKeyUrlSp = WebViewUtils.getTokenKeyUrlSp(context);
            String b = jei.b(tokenKeyUrlSp + "/rest.php?nsp_ts=" + (System.currentTimeMillis() / 1000) + "&nsp_svc=huawei.oauth2.user.getTokenInfo", initParam(encode), initHeader(Utils.getHostByJdk(tokenKeyUrlSp)));
            LogUtil.c(TAG, "doAccessTokenCheck after postRequest");
            if (b == null) {
                return false;
            }
            try {
                String string = new JSONObject(b).getString("scope");
                LogUtil.c(TAG, "cloudScope = ", string);
                if (string == null) {
                    LogUtil.a(TAG, "cloudScope is null");
                    return false;
                }
                String[] split = string.split(" ");
                LogUtil.a(TAG, "scopes.length = ", Integer.valueOf(split.length));
                for (String str3 : split) {
                    if (str3.equals(str2)) {
                        return true;
                    }
                }
                LogUtil.a(TAG, "scope is not exit cloudScope!");
                return false;
            } catch (JSONException e) {
                LogUtil.b(TAG, "parse param json fail! exception is ", e.getMessage());
                return false;
            }
        } catch (UnsupportedEncodingException e2) {
            LogUtil.b(TAG, "doHttpPost exception is ", e2.getMessage());
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0073, code lost:
    
        if (r10.startsWith(r5 + "/") != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x009e, code lost:
    
        if (r10.startsWith(r4 + "/") != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isMiaoUrl(com.huawei.operation.adapter.SendCurrentUrlCallback r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L4
            return r0
        L4:
            java.lang.String r10 = r10.getCurrentUrl()
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r2 = "PluginOperation_OperationUtils"
            if (r1 == 0) goto L1a
            java.lang.String r10 = "isMiaoUrl url is : empty"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r10)
            return r0
        L1a:
            jah r1 = defpackage.jah.c()
            java.lang.String r3 = "domain_mlhwm_miaohealth_net"
            java.lang.String r1 = r1.e(r3)
            jah r3 = defpackage.jah.c()
            java.lang.String r4 = "domain_mlhwmtest_miaohealth_net"
            java.lang.String r3 = r3.e(r4)
            jah r4 = defpackage.jah.c()
            java.lang.String r5 = "domain_pay_miaohealth_net"
            java.lang.String r4 = r4.e(r5)
            jah r5 = defpackage.jah.c()
            java.lang.String r6 = "domain_paytest_miaohealth_net"
            java.lang.String r5 = r5.e(r6)
            boolean r6 = health.compact.a.CommonUtil.cc()
            r7 = 1
            java.lang.String r8 = "/"
            if (r6 == 0) goto L76
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            boolean r1 = r10.startsWith(r1)
            if (r1 != 0) goto L75
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            boolean r10 = r10.startsWith(r1)
            if (r10 == 0) goto La1
        L75:
            return r7
        L76:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            r3.append(r8)
            java.lang.String r1 = r3.toString()
            boolean r1 = r10.startsWith(r1)
            if (r1 != 0) goto Lab
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            boolean r10 = r10.startsWith(r1)
            if (r10 == 0) goto La1
            goto Lab
        La1:
            java.lang.String r10 = "isMiaoUrl url is : false"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r10)
            return r0
        Lab:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.utils.OperationUtils.isMiaoUrl(com.huawei.operation.adapter.SendCurrentUrlCallback):boolean");
    }

    public Map<String, Object> jsonToMap(String str) {
        HashMap hashMap = new HashMap(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                if (string == null) {
                    hashMap.put(next, "");
                } else {
                    hashMap.put(next, string);
                }
            }
        } catch (JSONException e) {
            LogUtil.b(TAG, e.getMessage());
        }
        LogUtil.c(TAG, "jsonToMap");
        return hashMap;
    }

    public boolean checkFunctionIsLegal(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        int i;
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.getString("accessToken");
            i = jSONObject.getInt("type");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getHealthData JSONException param fail exception = ", e.getMessage());
            i = 0;
        }
        return checkUrlIsLegalNewForWeb(context, sendCurrentUrlCallback, pluginOperationAdapter, str2, getScopeByType(i));
    }

    public boolean checkUrlIsLegalNewForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (!Utils.needAuth(context)) {
            LogUtil.a(TAG, "checkUrlIsLegalNew do not need auth version!");
            return true;
        }
        if (sendCurrentUrlCallback == null || pluginOperationAdapter == null) {
            return false;
        }
        if (pluginOperationAdapter.checkWhiteUrl(sendCurrentUrlCallback.getWebViewUrl())) {
            LogUtil.a(TAG, "checkUrlIsLegalNew is White url");
            return true;
        }
        if (H5MiniProgramSecurityHelper.isSuperTrustedMiniProgram(sendCurrentUrlCallback.getWebViewUrl())) {
            LogUtil.a(TAG, "checkUrlIsLegalNew is isSuperTrustedMiniProgram");
            return true;
        }
        return getInstance().doAccessTokenCheck(context, str, str2);
    }

    public boolean checkUrlIsLegalForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        String host;
        if (!Utils.needAuth(context)) {
            LogUtil.a(TAG, "checkUrlIsLegal do not need auth version!");
            return true;
        }
        if (sendCurrentUrlCallback == null || pluginOperationAdapter == null) {
            return false;
        }
        String webViewUrl = sendCurrentUrlCallback.getWebViewUrl();
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (webViewUrl.startsWith("https://") || webViewUrl.startsWith("http://")) {
            LogUtil.a(TAG, "url.startsWith(\"https://\") || url.startsWith(\"http://\")");
            host = Uri.parse(webViewUrl).getHost();
        } else {
            host = null;
        }
        if (PluginOperation.getInstance(context).getActivityFlag()) {
            LogUtil.a(TAG, "checkUrlIsLegal WebViewActivity.getActiveFlag() = true");
            if (str != null && str.equals(host)) {
                LogUtil.a(TAG, "(mUrlLoginHost != null) && (mUrlCheckHost != null)");
                webViewUrl = Constants.HEALTH_LOGIN + webViewUrl;
            }
        } else {
            LogUtil.a(TAG, "checkUrlIsLegal WebViewActivity.getActiveFlag() = false");
        }
        return pluginOperationAdapter.checkCurrentUrlAuth(webViewUrl, accountInfo);
    }

    public boolean checkPersonalPrivacySettingsUrlIsLegal(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        String str2;
        LogUtil.a(TAG, "checkPersonalPrivacySettingsUrlIsLegal");
        try {
            str2 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "checkIsLegal JSONException param fail exception = ", e.getMessage());
            str2 = "";
        }
        return checkUrlIsLegalNewForWeb(context, sendCurrentUrlCallback, pluginOperationAdapter, str2, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
    }

    public String parseJsonFromShop(String str, String str2) {
        String str3;
        String str4;
        if (Constants.SHOP_TYPE_MIAO.equals(str2)) {
            str3 = "mpqlf0xjydgwtp9es7";
            StringBuilder sb = new StringBuilder(16);
            sb.append(this.mWhiteBoxUtil.d(1, 1) + this.mWhiteBoxUtil.d(1, 1001) + this.mWhiteBoxUtil.d(1, 2001));
            try {
                str4 = new String(this.mWhiteBoxUtil.a(Base64.decode(sb.toString(), 0)), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LogUtil.b(TAG, e.getMessage());
                str4 = "";
            }
        } else {
            str3 = "";
            str4 = str3;
        }
        StringBuilder sb2 = new StringBuilder(16);
        TreeMap treeMap = new TreeMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                if (string == null) {
                    treeMap.put(next, "");
                } else {
                    treeMap.put(next, string);
                }
            }
        } catch (JSONException e2) {
            LogUtil.b(TAG, e2.getMessage());
        }
        treeMap.put("open_appid", str3);
        for (Map.Entry entry : treeMap.entrySet()) {
            sb2.append((String) entry.getKey());
            sb2.append("=");
            sb2.append((String) entry.getValue());
            sb2.append("&");
        }
        String str5 = sb2.substring(0, sb2.length() - 1) + str4;
        LogUtil.c(TAG, str5);
        return str5;
    }

    public static String getShopHomePageUrl() {
        String str = "";
        try {
            str = getVmallRules().getString("homePage");
            LogUtil.c(TAG, "homePageUrl: ", str);
            return str;
        } catch (JSONException unused) {
            LogUtil.h(TAG, "getShopCenterUrl JSONException.");
            return str;
        }
    }

    public static boolean isVmallWrapSwitch() {
        try {
            boolean z = getVmallRules().getBoolean("enableVmallWrap");
            LogUtil.c(TAG, "getVmallWrapSwitch isVmallSwitchOpen:", Boolean.valueOf(z));
            return z;
        } catch (JSONException unused) {
            LogUtil.h(TAG, "getShopCenterUrl JSONException.");
            return false;
        }
    }

    public static String newPathConcat(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "newPathConcat oldUrl is empty");
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        LogUtil.c(TAG, "newPathConcat newUrl is ", sb.toString());
        return sb.toString();
    }

    public static JSONObject getVmallRules() {
        String b = SharedPreferenceManager.b(com.huawei.haf.application.BaseApplication.e(), SP_NAME, AI_VMALL_MODULE);
        JSONObject jSONObject = new JSONObject();
        try {
            return new JSONObject(b).getJSONObject("params");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "JSONException");
            return jSONObject;
        }
    }

    public static boolean isOperation(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "countryCode is empty");
            return false;
        }
        ArrayList<String> cloudRules = getCloudRules("ai-operation-001");
        if (cloudRules == null) {
            LogUtil.h(TAG, "isOperation() operationCountryList is null.");
            try {
                String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968691_res_0x7f040073);
                ArrayList<String> arrayList = new ArrayList<>();
                Collections.addAll(arrayList, stringArray);
                cloudRules = arrayList;
            } catch (Resources.NotFoundException | IllegalArgumentException | UnsupportedOperationException e) {
                LogUtil.b(TAG, "isOperation() meet exception: ", e.getMessage());
                return false;
            }
        }
        Iterator<String> it = cloudRules.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean switchToMarketingTwo() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a(TAG, "switchToMarketingTwo countryCode");
            return true;
        }
        ArrayList<String> cloudRules = getCloudRules(AI_MARKETING_TWO_MODULE);
        LogUtil.a(TAG, "marketingTwoCountryList: ", cloudRules);
        if (koq.b(cloudRules)) {
            LogUtil.a(TAG, "switchToMarketingTwo marketingTwoCountryList is empty");
            return false;
        }
        return cloudRules.contains(accountInfo);
    }

    public static boolean isModuleOperatedToOversea(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h(TAG, "isModuleOperatedToOversea countryCode");
            return true;
        }
        ArrayList<String> cloudRules = getCloudRules(str);
        LogUtil.a(TAG, "isModuleOperatedToOversea: ", cloudRules);
        if (koq.b(cloudRules)) {
            LogUtil.h(TAG, "isModuleOperatedToOversea countryList is empty");
            return false;
        }
        return cloudRules.contains(accountInfo);
    }

    public static boolean isSupportMenstrual(String str) {
        ArrayList<String> cloudRules = getCloudRules("ai-menstrual-001");
        if (cloudRules == null) {
            LogUtil.h(TAG, "isSupportMenstrual() countryList is null.");
            String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968684_res_0x7f04006c);
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                Collections.addAll(arrayList, stringArray);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                LogUtil.b(TAG, "isSupportMenstrual() meet exception: ", e.getMessage());
            }
            cloudRules = arrayList;
        }
        Iterator<String> it = cloudRules.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next(), str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSupportCountry(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "countryCode is empty");
            return false;
        }
        ArrayList<String> cloudRules = getCloudRules(str2);
        if (cloudRules == null) {
            LogUtil.h(TAG, "isOperation() operationCountryList is null.");
            try {
                String[] stringArray = BaseApplication.getContext().getResources().getStringArray(i);
                ArrayList<String> arrayList = new ArrayList<>();
                Collections.addAll(arrayList, stringArray);
                cloudRules = arrayList;
            } catch (Resources.NotFoundException | IllegalArgumentException | UnsupportedOperationException e) {
                LogUtil.b(TAG, "isOperation() meet exception: ", e.getMessage());
                return false;
            }
        }
        Iterator<String> it = cloudRules.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> getCloudRules(String str) {
        String[] split;
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, String.valueOf(30004), str);
        if (AI_MARKETING_TWO_MODULE.equals(str) && TextUtils.isEmpty(b)) {
            b = moh.d(context, AI_MARKETING_TWO_COUNTRIES_LOCAL_LIST);
        }
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(b).getJSONObject("params");
            if (!jSONObject.has(JSON_KEY_ABROAD_COUNTRY_LIST)) {
                return null;
            }
            String string = jSONObject.getString(JSON_KEY_ABROAD_COUNTRY_LIST);
            if (string == null) {
                LogUtil.h(TAG, "getCloudRules() overseaCountryString is null");
                return null;
            }
            String[] split2 = string.split(",");
            if (split2 == null) {
                LogUtil.h(TAG, "getCloudRules() countryIncludeFlagList is null");
                return null;
            }
            for (String str2 : split2) {
                if (str2 != null && (split = str2.split(":")) != null && split.length > 0) {
                    String str3 = split.length > 1 ? split[1] : "0";
                    if (!CommonUtil.bv() || TextUtils.isEmpty(str3) || !"1".equals(str3)) {
                        arrayList.add(split[0]);
                    }
                }
            }
            return arrayList;
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getCloudRules() JSONException");
            return null;
        }
    }
}
