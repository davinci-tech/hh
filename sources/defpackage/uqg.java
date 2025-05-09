package defpackage;

import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0017J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0017J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/scheduling/DefaultIoScheduler;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "()V", "default", "Lkotlinx/coroutines/CoroutineDispatcher;", "executor", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", "dispatch", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "execute", AwarenessRequest.Field.COMMAND, "limitedParallelism", "parallelism", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uqg extends ExecutorCoroutineDispatcher implements Executor {
    public static final uqg b = new uqg();
    private static final CoroutineDispatcher d;

    private uqg() {
    }

    static {
        int d2;
        uqj uqjVar = uqj.d;
        d2 = systemProp.d("kotlinx.coroutines.io.parallelism", uja.a(64, upy.d()), 0, 0, 12, null);
        d = uqjVar.limitedParallelism(d2);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        dispatch(ugq.f17420a, command);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int parallelism) {
        return uqj.d.limitedParallelism(parallelism);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext context, Runnable block) {
        d.dispatch(context, block);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext context, Runnable block) {
        d.dispatchYield(context, block);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.IO";
    }
}
