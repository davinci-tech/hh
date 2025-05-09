package com.huawei.hms.aaid.plugin;

/* loaded from: classes4.dex */
public class ProxyCenter {
    private PushProxy proxy;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static ProxyCenter f4249a = new ProxyCenter();
    }

    private static ProxyCenter getInstance() {
        return a.f4249a;
    }

    public static PushProxy getProxy() {
        return getInstance().proxy;
    }

    public static void register(PushProxy pushProxy) {
        getInstance().proxy = pushProxy;
    }
}
