package health.compact.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class AsyncInvocationHandler implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    private static Executor f13100a;
    private Object c;

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        f13100a = newSingleThreadExecutor;
        newSingleThreadExecutor.execute(new Runnable() { // from class: health.compact.a.AsyncInvocationHandler.4
            @Override // java.lang.Runnable
            public void run() {
                Thread.currentThread().setName("Daemon_UIWorkThread");
            }
        });
    }

    public AsyncInvocationHandler(Object obj) {
        this.c = null;
        if (obj == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", "target is null");
        } else {
            this.c = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object b(Method method, Object[] objArr) {
        Object[] objArr2 = new Object[1];
        try {
            objArr2[0] = method.invoke(this.c, objArr);
        } catch (IllegalAccessException e) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", e.getMessage());
        } catch (InvocationTargetException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", e2.getMessage());
        }
        return objArr2[0];
    }

    private Object a(final Method method, final Object[] objArr) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_AsyncInvocationHandler", "async method");
        f13100a.execute(new Runnable() { // from class: health.compact.a.AsyncInvocationHandler.5
            @Override // java.lang.Runnable
            public void run() {
                AsyncInvocationHandler.this.b(method, objArr);
            }
        });
        return null;
    }

    private Object e(final Method method, final Object[] objArr) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_AsyncInvocationHandler", "sync method");
        final Object[] objArr2 = new Object[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        f13100a.execute(new Runnable() { // from class: health.compact.a.AsyncInvocationHandler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        try {
                            objArr2[0] = method.invoke(AsyncInvocationHandler.this.c, objArr);
                        } catch (IllegalAccessException e) {
                            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", e.getMessage());
                            countDownLatch.countDown();
                        }
                    } catch (InvocationTargetException e2) {
                        com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", e2.getMessage());
                        countDownLatch.countDown();
                    }
                    countDownLatch.countDown();
                } catch (Throwable th) {
                    countDownLatch.countDown();
                    throw th;
                }
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_AsyncInvocationHandler", "execute latch timeout class:", method.getClass().getName(), " method:", method.getName());
            }
        } catch (InterruptedException e) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", e.getMessage());
        }
        return objArr2[0];
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method == null || obj == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AsyncInvocationHandler", "method or proxy is null");
            return null;
        }
        if ("Daemon_UIWorkThread".equals(Thread.currentThread().getName())) {
            com.huawei.hwlogsmodel.LogUtil.c("Step_AsyncInvocationHandler", "same method invoke");
            return b(method, objArr);
        }
        if (method.getReturnType() == Void.TYPE) {
            return a(method, objArr);
        }
        return e(method, objArr);
    }
}
