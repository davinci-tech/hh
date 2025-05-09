package com.huawei.hms.iapfull.network;

import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;

/* loaded from: classes4.dex */
public class g<T extends WebBaseResponse> implements ResultCallback<T> {

    /* renamed from: a, reason: collision with root package name */
    private h<T> f4735a;

    @Override // com.huawei.hms.framework.network.restclient.ResultCallback
    public void onResponse(Response<T> response) {
        if (response.getBody() != null) {
            this.f4735a.a(response.getBody());
            return;
        }
        this.f4735a.onFailure(new Throwable("code:" + response.getCode()));
    }

    @Override // com.huawei.hms.framework.network.restclient.ResultCallback
    public void onFailure(Throwable th) {
        this.f4735a.onFailure(th);
    }

    public g(h<T> hVar) {
        this.f4735a = hVar;
    }
}
