package com.huawei.agconnect.apms;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

/* loaded from: classes7.dex */
public class uts<T> implements ResponseHandler<T> {
    public final ResponseHandler<T> abc;
    public final wxy bcd;

    public uts(ResponseHandler<T> responseHandler, wxy wxyVar) {
        this.abc = responseHandler;
        this.bcd = wxyVar;
    }

    @Override // org.apache.http.client.ResponseHandler
    public T handleResponse(HttpResponse httpResponse) throws IOException {
        yxw.abc(httpResponse, this.bcd);
        return this.abc.handleResponse(httpResponse);
    }
}
