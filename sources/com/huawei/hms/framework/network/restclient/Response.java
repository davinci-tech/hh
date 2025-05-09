package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.framework.common.Logger;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Deprecated
/* loaded from: classes.dex */
public class Response<T> {
    private static final String TAG = "Response";
    private com.huawei.hms.network.httpclient.Response<T> rawResponse;

    public com.huawei.hms.network.httpclient.Response<T> getRawResponse() {
        return this.rawResponse;
    }

    public int getCode() {
        return this.rawResponse.getCode();
    }

    public String getMessage() {
        return this.rawResponse.getMessage();
    }

    public boolean isOK() {
        return this.rawResponse.isOK();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    public T getBody() {
        return this.rawResponse.getBody();
    }

    public URL getUrl() {
        try {
            return new URL(this.rawResponse.getUrl());
        } catch (MalformedURLException unused) {
            Logger.w(TAG, "getUrl catch a MalformedURLException");
            return null;
        }
    }

    public byte[] getErrorBody() {
        try {
            if (this.rawResponse.getErrorBody() != null) {
                return this.rawResponse.getErrorBody().bytes();
            }
        } catch (IOException unused) {
            Logger.w(TAG, "getErrorBody IOException");
        }
        return new byte[0];
    }

    public Headers getHeaders() {
        return Headers.of(Headers.toArray(this.rawResponse.getHeaders()));
    }

    public String toString() {
        return super.toString();
    }

    public Response(com.huawei.hms.network.httpclient.Response<T> response) {
        this.rawResponse = response;
    }
}
