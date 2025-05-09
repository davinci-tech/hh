package com.huawei.operation.vmall;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.bzw;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class AuthVmall {
    private static final long CID_MAX_AGE_SECOND = 259200;
    private static final String COOKIE = "cookie";
    private static final String EUID = "euid";
    private static final String TAG = "AuthVmall";
    private static final String VMALL_LOGIN_URL = "/achievement/vmall/accessTokenLogin";
    private static String cookieStr = "";

    private AuthVmall() {
    }

    public static void vmallAtLogin(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        final JSONObject publicParams = getPublicParams(context, getPrivateParams(context));
        final HashMap<String, String> headers = getHeaders(context);
        LogUtil.b(TAG, "vmallAtLogin param: ", publicParams);
        GRSManager.a(context).e("achievementUrl", new GrsQueryCallback() { // from class: com.huawei.operation.vmall.AuthVmall.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.d(AuthVmall.TAG, "request vmall login.");
                bzw.e().doPost(str + AuthVmall.VMALL_LOGIN_URL, publicParams, headers, new IBaseResponseCallback() { // from class: com.huawei.operation.vmall.AuthVmall.1.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (obj instanceof String) {
                            AuthVmall.handleRequestResult((String) obj, iBaseResponseCallback);
                        } else {
                            LogUtil.c(AuthVmall.TAG, "onResponse not instanceof String");
                            iBaseResponseCallback.d(i, null);
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c(AuthVmall.TAG, "GRSManager getUrl failed:", Integer.valueOf(i));
                iBaseResponseCallback.d(i, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRequestResult(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c(TAG, "post vmall login result is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("resultCode");
            LogUtil.d(TAG, "post vmall login resultCode: ", Integer.valueOf(i));
            if (i == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                if (!jSONObject2.isNull(COOKIE)) {
                    saveCookie(jSONObject2.getString(COOKIE));
                    iBaseResponseCallback.d(0, null);
                } else {
                    LogUtil.c(TAG, "post vmall login result is null");
                    iBaseResponseCallback.d(-1, null);
                }
            } else {
                LogUtil.e(TAG, "post vmall login result failed: ", Integer.valueOf(i));
                iBaseResponseCallback.d(i, null);
            }
        } catch (JSONException e) {
            LogUtil.e(TAG, "JSONException is ", e.getMessage());
            iBaseResponseCallback.d(-1, null);
        }
    }

    private static HashMap<String, String> getHeaders(Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
        hashMap.put("x-version", CommonUtil.e(context));
        return hashMap;
    }

    private static JSONObject getPrivateParams(Context context) {
        AtLoginVmallReqBean atLoginVmallReqBean = new AtLoginVmallReqBean();
        LoginInit loginInit = LoginInit.getInstance(context);
        String accountInfo = loginInit.getAccountInfo(1010);
        atLoginVmallReqBean.setAccessToken(loginInit.getAccountInfo(1008));
        atLoginVmallReqBean.setLang(mtj.e(null));
        atLoginVmallReqBean.setCountry(accountInfo);
        atLoginVmallReqBean.setVersion(Long.valueOf(CommonUtil.d(context)));
        atLoginVmallReqBean.setBeCode(accountInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timeZone", HiDateUtil.d((String) null));
            jSONObject.put(CloudParamKeys.INFO, parseListToJsonString(atLoginVmallReqBean));
        } catch (JSONException e) {
            LogUtil.e(TAG, "JSONException: ", e.getMessage());
        }
        return jSONObject;
    }

    private static JSONObject parseListToJsonString(AtLoginVmallReqBean atLoginVmallReqBean) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("accessToken", atLoginVmallReqBean.getAccessToken());
            jSONObject.put("lang", atLoginVmallReqBean.getLang());
            jSONObject.put("country", atLoginVmallReqBean.getCountry());
            jSONObject.put("version", atLoginVmallReqBean.getVersion());
            jSONObject.put(CloudParamKeys.BECODE, atLoginVmallReqBean.getBeCode());
        } catch (JSONException e) {
            LogUtil.c(TAG, "JSONException", e.getMessage());
        }
        return jSONObject;
    }

    private static JSONObject getPublicParams(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            LoginInit loginInit = LoginInit.getInstance(context);
            jSONObject.put("token", loginInit.getAccountInfo(1008));
            jSONObject.put("appId", OperationUtils.getAppId(BaseApplication.getContext()));
            jSONObject.put("deviceType", PhoneInfoUtils.getPhoneType());
            String deviceId = loginInit.getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = "clientnull";
            }
            jSONObject.put("deviceId", deviceId);
            jSONObject.put("sysVersion", OperationUtils.getSysVersion());
            jSONObject.put("bindDeviceType", String.valueOf(CommonUtil.h(context)));
            jSONObject.put("appType", String.valueOf(AppTypeUtils.getAppType()));
            jSONObject.put("ts", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("iVersion", String.valueOf(CommonUtil.d(context)));
            jSONObject.put("language", mtj.e(null));
            jSONObject.put("environment", String.valueOf(CommonUtil.l(context)));
            if (Utils.o()) {
                jSONObject.put("siteId", loginInit.getAccountInfo(1009));
            }
            jSONObject.put("upDeviceType", loginInit.getDeviceType());
        } catch (JSONException e) {
            LogUtil.e(TAG, "JSONException: ", e.getMessage());
        }
        return jSONObject;
    }

    private static void saveCookie(String str) {
        cookieStr = str;
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(20006), COOKIE, str, new StorageParams());
    }

    public static void setCookie(String str) {
        LogUtil.d(TAG, "enter setCookie().");
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "setCookie() url is null");
            return;
        }
        String str2 = cookieStr;
        if (TextUtils.isEmpty(str2)) {
            str2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20006), COOKIE);
            if (!TextUtils.isEmpty(str2)) {
                cookieStr = str2;
            }
        }
        if (TextUtils.isEmpty(str2) || !str2.contains(EUID)) {
            LogUtil.e(TAG, "storeVmallCookie is null or no euid");
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        String cookieCid = getCookieCid(str);
        cookieManager.setAcceptCookie(true);
        for (String str3 : str2.split(",")) {
            if (!TextUtils.isEmpty(str3)) {
                cookieManager.setCookie(str, str3);
            }
        }
        cookieManager.setCookie(str, "hasLogin=1610334747531");
        if (!TextUtils.isEmpty(cookieCid)) {
            cookieManager.setCookie(str, cookieCid);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a(TAG, "CookieManager cookie2: ", CookieManager.getInstance().getCookie(str));
    }

    private static String getCookieCid(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c(TAG, "getCookieCid url is empty!");
            return "";
        }
        String cookie = CookieManager.getInstance().getCookie(str);
        if (TextUtils.isEmpty(cookie) || !cookie.contains("cps_id=")) {
            LogUtil.c(TAG, "getCookieCid cid doesn't exist!");
            return "";
        }
        for (String str2 : cookie.split(";")) {
            if (str2.contains("cps_id=")) {
                LogUtil.d(TAG, "getCookieCid cid cookie:", str2);
                return str2.trim() + ";max-age=259200;path=/";
            }
        }
        return "";
    }
}
