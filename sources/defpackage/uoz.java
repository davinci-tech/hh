package defpackage;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000O2\u00060?j\u0002`@2\b\u0012\u0004\u0012\u00028\u00000\u0004B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ!\u0010\u0011\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001d\u001a\n\u0018\u00010\u001bj\u0004\u0018\u0001`\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\b¢\u0006\u0004\b$\u0010\nJH\u0010+\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%2%\b\b\u0010*\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010'H\u0086\bø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001a\u0010.\u001a\u00020\u001f2\b\u0010-\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b.\u0010/J!\u00100\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0086\bø\u0001\u0000¢\u0006\u0004\b0\u00101J \u00102\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0016ø\u0001\u0000¢\u0006\u0004\b2\u00101J\u0011\u00105\u001a\u0004\u0018\u00010\u000bH\u0010¢\u0006\u0004\b3\u00104J\u000f\u00107\u001a\u000206H\u0016¢\u0006\u0004\b7\u00108J\u001b\u0010:\u001a\u0004\u0018\u00010\r2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u000309¢\u0006\u0004\b:\u0010;R\u001e\u0010<\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b<\u0010=\u0012\u0004\b>\u0010\nR\u001c\u0010C\u001a\n\u0018\u00010?j\u0004\u0018\u0001`@8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0014\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010FR\u0014\u0010G\u001a\u00020\u000b8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\bG\u0010=R\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010KR\u001a\u0010M\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlin/coroutines/Continuation;", "continuation", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "", "awaitReusability", "()V", "", "takenState", "", "cause", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "Lkotlinx/coroutines/CancellableContinuationImpl;", "claimReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlin/coroutines/CoroutineContext;", ParamConstants.Param.CONTEXT, "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "dispatchYield", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "", "isReusable", "()Z", "postponeCancellation", "(Ljava/lang/Throwable;)Z", "release", "Lkotlin/Result;", "result", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onCancellation", "resumeCancellableWith", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "state", "resumeCancelled", "(Ljava/lang/Object;)Z", "resumeUndispatchedWith", "(Ljava/lang/Object;)V", "resumeWith", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuation;", "tryReleaseClaimedContinuation", "(Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Throwable;", "_state", "Ljava/lang/Object;", "get_state$kotlinx_coroutines_core$annotations", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "countOrElement", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "Lkotlinx/coroutines/CoroutineDispatcher;", "getReusableCancellableContinuation", "reusableCancellableContinuation", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class uoz<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(uoz.class, Object.class, "_reusableCancellableContinuation");
    private volatile /* synthetic */ Object _reusableCancellableContinuation;

    /* renamed from: a, reason: collision with root package name */
    public final Object f17493a;
    public final CoroutineDispatcher b;
    public final Continuation<T> c;
    public Object d;

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public uoz(CoroutineDispatcher coroutineDispatcher, Continuation<? super T> continuation) {
        super(-1);
        upu upuVar;
        this.b = coroutineDispatcher;
        this.c = continuation;
        upuVar = REUSABLE_CLAIMED.d;
        this.d = upuVar;
        this.f17493a = NO_THREAD_ELEMENTS.b(getContext());
        this._reusableCancellableContinuation = null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.c;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    private final ukr<?> c() {
        Object obj = this._reusableCancellableContinuation;
        if (obj instanceof ukr) {
            return (ukr) obj;
        }
        return null;
    }

    public final boolean e() {
        return this._reusableCancellableContinuation != null;
    }

    public final void a() {
        b();
        ukr<?> c = c();
        if (c == null) {
            return;
        }
        c.c();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        upu upuVar;
        upu upuVar2;
        Object obj = this.d;
        if (ASSERTIONS_ENABLED.a()) {
            upuVar2 = REUSABLE_CLAIMED.d;
            if (obj == upuVar2) {
                throw new AssertionError();
            }
        }
        upuVar = REUSABLE_CLAIMED.d;
        this.d = upuVar;
        return obj;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        CoroutineContext context;
        Object d;
        CoroutineContext context2 = this.c.getContext();
        Object a2 = recoverResult.a(result, null, 1, null);
        if (this.b.isDispatchNeeded(context2)) {
            this.d = a2;
            this.resumeMode = 0;
            this.b.dispatch(context2, this);
            return;
        }
        ASSERTIONS_ENABLED.a();
        EventLoop e2 = umr.d.e();
        if (e2.isUnconfinedLoopActive()) {
            this.d = a2;
            this.resumeMode = 0;
            e2.dispatchUnconfined(this);
            return;
        }
        uoz<T> uozVar = this;
        e2.incrementUseCount(true);
        try {
            context = getContext();
            d = NO_THREAD_ELEMENTS.d(context, this.f17493a);
        } finally {
            try {
            } finally {
            }
        }
        try {
            this.c.resumeWith(result);
            ueu ueuVar = ueu.d;
            while (e2.processUnconfinedEvent()) {
            }
        } finally {
            NO_THREAD_ELEMENTS.a(context, d);
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object takenState, Throwable cause) {
        if (takenState instanceof ukz) {
            ((ukz) takenState).d.invoke(cause);
        }
    }

    public String toString() {
        return "DispatchedContinuation[" + this.b + ", " + classSimpleName.e(this.c) + ']';
    }

    public final void b() {
        while (this._reusableCancellableContinuation == REUSABLE_CLAIMED.e) {
        }
    }

    public final ukr<T> d() {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = REUSABLE_CLAIMED.e;
                return null;
            }
            if (obj instanceof ukr) {
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, this, obj, REUSABLE_CLAIMED.e)) {
                    return (ukr) obj;
                }
            } else if (obj != REUSABLE_CLAIMED.e && !(obj instanceof Throwable)) {
                throw new IllegalStateException(uhy.b("Inconsistent state ", obj).toString());
            }
        }
    }

    public final Throwable c(CancellableContinuation<?> cancellableContinuation) {
        do {
            Object obj = this._reusableCancellableContinuation;
            if (obj != REUSABLE_CLAIMED.e) {
                if (obj instanceof Throwable) {
                    if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, this, obj, (Object) null)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    return (Throwable) obj;
                }
                throw new IllegalStateException(uhy.b("Inconsistent state ", obj).toString());
            }
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, this, REUSABLE_CLAIMED.e, cancellableContinuation));
        return null;
    }

    public final boolean d(Throwable th) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            if (uhy.e(obj, REUSABLE_CLAIMED.e)) {
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, this, REUSABLE_CLAIMED.e, th)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, this, obj, (Object) null)) {
                    return false;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.c.getContext();
    }
}
