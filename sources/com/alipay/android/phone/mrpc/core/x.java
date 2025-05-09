package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* loaded from: classes7.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    public g f834a;
    public z b = new z(this);

    public final <T> T a(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new y(this.f834a, cls, this.b));
    }

    public final g a() {
        return this.f834a;
    }

    public x(g gVar) {
        this.f834a = gVar;
    }
}
