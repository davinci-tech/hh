package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import defpackage.uel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a.\u0010\u0011\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0015\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000fH\u0002\u001a\u0019\u0010\u0016\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a'\u0010\u0019\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u001dH\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n\"\u0018\u0010\u000b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\n¨\u0006\u001e"}, d2 = {"MODE_ATOMIC", "", "MODE_CANCELLABLE", "getMODE_CANCELLABLE$annotations", "()V", "MODE_CANCELLABLE_REUSABLE", "MODE_UNDISPATCHED", "MODE_UNINITIALIZED", "isCancellableMode", "", "(I)Z", "isReusableMode", "dispatch", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/DispatchedTask;", Wpt.MODE, "resume", "delegate", "Lkotlin/coroutines/Continuation;", "undispatched", "resumeUnconfined", "resumeWithStackTrace", TrackConstants$Events.EXCEPTION, "", "runUnconfinedEventLoop", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "block", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulq, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class MODE_ATOMIC {
    public static final boolean c(int i) {
        return i == 1 || i == 2;
    }

    public static final boolean d(int i) {
        return i == 2;
    }

    public static final <T> void c(DispatchedTask<? super T> dispatchedTask, int i) {
        if (ASSERTIONS_ENABLED.a() && i == -1) {
            throw new AssertionError();
        }
        Continuation<? super T> delegate$kotlinx_coroutines_core = dispatchedTask.getDelegate$kotlinx_coroutines_core();
        boolean z = i == 4;
        if (!z && (delegate$kotlinx_coroutines_core instanceof uoz) && c(i) == c(dispatchedTask.resumeMode)) {
            CoroutineDispatcher coroutineDispatcher = ((uoz) delegate$kotlinx_coroutines_core).b;
            CoroutineContext f17451a = delegate$kotlinx_coroutines_core.getF17451a();
            if (coroutineDispatcher.isDispatchNeeded(f17451a)) {
                coroutineDispatcher.dispatch(f17451a, dispatchedTask);
                return;
            } else {
                e(dispatchedTask);
                return;
            }
        }
        e(dispatchedTask, delegate$kotlinx_coroutines_core, z);
    }

    public static final <T> void e(DispatchedTask<? super T> dispatchedTask, Continuation<? super T> continuation, boolean z) {
        Object successfulResult$kotlinx_coroutines_core;
        umt<?> umtVar;
        boolean d;
        Object takeState$kotlinx_coroutines_core = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exceptionalResult$kotlinx_coroutines_core = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            uel.b bVar = uel.d;
            successfulResult$kotlinx_coroutines_core = createFailure.b(exceptionalResult$kotlinx_coroutines_core);
        } else {
            uel.b bVar2 = uel.d;
            successfulResult$kotlinx_coroutines_core = dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        }
        Object b = uel.b(successfulResult$kotlinx_coroutines_core);
        if (z) {
            uoz uozVar = (uoz) continuation;
            Continuation<T> continuation2 = uozVar.c;
            Object obj = uozVar.f17493a;
            CoroutineContext f17451a = continuation2.getF17451a();
            Object d2 = NO_THREAD_ELEMENTS.d(f17451a, obj);
            if (d2 != NO_THREAD_ELEMENTS.d) {
                umtVar = DEBUG_THREAD_NAME_SEPARATOR.c(continuation2, f17451a, d2);
            } else {
                umtVar = null;
            }
            try {
                uozVar.c.resumeWith(b);
                ueu ueuVar = ueu.d;
                if (umtVar != null) {
                    if (!d) {
                        return;
                    }
                }
                return;
            } finally {
                if (umtVar == null || umtVar.d()) {
                    NO_THREAD_ELEMENTS.a(f17451a, d2);
                }
            }
        }
        continuation.resumeWith(b);
    }

    private static final void e(DispatchedTask<?> dispatchedTask) {
        EventLoop e = umr.d.e();
        if (e.isUnconfinedLoopActive()) {
            e.dispatchUnconfined(dispatchedTask);
            return;
        }
        e.incrementUseCount(true);
        try {
            e(dispatchedTask, dispatchedTask.getDelegate$kotlinx_coroutines_core(), true);
            do {
            } while (e.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }
}
