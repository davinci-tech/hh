package com.huawei.hmf.services.ui.internal;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.services.ui.PojoObject;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class PojoGenerator<T> implements IMessageEntity, Cloneable {
    private final transient Class<T> mInterface;
    private Map<String, Object> mValues = new HashMap();
    private transient InvocationHandler handler = new InvocationHandler() { // from class: com.huawei.hmf.services.ui.internal.PojoGenerator.1
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String substring;
            String name = method.getName();
            if (name.startsWith("set")) {
                PojoGenerator.this.mValues.put(name.substring(3), objArr[0]);
                return null;
            }
            if (name.startsWith("get")) {
                substring = name.substring(3);
            } else {
                if (!name.startsWith("is")) {
                    return null;
                }
                substring = name.substring(2);
            }
            Object obj2 = PojoGenerator.this.mValues.get(substring);
            return (obj2 == null && method.getReturnType().isPrimitive()) ? PojoGenerator.getReturnValue(method) : obj2;
        }
    };

    static {
        if (Boolean.FALSE.booleanValue()) {
            new Throwable().printStackTrace((PrintWriter) null);
        }
    }

    public static String resolveName(String str) {
        if (str.startsWith("set")) {
            return str.substring(3);
        }
        if (str.startsWith("get")) {
            return str.substring(3);
        }
        return null;
    }

    public PojoGenerator(Class<T> cls) {
        if (!PojoObject.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(cls + " must extends from PojoObject.");
        }
        this.mInterface = cls;
    }

    public T get() {
        return (T) Proxy.newProxyInstance(this.mInterface.getClassLoader(), new Class[]{this.mInterface}, this.handler);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public PojoGenerator<T> m599clone() {
        PojoGenerator<T> pojoGenerator = new PojoGenerator<>(this.mInterface);
        pojoGenerator.mValues.putAll(this.mValues);
        return pojoGenerator;
    }

    public Class<T> getInterface() {
        return this.mInterface;
    }

    public void setValue(String str, Object obj) {
        this.mValues.put(str, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getReturnValue(Method method) {
        Class<?> returnType = method.getReturnType();
        if (returnType == Integer.TYPE) {
            return 0;
        }
        if (returnType == Long.TYPE) {
            return 0L;
        }
        if (returnType == Short.TYPE) {
            return (short) 0;
        }
        if (returnType == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (returnType == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (returnType == Boolean.TYPE) {
            return false;
        }
        if (returnType == Character.TYPE) {
            return (char) 0;
        }
        if (returnType == Byte.TYPE) {
            return (byte) 0;
        }
        if (returnType != Void.TYPE) {
            return null;
        }
        throw new TypeNotPresentException("Getter method '" + method.getName() + "' cannot return a value with void type", null);
    }
}
