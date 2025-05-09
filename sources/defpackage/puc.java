package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class puc {
    private static final puc c = new puc();
    private final ParamsFactory d = new pub();

    private puc() {
    }

    public static puc d() {
        return c;
    }

    public void c(Object obj, String str, boolean z, DataCallback dataCallback) {
        c(null, obj, str, z, dataCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c(IRequest iRequest, Object obj, String str, boolean z, final DataCallback dataCallback) {
        if (iRequest != null) {
            str = iRequest.getUrl();
        } else {
            iRequest = obj;
        }
        String str2 = str;
        Map<String, String> headers = this.d.getHeaders();
        Map<String, Object> body = this.d.getBody(iRequest);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!z) {
            lqi.d().b(str2, headers, lql.b(body), String.class, new ResultCallback<String>() { // from class: puc.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str3) {
                    puc.this.b(str3, dataCallback, elapsedRealtime);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    puc.this.d(th, dataCallback, elapsedRealtime);
                }
            });
            return;
        }
        String b = lql.b(iRequest);
        String e = jdq.e(BaseApplication.e(), str2 + b);
        LogUtil.a("Suggestion_CloudHelper", str2, "md5 = ", e);
        lqi.d().d(str2, headers, lql.b(body), String.class, new ResultCallback<lqq<String>>() { // from class: puc.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(lqq<String> lqqVar) {
                LogUtil.c("Suggestion_CloudHelper", "source from = ", Integer.valueOf(lqqVar.b()));
                puc.this.b(lqqVar.c(), dataCallback, elapsedRealtime);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                puc.this.d(th, dataCallback, elapsedRealtime);
            }
        }, new pue().d(e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, DataCallback dataCallback, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (TextUtils.isEmpty(jSONObject.toString())) {
                ReleaseLogUtil.c("Suggestion_CloudHelper", "onResponse, response data is empty");
                dataCallback.onFailure(9999, ResultUtil.d(9999));
            } else {
                int optInt = jSONObject.optInt("resultCode");
                String optString = jSONObject.optString("resultDesc");
                if (optInt != 0 && optInt != 20004) {
                    ReleaseLogUtil.c("Suggestion_CloudHelper", "onResponse, onFailure ", Integer.valueOf(optInt), ":", optString);
                    dataCallback.onFailure(optInt, optString);
                }
                dataCallback.onSuccess(jSONObject);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("Suggestion_CloudHelper", "onResponse, JSONException");
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
        d(j, elapsedRealtime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Throwable th, DataCallback dataCallback, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i = 9999;
        if (th instanceof Exception) {
            Exception exc = (Exception) th;
            if ((exc instanceof SocketTimeoutException) || ((exc instanceof SSLException) && TextUtils.equals(exc.getMessage(), "Connection timed out"))) {
                i = 1003;
            } else if ((exc instanceof ConnectException) || (exc instanceof UnknownHostException)) {
                i = -8;
            } else if ((exc instanceof IOException) && TextUtils.equals(exc.getMessage(), "Canceled")) {
                i = -2;
            } else {
                int i2 = 500;
                if (!ResultUtil.d(500).equals(exc.getMessage())) {
                    i2 = 503;
                    if (!ResultUtil.d(503).equals(exc.getMessage())) {
                        if (exc instanceof lqj) {
                            i = ((lqj) exc).e();
                        } else {
                            ReleaseLogUtil.c("Suggestion_CloudHelper", "onFailure, unknow exception");
                        }
                    }
                }
                i = i2;
            }
            ReleaseLogUtil.c("Suggestion_CloudHelper", "onFailure ", Integer.valueOf(i), ":", ResultUtil.d(i));
            dataCallback.onFailure(i, ResultUtil.d(i));
        } else {
            String message = th.getMessage() != null ? th.getMessage() : ResultUtil.d(9999);
            LogUtil.b("Suggestion_CloudHelper", "onFailure, throwable = ", message);
            dataCallback.onFailure(9999, message);
        }
        d(i, j, elapsedRealtime);
    }

    private void d(int i, long j, long j2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
        linkedHashMap.put("module", "3");
        linkedHashMap.put("status", "1");
        linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(j2 - j));
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
    }

    private void d(long j, long j2) {
        long j3 = j2 - j;
        if (j3 >= 5000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
            linkedHashMap.put("module", "3");
            linkedHashMap.put("status", "0");
            linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(j3));
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(200));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
        }
    }
}
