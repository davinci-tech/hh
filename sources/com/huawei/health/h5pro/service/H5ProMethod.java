package com.huawei.health.h5pro.service;

import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes3.dex */
public class H5ProMethod {

    /* renamed from: a, reason: collision with root package name */
    public final String f2451a;
    public H5ProCallback d;
    public final Method e;

    public boolean hasCallback() {
        return this.d != null;
    }

    public String getScope() {
        return this.f2451a;
    }

    public Method getMethod() {
        return this.e;
    }

    public <H5PS, P> Object call(H5PS h5ps, P... pArr) {
        return (pArr == null || pArr.length == 0) ? this.e.invoke(h5ps, new Object[0]) : this.e.invoke(h5ps, pArr);
    }

    public <H5PS, P> void asyncCall(H5PS h5ps, H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, P... pArr) {
        H5ProCallback h5ProCallback = this.d;
        if (h5ProCallback == null) {
            LogUtil.w("H5PRO_H5ProMethod", "asyncCall: mH5ProCallback is null");
            return;
        }
        Object createImpl = h5ProCallback.createImpl(h5ProJsCbkInvoker, j);
        if (pArr == null || pArr.length == 0) {
            this.e.invoke(h5ps, createImpl);
            return;
        }
        Object[] objArr = new Object[pArr.length + 1];
        System.arraycopy(pArr, 0, objArr, 0, pArr.length);
        objArr[pArr.length] = createImpl;
        this.e.invoke(h5ps, objArr);
    }

    private void e() {
        Method method = this.e;
        if (method == null) {
            LogUtil.w("H5PRO_H5ProMethod", "initH5ProCallback: mMethod is null");
            return;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0) {
            return;
        }
        Class<?> cls = parameterTypes[parameterTypes.length - 1];
        if (cls.isInterface()) {
            if (!cls.isAnnotationPresent(com.huawei.health.h5pro.service.anotation.H5ProCallback.class)) {
                LogUtil.w("H5PRO_H5ProMethod", String.format(Locale.ENGLISH, "%s: If callback is required, the last parameter must be of the interface type and marked as H5ProCallback", this.e.getName()));
                return;
            }
            try {
                cls.getMethod("onSuccess", Object.class);
                try {
                    cls.getMethod("onFailure", Integer.TYPE, String.class);
                    this.d = new H5ProCallback(cls);
                } catch (NoSuchMethodException unused) {
                    LogUtil.e("H5PRO_H5ProMethod", String.format(Locale.ENGLISH, "%s: Method %s(int,Object) not found in %s", this.e.getName(), "onFailure", cls.getName()));
                } catch (SecurityException e) {
                    LogUtil.e("H5PRO_H5ProMethod", String.format(Locale.ENGLISH, "%s: %s(int,Object) SecurityException-> %s", this.e.getName(), "onFailure", e.getMessage()));
                }
            } catch (NoSuchMethodException unused2) {
                LogUtil.e("H5PRO_H5ProMethod", String.format(Locale.ENGLISH, "%s: Method %s(Object) not found in %s", this.e.getName(), "onSuccess", cls.getName()));
            } catch (SecurityException e2) {
                LogUtil.e("H5PRO_H5ProMethod", String.format(Locale.ENGLISH, "%s: %s(Object) SecurityException-> %s", this.e.getName(), "onSuccess", e2.getMessage()));
            }
        }
    }

    public H5ProMethod(Method method, String str) {
        this.e = method;
        this.f2451a = str;
        e();
    }
}
