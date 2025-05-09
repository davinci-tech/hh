package defpackage;

import androidx.exifinterface.media.ExifInterface;
import defpackage.uel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001aI\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a.\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"recoverResult", "Lkotlin/Result;", ExifInterface.GPS_DIRECTION_TRUE, "state", "", "uCont", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toState", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "caller", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulb, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class recoverResult {
    public static /* synthetic */ Object a(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return a(obj, function1);
    }

    public static final <T> Object a(Object obj, Function1<? super Throwable, ueu> function1) {
        Throwable e = uel.e(obj);
        if (e == null) {
            return function1 != null ? new ukz(obj, function1) : obj;
        }
        return new ula(e, false, 2, null);
    }

    public static final <T> Object e(Object obj, CancellableContinuation<?> cancellableContinuation) {
        Throwable e = uel.e(obj);
        if (e == null) {
            return obj;
        }
        if (ASSERTIONS_ENABLED.b()) {
            CancellableContinuation<?> cancellableContinuation2 = cancellableContinuation;
            if (cancellableContinuation2 instanceof CoroutineStackFrame) {
                e = baseContinuationImplClass.c(e, (CoroutineStackFrame) cancellableContinuation2);
            }
        }
        return new ula(e, false, 2, null);
    }

    public static final <T> Object c(Object obj, Continuation<? super T> continuation) {
        if (obj instanceof ula) {
            uel.b bVar = uel.d;
            Throwable th = ((ula) obj).d;
            if (ASSERTIONS_ENABLED.b() && (continuation instanceof CoroutineStackFrame)) {
                th = baseContinuationImplClass.c(th, (CoroutineStackFrame) continuation);
            }
            return uel.b(createFailure.b(th));
        }
        uel.b bVar2 = uel.d;
        return uel.b(obj);
    }
}
