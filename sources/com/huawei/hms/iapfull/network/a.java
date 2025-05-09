package com.huawei.hms.iapfull.network;

import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.iapfull.network.model.BaseResponse;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
public class a<T extends BaseResponse> implements ResultCallback<T> {

    /* renamed from: a, reason: collision with root package name */
    private e<T> f4731a;

    @Override // com.huawei.hms.framework.network.restclient.ResultCallback
    public void onResponse(Response<T> response) {
        if (response.getBody() != null) {
            this.f4731a.a(response.getBody(), response.getHeaders());
            return;
        }
        y0.a("Callback", "Callback onResponse response body is null");
        this.f4731a.onFailure(new Throwable("code:" + response.getCode()));
    }

    @Override // com.huawei.hms.framework.network.restclient.ResultCallback
    public void onFailure(Throwable th) {
        y0.a("Callback", "throwable.getMessage: " + th.getMessage());
        this.f4731a.onFailure(th);
    }

    public a(e<T> eVar) {
        this.f4731a = eVar;
    }
}
