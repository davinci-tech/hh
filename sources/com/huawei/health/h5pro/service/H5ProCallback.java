package com.huawei.health.h5pro.service;

import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes3.dex */
public class H5ProCallback {

    /* renamed from: a, reason: collision with root package name */
    public Class<?> f2450a;

    public Object createImpl(final H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, final long j) {
        return Proxy.newProxyInstance(this.f2450a.getClassLoader(), new Class[]{this.f2450a}, new InvocationHandler() { // from class: com.huawei.health.h5pro.service.H5ProCallback.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                if (name.equals("onSuccess")) {
                    h5ProJsCbkInvoker.onSuccess(objArr[0], j);
                    return null;
                }
                if (name.equals("onFailure")) {
                    h5ProJsCbkInvoker.onFailure(((Integer) objArr[0]).intValue(), (String) objArr[1], j);
                    return null;
                }
                if ("onComplete".equals(name)) {
                    h5ProJsCbkInvoker.onComplete(((Integer) objArr[0]).intValue(), (String) objArr[1], j);
                    return null;
                }
                throw new RuntimeException("invalid method: " + name);
            }
        });
    }

    public H5ProCallback(Class<?> cls) {
        this.f2450a = cls;
    }
}
