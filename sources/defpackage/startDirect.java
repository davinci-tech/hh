package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.tencent.open.SocialConstants;
import defpackage.uel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a9\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u001a\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0082\b\u001a>\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aR\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b2\u0006\u0010\f\u001a\u0002H\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a>\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aR\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b2\u0006\u0010\f\u001a\u0002H\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001aY\u0010\u000f\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001a\u0002H\n2'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u0011H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aY\u0010\u0013\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001a\u0002H\n2'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u0011H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a?\u0010\u0014\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0019H\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"startDirect", "", ExifInterface.GPS_DIRECTION_TRUE, "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function1;", "", "startCoroutineUndispatched", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", SocialConstants.PARAM_RECEIVER, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startCoroutineUnintercepted", "startUndispatchedOrReturn", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/internal/ScopeCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startUndispatchedOrReturnIgnoreTimeout", "undispatchedResult", "shouldThrow", "", "", "startBlock", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: uqe, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class startDirect {
    public static final <T, R> Object d(upq<? super T> upqVar, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object ulaVar;
        Object makeCompletingOnce$kotlinx_coroutines_core;
        Throwable c;
        try {
            ulaVar = ((Function2) uii.e(function2, 2)).invoke(r, upqVar);
        } catch (Throwable th) {
            ulaVar = new ula(th, false, 2, null);
        }
        if (ulaVar != ugw.a() && (makeCompletingOnce$kotlinx_coroutines_core = upqVar.makeCompletingOnce$kotlinx_coroutines_core(ulaVar)) != COMPLETING_ALREADY.d) {
            if (makeCompletingOnce$kotlinx_coroutines_core instanceof ula) {
                ula ulaVar2 = (ula) makeCompletingOnce$kotlinx_coroutines_core;
                Throwable th2 = ulaVar2.d;
                Throwable th3 = ulaVar2.d;
                Continuation<? super T> continuation = upqVar.e;
                if (!ASSERTIONS_ENABLED.b() || !(continuation instanceof CoroutineStackFrame)) {
                    throw th3;
                }
                c = baseContinuationImplClass.c(th3, (CoroutineStackFrame) continuation);
                throw c;
            }
            return COMPLETING_ALREADY.c(makeCompletingOnce$kotlinx_coroutines_core);
        }
        return ugw.a();
    }

    public static final <T> void e(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        Continuation e = probeCoroutineCreated.e(continuation);
        try {
            Object invoke = ((Function1) uii.e(function1, 1)).invoke(e);
            if (invoke != ugw.a()) {
                uel.b bVar = uel.d;
                e.resumeWith(uel.b(invoke));
            }
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            e.resumeWith(uel.b(createFailure.b(th)));
        }
    }

    public static final <R, T> void e(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation) {
        Continuation e = probeCoroutineCreated.e(continuation);
        try {
            Object invoke = ((Function2) uii.e(function2, 2)).invoke(r, e);
            if (invoke != ugw.a()) {
                uel.b bVar = uel.d;
                e.resumeWith(uel.b(invoke));
            }
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            e.resumeWith(uel.b(createFailure.b(th)));
        }
    }

    public static final <T> void d(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        Continuation e = probeCoroutineCreated.e(continuation);
        try {
            CoroutineContext f17451a = continuation.getF17451a();
            Object d = NO_THREAD_ELEMENTS.d(f17451a, null);
            try {
                Object invoke = ((Function1) uii.e(function1, 1)).invoke(e);
                if (invoke != ugw.a()) {
                    uel.b bVar = uel.d;
                    e.resumeWith(uel.b(invoke));
                }
            } finally {
                NO_THREAD_ELEMENTS.a(f17451a, d);
            }
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            e.resumeWith(uel.b(createFailure.b(th)));
        }
    }

    public static final <R, T> void c(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation) {
        Continuation e = probeCoroutineCreated.e(continuation);
        try {
            CoroutineContext f17451a = continuation.getF17451a();
            Object d = NO_THREAD_ELEMENTS.d(f17451a, null);
            try {
                Object invoke = ((Function2) uii.e(function2, 2)).invoke(r, e);
                if (invoke != ugw.a()) {
                    uel.b bVar = uel.d;
                    e.resumeWith(uel.b(invoke));
                }
            } finally {
                NO_THREAD_ELEMENTS.a(f17451a, d);
            }
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            e.resumeWith(uel.b(createFailure.b(th)));
        }
    }
}
