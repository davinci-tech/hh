package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes7.dex */
public final class f implements ConnectionKeepAliveStrategy {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f822a;

    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000L;
    }

    public f(d dVar) {
        this.f822a = dVar;
    }
}
