package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public final class y implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    public g f835a;
    public Class<?> b;
    public z c;

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.c.a(method, objArr);
    }

    public y(g gVar, Class<?> cls, z zVar) {
        this.f835a = gVar;
        this.b = cls;
        this.c = zVar;
    }
}
