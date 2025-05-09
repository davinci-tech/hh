package defpackage;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003H\u0002J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0097Aø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003H\u0016J#\u0010\u001a\u001a\u00020\u00132\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u001bH\u0082\bJ\u001c\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003H\u0017J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00152\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\b\u0010!\u001a\u00020\u0013H\u0016J\u001f\u0010\"\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00152\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00130$H\u0096\u0001J\b\u0010%\u001a\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lkotlinx/coroutines/internal/LimitedDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/Delay;", "dispatcher", "parallelism", "", "(Lkotlinx/coroutines/CoroutineDispatcher;I)V", "queue", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "runningWorkers", "workerAllocationLock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "addAndTryDispatching", "", "block", OpAnalyticsConstants.DELAY, "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatch", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "dispatchInternal", "Lkotlin/Function0;", "dispatchYield", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "limitedParallelism", "run", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "tryAllocateWorker", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class uph extends CoroutineDispatcher implements Runnable, Delay {

    /* renamed from: a, reason: collision with root package name */
    private final upn<Runnable> f17497a;
    private final /* synthetic */ Delay b;
    private final int c;
    private final CoroutineDispatcher d;
    private final Object e;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public uph(CoroutineDispatcher coroutineDispatcher, int i) {
        this.d = coroutineDispatcher;
        this.c = i;
        Delay delay = coroutineDispatcher instanceof Delay ? (Delay) coroutineDispatcher : null;
        this.b = delay == null ? DefaultDelay.d() : delay;
        this.f17497a = new upn<>(false);
        this.e = new Object();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int parallelism) {
        checkParallelism.c(parallelism);
        return parallelism >= this.c ? this : super.limitedParallelism(parallelism);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0031, code lost:
    
        r0 = r3.e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0033, code lost:
    
        monitor-enter(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0034, code lost:
    
        r3.runningWorkers--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0040, code lost:
    
        if (r3.f17497a.c() != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
    
        r3.runningWorkers++;
        r1 = defpackage.ueu.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0042, code lost:
    
        monitor-exit(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0043, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r3 = this;
        L0:
            r0 = 0
        L1:
            upn<java.lang.Runnable> r1 = r3.f17497a
            java.lang.Object r1 = r1.d()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            if (r1 == 0) goto L31
            r1.run()     // Catch: java.lang.Throwable -> Lf
            goto L17
        Lf:
            r1 = move-exception
            ugq r2 = defpackage.ugq.f17420a
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            defpackage.CoroutineExceptionHandler.b(r2, r1)
        L17:
            int r0 = r0 + 1
            r1 = 16
            if (r0 < r1) goto L1
            kotlinx.coroutines.CoroutineDispatcher r1 = r3.d
            r2 = r3
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            boolean r1 = r1.isDispatchNeeded(r2)
            if (r1 == 0) goto L1
            kotlinx.coroutines.CoroutineDispatcher r0 = r3.d
            r1 = r3
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r0.dispatch(r2, r1)
            return
        L31:
            java.lang.Object r0 = r3.e
            monitor-enter(r0)
            int r1 = r3.runningWorkers     // Catch: java.lang.Throwable -> L4e
            int r1 = r1 + (-1)
            r3.runningWorkers = r1     // Catch: java.lang.Throwable -> L4e
            upn<java.lang.Runnable> r1 = r3.f17497a     // Catch: java.lang.Throwable -> L4e
            int r1 = r1.c()     // Catch: java.lang.Throwable -> L4e
            if (r1 != 0) goto L44
            monitor-exit(r0)
            return
        L44:
            int r1 = r3.runningWorkers     // Catch: java.lang.Throwable -> L4e
            int r1 = r1 + 1
            r3.runningWorkers = r1     // Catch: java.lang.Throwable -> L4e
            ueu r1 = defpackage.ueu.d     // Catch: java.lang.Throwable -> L4e
            monitor-exit(r0)
            goto L0
        L4e:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uph.run():void");
    }

    private final boolean c() {
        synchronized (this.e) {
            if (this.runningWorkers >= this.c) {
                return false;
            }
            this.runningWorkers++;
            return true;
        }
    }

    private final boolean e(Runnable runnable) {
        this.f17497a.c(runnable);
        return this.runningWorkers >= this.c;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext context, Runnable block) {
        if (!e(block) && c()) {
            this.d.dispatch(this, this);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext context, Runnable block) {
        if (!e(block) && c()) {
            this.d.dispatchYield(this, this);
        }
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long timeMillis, CancellableContinuation<? super ueu> continuation) {
        this.b.scheduleResumeAfterDelay(timeMillis, continuation);
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context) {
        return this.b.invokeOnTimeout(timeMillis, block, context);
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long j, Continuation<? super ueu> continuation) {
        return this.b.delay(j, continuation);
    }
}
