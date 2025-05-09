package com.huawei.agconnect.https;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes8.dex */
class g implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private int f1809a;
    private int b;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed;
        int i;
        Request request = chain.request();
        while (true) {
            proceed = chain.proceed(request);
            if (proceed.isSuccessful() || (i = this.b) >= this.f1809a) {
                break;
            }
            this.b = i + 1;
        }
        return proceed;
    }

    g(int i) {
        this.f1809a = i;
    }
}
