package com.huawei.openalliance.ad.net.http;

import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes5.dex */
public interface i {
    HttpConnection a(Request request);

    OkHttpClient.Builder a(OkHttpClient.Builder builder);
}
