package com.huawei.appgallery.agd.api;

import android.content.Context;
import com.huawei.appgallery.agd.internal.support.log.AgdLog;
import com.huawei.appgallery.agd.internalapi.CrossClientApi;
import com.huawei.appgallery.agd.internalapi.ICrossClient;
import com.huawei.appgallery.coreservice.api.PendingCall;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCResponse;
import com.huawei.appmarket.framework.coreservice.Status;

/* loaded from: classes2.dex */
public class PendingResult<C extends BaseIPCRequest, R extends BaseIPCResponse> extends PendingCall<C, R> {
    public static final int RESOLUTION_AUTOFINISH = 1;
    public static final int RESOLUTION_DONOT_AUTOFINISH = 0;
    public static final String RESOLUTION_EXTRA_AUTOFINISH = "agd.extra.autofinish";
    public static final String RESOLUTION_EXTRA_BUNDLE = "agd.extra.bundle";
    public static final String RESOLUTION_EXTRA_BUNDLE_BINDER = "agd.extra.bundle.binder";
    public static final String RESOLUTION_EXTRA_BUNDLE_REQUESTCODE = "agd.extra.bundle.requestcode";

    /* renamed from: a, reason: collision with root package name */
    private C f1863a;
    private Context b;
    private ICrossClient c;
    private boolean d;
    private final Object e;

    public void setResultCallback(final ResultCallback<R> resultCallback) {
        AgdLog.LOG.i("PendingResult", "setResultCallback");
        if (!this.d) {
            resultCallback.getClass();
            super.setCallback(new PendingCall.Callback() { // from class: com.huawei.appgallery.agd.api.PendingResult$$ExternalSyntheticLambda0
                @Override // com.huawei.appgallery.coreservice.api.PendingCall.Callback
                public final void onReceiveStatus(Status status) {
                    ResultCallback.this.onResult(status);
                }
            });
        } else {
            AgdLog.LOG.w("PendingResult", "crossClientTaskProcess");
            this.mResult = this.c.crossClientTaskProcess(this.b, this.f1863a);
            resultCallback.onResult(getResult());
        }
    }

    @Override // com.huawei.appgallery.coreservice.api.PendingCall
    public void awaitOnAnyThread() {
        if (!this.d) {
            super.awaitOnAnyThread();
            return;
        }
        if (this.api.get() != null) {
            setResult(this.mResult);
            return;
        }
        AgdLog.LOG.e("PendingResult", "api is null");
        synchronized (this.e) {
            this.mResult.setStatusCode(12);
        }
    }

    public PendingResult(AgdApiClient agdApiClient, C c) {
        super(agdApiClient, c);
        this.e = new Object();
        Context context = agdApiClient.getContext();
        this.b = context;
        this.f1863a = c;
        boolean needCrossClient = CrossClientApi.needCrossClient(context);
        this.d = needCrossClient;
        if (needCrossClient) {
            this.c = CrossClientApi.getCrossClient();
        }
    }
}
