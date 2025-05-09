package com.huawei.watchface;

import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.videoedit.sysc.Consumer;
import com.huawei.watchface.videoedit.sysc.Function;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class es extends Interceptor {
    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        final Response<ResponseBody> proceed = chain.proceed(a(chain.request()));
        Optional.ofNullable(chain.requestFinishedInfo()).flatMap(new Function() { // from class: com.huawei.watchface.es$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                Optional a2;
                a2 = es.a((RequestFinishedInfo) obj);
                return a2;
            }
        }).ifPresent(new Consumer() { // from class: com.huawei.watchface.es$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.videoedit.sysc.Consumer
            public final void accept(Object obj) {
                es.a(Response.this, (RequestFinishedInfo.Metrics) obj);
            }
        });
        return proceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Optional a(RequestFinishedInfo requestFinishedInfo) {
        return Optional.ofNullable(requestFinishedInfo.getMetrics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Response response, RequestFinishedInfo.Metrics metrics) {
        String successIp = metrics.getSuccessIp();
        HwLog.i("CommonParamsInterceptor", "successIp ip type:" + dj.b(successIp));
        FlavorConfig.safeHwLog("CommonParamsInterceptor", "getSuccessIp ip =" + successIp);
        if (response == null || response.getHeaders() == null) {
            return;
        }
        response.getHeaders().put("server_ip", new ArrayList(Collections.singletonList(successIp)));
        response.getHeaders().put("request_engine", new ArrayList(Collections.singletonList("network_client")));
    }

    private Request a(Request request) {
        long j;
        try {
            j = request.getBody().contentLength();
        } catch (Exception e) {
            HwLog.e("CommonParamsInterceptor", "addCommonParams request contentLength error: " + HwLog.printException(e));
            j = 0L;
        }
        Map<String, List<String>> headers = request.getHeaders();
        Request.Builder newBuilder = request.newBuilder();
        if (!dg.a(headers, "Content-Type")) {
            newBuilder.addHeader("Content-Type", "application/json; charset=UTF-8");
        }
        if (j > 0) {
            HwLog.i("CommonParamsInterceptor", "requestLength =" + j);
            newBuilder.addHeader("Content-Length", String.valueOf(j));
        }
        Map<String, String> k = WatchFaceHttpUtil.k();
        if (!dg.isEmpty(k)) {
            for (Map.Entry<String, String> entry : k.entrySet()) {
                newBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (newBuilder != null) {
            return newBuilder.build();
        }
        return null;
    }
}
