package defpackage;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.network.embedded.r3;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.NotCompleted;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\t\u0012\u0004\u0012\u00028\u00000\u008a\u00012\t\u0012\u0004\u0012\u00028\u00000\u008b\u00012\u00060tj\u0002`uB\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0012\u0010\u0013JB\u0010\u0012\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0018J\u001e\u0010\u001b\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0019H\u0082\b¢\u0006\u0004\b\u001b\u0010\u001cJ8\u0010\u001e\u001a\u00020\u00112!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u00142\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001e\u0010\u0018J\u0019\u0010 \u001a\u00020\u001f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0004\b \u0010!J!\u0010%\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0010¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b&\u0010!J\u0017\u0010(\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\bH\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010,\u001a\u00020\u0011H\u0000¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020\u0011H\u0002¢\u0006\u0004\b-\u0010+J\u0017\u0010/\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0004H\u0002¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u000f2\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J\u001b\u00108\u001a\u0004\u0018\u00010\u000f2\b\u00105\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b6\u00107J\u0011\u00109\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0004\b9\u0010:J\u0017\u0010=\u001a\n\u0018\u00010;j\u0004\u0018\u0001`<H\u0016¢\u0006\u0004\b=\u0010>J\u001f\u0010A\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u00105\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\b?\u0010@J\u000f\u0010B\u001a\u00020\u0011H\u0016¢\u0006\u0004\bB\u0010+J\u0011\u0010D\u001a\u0004\u0018\u00010CH\u0002¢\u0006\u0004\bD\u0010EJ8\u0010F\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u0017H\u0016¢\u0006\u0004\bF\u0010GJ\u000f\u0010H\u001a\u00020\u001fH\u0002¢\u0006\u0004\bH\u0010IJ8\u0010J\u001a\u00020\r2'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u0017H\u0002¢\u0006\u0004\bJ\u0010KJB\u0010L\u001a\u00020\u00112'\u0010\u000e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u0014j\u0002`\u00172\b\u00105\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bL\u0010MJ\u000f\u0010O\u001a\u00020NH\u0014¢\u0006\u0004\bO\u0010PJ\u0017\u0010S\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\bQ\u0010RJ\u000f\u0010T\u001a\u00020\u0011H\u0002¢\u0006\u0004\bT\u0010+J\u000f\u0010U\u001a\u00020\u001fH\u0001¢\u0006\u0004\bU\u0010IJ<\u0010W\u001a\u00020\u00112\u0006\u0010V\u001a\u00028\u00002#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0016¢\u0006\u0004\bW\u0010XJH\u0010Y\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042%\b\u0002\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0002¢\u0006\u0004\bY\u0010ZJ \u0010]\u001a\u00020\u00112\f\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000[H\u0016ø\u0001\u0000¢\u0006\u0004\b]\u0010)JZ\u0010`\u001a\u0004\u0018\u00010\b2\u0006\u00105\u001a\u00020^2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00142\b\u0010_\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b`\u0010aJ\u0011\u0010c\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0004\bb\u0010:J\u000f\u0010d\u001a\u00020NH\u0016¢\u0006\u0004\bd\u0010PJ\u000f\u0010e\u001a\u00020\u001fH\u0002¢\u0006\u0004\be\u0010IJ#\u0010e\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00028\u00002\b\u0010_\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\be\u0010fJH\u0010e\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00028\u00002\b\u0010_\u001a\u0004\u0018\u00010\b2#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0016¢\u0006\u0004\be\u0010gJJ\u0010i\u001a\u0004\u0018\u00010h2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010_\u001a\u0004\u0018\u00010\b2#\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0014H\u0002¢\u0006\u0004\bi\u0010jJ\u0019\u0010l\u001a\u0004\u0018\u00010\b2\u0006\u0010k\u001a\u00020\u000fH\u0016¢\u0006\u0004\bl\u0010mJ\u000f\u0010n\u001a\u00020\u001fH\u0002¢\u0006\u0004\bn\u0010IJ\u001b\u0010p\u001a\u00020\u0011*\u00020o2\u0006\u0010V\u001a\u00028\u0000H\u0016¢\u0006\u0004\bp\u0010qJ\u001b\u0010r\u001a\u00020\u0011*\u00020o2\u0006\u0010k\u001a\u00020\u000fH\u0016¢\u0006\u0004\br\u0010sR\u001c\u0010x\u001a\n\u0018\u00010tj\u0004\u0018\u0001`u8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bv\u0010wR\u001a\u0010z\u001a\u00020y8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bz\u0010{\u001a\u0004\b|\u0010}R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0080\u0004¢\u0006\r\n\u0004\b\u0003\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010IR\u0016\u0010\u0082\u0001\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010IR\u0016\u0010\u0083\u0001\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010IR\u001b\u0010\u0084\u0001\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0017\u00105\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0086\u0001\u0010:R\u0016\u0010\u0088\u0001\u001a\u00020N8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010P\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0089\u0001"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/coroutines/Continuation;", "delegate", "", "resumeMode", "<init>", "(Lkotlin/coroutines/Continuation;I)V", "", "proposedUpdate", "", "alreadyResumedError", "(Ljava/lang/Object;)Ljava/lang/Void;", "Lkotlinx/coroutines/CancelHandler;", "handler", "", "cause", "", "callCancelHandler", "(Lkotlinx/coroutines/CancelHandler;Ljava/lang/Throwable;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Throwable;)V", "Lkotlin/Function0;", "block", "callCancelHandlerSafely", "(Lkotlin/jvm/functions/Function0;)V", "onCancellation", "callOnCancellation", "", "cancel", "(Ljava/lang/Throwable;)Z", "takenState", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "cancelLater", "token", "completeResume", "(Ljava/lang/Object;)V", "detachChild$kotlinx_coroutines_core", "()V", "detachChild", "detachChildIfNonResuable", Wpt.MODE, "dispatchResume", "(I)V", "Lkotlinx/coroutines/Job;", "parent", "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "state", "getExceptionalResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getExceptionalResult", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "initCancellability", "Lkotlinx/coroutines/DisposableHandle;", "installParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "isReusable", "()Z", "makeCancelHandler", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/CancelHandler;", "multipleHandlersError", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)V", "", "nameString", "()Ljava/lang/String;", "parentCancelled$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "parentCancelled", "releaseClaimedReusableContinuation", "resetStateReusable", "value", "resume", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeImpl", "(Ljava/lang/Object;ILkotlin/jvm/functions/Function1;)V", "Lkotlin/Result;", "result", "resumeWith", "Lkotlinx/coroutines/NotCompleted;", "idempotent", "resumedState", "(Lkotlinx/coroutines/NotCompleted;Ljava/lang/Object;ILkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/lang/Object;", "takeState$kotlinx_coroutines_core", "takeState", "toString", "tryResume", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeImpl", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/Symbol;", TrackConstants$Events.EXCEPTION, "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "trySuspend", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/CoroutineContext;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", r3.B, "isCancelled", "isCompleted", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "getState$kotlinx_coroutines_core", "getStateDebugRepresentation", "stateDebugRepresentation", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public class ukr<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    private volatile /* synthetic */ int _decision;
    private volatile /* synthetic */ Object _state;

    /* renamed from: a, reason: collision with root package name */
    private final CoroutineContext f17451a;
    private final Continuation<T> b;
    private DisposableHandle e;
    private static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(ukr.class, "_decision");
    private static final /* synthetic */ AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(ukr.class, Object.class, "_state");

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ukr(Continuation<? super T> continuation, int i) {
        super(i);
        this.b = continuation;
        if (ASSERTIONS_ENABLED.a() && i == -1) {
            throw new AssertionError();
        }
        this.f17451a = continuation.getContext();
        this._decision = 0;
        this._state = ukt.c;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f17451a;
    }

    /* renamed from: b, reason: from getter */
    public final Object get_state() {
        return this._state;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return get_state() instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(get_state() instanceof NotCompleted);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return get_state() instanceof ukw;
    }

    private final String h() {
        Object obj = get_state();
        return obj instanceof NotCompleted ? "Active" : obj instanceof ukw ? "Cancelled" : "Completed";
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void initCancellability() {
        DisposableHandle f = f();
        if (f != null && isCompleted()) {
            f.dispose();
            this.e = umi.e;
        }
    }

    private final boolean i() {
        return MODE_ATOMIC.d(this.resumeMode) && ((uoz) this.b).e();
    }

    public final boolean d() {
        if (ASSERTIONS_ENABLED.a() && this.resumeMode != 2) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && this.e == umi.e) {
            throw new AssertionError();
        }
        Object obj = this._state;
        if (ASSERTIONS_ENABLED.a() && !(!(obj instanceof NotCompleted))) {
            throw new AssertionError();
        }
        if ((obj instanceof uld) && ((uld) obj).f17454a != null) {
            c();
            return false;
        }
        this._decision = 0;
        this._state = ukt.c;
        return true;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.b;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        return get_state();
    }

    private final boolean d(Throwable th) {
        if (i()) {
            return ((uoz) this.b).d(th);
        }
        return false;
    }

    public final void c(Throwable th) {
        if (d(th)) {
            return;
        }
        cancel(th);
        g();
    }

    public final void c(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandler.b(getContext(), new ulc(uhy.b("Exception in invokeOnCancellation handler for ", this), th2));
        }
    }

    public final void d(Function1<? super Throwable, ueu> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandler.b(getContext(), new ulc(uhy.b("Exception in resume onCancellation handler for ", this), th2));
        }
    }

    public Throwable c(Job job) {
        return job.getCancellationException();
    }

    public final Object e() {
        Job job;
        Throwable c2;
        Throwable c3;
        boolean i = i();
        if (m()) {
            if (this.e == null) {
                f();
            }
            if (i) {
                j();
            }
            return ugw.a();
        }
        if (i) {
            j();
        }
        Object obj = get_state();
        if (!(obj instanceof ula)) {
            if (MODE_ATOMIC.c(this.resumeMode) && (job = (Job) getContext().get(Job.INSTANCE)) != null && !job.isActive()) {
                CancellationException cancellationException = job.getCancellationException();
                cancelCompletedResult$kotlinx_coroutines_core(obj, cancellationException);
                if (!ASSERTIONS_ENABLED.b()) {
                    throw cancellationException;
                }
                ukr<T> ukrVar = this;
                if (!(ukrVar instanceof CoroutineStackFrame)) {
                    throw cancellationException;
                }
                c2 = baseContinuationImplClass.c(cancellationException, ukrVar);
                throw c2;
            }
            return getSuccessfulResult$kotlinx_coroutines_core(obj);
        }
        Throwable th = ((ula) obj).d;
        if (!ASSERTIONS_ENABLED.b()) {
            throw th;
        }
        ukr<T> ukrVar2 = this;
        if (!(ukrVar2 instanceof CoroutineStackFrame)) {
            throw th;
        }
        c3 = baseContinuationImplClass.c(th, ukrVar2);
        throw c3;
    }

    private final DisposableHandle f() {
        Job job = (Job) getContext().get(Job.INSTANCE);
        if (job == null) {
            return null;
        }
        DisposableHandle a2 = Job.e.a(job, true, false, new uku(this), 2, null);
        this.e = a2;
        return a2;
    }

    private final void j() {
        Continuation<T> continuation = this.b;
        uoz uozVar = continuation instanceof uoz ? (uoz) continuation : null;
        Throwable c2 = uozVar != null ? uozVar.c(this) : null;
        if (c2 == null) {
            return;
        }
        c();
        cancel(c2);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        c(this, recoverResult.e(result, this), this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T value, Function1<? super Throwable, ueu> onCancellation) {
        c(value, this.resumeMode, onCancellation);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1<? super Throwable, ueu> handler) {
        CancelHandler e = e(handler);
        while (true) {
            Object obj = this._state;
            if (obj instanceof ukt) {
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, e)) {
                    return;
                }
            } else if (obj instanceof CancelHandler) {
                c(handler, obj);
            } else {
                boolean z = obj instanceof ula;
                if (z) {
                    ula ulaVar = (ula) obj;
                    if (!ulaVar.b()) {
                        c(handler, obj);
                    }
                    if (obj instanceof ukw) {
                        if (!z) {
                            ulaVar = null;
                        }
                        e(handler, ulaVar != null ? ulaVar.d : null);
                        return;
                    }
                    return;
                }
                if (obj instanceof uld) {
                    uld uldVar = (uld) obj;
                    if (uldVar.d != null) {
                        c(handler, obj);
                    }
                    if (e instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (uldVar.d()) {
                        e(handler, uldVar.c);
                        return;
                    } else {
                        if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, uld.c(uldVar, null, e, null, null, null, 29, null))) {
                            return;
                        }
                    }
                } else {
                    if (e instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, new uld(obj, e, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    private final void c(Function1<? super Throwable, ueu> function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    private final CancelHandler e(Function1<? super Throwable, ueu> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new uly(function1);
    }

    private final void a(int i) {
        if (l()) {
            return;
        }
        MODE_ATOMIC.c(this, i);
    }

    private final Object a(NotCompleted notCompleted, Object obj, int i, Function1<? super Throwable, ueu> function1, Object obj2) {
        if (obj instanceof ula) {
            if (ASSERTIONS_ENABLED.a() && obj2 != null) {
                throw new AssertionError();
            }
            if (!ASSERTIONS_ENABLED.a() || function1 == null) {
                return obj;
            }
            throw new AssertionError();
        }
        if (!MODE_ATOMIC.c(i) && obj2 == null) {
            return obj;
        }
        if (function1 != null || (((notCompleted instanceof CancelHandler) && !(notCompleted instanceof BeforeResumeCancelHandler)) || obj2 != null)) {
            return new uld(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, null, 16, null);
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void c(ukr ukrVar, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        ukrVar.c(obj, i, function1);
    }

    private final Void b(Object obj) {
        throw new IllegalStateException(uhy.b("Already resumed, but proposed with update ", obj).toString());
    }

    private final void g() {
        if (i()) {
            return;
        }
        c();
    }

    public final void c() {
        DisposableHandle disposableHandle = this.e;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this.e = umi.e;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T value, Object idempotent) {
        return e(value, idempotent, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T value, Object idempotent, Function1<? super Throwable, ueu> onCancellation) {
        return e(value, idempotent, onCancellation);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResumeWithException(Throwable exception) {
        return e(new ula(exception, false, 2, null), null, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object token) {
        if (ASSERTIONS_ENABLED.a() && token != RESUMED.c) {
            throw new AssertionError();
        }
        a(this.resumeMode);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.b;
        uoz uozVar = continuation instanceof uoz ? (uoz) continuation : null;
        c(this, t, (uozVar != null ? uozVar.b : null) == coroutineDispatcher ? 4 : this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(CoroutineDispatcher coroutineDispatcher, Throwable th) {
        Continuation<T> continuation = this.b;
        uoz uozVar = continuation instanceof uoz ? (uoz) continuation : null;
        c(this, new ula(th, false, 2, null), (uozVar != null ? uozVar.b : null) == coroutineDispatcher ? 4 : this.resumeMode, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object state) {
        return state instanceof uld ? (T) ((uld) state).b : state;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object state) {
        Throwable c2;
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(state);
        if (exceptionalResult$kotlinx_coroutines_core == null) {
            return null;
        }
        Continuation<T> delegate$kotlinx_coroutines_core = getDelegate$kotlinx_coroutines_core();
        if (!ASSERTIONS_ENABLED.b() || !(delegate$kotlinx_coroutines_core instanceof CoroutineStackFrame)) {
            return exceptionalResult$kotlinx_coroutines_core;
        }
        c2 = baseContinuationImplClass.c(exceptionalResult$kotlinx_coroutines_core, (CoroutineStackFrame) delegate$kotlinx_coroutines_core);
        return c2;
    }

    public String toString() {
        return a() + g4.k + classSimpleName.e(this.b) + "){" + h() + "}@" + classSimpleName.d(this);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object takenState, Throwable cause) {
        while (true) {
            Object obj = this._state;
            if (obj instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj instanceof ula) {
                return;
            }
            if (obj instanceof uld) {
                uld uldVar = (uld) obj;
                if (!(!uldVar.d())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, uld.c(uldVar, null, null, null, null, cause, 15, null))) {
                    uldVar.e(this, cause);
                    return;
                }
            } else if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, new uld(obj, null, null, null, cause, 14, null))) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(Throwable cause) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj, new ukw(this, cause, z)));
        CancelHandler cancelHandler = z ? (CancelHandler) obj : null;
        if (cancelHandler != null) {
            c(cancelHandler, cause);
        }
        g();
        a(this.resumeMode);
        return true;
    }

    private final void e(Function1<? super Throwable, ueu> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandler.b(getContext(), new ulc(uhy.b("Exception in invokeOnCancellation handler for ", this), th2));
        }
    }

    private final boolean m() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!d.compareAndSet(this, 0, 1));
        return true;
    }

    private final boolean l() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!d.compareAndSet(this, 0, 2));
        return true;
    }

    private final void c(Object obj, int i, Function1<? super Throwable, ueu> function1) {
        Object obj2;
        do {
            obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
            } else {
                if (obj2 instanceof ukw) {
                    ukw ukwVar = (ukw) obj2;
                    if (ukwVar.a()) {
                        if (function1 == null) {
                            return;
                        }
                        d(function1, ukwVar.d);
                        return;
                    }
                }
                b(obj);
                throw new uek();
            }
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj2, a((NotCompleted) obj2, obj, i, function1, null)));
        g();
        a(i);
    }

    private final upu e(Object obj, Object obj2, Function1<? super Throwable, ueu> function1) {
        Object obj3;
        do {
            obj3 = this._state;
            if (obj3 instanceof NotCompleted) {
            } else {
                if (!(obj3 instanceof uld)) {
                    return null;
                }
                if (obj2 != null) {
                    uld uldVar = (uld) obj3;
                    if (uldVar.f17454a == obj2) {
                        if (!ASSERTIONS_ENABLED.a() || uhy.e(uldVar.b, obj)) {
                            return RESUMED.c;
                        }
                        throw new AssertionError();
                    }
                }
                return null;
            }
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, obj3, a((NotCompleted) obj3, obj, this.resumeMode, function1, obj2)));
        g();
        return RESUMED.c;
    }

    protected String a() {
        return "CancellableContinuation";
    }
}
