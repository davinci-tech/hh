package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
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

/* loaded from: classes3.dex */
public class etc {

    /* renamed from: a, reason: collision with root package name */
    private static final etc f12245a = new etc();
    private final ParamsFactory c = new esw();

    private etc() {
    }

    public static etc a() {
        return f12245a;
    }

    public void c(IRequest iRequest, DataCallback dataCallback) {
        b(iRequest, null, null, false, dataCallback);
    }

    public void d(IRequest iRequest, boolean z, DataCallback dataCallback) {
        b(iRequest, null, null, z, dataCallback);
    }

    public void b(Object obj, String str, DataCallback dataCallback) {
        b(null, obj, str, false, dataCallback);
    }

    public void a(Object obj, String str, boolean z, DataCallback dataCallback) {
        b(null, obj, str, z, dataCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(IRequest iRequest, Object obj, String str, boolean z, final DataCallback dataCallback) {
        if (iRequest != null) {
            str = iRequest.getUrl();
        } else {
            iRequest = obj;
        }
        Map<String, String> headers = this.c.getHeaders();
        Map<String, Object> body = this.c.getBody(iRequest);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.a("Suggestion_CloudHelper", "url: ", str, ", headers: ", headers.toString(), ", bodyMap: ", body.toString());
        if (!z) {
            try {
                lqi.d().b(str, headers, lql.b(body), String.class, new ResultCallback<String>() { // from class: etc.5
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str2) {
                        etc.this.b(str2, dataCallback, elapsedRealtime);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        LogUtil.b("Suggestion_CloudHelper", "callHttpPost !useCache onFailure : " + th.toString());
                        etc.this.a(th, dataCallback, elapsedRealtime);
                    }
                });
                return;
            } catch (NullPointerException e) {
                a(e, dataCallback, elapsedRealtime);
                return;
            }
        }
        String b = lql.b(iRequest);
        String e2 = jdq.e(BaseApplication.e(), str + b);
        LogUtil.a("Suggestion_CloudHelper", str, "md5 = ", e2);
        lqi.d().d(str, headers, lql.b(body), String.class, new ResultCallback<lqq<String>>() { // from class: etc.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(lqq<String> lqqVar) {
                LogUtil.c("Suggestion_CloudHelper", "source from = ", Integer.valueOf(lqqVar.b()));
                etc.this.b(lqqVar.c(), dataCallback, elapsedRealtime);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("Suggestion_CloudHelper", "callHttpPost useCache onFailure : " + th.toString());
                etc.this.a(th, dataCallback, elapsedRealtime);
            }
        }, new esx().b(e2));
    }

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
            LogUtil.a("Suggestion_CloudHelper", "onResponse JSONException json=", str);
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
        d(j, elapsedRealtime);
    }

    public void a(Throwable th, DataCallback dataCallback, long j) {
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
        a(i, j, elapsedRealtime);
    }

    private void a(int i, long j, long j2) {
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
