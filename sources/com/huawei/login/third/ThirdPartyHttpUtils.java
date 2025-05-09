package com.huawei.login.third;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.common.OpAnalyticsApi;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.BuildConfig;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.jec;
import defpackage.lqi;
import defpackage.lqj;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ThirdPartyHttpUtils {
    private static final int DEFAULT_HEADER_CAPACITY = 4;
    private static final int DEFAULT_MAP_CAPACITY = 2;
    private static final int DEFAULT_MAP_SIZE = 8;
    private static final int ERR_CODE_UNKNOWN = -1;
    private static final int HTTP_CONNECT_TIME_OUT = 10000;
    private static final int NOT_AUTH = 1;
    private static final String PARAM_ACCESS_TOKEN = "accessToken";
    private static final String PARAM_APP_ID = "appId";
    private static final String PARAM_APP_TYPE = "appType";
    private static final String PARAM_AUTH_CODE = "authorizationCode";
    private static final String PARAM_CLIENT_ID = "thridPartyClientId";
    private static final String PARAM_REFRESH_TOKEN = "refreshToken";
    private static final String PARAM_SITE_ID = "siteID";
    private static final String PARAM_TOKEN = "token";
    private static final String PARAM_TOKEN_TYPE = "tokenType";
    private static final String PARAM_TS = "ts";
    private static final String TAG = "UIDV_3rdPtyHttpUts";

    public interface RequestCallBack {
        void onFailed(int i);

        void onSuccess(String str);
    }

    public static void postToGetAccessToken(String str, String str2, RequestCallBack requestCallBack) {
        LogUtil.a(TAG, "enter postToGetAT");
        if (!NetworkUtil.i()) {
            ReleaseLogUtil.d(TAG, "postToGetAccessToken no network");
            if (requestCallBack != null) {
                requestCallBack.onFailed(-1);
                return;
            }
            return;
        }
        report(str);
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_AUTH_CODE, str2);
        hashMap.put("appId", String.valueOf(BuildConfig.HMS_APPLICATION_ID));
        postRequest(str, null, hashMap, requestCallBack);
        LogUtil.a(TAG, "end postToGetAT");
    }

    public static void refreshRt(String str, String str2, String str3, RequestCallBack requestCallBack) {
        LogUtil.a(TAG, "enter refreshRT");
        if (!NetworkUtil.i()) {
            ReleaseLogUtil.d(TAG, "refreshRt no network");
            if (requestCallBack != null) {
                requestCallBack.onFailed(-1);
                return;
            }
            return;
        }
        report(str);
        HashMap hashMap = new HashMap(2);
        hashMap.put("refreshToken", str2);
        hashMap.put("appId", String.valueOf(BuildConfig.HMS_APPLICATION_ID));
        postRequest(str, str3, hashMap, requestCallBack);
        LogUtil.a(TAG, "end refreshRT");
    }

    public static void queryUserData(String str, String str2, String str3, RequestCallBack requestCallBack) {
        LogUtil.a(TAG, "enter queryUserData");
        if (!NetworkUtil.i()) {
            ReleaseLogUtil.d(TAG, "queryUserData no network");
            if (requestCallBack != null) {
                requestCallBack.onFailed(-1);
                return;
            }
            return;
        }
        report(str);
        HashMap hashMap = new HashMap(2);
        hashMap.put("accessToken", str2);
        hashMap.put("appId", String.valueOf(BuildConfig.HMS_APPLICATION_ID));
        postRequest(str, str3, hashMap, requestCallBack);
        LogUtil.a(TAG, "end queryUserData");
    }

    public static void queryUserAccount(String str, String str2, String str3, RequestCallBack requestCallBack) {
        LogUtil.a(TAG, "enter queryUserAccount");
        if (!NetworkUtil.i()) {
            ReleaseLogUtil.d(TAG, "queryUserAccount no network");
            if (requestCallBack != null) {
                requestCallBack.onFailed(-1);
                return;
            }
            return;
        }
        report(str);
        HashMap hashMap = new HashMap(8);
        hashMap.put("accessToken", str2);
        hashMap.put(PARAM_CLIENT_ID, String.valueOf(BuildConfig.HMS_APPLICATION_ID));
        hashMap.put(PARAM_SITE_ID, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put("token", str2);
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("ts", String.valueOf(jec.h()));
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        postRequest(str, str3, hashMap, requestCallBack);
        LogUtil.a(TAG, "end queryUserAccount");
    }

    private static void postRequest(final String str, String str2, HashMap<String, String> hashMap, RequestCallBack requestCallBack) {
        if (!"true".equals(KeyValDbManager.b(BaseApplication.getContext()).e("key_wether_to_auth"))) {
            requestCallBack.onFailed(1);
            return;
        }
        final String[] strArr = new String[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        lqi.d().b(str, getHeader(str2), getParams(hashMap), String.class, new ResultCallback<String>() { // from class: com.huawei.login.third.ThirdPartyHttpUtils.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str3) {
                strArr[0] = str3;
                countDownLatch.countDown();
                LogUtil.a(ThirdPartyHttpUtils.TAG, "postReq-->result:", str3);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                countDownLatch.countDown();
                if (th != null) {
                    ReleaseLogUtil.d(ThirdPartyHttpUtils.TAG, "postRequest throwable:", th.getMessage());
                    ThirdPartyHttpUtils.callbackErrorCode(str, th);
                } else {
                    ReleaseLogUtil.d(ThirdPartyHttpUtils.TAG, "postRequest throwable null");
                }
            }
        });
        try {
            ReleaseLogUtil.e(TAG, "postRequest isOnTime:", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c(TAG, "postRequest InterruptedException");
        }
        if (TextUtils.isEmpty(strArr[0])) {
            requestCallBack.onFailed(-1);
        } else {
            requestCallBack.onSuccess(strArr[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackErrorCode(String str, Throwable th) {
        if (!(th instanceof lqj) || TextUtils.isEmpty(str)) {
            return;
        }
        int e = ((lqj) th).e();
        String[] split = str.split("/");
        if (split.length == 0) {
            return;
        }
        String str2 = split[split.length - 1];
        LogUtil.a(TAG, "callbackErrorCode lastPart=", str2, " errorCode=", Integer.valueOf(e));
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        HuaweiLoginManager.reportErrorCode(str2, e);
    }

    private static HashMap<String, String> getHeader(String str) {
        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(System.currentTimeMillis()));
        hashMap.put("x-version", CommonUtil.c((Context) null));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("x-huid", str);
        }
        hashMap.put("Content-Type", "application/json");
        return hashMap;
    }

    private static String getParams(HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        if (hashMap == null || hashMap.size() <= 0 || (r4 = hashMap.entrySet().iterator()) == null) {
            return "";
        }
        stringBuffer.append("{");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry instanceof Map.Entry) {
                Map.Entry<String, String> entry2 = entry;
                stringBuffer.append("\n\"").append((Object) entry2.getKey()).append("\":\"").append((Object) entry2.getValue()).append("\",");
            }
        }
        String trim = stringBuffer.toString().trim();
        return trim.substring(0, trim.length() + (-1) < 0 ? 0 : trim.length() - 1) + "\n}";
    }

    private static void report(String str) {
        OpAnalyticsApi opAnalyticsApi = LoginInit.getInstance(BaseApplication.getContext()).getOpAnalyticsApi();
        if (opAnalyticsApi != null) {
            opAnalyticsApi.onReport(str, Arrays.asList(Thread.currentThread().getStackTrace()).toString());
        }
    }
}
