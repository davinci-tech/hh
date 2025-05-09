package kotlinx.coroutines;

import defpackage.timeSource;
import defpackage.ueu;
import defpackage.ulj;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlinx.coroutines.EventLoopImplBase;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\bH\u0004R\u0012\u0010\u0003\u001a\u00020\u0004X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/EventLoop;", "()V", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "reschedule", "", "now", "", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "unpark", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    /* renamed from: getThread */
    protected abstract Thread getD();

    protected final void unpark() {
        ueu ueuVar;
        Thread d = getD();
        if (Thread.currentThread() != d) {
            AbstractTimeSource a2 = timeSource.a();
            if (a2 == null) {
                ueuVar = null;
            } else {
                a2.unpark(d);
                ueuVar = ueu.d;
            }
            if (ueuVar == null) {
                LockSupport.unpark(d);
            }
        }
    }

    protected void reschedule(long now, EventLoopImplBase.DelayedTask delayedTask) {
        ulj.e.schedule(now, delayedTask);
    }
}
