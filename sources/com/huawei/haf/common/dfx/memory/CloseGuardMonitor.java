package com.huawei.haf.common.dfx.memory;

import com.huawei.haf.common.dfx.DfxUtils;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
final class CloseGuardMonitor {
    private static CloseLeakDetector c;

    private CloseGuardMonitor() {
    }

    static void a() {
        if (c != null) {
            return;
        }
        try {
            Class<?> c2 = c();
            e(c2, true);
            c = d(c2, null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.c("HAF_CloseGuard", "install monitor CloseGuard fail, ex=", LogUtil.a(e));
        }
    }

    static void e() {
        CloseLeakDetector closeLeakDetector = c;
        if (closeLeakDetector == null) {
            return;
        }
        c = null;
        try {
            d(c(), closeLeakDetector.f2087a);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.c("HAF_CloseGuard", "uninstall monitor CloseGuard fail, ex=", LogUtil.a(e));
        }
    }

    private static Class<?> c() throws ClassNotFoundException {
        return ReflectionUtils.d("dalvik.system.CloseGuard");
    }

    private static void e(Class<?> cls, boolean z) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflectionUtils.b(cls, "setEnabled", Boolean.TYPE).invoke(null, Boolean.valueOf(z));
    }

    private static CloseLeakDetector d(Class<?> cls, Object obj) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> d = ReflectionUtils.d("dalvik.system.CloseGuard$Reporter");
        Method b = ReflectionUtils.b(cls, "setReporter", d);
        if (obj == null) {
            CloseLeakDetector closeLeakDetector = new CloseLeakDetector(ReflectionUtils.c(cls, "getReporter"));
            b.invoke(null, Proxy.newProxyInstance(d.getClassLoader(), new Class[]{d}, closeLeakDetector));
            return closeLeakDetector;
        }
        b.invoke(null, obj);
        return null;
    }

    static class CloseLeakDetector implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        private final Object f2087a;

        CloseLeakDetector(Object obj) {
            this.f2087a = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr != null && objArr.length > 1 && "report".equals(method.getName()) && (objArr[1] instanceof Throwable)) {
                DfxMonitorCenter.b(new CloseGuardLeakRunnable(String.valueOf(objArr[0]), (Throwable) objArr[1]));
            }
            return method.invoke(this.f2087a, objArr);
        }
    }

    static class CloseGuardLeakRunnable implements Runnable {
        private final Throwable c;
        private final String e;

        CloseGuardLeakRunnable(String str, Throwable th) {
            this.e = str;
            this.c = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            MemoryMonitor.b("CloseGuard", this.e, DfxUtils.d(Thread.currentThread(), this.c), System.currentTimeMillis());
        }
    }
}
