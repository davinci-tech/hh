package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes7.dex */
public final class e extends DefaultRedirectHandler {

    /* renamed from: a, reason: collision with root package name */
    public int f821a;
    public final /* synthetic */ d b;

    @Override // org.apache.http.impl.client.DefaultRedirectHandler, org.apache.http.client.RedirectHandler
    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        int statusCode;
        this.f821a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f821a >= 5 || !((statusCode = httpResponse.getStatusLine().getStatusCode()) == 301 || statusCode == 302)) {
            return isRedirectRequested;
        }
        return true;
    }

    public e(d dVar) {
        this.b = dVar;
    }
}
