package com.huawei.maps.offlinedata.network;

import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.maps.offlinedata.network.d;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class b<Result extends d> extends Callback<Result> {
    public abstract void a(int i, d dVar, String str);

    public abstract void a(Result result);

    @Override // com.huawei.hms.network.httpclient.Callback
    public void onResponse(Submit<Result> submit, Response<Result> response) {
        if (response.isOK()) {
            a((b<Result>) response.getBody());
        } else {
            a(response.getCode(), response.getBody(), "data.getMessage()");
        }
    }

    @Override // com.huawei.hms.network.httpclient.Callback
    public void onFailure(Submit<Result> submit, Throwable th) {
        a(a(th), a(), th.getMessage());
    }

    protected d a() {
        d dVar = new d();
        dVar.setReturnCode("-1000");
        return dVar;
    }

    private int a(Throwable th) {
        if (IOException.class.equals(th.getClass())) {
            return -2;
        }
        return NullPointerException.class.equals(th.getClass()) ? -3 : -1;
    }
}
