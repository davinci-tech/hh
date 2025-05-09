package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.scheduling.Task;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020*B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J!\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u001a\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\r*\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010&\u001a\u00020#8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010(\u001a\u00020#8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%¨\u0006)"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "<init>", "()V", "Lkotlinx/coroutines/scheduling/Task;", "task", "", "fair", "add", "(Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "addLast", "(Lkotlinx/coroutines/scheduling/Task;)Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalQueue", "", "offloadAllWorkTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)V", "poll", "()Lkotlinx/coroutines/scheduling/Task;", "pollBuffer", "queue", "pollTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)Z", "victim", "", "tryStealBlockingFrom", "(Lkotlinx/coroutines/scheduling/WorkQueue;)J", "tryStealFrom", "blockingOnly", "tryStealLastScheduled", "(Lkotlinx/coroutines/scheduling/WorkQueue;Z)J", "decrementIfBlocking", "(Lkotlinx/coroutines/scheduling/Task;)V", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "", "getBufferSize$kotlinx_coroutines_core", "()I", "bufferSize", "getSize$kotlinx_coroutines_core", "size", "kotlinx-coroutines-core", ""}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uqo {

    /* renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f17506a = AtomicReferenceFieldUpdater.newUpdater(uqo.class, Object.class, "lastScheduledTask");
    private static final /* synthetic */ AtomicIntegerFieldUpdater e = AtomicIntegerFieldUpdater.newUpdater(uqo.class, "producerIndex");
    private static final /* synthetic */ AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(uqo.class, "consumerIndex");
    private static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(uqo.class, "blockingTasksInBuffer");
    private final AtomicReferenceArray<Task> c = new AtomicReferenceArray<>(128);
    private volatile /* synthetic */ Object lastScheduledTask = null;
    private volatile /* synthetic */ int producerIndex = 0;
    private volatile /* synthetic */ int consumerIndex = 0;
    private volatile /* synthetic */ int blockingTasksInBuffer = 0;

    public final int a() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int c() {
        return this.lastScheduledTask != null ? a() + 1 : a();
    }

    public final Task e() {
        Task task = (Task) f17506a.getAndSet(this, null);
        return task == null ? d() : task;
    }

    public static /* synthetic */ Task d(uqo uqoVar, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return uqoVar.a(task, z);
    }

    public final Task a(Task task, boolean z) {
        if (z) {
            return b(task);
        }
        Task task2 = (Task) f17506a.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return b(task2);
    }

    public final long a(uqo uqoVar) {
        if (ASSERTIONS_ENABLED.a() && a() != 0) {
            throw new AssertionError();
        }
        Task d2 = uqoVar.d();
        if (d2 != null) {
            Task d3 = d(this, d2, false, 2, null);
            if (!ASSERTIONS_ENABLED.a() || d3 == null) {
                return -1L;
            }
            throw new AssertionError();
        }
        return e(uqoVar, false);
    }

    public final long d(uqo uqoVar) {
        if (ASSERTIONS_ENABLED.a() && a() != 0) {
            throw new AssertionError();
        }
        int i = uqoVar.producerIndex;
        AtomicReferenceArray<Task> atomicReferenceArray = uqoVar.c;
        for (int i2 = uqoVar.consumerIndex; i2 != i; i2++) {
            int i3 = i2 & 127;
            if (uqoVar.blockingTasksInBuffer == 0) {
                break;
            }
            Task task = atomicReferenceArray.get(i3);
            if (task != null && task.taskContext.getB() == 1 && ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray, i3, task, (Object) null)) {
                d.decrementAndGet(uqoVar);
                d(this, task, false, 2, null);
                return -1L;
            }
        }
        return e(uqoVar, true);
    }

    public final void e(uqh uqhVar) {
        Task task = (Task) f17506a.getAndSet(this, null);
        if (task != null) {
            uqhVar.c(task);
        }
        while (b(uqhVar)) {
        }
    }

    private final long e(uqo uqoVar, boolean z) {
        Task task;
        do {
            task = (Task) uqoVar.lastScheduledTask;
            if (task == null) {
                return -2L;
            }
            if (z && task.taskContext.getB() != 1) {
                return -2L;
            }
            long nanoTime = BlockingContext.j.nanoTime() - task.submissionTime;
            if (nanoTime < BlockingContext.g) {
                return BlockingContext.g - nanoTime;
            }
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(f17506a, uqoVar, task, (Object) null));
        d(this, task, false, 2, null);
        return -1L;
    }

    private final boolean b(uqh uqhVar) {
        Task d2 = d();
        if (d2 == null) {
            return false;
        }
        uqhVar.c(d2);
        return true;
    }

    private final Task d() {
        Task andSet;
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            if (b.compareAndSet(this, i, i + 1) && (andSet = this.c.getAndSet(i & 127, null)) != null) {
                d(andSet);
                return andSet;
            }
        }
    }

    private final Task b(Task task) {
        if (task.taskContext.getB() == 1) {
            d.incrementAndGet(this);
        }
        if (a() == 127) {
            return task;
        }
        int i = this.producerIndex & 127;
        while (this.c.get(i) != null) {
            Thread.yield();
        }
        this.c.lazySet(i, task);
        e.incrementAndGet(this);
        return null;
    }

    private final void d(Task task) {
        if (task == null || task.taskContext.getB() != 1) {
            return;
        }
        int decrementAndGet = d.decrementAndGet(this);
        if (ASSERTIONS_ENABLED.a() && decrementAndGet < 0) {
            throw new AssertionError();
        }
    }
}
