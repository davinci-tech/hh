package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpo {
    public static void b(JSONObject jSONObject, Map<String, String> map) {
        try {
            b(jSONObject);
            if (map == null || map.size() <= 0) {
                return;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            LogUtil.b("CloudImplHelper", e.getMessage());
        }
    }

    private static void b(JSONObject jSONObject) throws JSONException {
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            jSONObject.put("appType", 2);
            jSONObject.put("appId", "com.huawei.bone");
        } else {
            jSONObject.put("appType", 1);
            jSONObject.put("appId", "com.huawei.health");
        }
        jSONObject.put("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue());
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        jSONObject.put("token", loginInit.getAccountInfo(1008));
        jSONObject.put("siteId", loginInit.getSiteId());
        jSONObject.put("deviceType", Build.MODEL);
        jSONObject.put("deviceId", LoginInit.getInstance(BaseApplication.getContext()).getDeviceId());
        jSONObject.put("sysVersion", Build.VERSION.RELEASE);
        jSONObject.put("bindDeviceType", CommonUtil.h(BaseApplication.getContext()));
        jSONObject.put("ts", System.currentTimeMillis());
        jSONObject.put("iVersion", 2);
        jSONObject.put("environment", CommonUtil.l(BaseApplication.getContext()));
        jSONObject.put("manufacturer", Build.MANUFACTURER);
    }

    public static void a(String str, final String str2, final JSONObject jSONObject, final Map<String, String> map, final HttpDataCallback httpDataCallback) {
        if (e(str2, httpDataCallback)) {
            if (!Utils.i()) {
                LogUtil.h("CloudImplHelper", "fetch HEALTH_CLOUD from no_cloud user login");
            } else if (!CommonUtil.aa(BaseApplication.getContext())) {
                LogUtil.b("CloudImplHelper", "no network,please check");
                httpDataCallback.onFailure(AwarenessConstants.ERROR_TIMEOUT_CODE, "Internet connection failed. Please check your network settings and try again.");
            } else {
                GRSManager.a(BaseApplication.getContext()).e(str, new GrsQueryCallback() { // from class: mpo.3
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(final String str3) {
                        mpo.b(jSONObject, map);
                        mpt.c(str3 + str2, jSONObject, new HttpDataCallback() { // from class: mpo.3.1
                            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                            public void onFailure(int i, String str4) {
                                mpo.b(str3, i, str4, httpDataCallback);
                            }

                            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                            public void onSuccess(JSONObject jSONObject2) {
                                mpo.e(str3, jSONObject2, httpDataCallback);
                            }
                        });
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                        LogUtil.b("CloudImplHelper", "get domainHealthdataHicloud failed :", Integer.valueOf(i));
                        httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
                    }
                });
            }
        }
    }

    public static boolean e(String str, HttpDataCallback httpDataCallback) {
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, "args valid");
        LogUtil.b("CloudImplHelper", "url == null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, int i, String str2, HttpDataCallback httpDataCallback) {
        if (httpDataCallback != null) {
            httpDataCallback.onFailure(i, str2);
        }
        a(str, i, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, JSONObject jSONObject, HttpDataCallback httpDataCallback) {
        int i = 209999;
        String b = mpu.b(209999);
        if (jSONObject != null) {
            LogUtil.c("CloudImplHelper", "data = ", jSONObject);
            i = jSONObject.optInt("resultCode");
            LogUtil.a("CloudImplHelper", "resultCode = ", Integer.valueOf(i));
            b = jSONObject.optString("resultDesc");
            if (i == 0) {
                if (httpDataCallback != null) {
                    httpDataCallback.onSuccess(jSONObject);
                }
                LogUtil.c("CloudImplHelper", str, jSONObject.toString());
            } else if (httpDataCallback != null) {
                httpDataCallback.onFailure(i, b);
            }
        } else if (httpDataCallback != null) {
            httpDataCallback.onFailure(209999, b);
        }
        LogUtil.c("CloudImplHelper", str, mpu.b(i), b);
    }

    private static void a(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        String str3 = null;
        try {
            str3 = new URL(str).getHost();
            for (InetAddress inetAddress : InetAddress.getAllByName(str3)) {
                if (inetAddress != null) {
                    sb.append(inetAddress.getHostAddress());
                    sb.append("_");
                }
            }
        } catch (MalformedURLException | UnknownHostException e) {
            LogUtil.h("CloudImplHelper", "requestFailureLog exception ", LogAnonymous.b(e));
        }
        LogUtil.c("CloudImplHelper", "url:", str3, "_ip:", sb.toString(), "_netConnected:", !CommonUtil.aa(BaseApplication.getContext()) ? "net not Connect" : "net Connect", "_errorCode:", Integer.valueOf(i), "_errorInfo:", str2, "_exceptionString:");
    }
}
