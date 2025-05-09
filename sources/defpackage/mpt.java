package defpackage;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpt {
    private static Map<String, String> e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", HealthEngineRequestManager.CONTENT_TYPE);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
        }
        String c = CommonUtil.c(BaseApplication.getContext());
        if (!TextUtils.isEmpty(c)) {
            hashMap.put("x-version", c);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, long j, Throwable th, HttpDataCallback httpDataCallback) {
        int i;
        String b;
        int i2;
        String b2;
        LogUtil.b("HttpRequest", str, LogAnonymous.b(th));
        if ((th instanceof SocketTimeoutException) || ((th instanceof SSLException) && th.getMessage().contains("Connection timed out"))) {
            i = AwarenessConstants.ERROR_NO_PERMISSION_CODE;
            b = mpu.b(AwarenessConstants.ERROR_NO_PERMISSION_CODE);
        } else if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
            i = AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE;
            b = mpu.b(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE);
        } else if ((th instanceof IOException) && th.getMessage() != null && th.getMessage().contains("Canceled")) {
            i = AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE;
            b = mpu.b(AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE);
        } else {
            if (th.getMessage() != null) {
                i2 = 500;
                if (th.getMessage().contains(mpu.b(500))) {
                    b2 = mpu.b(500);
                    b = b2;
                    i = i2;
                }
            }
            if (th.getMessage() != null) {
                i2 = 503;
                if (th.getMessage().contains(mpu.b(503))) {
                    b2 = mpu.b(503);
                    b = b2;
                    i = i2;
                }
            }
            if (th.getMessage() != null && th.getMessage().contains("reach max quota limit")) {
                i = 30000001;
                b = mpu.b(30000001);
            } else if (th.getMessage() != null && th.getMessage().contains("reach max follow limit")) {
                i = 30000002;
                b = mpu.b(30000002);
            } else if (th.getMessage() != null && th.getMessage().contains("reach max authorization limit")) {
                i = 30000008;
                b = mpu.b(30000008);
            } else {
                i = 9999;
                b = mpu.b(9999);
            }
        }
        a(i, j, System.currentTimeMillis(), false);
        if (httpDataCallback != null) {
            httpDataCallback.onFailure(i, b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, long j, String str2, HttpDataCallback httpDataCallback) {
        JSONObject jSONObject;
        LogUtil.a("HttpRequest", str, " request onSuccess.");
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException e) {
            c(str, j, e, httpDataCallback);
            jSONObject = null;
        }
        if (jSONObject != null) {
            httpDataCallback.onSuccess(jSONObject);
        }
        a(200, j, System.currentTimeMillis(), true);
    }

    public static void c(final String str, JSONObject jSONObject, final HttpDataCallback httpDataCallback) {
        if (!c(str)) {
            LogUtil.h("HttpRequest", "url is invalid");
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, "parameter invalid");
        } else {
            LogUtil.c("HttpRequest", str, " params ", jSONObject);
            final long currentTimeMillis = System.currentTimeMillis();
            lqi.d().b(str, e(), jSONObject.toString(), String.class, new ResultCallback<String>() { // from class: mpt.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str2) {
                    mpt.a(str, currentTimeMillis, str2, httpDataCallback);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    mpt.c(str, currentTimeMillis, th, httpDataCallback);
                }
            });
        }
    }

    private static void a(int i, long j, long j2, boolean z) {
        long j3 = j2 - j;
        if (j3 >= 5000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
            linkedHashMap.put("module", String.valueOf(4));
            linkedHashMap.put("status", z ? "0" : "1");
            linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(j3));
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
        }
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        return normalize.startsWith("http://") || normalize.startsWith("https://");
    }
}
