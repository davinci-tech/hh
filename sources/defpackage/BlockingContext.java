package defpackage;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlinx.coroutines.scheduling.SchedulerTimeSource;
import kotlinx.coroutines.scheduling.TaskContext;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\b\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0012\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000\"\u0019\u0010\u000f\u001a\u00020\u0010*\u00020\u00118À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0012¨\u0006\u0013"}, d2 = {"BlockingContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "CORE_POOL_SIZE", "", "DEFAULT_SCHEDULER_NAME", "", "IDLE_WORKER_KEEP_ALIVE_NS", "", "MAX_POOL_SIZE", "NonBlockingContext", "TASK_NON_BLOCKING", "TASK_PROBABLY_BLOCKING", "WORK_STEALING_TIME_RESOLUTION_NS", "schedulerTimeSource", "Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", "isBlocking", "", "Lkotlinx/coroutines/scheduling/Task;", "(Lkotlinx/coroutines/scheduling/Task;)Z", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: uql, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class BlockingContext {

    /* renamed from: a, reason: collision with root package name */
    public static final long f17503a;
    public static final int b;
    public static final TaskContext c;
    public static final TaskContext d;
    public static final int e;
    public static final long g;
    public static SchedulerTimeSource j;

    static {
        long c2;
        int d2;
        int d3;
        long c3;
        c2 = systemProp.c("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null);
        g = c2;
        d2 = systemProp.d("kotlinx.coroutines.scheduler.core.pool.size", uja.a(upy.d(), 2), 1, 0, 8, null);
        b = d2;
        d3 = systemProp.d("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);
        e = d3;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        c3 = systemProp.c("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null);
        f17503a = timeUnit.toNanos(c3);
        j = uqi.b;
        c = new uqk(0);
        d = new uqk(1);
    }
}
