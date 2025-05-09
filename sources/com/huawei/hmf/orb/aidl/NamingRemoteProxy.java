package com.huawei.hmf.orb.aidl;

import android.util.Log;
import com.huawei.hmf.annotation.NamedMethod;
import com.huawei.hmf.orb.RemoteInvoker;
import com.huawei.hmf.orb.RemoteProxy;
import com.huawei.hmf.orb.exception.InvocationException;
import com.huawei.hmf.services.codec.TypeToken;
import com.huawei.hmf.services.interception.Signature;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes9.dex */
public class NamingRemoteProxy extends RemoteProxy implements InvocationHandler {
    private static final String TAG = "NamingRemoteProxy";
    private Object mObject;
    private String mUri;

    public static <T> T create(RemoteInvoker remoteInvoker, T t, Long l) {
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new NamingRemoteProxy(remoteInvoker, l, t));
    }

    public static <T> T create(RemoteInvoker remoteInvoker, Class<?>[] clsArr, Long l) {
        NamingRemoteProxy namingRemoteProxy = new NamingRemoteProxy(remoteInvoker, l, (Object) null);
        return (T) Proxy.newProxyInstance(namingRemoteProxy.getClass().getClassLoader(), clsArr, namingRemoteProxy);
    }

    public static <T> T create(RemoteInvoker remoteInvoker, Class<?>[] clsArr, String str, Object... objArr) {
        NamingRemoteProxy namingRemoteProxy = new NamingRemoteProxy(remoteInvoker, str, objArr);
        return (T) Proxy.newProxyInstance(namingRemoteProxy.getClass().getClassLoader(), clsArr, namingRemoteProxy);
    }

    private NamingRemoteProxy(RemoteInvoker remoteInvoker, String str, Object... objArr) {
        super(remoteInvoker);
        this.mUri = str;
        setSequence(((Long) send(str, Signature.ConstructorMethod, new TypeToken<Long>() { // from class: com.huawei.hmf.orb.aidl.NamingRemoteProxy.1
        }, objArr)).longValue());
    }

    private NamingRemoteProxy(RemoteInvoker remoteInvoker, Long l, Object obj) {
        super(remoteInvoker);
        this.mUri = null;
        setSequence(l.longValue());
        this.mObject = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Object obj2 = this.mObject;
        Object invoke = obj2 != null ? method.invoke(obj2, objArr) : null;
        NamedMethod namedMethod = (NamedMethod) method.getAnnotation(NamedMethod.class);
        if (namedMethod != null) {
            return send(this.mUri, namedMethod.value(), new TypeToken(method.getGenericReturnType()), objArr);
        }
        if (this.mObject != null) {
            return invoke;
        }
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls : obj.getClass().getInterfaces()) {
            sb.append(cls.getName());
            sb.append(",");
        }
        if (method.getReturnType().isPrimitive()) {
            throw new InvocationException(sb.toString() + " can not found NamedMethod `" + method.getName() + "`");
        }
        Log.e(TAG, sb.toString() + " can not found NamedMethod `" + method.getName() + "`");
        return invoke;
    }
}
