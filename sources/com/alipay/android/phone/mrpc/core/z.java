package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<Object> f836a = new ThreadLocal<>();
    public static final ThreadLocal<Map<String, Object>> b = new ThreadLocal<>();
    public byte c = 0;
    public AtomicInteger d = new AtomicInteger();
    public x e;

    public final Object a(Method method, Object[] objArr) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        boolean z = method.getAnnotation(ResetCookie.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        ThreadLocal<Object> threadLocal = f836a;
        threadLocal.set(null);
        ThreadLocal<Map<String, Object>> threadLocal2 = b;
        threadLocal2.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String value = operationType.value();
        int incrementAndGet = this.d.incrementAndGet();
        try {
            if (this.c == 0) {
                com.alipay.android.phone.mrpc.core.a.e eVar = new com.alipay.android.phone.mrpc.core.a.e(incrementAndGet, value, objArr);
                if (threadLocal2.get() != null) {
                    eVar.a(threadLocal2.get());
                }
                byte[] bArr = (byte[]) new j(this.e.a(), method, incrementAndGet, value, eVar.a(), z).a();
                threadLocal2.set(null);
                Object a2 = new com.alipay.android.phone.mrpc.core.a.d(genericReturnType, bArr).a();
                if (genericReturnType != Void.TYPE) {
                    threadLocal.set(a2);
                }
            }
            return threadLocal.get();
        } catch (RpcException e) {
            e.setOperationType(value);
            throw e;
        }
    }

    public z(x xVar) {
        this.e = xVar;
    }
}
