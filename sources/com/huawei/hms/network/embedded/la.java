package com.huawei.hms.network.embedded;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class la<T> {

    /* renamed from: a, reason: collision with root package name */
    public final Class<?> f5370a;
    public final String b;
    public final Class[] c;

    public Object d(T t, Object... objArr) {
        try {
            return a(t, objArr);
        } catch (NullPointerException e) {
            if ("ssl == null".equals(e.getMessage())) {
                return null;
            }
            throw e;
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object c(T t, Object... objArr) {
        try {
            return b(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object b(T t, Object... objArr) throws InvocationTargetException {
        Method a2 = a(t.getClass());
        if (a2 == null) {
            return null;
        }
        try {
            return a2.invoke(t, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public boolean a(T t) {
        return a(t.getClass()) != null;
    }

    public Object a(T t, Object... objArr) throws InvocationTargetException {
        Method a2 = a(t.getClass());
        if (a2 == null) {
            throw new AssertionError("Method " + this.b + " not supported for object " + t);
        }
        try {
            return a2.invoke(t, objArr);
        } catch (IllegalAccessException e) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + a2);
            assertionError.initCause(e);
            throw assertionError;
        }
    }

    public static Method a(Class<?> cls, String str, Class[] clsArr) {
        Method method;
        try {
            method = cls.getMethod(str, clsArr);
            try {
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
        }
        if ((method.getModifiers() & 1) == 0) {
            return null;
        }
        return method;
    }

    private Method a(Class<?> cls) {
        Method a2;
        Class<?> cls2;
        String str = this.b;
        if (str == null || !((a2 = a(cls, str, this.c)) == null || (cls2 = this.f5370a) == null || cls2.isAssignableFrom(a2.getReturnType()))) {
            return null;
        }
        return a2;
    }

    public la(Class<?> cls, String str, Class... clsArr) {
        this.f5370a = cls;
        this.b = str;
        this.c = clsArr;
    }
}
