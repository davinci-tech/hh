package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.EventLoopImplBase;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\u0014\u0010 \u001a\u00020\u001e2\n\u0010!\u001a\u00060\u0002j\u0002`\u0003H\u0016J\r\u0010\"\u001a\u00020\u001eH\u0000¢\u0006\u0002\b#J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\b2\n\u0010'\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0015H\u0002J\u0018\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.H\u0014J\b\u0010/\u001a\u00020\u001eH\u0016J\b\u00100\u001a\u00020\u001eH\u0016J\b\u00101\u001a\u00020\u001eH\u0002J\u000e\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0004R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00118TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u00064"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE_MS", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "THREAD_NAME", "", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", "debugStatus", "isShutDown", "", "()Z", "isShutdownRequested", "isThreadPresent", "isThreadPresent$kotlinx_coroutines_core", "thread", "getThread", "()Ljava/lang/Thread;", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "enqueue", "task", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "notifyStartup", "reschedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "run", "shutdown", "shutdownError", "shutdownForTests", "timeout", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ulj extends EventLoopImplBase implements Runnable {
    private static volatile Thread _thread;
    private static final long b;
    private static volatile int debugStatus;
    public static final ulj e;

    private ulj() {
    }

    static {
        Long l;
        ulj uljVar = new ulj();
        e = uljVar;
        EventLoop.incrementUseCount$default(uljVar, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        b = timeUnit.toNanos(l.longValue());
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    /* renamed from: getThread */
    public Thread getD() {
        Thread thread = _thread;
        return thread == null ? e() : thread;
    }

    private final boolean a() {
        return debugStatus == 4;
    }

    private final boolean c() {
        int i = debugStatus;
        return i == 2 || i == 3;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public void enqueue(Runnable task) {
        if (a()) {
            f();
        }
        super.enqueue(task);
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public void reschedule(long now, EventLoopImplBase.DelayedTask delayedTask) {
        f();
    }

    private final void f() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context) {
        return scheduleInvokeOnTimeout(timeMillis, block);
    }

    @Override // java.lang.Runnable
    public void run() {
        ueu ueuVar;
        boolean isEmpty;
        umr.d.c(this);
        AbstractTimeSource a2 = timeSource.a();
        if (a2 != null) {
            a2.registerTimeLoopThread();
        }
        try {
            if (!b()) {
                if (isEmpty) {
                    return;
                } else {
                    return;
                }
            }
            long j = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long processNextEvent = processNextEvent();
                if (processNextEvent == LocationRequestCompat.PASSIVE_INTERVAL) {
                    AbstractTimeSource a3 = timeSource.a();
                    Long valueOf = a3 == null ? null : Long.valueOf(a3.nanoTime());
                    long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
                    if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
                        j = b + nanoTime;
                    }
                    long j2 = j - nanoTime;
                    if (j2 <= 0) {
                        _thread = null;
                        d();
                        AbstractTimeSource a4 = timeSource.a();
                        if (a4 != null) {
                            a4.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getD();
                        return;
                    }
                    processNextEvent = uja.c(processNextEvent, j2);
                } else {
                    j = Long.MAX_VALUE;
                }
                if (processNextEvent > 0) {
                    if (c()) {
                        _thread = null;
                        d();
                        AbstractTimeSource a5 = timeSource.a();
                        if (a5 != null) {
                            a5.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getD();
                        return;
                    }
                    AbstractTimeSource a6 = timeSource.a();
                    if (a6 == null) {
                        ueuVar = null;
                    } else {
                        a6.parkNanos(this, processNextEvent);
                        ueuVar = ueu.d;
                    }
                    if (ueuVar == null) {
                        LockSupport.parkNanos(this, processNextEvent);
                    }
                }
            }
        } finally {
            _thread = null;
            d();
            AbstractTimeSource a7 = timeSource.a();
            if (a7 != null) {
                a7.unregisterTimeLoopThread();
            }
            if (!isEmpty()) {
                getD();
            }
        }
    }

    private final Thread e() {
        Thread thread;
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    private final boolean b() {
        synchronized (this) {
            if (c()) {
                return false;
            }
            debugStatus = 1;
            notifyAll();
            return true;
        }
    }

    private final void d() {
        synchronized (this) {
            if (c()) {
                debugStatus = 3;
                resetAll();
                notifyAll();
            }
        }
    }
}
