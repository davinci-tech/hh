package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0014J\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bR\"\u0010\b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/internal/ScopeCoroutine;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "threadStateToRecover", "Ljava/lang/ThreadLocal;", "Lkotlin/Pair;", "", "afterResume", "", "state", "clearThreadContext", "", "saveThreadContext", "oldValue", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class umt<T> extends upq<T> {

    /* renamed from: a, reason: collision with root package name */
    private ThreadLocal<ueo<CoroutineContext, Object>> f17470a;

    public final void e(CoroutineContext coroutineContext, Object obj) {
        this.f17470a.set(to.d(coroutineContext, obj));
    }

    public final boolean d() {
        if (this.f17470a.get() == null) {
            return false;
        }
        this.f17470a.set(null);
        return true;
    }

    @Override // defpackage.upq, kotlinx.coroutines.AbstractCoroutine
    public void afterResume(Object state) {
        ueo<CoroutineContext, Object> ueoVar = this.f17470a.get();
        umt<?> umtVar = null;
        if (ueoVar != null) {
            NO_THREAD_ELEMENTS.a(ueoVar.b(), ueoVar.d());
            this.f17470a.set(null);
        }
        Object c = recoverResult.c(state, this.e);
        Continuation<T> continuation = this.e;
        CoroutineContext context = continuation.getContext();
        Object d = NO_THREAD_ELEMENTS.d(context, null);
        if (d != NO_THREAD_ELEMENTS.d) {
            umtVar = DEBUG_THREAD_NAME_SEPARATOR.c(continuation, context, d);
        }
        try {
            this.e.resumeWith(c);
            ueu ueuVar = ueu.d;
        } finally {
            if (umtVar == null || umtVar.d()) {
                NO_THREAD_ELEMENTS.a(context, d);
            }
        }
    }
}
