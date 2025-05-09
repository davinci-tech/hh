package com.huawei.hms.framework.network.restclient.proxy.new2old;

import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;

/* loaded from: classes4.dex */
public class DynamicResponseSubmit implements Submit {
    private com.huawei.hms.network.httpclient.Submit<ResponseBody> submit;

    public void setSubmit(com.huawei.hms.network.httpclient.Submit<ResponseBody> submit) {
        this.submit = submit;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public Request request() throws IOException {
        Request request2Old = ProxyRequest.request2Old(this.submit.request());
        request2Old.setRequestFinishedInfo(new RequestFinishedInfo(this.submit.getRequestFinishedInfo()));
        return request2Old;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public Response execute() throws IOException {
        return ProxyResponse.response2Old(this.submit.execute());
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public void enqueue(final Callback callback) {
        this.submit.enqueue(new com.huawei.hms.network.httpclient.Callback<ResponseBody>() { // from class: com.huawei.hms.framework.network.restclient.proxy.new2old.DynamicResponseSubmit.1
            @Override // com.huawei.hms.network.httpclient.Callback
            public void onResponse(com.huawei.hms.network.httpclient.Submit<ResponseBody> submit, com.huawei.hms.network.httpclient.Response<ResponseBody> response) throws IOException {
                callback.onResponse(DynamicResponseSubmit.this, ProxyResponse.response2Old(response));
            }

            @Override // com.huawei.hms.network.httpclient.Callback
            public void onFailure(com.huawei.hms.network.httpclient.Submit<ResponseBody> submit, Throwable th) {
                callback.onFailure(DynamicResponseSubmit.this, th);
            }
        });
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public void cancel() {
        this.submit.cancel();
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public boolean isExecuted() {
        return this.submit.isExecuted();
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    public boolean isCanceled() {
        return this.submit.isCanceled();
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Submit
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Submit m603clone() {
        DynamicResponseSubmit dynamicResponseSubmit = new DynamicResponseSubmit();
        dynamicResponseSubmit.setSubmit(this.submit.mo631clone());
        return dynamicResponseSubmit;
    }
}
