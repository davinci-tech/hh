package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class f3 implements t6 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5241a = "AbstractConnectCall";
    public static Method b;
    public q7 client;
    public v8 exchange;
    public List<n7> interceptors = new ArrayList();
    public t7 request;
    public d9 transmitter;

    public abstract void addResponseInterceptor();

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract t6 mo630clone();

    @Override // com.huawei.hms.network.embedded.t6
    public void enqueue(u6 u6Var) {
    }

    @Override // com.huawei.hms.network.embedded.t6
    public ac timeout() {
        return this.transmitter.timeout();
    }

    @Override // com.huawei.hms.network.embedded.t6
    public t7 request() {
        return this.request;
    }

    @Override // com.huawei.hms.network.embedded.t6
    public boolean isExecuted() {
        synchronized (this) {
        }
        return false;
    }

    @Override // com.huawei.hms.network.embedded.t6
    public boolean isCanceled() {
        return this.transmitter.isCanceled();
    }

    @Override // com.huawei.hms.network.embedded.t6
    public v7 execute() throws IOException {
        try {
            this.transmitter.callStart();
            this.interceptors.add(new n9(this.client));
            this.interceptors.add(new t8(this.client));
            addResponseInterceptor();
            v7 a2 = new k9(this.interceptors, this.transmitter, null, 0, this.request, this, this.client.e(), this.client.z(), this.client.D()).a(this.request);
            this.transmitter.noMoreExchanges(null);
            final Method a3 = a();
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.network.embedded.f3$$ExternalSyntheticLambda0
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    return f3.a(a3);
                }
            });
            a3.invoke(this.transmitter, this.exchange, true, true, null);
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.network.embedded.f3$$ExternalSyntheticLambda1
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    return f3.b(a3);
                }
            });
            return a2;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            throw new IOException("connect host error", th);
        }
    }

    @Override // com.huawei.hms.network.embedded.t6
    public void cancel() {
        this.transmitter.cancel();
    }

    public static /* synthetic */ Object b(Method method) {
        method.setAccessible(false);
        return null;
    }

    public static Method a() {
        Method method;
        synchronized (f3.class) {
            try {
                if (b == null) {
                    b = d9.class.getDeclaredMethod("exchangeMessageDone", v8.class, Boolean.TYPE, Boolean.TYPE, IOException.class);
                }
            } catch (NoSuchMethodException e) {
                Logger.w(f5241a, "Get Transmitter exchangeMessageDone error ", e);
            }
            method = b;
        }
        return method;
    }

    public static /* synthetic */ Object a(Method method) {
        method.setAccessible(true);
        return null;
    }

    public f3(q7 q7Var, t7 t7Var) {
        this.client = q7Var;
        this.request = t7Var;
        this.transmitter = new d9(q7Var, this);
    }

    static {
        try {
            b = d9.class.getDeclaredMethod("exchangeMessageDone", v8.class, Boolean.TYPE, Boolean.TYPE, IOException.class);
        } catch (NoSuchMethodException e) {
            Logger.w(f5241a, "Get Transmitter exchangeMessageDone error ", e);
        }
    }
}
