package com.huawei.agconnect.https;

import okhttp3.Request;

/* loaded from: classes2.dex */
class f<HttpsRequest> {

    /* renamed from: a, reason: collision with root package name */
    private HttpsRequest f1808a;

    Request.Builder a() {
        return a.a(this.f1808a);
    }

    static <HttpsRequest> f a(HttpsRequest httpsrequest) {
        return new f(httpsrequest);
    }

    private f(HttpsRequest httpsrequest) {
        this.f1808a = httpsrequest;
    }
}
