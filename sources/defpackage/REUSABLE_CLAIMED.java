package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import defpackage.uel;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082\b\u001aU\u0010\u0011\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00152%\b\u0002\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00100\bH\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"REUSABLE_CLAIMED", "Lkotlinx/coroutines/internal/Symbol;", "getREUSABLE_CLAIMED$annotations", "()V", "UNDEFINED", "getUNDEFINED$annotations", "executeUnconfined", "", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "contState", "", Wpt.MODE, "", "doYield", "block", "Lkotlin/Function0;", "", "resumeCancellableWith", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/coroutines/Continuation;", "result", "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "yieldUndispatched", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: upe, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class REUSABLE_CLAIMED {
    private static final upu d = new upu("UNDEFINED");
    public static final upu e = new upu("REUSABLE_CLAIMED");

    public static /* synthetic */ void a(Continuation continuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        a(continuation, obj, function1);
    }

    public static final <T> void a(Continuation<? super T> continuation, Object obj, Function1<? super Throwable, ueu> function1) {
        umt<?> umtVar;
        if (!(continuation instanceof uoz)) {
            continuation.resumeWith(obj);
            return;
        }
        uoz uozVar = (uoz) continuation;
        Object a2 = recoverResult.a(obj, function1);
        if (uozVar.b.isDispatchNeeded(uozVar.getContext())) {
            uozVar.d = a2;
            uozVar.resumeMode = 1;
            uozVar.b.dispatch(uozVar.getContext(), uozVar);
            return;
        }
        ASSERTIONS_ENABLED.a();
        EventLoop e2 = umr.d.e();
        if (e2.isUnconfinedLoopActive()) {
            uozVar.d = a2;
            uozVar.resumeMode = 1;
            e2.dispatchUnconfined(uozVar);
            return;
        }
        uoz uozVar2 = uozVar;
        e2.incrementUseCount(true);
        try {
            Job job = (Job) uozVar.getContext().get(Job.INSTANCE);
            if (job != null && !job.isActive()) {
                CancellationException cancellationException = job.getCancellationException();
                uozVar.cancelCompletedResult$kotlinx_coroutines_core(a2, cancellationException);
                uel.b bVar = uel.d;
                uozVar.resumeWith(uel.b(createFailure.b((Throwable) cancellationException)));
            } else {
                Continuation<T> continuation2 = uozVar.c;
                Object obj2 = uozVar.f17493a;
                CoroutineContext context = continuation2.getContext();
                Object d2 = NO_THREAD_ELEMENTS.d(context, obj2);
                if (d2 != NO_THREAD_ELEMENTS.d) {
                    umtVar = DEBUG_THREAD_NAME_SEPARATOR.c(continuation2, context, d2);
                } else {
                    umtVar = null;
                }
                try {
                    uozVar.c.resumeWith(obj);
                    ueu ueuVar = ueu.d;
                } finally {
                    if (umtVar == null || umtVar.d()) {
                        NO_THREAD_ELEMENTS.a(context, d2);
                    }
                }
            }
            while (e2.processUnconfinedEvent()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }
}
