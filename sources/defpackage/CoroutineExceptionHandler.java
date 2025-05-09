package defpackage;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\u001a\b\u0004\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\b\u001a\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0000¨\u0006\r"}, d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", ParamConstants.Param.CONTEXT, TrackConstants$Events.EXCEPTION, "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ule, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class CoroutineExceptionHandler {
    public static final void b(CoroutineContext coroutineContext, Throwable th) {
        try {
            kotlinx.coroutines.CoroutineExceptionHandler coroutineExceptionHandler = (kotlinx.coroutines.CoroutineExceptionHandler) coroutineContext.get(kotlinx.coroutines.CoroutineExceptionHandler.INSTANCE);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.handleException(coroutineContext, th);
            } else {
                handlers.d(coroutineContext, th);
            }
        } catch (Throwable th2) {
            handlers.d(coroutineContext, e(th, th2));
        }
    }

    public static final Throwable e(Throwable th, Throwable th2) {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        ued.c(runtimeException, th);
        return runtimeException;
    }
}
