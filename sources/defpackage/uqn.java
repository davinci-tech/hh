package defpackage;

import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.scheduling.CoroutineScheduler;
import kotlinx.coroutines.scheduling.TaskContext;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0010\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\u001c\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0016J)\u0010\u0019\u001a\u00020\u00112\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u00182\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u001c\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0016J\r\u0010\u001f\u001a\u00020\u0011H\u0000¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0006H\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u0011H\u0000¢\u0006\u0002\b%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/SchedulerCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "idleWorkerKeepAliveNs", "", "schedulerName", "", "(IIJLjava/lang/String;)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", "createScheduler", "dispatch", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "tailDispatch", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "shutdown$kotlinx_coroutines_core", "usePrivateScheduler", "usePrivateScheduler$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class uqn extends ExecutorCoroutineDispatcher {

    /* renamed from: a, reason: collision with root package name */
    private final long f17505a;
    private final int b;
    private final int c;
    private CoroutineScheduler d;
    private final String e;

    public /* synthetic */ uqn(int i, int i2, long j, String str, int i3, uib uibVar) {
        this((i3 & 1) != 0 ? BlockingContext.b : i, (i3 & 2) != 0 ? BlockingContext.e : i2, (i3 & 4) != 0 ? BlockingContext.f17503a : j, (i3 & 8) != 0 ? "CoroutineScheduler" : str);
    }

    public uqn(int i, int i2, long j, String str) {
        this.c = i;
        this.b = i2;
        this.f17505a = j;
        this.e = str;
        this.d = d();
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.d;
    }

    private final CoroutineScheduler d() {
        return new CoroutineScheduler(this.c, this.b, this.f17505a, this.e);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext context, Runnable block) {
        CoroutineScheduler.a(this.d, block, null, false, 6, null);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext context, Runnable block) {
        CoroutineScheduler.a(this.d, block, null, true, 2, null);
    }

    public final void a(Runnable runnable, TaskContext taskContext, boolean z) {
        this.d.e(runnable, taskContext, z);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.d.close();
    }

    public uqn() {
        this(0, 0, 0L, null, 15, null);
    }
}
