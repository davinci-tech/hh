package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.IOException;

/* loaded from: classes9.dex */
public class w0 extends Interceptor {
    public static final String b = "CallServerInterceptor";

    /* renamed from: a, reason: collision with root package name */
    public WebSocket f5552a;

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        if (!(chain instanceof h1.b)) {
            throw new ClassCastException("the classType has error!,please use SafeApi.SafeChain");
        }
        h1.b bVar = (h1.b) chain;
        z2 rCEventListener = bVar.getRCEventListener();
        h1.d a2 = a(bVar.request());
        h1.d traceRequestNetworkKitOutEvent = g4.getInstance().traceRequestNetworkKitOutEvent(a2);
        rCEventListener.rcNetworkInterceptorReqEnd(a2);
        if (!InitReport.isHasConnectNet()) {
            InitReport.enableConnectNet();
            NetworkUtil.updateCurrentNetworkType();
        }
        h1.f<ResponseBody> execute = bVar.getRequestTask().execute(traceRequestNetworkKitOutEvent, this.f5552a);
        rCEventListener.rcNetworkInterceptorResStart();
        g4.getInstance().traceResponseNetworkKitInEvent(rCEventListener);
        return execute;
    }

    private h1.d a(h1.d dVar) {
        for (String str : dVar.getHeaders().keySet()) {
            if (str != null && StringUtils.toLowerCase(str).equals(FeedbackWebConstants.USER_AGENT)) {
                return dVar;
            }
        }
        return new h1.d(dVar.newBuilder().addHeader("User-Agent", j1.getUserAgent(ContextHolder.getAppContext())).build());
    }

    public w0(WebSocket webSocket) {
        this.f5552a = webSocket;
    }
}
