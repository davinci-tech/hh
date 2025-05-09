package kotlinx.coroutines;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.DefaultDelay;
import defpackage.probeCoroutineCreated;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.ukr;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/Delay;", "", OpAnalyticsConstants.DELAY, "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface Delay {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    Object delay(long j, Continuation<? super ueu> continuation);

    DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context);

    void scheduleResumeAfterDelay(long timeMillis, CancellableContinuation<? super ueu> continuation);

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class c {
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
        public static Object b(Delay delay, long j, Continuation<? super ueu> continuation) {
            if (j <= 0) {
                return ueu.d;
            }
            ukr ukrVar = new ukr(ugw.a(continuation), 1);
            ukrVar.initCancellability();
            delay.scheduleResumeAfterDelay(j, ukrVar);
            Object e = ukrVar.e();
            if (e == ugw.a()) {
                probeCoroutineCreated.b(continuation);
            }
            return e == ugw.a() ? e : ueu.d;
        }

        public static DisposableHandle d(Delay delay, long j, Runnable runnable, CoroutineContext coroutineContext) {
            return DefaultDelay.d().invokeOnTimeout(j, runnable, coroutineContext);
        }
    }
}
