package com.huawei.hwcloudjs.core.a;

import com.huawei.hwcloudjs.core.JSRequest;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.f.d;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private Method f6195a;
    private Class<?> b;

    public Class<?> b() {
        return this.b;
    }

    public void a(Object obj, JsCallback jsCallback) {
        String str;
        try {
            Object newInstance = this.f6195a.getDeclaringClass().newInstance();
            if (newInstance instanceof JSRequest) {
                ((JSRequest) newInstance).execute(this.f6195a, obj, jsCallback);
            } else {
                d.b("CallObject", "clazz.newInstance() is not JSRequest type", true);
            }
        } catch (IllegalAccessException unused) {
            str = "call IllegalAccessException";
            d.b("CallObject", str, true);
        } catch (InstantiationException unused2) {
            str = "call InstantiationException";
            d.b("CallObject", str, true);
        }
    }

    public Method a() {
        return this.f6195a;
    }

    public a(Method method) {
        Class<?> cls;
        this.b = null;
        this.f6195a = method;
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length <= 0 || JsCallback.class == (cls = parameterTypes[0])) {
            return;
        }
        this.b = cls;
    }
}
