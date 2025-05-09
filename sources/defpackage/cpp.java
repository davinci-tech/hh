package defpackage;

import android.content.Context;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.util.ISimpleHandler;
import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class cpp {

    /* renamed from: a, reason: collision with root package name */
    private volatile ScheduledThreadPoolExecutor f11406a;
    private volatile ThreadPoolExecutor b;
    private HashMap<String, ISimpleHandler> c = new HashMap<>(8);
    private static final cpp e = new cpp();
    private static final Object d = new Object();

    private cpp() {
    }

    public static Context a() {
        return BaseApplication.e();
    }

    public static void a(Runnable runnable) {
        ThreadPoolManager.d().execute(runnable);
    }

    public static void b(Runnable runnable) {
        c().execute(runnable);
    }

    public static ScheduledFuture<?> e(Runnable runnable, long j) {
        if (runnable != null) {
            return b().schedule(runnable, j, TimeUnit.MILLISECONDS);
        }
        return null;
    }

    public static boolean d(Runnable runnable) {
        return runnable != null && b().remove(runnable);
    }

    public static void d(String str, ISimpleHandler iSimpleHandler) {
        if (iSimpleHandler == null || str == null || str.trim().length() <= 0) {
            return;
        }
        synchronized (d) {
            cpp cppVar = e;
            if (!cppVar.c.containsKey(str)) {
                cppVar.c.put(str, iSimpleHandler);
            }
        }
    }

    public static void b(String str, ISimpleHandler iSimpleHandler) {
        if (str != null) {
            synchronized (d) {
                e.c.remove(str);
            }
        }
    }

    public static void Kk_(String str, Message message) {
        ISimpleHandler iSimpleHandler;
        synchronized (d) {
            iSimpleHandler = e.c.get(str);
        }
        if (iSimpleHandler != null) {
            iSimpleHandler.handleMessage(message);
        }
    }

    private static ScheduledThreadPoolExecutor b() {
        cpp cppVar = e;
        if (cppVar.f11406a != null) {
            return cppVar.f11406a;
        }
        synchronized (cppVar) {
            if (cppVar.f11406a == null) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(15, new ThreadPoolManager.DefaultThreadFactory("envsd"));
                if (scheduledThreadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS) > 0) {
                    scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
                }
                cppVar.f11406a = scheduledThreadPoolExecutor;
            }
        }
        return cppVar.f11406a;
    }

    private static ThreadPoolExecutor c() {
        cpp cppVar = e;
        if (cppVar.b != null) {
            return cppVar.b;
        }
        synchronized (cppVar) {
            if (cppVar.b == null) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 30L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new ThreadPoolManager.DefaultThreadFactory("envfx"));
                threadPoolExecutor.allowCoreThreadTimeOut(true);
                cppVar.b = threadPoolExecutor;
            }
        }
        return cppVar.b;
    }
}
