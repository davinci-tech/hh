package com.huawei.hms.framework.network.restclient.hianalytics;

import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestTask;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class RCEventListener {
    public static final int ALL_LISTENER_FINISH = 3;
    public static final int NET_LIB_LISTENER_FINISH = 1;
    public static final RCEventListener NONE = new RCEventListener() { // from class: com.huawei.hms.framework.network.restclient.hianalytics.RCEventListener.1
    };
    public static final int RCEVENT_LISTENER_FINISH = 2;

    /* loaded from: classes.dex */
    public interface Factory {
        RCEventListener create(Submit submit);
    }

    public void acquireRequestEnd(Request request) {
    }

    public void acquireRequestStart() {
    }

    public void callEnd(Response response) {
    }

    public void callFailed(Exception exc) {
    }

    public void callFinishAtNetLib() {
    }

    public void callStart() {
    }

    public void cancel() {
    }

    public void convertGrsEnd(String str) {
    }

    public void convertGrsStart(String str) {
    }

    public void retryInterceptorEnd(Response response) {
    }

    public void retryInterceptorFailed(IOException iOException) {
    }

    public void retryInterceptorStart(Request request, RequestTask requestTask) {
    }

    public static Factory factory(RCEventListener rCEventListener) {
        return new Factory() { // from class: com.huawei.hms.framework.network.restclient.hianalytics.RCEventListener.2
            @Override // com.huawei.hms.framework.network.restclient.hianalytics.RCEventListener.Factory
            public RCEventListener create(Submit submit) {
                return RCEventListener.this;
            }
        };
    }
}
