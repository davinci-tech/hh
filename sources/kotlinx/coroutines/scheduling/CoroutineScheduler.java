package kotlinx.coroutines.scheduling;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.location.LocationRequestCompat;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import defpackage.ASSERTIONS_ENABLED;
import defpackage.BlockingContext;
import defpackage.classSimpleName;
import defpackage.timeSource;
import defpackage.ueu;
import defpackage.uhy;
import defpackage.uib;
import defpackage.uja;
import defpackage.upp;
import defpackage.upu;
import defpackage.uqh;
import defpackage.uqm;
import defpackage.uqo;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.random.Random;
import kotlinx.coroutines.AbstractTimeSource;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0000\u0018\u0000 X2\u00020\\2\u00020]:\u0003XYZB+\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u001d\u001a\u00020\n2\n\u0010\u001a\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b\u001f\u0010\u0011J\u0015\u0010!\u001a\b\u0018\u00010 R\u00020\u0000H\u0002¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0013H\u0082\b¢\u0006\u0004\b#\u0010\u0015J\u0010\u0010$\u001a\u00020\u0001H\u0082\b¢\u0006\u0004\b$\u0010\u0017J-\u0010&\u001a\u00020\u00132\n\u0010\u001a\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020\f¢\u0006\u0004\b&\u0010'J\u001b\u0010)\u001a\u00020\u00132\n\u0010(\u001a\u00060\u0018j\u0002`\u0019H\u0016¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020\u0001H\u0082\b¢\u0006\u0004\b-\u0010\u0017J\u001b\u0010/\u001a\u00020\u00012\n\u0010.\u001a\u00060 R\u00020\u0000H\u0002¢\u0006\u0004\b/\u00100J\u0015\u00101\u001a\b\u0018\u00010 R\u00020\u0000H\u0002¢\u0006\u0004\b1\u0010\"J\u0019\u00102\u001a\u00020\f2\n\u0010.\u001a\u00060 R\u00020\u0000¢\u0006\u0004\b2\u00103J)\u00106\u001a\u00020\u00132\n\u0010.\u001a\u00060 R\u00020\u00002\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u0001¢\u0006\u0004\b6\u00107J\u0010\u00108\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b8\u0010,J\u0015\u00109\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b9\u0010:J\u0015\u0010<\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u0004¢\u0006\u0004\b<\u0010=J\u0017\u0010?\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\fH\u0002¢\u0006\u0004\b?\u0010@J\r\u0010A\u001a\u00020\u0013¢\u0006\u0004\bA\u0010\u0015J\u000f\u0010B\u001a\u00020\u0006H\u0016¢\u0006\u0004\bB\u0010CJ\u0010\u0010D\u001a\u00020\fH\u0082\b¢\u0006\u0004\bD\u0010EJ\u0019\u0010F\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0004H\u0002¢\u0006\u0004\bF\u0010GJ\u000f\u0010H\u001a\u00020\fH\u0002¢\u0006\u0004\bH\u0010EJ+\u0010I\u001a\u0004\u0018\u00010\n*\b\u0018\u00010 R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010%\u001a\u00020\fH\u0002¢\u0006\u0004\bI\u0010JR\u0015\u0010\u0010\u001a\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010LR\u0015\u0010\u001f\u001a\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010\u0017R\u0014\u0010O\u001a\u00020N8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010Q\u001a\u00020N8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bQ\u0010PR\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010RR\u0011\u0010S\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bS\u0010ER\u0014\u0010\u0003\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010LR\u0014\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010TR\u001e\u0010V\u001a\f\u0012\b\u0012\u00060 R\u00020\u00000U8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bV\u0010W¨\u0006["}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "", "corePoolSize", "maxPoolSize", "", "idleWorkerKeepAliveNs", "", "schedulerName", "<init>", "(IIJLjava/lang/String;)V", "Lkotlinx/coroutines/scheduling/Task;", "task", "", "addToGlobalQueue", "(Lkotlinx/coroutines/scheduling/Task;)Z", "state", "availableCpuPermits", "(J)I", "blockingTasks", "", "close", "()V", "createNewWorker", "()I", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "Lkotlinx/coroutines/scheduling/TaskContext;", "taskContext", "createTask", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;)Lkotlinx/coroutines/scheduling/Task;", "createdWorkers", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "currentWorker", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "decrementBlockingTasks", "decrementCreatedWorkers", "tailDispatch", "dispatch", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;Z)V", AwarenessRequest.Field.COMMAND, "execute", "(Ljava/lang/Runnable;)V", "incrementBlockingTasks", "()J", "incrementCreatedWorkers", "worker", "parkedWorkersStackNextIndex", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)I", "parkedWorkersStackPop", "parkedWorkersStackPush", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)Z", "oldIndex", "newIndex", "parkedWorkersStackTopUpdate", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;II)V", "releaseCpuPermit", "runSafely", "(Lkotlinx/coroutines/scheduling/Task;)V", "timeout", "shutdown", "(J)V", "skipUnpark", "signalBlockingWork", "(Z)V", "signalCpuWork", "toString", "()Ljava/lang/String;", "tryAcquireCpuPermit", "()Z", "tryCreateWorker", "(J)Z", "tryUnpark", "submitToLocalQueue", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "getAvailableCpuPermits", "I", "getCreatedWorkers", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalBlockingQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalCpuQueue", RequestOptions.AD_CONTENT_CLASSIFICATION_J, "isTerminated", "Ljava/lang/String;", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "workers", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    private volatile /* synthetic */ int _isTerminated;
    public final int b;
    volatile /* synthetic */ long controlState;
    public final uqh e;
    public final String f;
    public final upp<d> g;
    public final uqh h;
    public final long i;
    public final int j;
    private volatile /* synthetic */ long parkedWorkersStack;
    public static final a c = new a(null);
    public static final upu d = new upu("NOT_IN_STACK");
    private static final /* synthetic */ AtomicLongFieldUpdater l = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ AtomicLongFieldUpdater f14499a = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    private static final /* synthetic */ AtomicIntegerFieldUpdater m = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "DORMANT", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i, int i2, long j, String str) {
        this.b = i;
        this.j = i2;
        this.i = j;
        this.f = str;
        if (i < 1) {
            throw new IllegalArgumentException(("Core pool size " + i + " should be at least 1").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should be greater than or equals to core pool size " + i).toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.h = new uqh();
        this.e = new uqh();
        this.parkedWorkersStack = 0L;
        this.g = new upp<>(i + 1);
        this.controlState = i << 42;
        this._isTerminated = 0;
    }

    public final boolean c(d dVar) {
        long j;
        int indexInArray;
        if (dVar.getNextParkedWorker() != d) {
            return false;
        }
        do {
            j = this.parkedWorkersStack;
            int i = (int) (2097151 & j);
            indexInArray = dVar.getIndexInArray();
            if (ASSERTIONS_ENABLED.a() && indexInArray == 0) {
                throw new AssertionError();
            }
            dVar.a(this.g.d(i));
        } while (!l.compareAndSet(this, j, indexInArray | ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152))));
        return true;
    }

    private final int a(d dVar) {
        Object nextParkedWorker = dVar.getNextParkedWorker();
        while (nextParkedWorker != d) {
            if (nextParkedWorker == null) {
                return 0;
            }
            d dVar2 = (d) nextParkedWorker;
            int indexInArray = dVar2.getIndexInArray();
            if (indexInArray != 0) {
                return indexInArray;
            }
            nextParkedWorker = dVar2.getNextParkedWorker();
        }
        return -1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean d() {
        return this._isTerminated;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", "", "()V", "BLOCKING_MASK", "", "BLOCKING_SHIFT", "", "CLAIMED", "CPU_PERMITS_MASK", "CPU_PERMITS_SHIFT", "CREATED_MASK", "MAX_SUPPORTED_POOL_SIZE", "MIN_SUPPORTED_POOL_SIZE", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED", "PARKED_INDEX_MASK", "PARKED_VERSION_INC", "PARKED_VERSION_MASK", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(uib uibVar) {
            this();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        a(this, command, null, false, 6, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        e(PreConnectManager.CONNECT_INTERNAL);
    }

    public final void e(long j) {
        int i;
        if (m.compareAndSet(this, 0, 1)) {
            d a2 = a();
            synchronized (this.g) {
                i = (int) (this.controlState & 2097151);
            }
            if (1 <= i) {
                int i2 = 1;
                while (true) {
                    d d2 = this.g.d(i2);
                    uhy.d(d2);
                    d dVar = d2;
                    if (dVar != a2) {
                        while (dVar.isAlive()) {
                            LockSupport.unpark(dVar);
                            dVar.join(j);
                        }
                        WorkerState workerState = dVar.d;
                        if (ASSERTIONS_ENABLED.a() && workerState != WorkerState.TERMINATED) {
                            throw new AssertionError();
                        }
                        dVar.c.e(this.e);
                    }
                    if (i2 == i) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            this.e.a();
            this.h.a();
            while (true) {
                Task c2 = a2 == null ? null : a2.c(true);
                if (c2 == null && (c2 = this.h.d()) == null && (c2 = this.e.d()) == null) {
                    break;
                } else {
                    b(c2);
                }
            }
            if (a2 != null) {
                a2.a(WorkerState.TERMINATED);
            }
            if (ASSERTIONS_ENABLED.a() && ((int) ((this.controlState & 9223367638808264704L) >> 42)) != this.b) {
                throw new AssertionError();
            }
            this.parkedWorkersStack = 0L;
            this.controlState = 0L;
        }
    }

    public static /* synthetic */ void a(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            taskContext = BlockingContext.c;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.e(runnable, taskContext, z);
    }

    public final void e(Runnable runnable, TaskContext taskContext, boolean z) {
        AbstractTimeSource a2 = timeSource.a();
        if (a2 != null) {
            a2.trackTask();
        }
        Task e = e(runnable, taskContext);
        d a3 = a();
        Task b = b(a3, e, z);
        if (b != null && !a(b)) {
            throw new RejectedExecutionException(uhy.b(this.f, (Object) " was terminated"));
        }
        boolean z2 = z && a3 != null;
        if (e.taskContext.getB() != 0) {
            c(z2);
        } else {
            if (z2) {
                return;
            }
            b();
        }
    }

    public final Task e(Runnable runnable, TaskContext taskContext) {
        long nanoTime = BlockingContext.j.nanoTime();
        if (runnable instanceof Task) {
            Task task = (Task) runnable;
            task.submissionTime = nanoTime;
            task.taskContext = taskContext;
            return task;
        }
        return new uqm(runnable, nanoTime, taskContext);
    }

    public final void b() {
        if (i() || d(this, 0L, 1, null)) {
            return;
        }
        i();
    }

    static /* synthetic */ boolean d(CoroutineScheduler coroutineScheduler, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineScheduler.controlState;
        }
        return coroutineScheduler.d(j);
    }

    private final boolean d(long j) {
        if (uja.a(((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21)), 0) < this.b) {
            int e = e();
            if (e == 1 && this.b > 1) {
                e();
            }
            if (e > 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean i() {
        d c2;
        do {
            c2 = c();
            if (c2 == null) {
                return false;
            }
        } while (!d.e.compareAndSet(c2, -1, 0));
        LockSupport.unpark(c2);
        return true;
    }

    private final int e() {
        synchronized (this.g) {
            if (d()) {
                return -1;
            }
            long j = this.controlState;
            int i = (int) (j & 2097151);
            int a2 = uja.a(i - ((int) ((j & 4398044413952L) >> 21)), 0);
            if (a2 >= this.b) {
                return 0;
            }
            if (i >= this.j) {
                return 0;
            }
            int i2 = ((int) (this.controlState & 2097151)) + 1;
            if (i2 <= 0 || this.g.d(i2) != null) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            d dVar = new d(i2);
            this.g.e(i2, dVar);
            if (i2 == ((int) (2097151 & f14499a.incrementAndGet(this)))) {
                dVar.start();
                return a2 + 1;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final Task b(d dVar, Task task, boolean z) {
        if (dVar == null || dVar.d == WorkerState.TERMINATED) {
            return task;
        }
        if (task.taskContext.getB() == 0 && dVar.d == WorkerState.BLOCKING) {
            return task;
        }
        dVar.b = true;
        return dVar.c.a(task, z);
    }

    private final d a() {
        Thread currentThread = Thread.currentThread();
        d dVar = currentThread instanceof d ? (d) currentThread : null;
        if (dVar != null && uhy.e(CoroutineScheduler.this, this)) {
            return dVar;
        }
        return null;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        int d2 = this.g.d();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < d2; i6++) {
            d d3 = this.g.d(i6);
            if (d3 != null) {
                int c2 = d3.c.c();
                int i7 = c.c[d3.d.ordinal()];
                if (i7 == 1) {
                    i3++;
                } else if (i7 == 2) {
                    i2++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(c2);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i7 == 3) {
                    i++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(c2);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i7 == 4) {
                    i4++;
                    if (c2 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(c2);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i7 == 5) {
                    i5++;
                }
            }
        }
        long j = this.controlState;
        return this.f + '@' + classSimpleName.d(this) + "[Pool Size {core = " + this.b + ", max = " + this.j + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.h.c() + ", global blocking queue size = " + this.e.c() + ", Control State {created workers= " + ((int) (2097151 & j)) + ", blocking tasks = " + ((int) ((4398044413952L & j) >> 21)) + ", CPUs acquired = " + (this.b - ((int) ((9223367638808264704L & j) >> 42))) + "}]";
    }

    public final void b(Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            try {
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
                AbstractTimeSource a2 = timeSource.a();
                if (a2 == null) {
                }
            } finally {
                AbstractTimeSource a3 = timeSource.a();
                if (a3 != null) {
                    a3.unTrackTask();
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0080\u0004\u0018\u00002\u00020GB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\n\u0010\tJ\u0017\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0015\u0010\tJ\u000f\u0010\u0016\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001f\u0010\u001cJ\u000f\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0004\b \u0010\u001cJ\u000f\u0010!\u001a\u00020\u000fH\u0002¢\u0006\u0004\b!\u0010\u0017J\u000f\u0010\"\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\"\u0010\u001cJ\u0015\u0010%\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b%\u0010&J\u0019\u0010(\u001a\u0004\u0018\u00010\u000b2\u0006\u0010'\u001a\u00020\u000fH\u0002¢\u0006\u0004\b(\u0010\u0012J\u000f\u0010)\u001a\u00020\u0007H\u0002¢\u0006\u0004\b)\u0010\u001cR*\u0010*\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00018\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010\tR\u0014\u00100\u001a\u00020/8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R$\u00108\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010+R\u0012\u0010B\u001a\u00020?8Æ\u0002¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0016\u0010C\u001a\u00020#8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u00106¨\u0006F"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "", "index", "<init>", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "taskMode", "", "afterTask", "(I)V", "beforeTask", "Lkotlinx/coroutines/scheduling/Task;", "task", "executeTask", "(Lkotlinx/coroutines/scheduling/Task;)V", "", "scanLocalQueue", "findAnyTask", "(Z)Lkotlinx/coroutines/scheduling/Task;", "findTask", Wpt.MODE, "idleReset", "inStack", "()Z", "upperBound", "nextInt", "(I)I", "park", "()V", "pollGlobalQueues", "()Lkotlinx/coroutines/scheduling/Task;", "run", "runWorker", "tryAcquireCpuPermit", "tryPark", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "newState", "tryReleaseCpu", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)Z", "blockingOnly", "trySteal", "tryTerminateWorker", "indexInArray", "I", "getIndexInArray", "()I", "setIndexInArray", "Lkotlinx/coroutines/scheduling/WorkQueue;", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "mayHaveLocalTasks", "Z", "", "minDelayUntilStealableTaskNs", RequestOptions.AD_CONTENT_CLASSIFICATION_J, "", "nextParkedWorker", "Ljava/lang/Object;", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "rngState", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "scheduler", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "terminationDeadline", "kotlinx-coroutines-core", "Ljava/lang/Thread;"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class d extends Thread {
        static final /* synthetic */ AtomicIntegerFieldUpdater e = AtomicIntegerFieldUpdater.newUpdater(d.class, "workerCtl");
        public boolean b;
        public final uqo c;
        public WorkerState d;
        private int h;
        private long i;
        private volatile int indexInArray;
        private long j;
        private volatile Object nextParkedWorker;
        volatile /* synthetic */ int workerCtl;

        private d() {
            setDaemon(true);
            this.c = new uqo();
            this.d = WorkerState.DORMANT;
            this.workerCtl = 0;
            this.nextParkedWorker = CoroutineScheduler.d;
            this.h = Random.INSTANCE.nextInt();
        }

        /* renamed from: a, reason: from getter */
        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final void b(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.f);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public d(int i) {
            this();
            b(i);
        }

        public final void a(Object obj) {
            this.nextParkedWorker = obj;
        }

        /* renamed from: d, reason: from getter */
        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        private final boolean g() {
            long j;
            if (this.d != WorkerState.CPU_ACQUIRED) {
                CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
                do {
                    j = coroutineScheduler.controlState;
                    if (((int) ((9223367638808264704L & j) >> 42)) == 0) {
                        return false;
                    }
                } while (!CoroutineScheduler.f14499a.compareAndSet(coroutineScheduler, j, j - 4398046511104L));
                this.d = WorkerState.CPU_ACQUIRED;
            }
            return true;
        }

        public final boolean a(WorkerState workerState) {
            WorkerState workerState2 = this.d;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.f14499a.addAndGet(CoroutineScheduler.this, 4398046511104L);
            }
            if (workerState2 != workerState) {
                this.d = workerState;
            }
            return z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            i();
        }

        private final void i() {
            loop0: while (true) {
                boolean z = false;
                while (!CoroutineScheduler.this.d() && this.d != WorkerState.TERMINATED) {
                    Task c = c(this.b);
                    if (c != null) {
                        this.j = 0L;
                        a(c);
                    } else {
                        this.b = false;
                        if (this.j == 0) {
                            h();
                        } else if (z) {
                            a(WorkerState.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.j);
                            this.j = 0L;
                        } else {
                            z = true;
                        }
                    }
                }
            }
            a(WorkerState.TERMINATED);
        }

        private final void h() {
            if (!c()) {
                CoroutineScheduler.this.c(this);
                return;
            }
            if (ASSERTIONS_ENABLED.a() && this.c.c() != 0) {
                throw new AssertionError();
            }
            this.workerCtl = -1;
            while (c() && this.workerCtl == -1 && !CoroutineScheduler.this.d() && this.d != WorkerState.TERMINATED) {
                a(WorkerState.PARKING);
                Thread.interrupted();
                b();
            }
        }

        private final boolean c() {
            return this.nextParkedWorker != CoroutineScheduler.d;
        }

        private final void a(int i) {
            if (i != 0 && a(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.b();
            }
        }

        private final void e(int i) {
            if (i == 0) {
                return;
            }
            CoroutineScheduler.f14499a.addAndGet(CoroutineScheduler.this, -2097152L);
            WorkerState workerState = this.d;
            if (workerState != WorkerState.TERMINATED) {
                if (ASSERTIONS_ENABLED.a() && workerState != WorkerState.BLOCKING) {
                    throw new AssertionError();
                }
                this.d = WorkerState.DORMANT;
            }
        }

        public final int c(int i) {
            int i2 = this.h;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.h = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
        }

        private final void b() {
            if (this.i == 0) {
                this.i = System.nanoTime() + CoroutineScheduler.this.i;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.i);
            if (System.nanoTime() - this.i >= 0) {
                this.i = 0L;
                j();
            }
        }

        private final void j() {
            upp<d> uppVar = CoroutineScheduler.this.g;
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            synchronized (uppVar) {
                if (coroutineScheduler.d()) {
                    return;
                }
                if (((int) (coroutineScheduler.controlState & 2097151)) <= coroutineScheduler.b) {
                    return;
                }
                if (e.compareAndSet(this, -1, 1)) {
                    int indexInArray = getIndexInArray();
                    b(0);
                    coroutineScheduler.a(this, indexInArray, 0);
                    int andDecrement = (int) (CoroutineScheduler.f14499a.getAndDecrement(coroutineScheduler) & 2097151);
                    if (andDecrement != indexInArray) {
                        d d = coroutineScheduler.g.d(andDecrement);
                        uhy.d(d);
                        d dVar = d;
                        coroutineScheduler.g.e(indexInArray, dVar);
                        dVar.b(indexInArray);
                        coroutineScheduler.a(dVar, andDecrement, indexInArray);
                    }
                    coroutineScheduler.g.e(andDecrement, null);
                    ueu ueuVar = ueu.d;
                    this.d = WorkerState.TERMINATED;
                }
            }
        }

        private final void d(int i) {
            this.i = 0L;
            if (this.d == WorkerState.PARKING) {
                if (ASSERTIONS_ENABLED.a() && i != 1) {
                    throw new AssertionError();
                }
                this.d = WorkerState.BLOCKING;
            }
        }

        public final Task c(boolean z) {
            Task d;
            if (g()) {
                return d(z);
            }
            if (z) {
                d = this.c.e();
                if (d == null) {
                    d = CoroutineScheduler.this.e.d();
                }
            } else {
                d = CoroutineScheduler.this.e.d();
            }
            return d == null ? b(true) : d;
        }

        private final Task d(boolean z) {
            Task e2;
            Task e3;
            if (z) {
                boolean z2 = c(CoroutineScheduler.this.b * 2) == 0;
                if (z2 && (e3 = e()) != null) {
                    return e3;
                }
                Task e4 = this.c.e();
                if (e4 != null) {
                    return e4;
                }
                if (!z2 && (e2 = e()) != null) {
                    return e2;
                }
            } else {
                Task e5 = e();
                if (e5 != null) {
                    return e5;
                }
            }
            return b(false);
        }

        private final Task e() {
            if (c(2) == 0) {
                Task d = CoroutineScheduler.this.h.d();
                return d == null ? CoroutineScheduler.this.e.d() : d;
            }
            Task d2 = CoroutineScheduler.this.e.d();
            return d2 == null ? CoroutineScheduler.this.h.d() : d2;
        }

        private final Task b(boolean z) {
            long a2;
            if (ASSERTIONS_ENABLED.a() && this.c.c() != 0) {
                throw new AssertionError();
            }
            int i = (int) (CoroutineScheduler.this.controlState & 2097151);
            if (i < 2) {
                return null;
            }
            int c = c(i);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            int i2 = 0;
            long j = Long.MAX_VALUE;
            while (i2 < i) {
                i2++;
                c++;
                if (c > i) {
                    c = 1;
                }
                d d = coroutineScheduler.g.d(c);
                if (d != null && d != this) {
                    if (ASSERTIONS_ENABLED.a() && this.c.c() != 0) {
                        throw new AssertionError();
                    }
                    if (z) {
                        a2 = this.c.d(d.c);
                    } else {
                        a2 = this.c.a(d.c);
                    }
                    if (a2 == -1) {
                        return this.c.e();
                    }
                    if (a2 > 0) {
                        j = Math.min(j, a2);
                    }
                }
            }
            if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
                j = 0;
            }
            this.j = j;
            return null;
        }

        private final void a(Task task) {
            int b = task.taskContext.getB();
            d(b);
            a(b);
            CoroutineScheduler.this.b(task);
            e(b);
        }
    }

    private final boolean a(Task task) {
        if (task.taskContext.getB() == 1) {
            return this.e.c(task);
        }
        return this.h.c(task);
    }

    public final void a(d dVar, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j);
            if (i3 == i) {
                i3 = i2 == 0 ? a(dVar) : i2;
            }
            if (i3 >= 0 && l.compareAndSet(this, j, ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152)) | i3)) {
                return;
            }
        }
    }

    private final d c() {
        while (true) {
            long j = this.parkedWorkersStack;
            d d2 = this.g.d((int) (2097151 & j));
            if (d2 == null) {
                return null;
            }
            int a2 = a(d2);
            if (a2 >= 0 && l.compareAndSet(this, j, a2 | ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152)))) {
                d2.a(d);
                return d2;
            }
        }
    }

    private final void c(boolean z) {
        long addAndGet = f14499a.addAndGet(this, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
        if (z || i() || d(addAndGet)) {
            return;
        }
        i();
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public final /* synthetic */ class c {
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[WorkerState.values().length];
            iArr[WorkerState.PARKING.ordinal()] = 1;
            iArr[WorkerState.BLOCKING.ordinal()] = 2;
            iArr[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            iArr[WorkerState.DORMANT.ordinal()] = 4;
            iArr[WorkerState.TERMINATED.ordinal()] = 5;
            c = iArr;
        }
    }
}
