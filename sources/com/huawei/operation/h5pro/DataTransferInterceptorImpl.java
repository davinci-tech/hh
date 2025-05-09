package com.huawei.operation.h5pro;

import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.jsbridge.system.network.DataTransferInterceptor;
import com.huawei.health.h5pro.jsbridge.system.network.RequestObj;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DataTransferInterceptorImpl implements DataTransferInterceptor {
    private static final String TAG = "DataTransferInterceptorImpl";
    public static final String TOKEN_PLACEHOLDER = "TOKEN_PLACEHOLDER";
    private static volatile List<String> sTrustedApiHost;

    public DataTransferInterceptorImpl() {
        init();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.network.DataTransferInterceptor
    public RequestObj dataIntercept(H5ProAppInfo h5ProAppInfo, String str, RequestObj requestObj) {
        if (!TrustListCheckerImpl.isTrustedH5MiniProgram(h5ProAppInfo, str) && !TrustListCheckerImpl.isTrustedUrl(str)) {
            LogUtil.h(TAG, "dataIntercept: unTrusted h5 app");
            return requestObj;
        }
        if (!isTrustedNetworkApi(requestObj.getUrl())) {
            LogUtil.h(TAG, "dataIntercept: unTrusted network api");
            return requestObj;
        }
        requestObj.setHeaders(initHeaders(requestObj.getHeaders()));
        if (TextUtils.isEmpty(requestObj.getRequestBody())) {
            requestObj.setBody(initBody(requestObj.getBody()));
        } else {
            requestObj.setRequestBody(initRequestBody(requestObj.getRequestBody()));
        }
        return requestObj;
    }

    public static Map<String, String> initHeaders(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.a(TAG, "initHeaders: headers is empty");
            return map;
        }
        if (map.containsKey(CloudParamKeys.X_TOKEN_TYPE) && map.containsKey(CloudParamKeys.X_TOKEN) && "1".equalsIgnoreCase(map.get(CloudParamKeys.X_TOKEN_TYPE)) && TOKEN_PLACEHOLDER.equalsIgnoreCase(map.get(CloudParamKeys.X_TOKEN))) {
            LogUtil.a(TAG, "initHeaders: replaces");
            map.put(CloudParamKeys.X_TOKEN, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        }
        return map;
    }

    public static Map<String, Object> initBody(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.a(TAG, "initBody: body is empty");
            return map;
        }
        if (map.containsKey("tokenType") && map.containsKey("token") && "1".equalsIgnoreCase(String.valueOf(map.get("tokenType"))) && TOKEN_PLACEHOLDER.equalsIgnoreCase(String.valueOf(map.get("token")))) {
            LogUtil.a(TAG, "initBody: replaces");
            map.put("token", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String initRequestBody(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "tokenType"
            java.lang.String r1 = "token"
            boolean r2 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r3 = "DataTransferInterceptorImpl"
            if (r2 == 0) goto L18
            java.lang.String r0 = "initRequestBody: body is empty"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r0)
            return r7
        L18:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L68
            r2.<init>(r7)     // Catch: org.json.JSONException -> L68
            boolean r4 = r2.has(r0)     // Catch: org.json.JSONException -> L66
            if (r4 == 0) goto L83
            boolean r4 = r2.has(r1)     // Catch: org.json.JSONException -> L66
            if (r4 == 0) goto L83
            java.lang.String r4 = "1"
            java.lang.Object r0 = r2.get(r0)     // Catch: org.json.JSONException -> L66
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: org.json.JSONException -> L66
            boolean r0 = r4.equalsIgnoreCase(r0)     // Catch: org.json.JSONException -> L66
            if (r0 == 0) goto L83
            java.lang.String r0 = "TOKEN_PLACEHOLDER"
            java.lang.Object r4 = r2.get(r1)     // Catch: org.json.JSONException -> L66
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: org.json.JSONException -> L66
            boolean r0 = r0.equalsIgnoreCase(r4)     // Catch: org.json.JSONException -> L66
            if (r0 == 0) goto L83
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L66
            java.lang.String r4 = "initRequestBody: replaces"
            r5 = 0
            r0[r5] = r4     // Catch: org.json.JSONException -> L66
            com.huawei.hwlogsmodel.LogUtil.a(r3, r0)     // Catch: org.json.JSONException -> L66
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: org.json.JSONException -> L66
            com.huawei.login.ui.login.LoginInit r0 = com.huawei.login.ui.login.LoginInit.getInstance(r0)     // Catch: org.json.JSONException -> L66
            r4 = 1008(0x3f0, float:1.413E-42)
            java.lang.String r0 = r0.getAccountInfo(r4)     // Catch: org.json.JSONException -> L66
            r2.put(r1, r0)     // Catch: org.json.JSONException -> L66
            goto L83
        L66:
            r0 = move-exception
            goto L6a
        L68:
            r0 = move-exception
            r2 = 0
        L6a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "initRequestBody: exception -> "
            r1.<init>(r4)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
        L83:
            if (r2 != 0) goto L86
            goto L8a
        L86:
            java.lang.String r7 = r2.toString()
        L8a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.DataTransferInterceptorImpl.initRequestBody(java.lang.String):java.lang.String");
    }

    private static void init() {
        synchronized (DataTransferInterceptorImpl.class) {
            if (sTrustedApiHost == null || sTrustedApiHost.isEmpty()) {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.DataTransferInterceptorImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DataTransferInterceptorImpl.initTrustedNetworkApiHost();
                        }
                    });
                } else {
                    initTrustedNetworkApiHost();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initTrustedNetworkApiHost() {
        synchronized (DataTransferInterceptorImpl.class) {
            if (sTrustedApiHost == null || sTrustedApiHost.isEmpty()) {
                long currentTimeMillis = System.currentTimeMillis();
                String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968714_res_0x7f04008a);
                if (stringArray != null && stringArray.length != 0) {
                    sTrustedApiHost = new ArrayList(stringArray.length);
                    for (String str : stringArray) {
                        sTrustedApiHost.add(GRSManager.a(BaseApplication.getContext()).getUrl(str));
                    }
                    sTrustedApiHost = Collections.unmodifiableList(sTrustedApiHost);
                    try {
                        LogUtil.a(TAG, "initTrustedNetworkApiHost: duration -> " + Math.subtractExact(System.currentTimeMillis(), currentTimeMillis));
                    } catch (ArithmeticException e) {
                        LogUtil.b(TAG, "initTrustedNetworkApiHost: exception -> " + e.getMessage());
                    }
                }
                sTrustedApiHost = Collections.emptyList();
            }
        }
    }

    public static boolean isTrustedNetworkApi(String str) {
        synchronized (DataTransferInterceptorImpl.class) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h(TAG, "isTrustedNetworkApiHost: networkApi is empty");
                return false;
            }
            initTrustedNetworkApiHost();
            for (String str2 : sTrustedApiHost) {
                if (!TextUtils.isEmpty(str2) && str.startsWith(str2)) {
                    return true;
                }
            }
            LogUtil.h(TAG, "isTrustedNetworkApiHost: networkApi is untrusted");
            return false;
        }
    }
}
