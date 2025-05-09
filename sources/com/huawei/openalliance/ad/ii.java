package com.huawei.openalliance.ad;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class ii extends ProxySelector {

    /* renamed from: a, reason: collision with root package name */
    private static final List<Proxy> f6942a = Collections.singletonList(Proxy.NO_PROXY);
    private final ProxySelector b;
    private final String c;
    private final int d;

    @Override // java.net.ProxySelector
    public List<Proxy> select(URI uri) {
        return (this.c.equals(uri.getHost()) && this.d == uri.getPort()) ? f6942a : this.b.select(uri);
    }

    @Override // java.net.ProxySelector
    public void connectFailed(URI uri, SocketAddress socketAddress, IOException iOException) {
        this.b.connectFailed(uri, socketAddress, iOException);
    }

    public static void a(String str, int i) {
        ProxySelector.setDefault(new ii(ProxySelector.getDefault(), str, i));
    }

    private ii(ProxySelector proxySelector, String str, int i) {
        if (proxySelector == null || str == null) {
            throw null;
        }
        this.b = proxySelector;
        this.c = str;
        this.d = i;
    }
}
