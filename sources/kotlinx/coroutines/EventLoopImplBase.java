package kotlinx.coroutines;

import androidx.core.location.LocationRequestCompat;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.ASSERTIONS_ENABLED;
import defpackage.CLOSED_EMPTY;
import defpackage.getOrCreateCancellableContinuation;
import defpackage.timeSource;
import defpackage.ueu;
import defpackage.uhy;
import defpackage.uja;
import defpackage.ulj;
import defpackage.umi;
import defpackage.umr;
import defpackage.upo;
import defpackage.upu;
import defpackage.uqd;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b \u0018\u00002\u0002092\u00020::\u00044567B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0002J\u0017\u0010\u0007\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00032\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0017\u0010\u0002J\u000f\u0010\u0018\u001a\u00020\u0003H\u0004¢\u0006\u0004\b\u0018\u0010\u0002J\u001d\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\u00142\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006H\u0004¢\u0006\u0004\b#\u0010$J%\u0010'\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00142\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030%H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u001aH\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0003H\u0016¢\u0006\u0004\b+\u0010\u0002R$\u0010-\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00118B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00118TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b1\u0010.R\u0014\u00103\u001a\u00020\u00148TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0016¨\u00068"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "<init>", "()V", "", "closeQueue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dequeue", "()Ljava/lang/Runnable;", "Lkotlin/coroutines/CoroutineContext;", ParamConstants.Param.CONTEXT, "block", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "task", "enqueue", "(Ljava/lang/Runnable;)V", "", "enqueueImpl", "(Ljava/lang/Runnable;)Z", "", "processNextEvent", "()J", "rescheduleAllDelayed", "resetAll", "now", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "schedule", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "", "scheduleImpl", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)I", "timeMillis", "Lkotlinx/coroutines/DisposableHandle;", "scheduleInvokeOnTimeout", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "shouldUnpark", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)Z", "shutdown", "value", "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "getNextTime", "nextTime", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "DelayedTaskQueue", "kotlinx-coroutines-core", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    private volatile /* synthetic */ Object _queue = null;
    private volatile /* synthetic */ Object _delayed = null;
    private volatile /* synthetic */ int _isCompleted = 0;

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long j, Continuation<? super ueu> continuation) {
        return Delay.c.b(this, j, continuation);
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return Delay.c.d(this, j, runnable, coroutineContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean isCompleted() {
        return this._isCompleted;
    }

    private final void setCompleted(boolean z) {
        this._isCompleted = z ? 1 : 0;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected boolean isEmpty() {
        upu upuVar;
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        b bVar = (b) this._delayed;
        if (bVar != null && !bVar.d()) {
            return false;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof upo) {
                return ((upo) obj).d();
            }
            upuVar = CLOSED_EMPTY.e;
            if (obj != upuVar) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected long getNextTime() {
        upu upuVar;
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (!(obj instanceof upo)) {
                upuVar = CLOSED_EMPTY.e;
                if (obj == upuVar) {
                    return LocationRequestCompat.PASSIVE_INTERVAL;
                }
                return 0L;
            }
            if (!((upo) obj).d()) {
                return 0L;
            }
        }
        b bVar = (b) this._delayed;
        DelayedTask c = bVar == null ? null : bVar.c();
        if (c == null) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        long j = c.nanoTime;
        AbstractTimeSource a2 = timeSource.a();
        Long valueOf = a2 != null ? Long.valueOf(a2.nanoTime()) : null;
        return uja.b(j - (valueOf == null ? System.nanoTime() : valueOf.longValue()), 0L);
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        umr.d.a();
        setCompleted(true);
        closeQueue();
        while (processNextEvent() <= 0) {
        }
        rescheduleAllDelayed();
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long timeMillis, CancellableContinuation<? super ueu> continuation) {
        long b2 = CLOSED_EMPTY.b(timeMillis);
        if (b2 < 4611686018427387903L) {
            AbstractTimeSource a2 = timeSource.a();
            Long valueOf = a2 == null ? null : Long.valueOf(a2.nanoTime());
            long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
            e eVar = new e(b2 + nanoTime, continuation);
            getOrCreateCancellableContinuation.c(continuation, eVar);
            schedule(nanoTime, eVar);
        }
    }

    protected final DisposableHandle scheduleInvokeOnTimeout(long timeMillis, Runnable block) {
        long b2 = CLOSED_EMPTY.b(timeMillis);
        if (b2 < 4611686018427387903L) {
            AbstractTimeSource a2 = timeSource.a();
            Long valueOf = a2 == null ? null : Long.valueOf(a2.nanoTime());
            long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
            a aVar = new a(b2 + nanoTime, block);
            schedule(nanoTime, aVar);
            return aVar;
        }
        return umi.e;
    }

    @Override // kotlinx.coroutines.EventLoop
    public long processNextEvent() {
        DelayedTask delayedTask;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        b bVar = (b) this._delayed;
        if (bVar != null && !bVar.d()) {
            AbstractTimeSource a2 = timeSource.a();
            Long valueOf = a2 == null ? null : Long.valueOf(a2.nanoTime());
            long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
            do {
                b bVar2 = bVar;
                synchronized (bVar2) {
                    DelayedTask b2 = bVar2.b();
                    if (b2 == null) {
                        delayedTask = null;
                    } else {
                        DelayedTask delayedTask2 = b2;
                        if (delayedTask2.timeToExecute(nanoTime) && enqueueImpl(delayedTask2)) {
                            delayedTask = bVar2.c(0);
                        } else {
                            delayedTask = null;
                        }
                    }
                }
            } while (delayedTask != null);
        }
        Runnable dequeue = dequeue();
        if (dequeue != null) {
            dequeue.run();
            return 0L;
        }
        return getNextTime();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext context, Runnable block) {
        enqueue(block);
    }

    public void enqueue(Runnable task) {
        if (enqueueImpl(task)) {
            unpark();
        } else {
            ulj.e.enqueue(task);
        }
    }

    private final void closeQueue() {
        upu upuVar;
        upu upuVar2;
        if (ASSERTIONS_ENABLED.a() && !isCompleted()) {
            throw new AssertionError();
        }
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _queue$FU;
                upuVar = CLOSED_EMPTY.e;
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, upuVar)) {
                    return;
                }
            } else if (!(obj instanceof upo)) {
                upuVar2 = CLOSED_EMPTY.e;
                if (obj == upuVar2) {
                    return;
                }
                upo upoVar = new upo(8, true);
                if (obj != null) {
                    upoVar.c((Runnable) obj);
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, upoVar)) {
                        return;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                }
            } else {
                ((upo) obj).b();
                return;
            }
        }
    }

    public final void schedule(long now, DelayedTask delayedTask) {
        int scheduleImpl = scheduleImpl(now, delayedTask);
        if (scheduleImpl == 0) {
            if (shouldUnpark(delayedTask)) {
                unpark();
            }
        } else if (scheduleImpl == 1) {
            reschedule(now, delayedTask);
        } else if (scheduleImpl != 2) {
            throw new IllegalStateException("unexpected result".toString());
        }
    }

    private final boolean shouldUnpark(DelayedTask task) {
        b bVar = (b) this._delayed;
        return (bVar == null ? null : bVar.c()) == task;
    }

    private final int scheduleImpl(long now, DelayedTask delayedTask) {
        if (isCompleted()) {
            return 1;
        }
        b bVar = (b) this._delayed;
        if (bVar == null) {
            ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_delayed$FU, this, (Object) null, new b(now));
            Object obj = this._delayed;
            uhy.d(obj);
            bVar = (b) obj;
        }
        return delayedTask.scheduleTask(now, bVar, this);
    }

    protected final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    private final void rescheduleAllDelayed() {
        AbstractTimeSource a2 = timeSource.a();
        Long valueOf = a2 == null ? null : Long.valueOf(a2.nanoTime());
        long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
        while (true) {
            b bVar = (b) this._delayed;
            DelayedTask e2 = bVar == null ? null : bVar.e();
            if (e2 == null) {
                return;
            } else {
                reschedule(nanoTime, e2);
            }
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0000H\u0096\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u0007J\b\u0010$\u001a\u00020%H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "nanoTime", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "dispose", "", "scheduleTask", "now", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private Object _heap;
        private int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public uqd<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof uqd) {
                return (uqd) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(uqd<?> uqdVar) {
            upu upuVar;
            Object obj = this._heap;
            upuVar = CLOSED_EMPTY.f17461a;
            if (obj == upuVar) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = uqdVar;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(DelayedTask other) {
            long j = this.nanoTime - other.nanoTime;
            if (j > 0) {
                return 1;
            }
            return j < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long now) {
            return now - this.nanoTime >= 0;
        }

        public final int scheduleTask(long j, b bVar, EventLoopImplBase eventLoopImplBase) {
            upu upuVar;
            synchronized (this) {
                Object obj = this._heap;
                upuVar = CLOSED_EMPTY.f17461a;
                if (obj == upuVar) {
                    return 2;
                }
                b bVar2 = bVar;
                synchronized (bVar2) {
                    DelayedTask b = bVar2.b();
                    if (eventLoopImplBase.isCompleted()) {
                        return 1;
                    }
                    if (b == null) {
                        bVar.d = j;
                    } else {
                        long j2 = b.nanoTime;
                        if (j2 - j < 0) {
                            j = j2;
                        }
                        if (j - bVar.d > 0) {
                            bVar.d = j;
                        }
                    }
                    if (this.nanoTime - bVar.d < 0) {
                        this.nanoTime = bVar.d;
                    }
                    bVar2.e(this);
                    return 0;
                }
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            upu upuVar;
            upu upuVar2;
            synchronized (this) {
                Object obj = this._heap;
                upuVar = CLOSED_EMPTY.f17461a;
                if (obj == upuVar) {
                    return;
                }
                b bVar = obj instanceof b ? (b) obj : null;
                if (bVar != null) {
                    bVar.c((b) this);
                }
                upuVar2 = CLOSED_EMPTY.f17461a;
                this._heap = upuVar2;
            }
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class e extends DelayedTask {
        private final CancellableContinuation<ueu> b;

        /* JADX WARN: Multi-variable type inference failed */
        public e(long j, CancellableContinuation<? super ueu> cancellableContinuation) {
            super(j);
            this.b = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.resumeUndispatched(EventLoopImplBase.this, ueu.d);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return uhy.b(super.toString(), this.b);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class a extends DelayedTask {
        private final Runnable e;

        public a(long j, Runnable runnable) {
            super(j);
            this.e = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return uhy.b(super.toString(), this.e);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", "", "(J)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class b extends uqd<DelayedTask> {
        public long d;

        public b(long j) {
            this.d = j;
        }
    }

    private final boolean enqueueImpl(Runnable task) {
        upu upuVar;
        while (true) {
            Object obj = this._queue;
            if (isCompleted()) {
                return false;
            }
            if (obj == null) {
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, (Object) null, task)) {
                    return true;
                }
            } else if (!(obj instanceof upo)) {
                upuVar = CLOSED_EMPTY.e;
                if (obj == upuVar) {
                    return false;
                }
                upo upoVar = new upo(8, true);
                if (obj != null) {
                    upoVar.c((Runnable) obj);
                    upoVar.c(task);
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, upoVar)) {
                        return true;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                }
            } else if (obj != null) {
                upo upoVar2 = (upo) obj;
                int c = upoVar2.c(task);
                if (c == 0) {
                    return true;
                }
                if (c == 1) {
                    ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, upoVar2.a());
                } else if (c == 2) {
                    return false;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
            }
        }
    }

    private final Runnable dequeue() {
        upu upuVar;
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (!(obj instanceof upo)) {
                upuVar = CLOSED_EMPTY.e;
                if (obj == upuVar) {
                    return null;
                }
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, (Object) null)) {
                    if (obj != null) {
                        return (Runnable) obj;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                }
            } else if (obj != null) {
                upo upoVar = (upo) obj;
                Object c = upoVar.c();
                if (c != upo.f17499a) {
                    return (Runnable) c;
                }
                ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, obj, upoVar.a());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
            }
        }
    }
}
