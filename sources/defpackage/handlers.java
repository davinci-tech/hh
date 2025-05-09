package defpackage;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import defpackage.uel;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"handlers", "", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleCoroutineExceptionImpl", "", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", TrackConstants$Events.EXCEPTION, "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulg, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class handlers {

    /* renamed from: a, reason: collision with root package name */
    private static final List<CoroutineExceptionHandler> f17456a = ujh.d(ujh.e(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator()));

    public static final void d(CoroutineContext coroutineContext, Throwable th) {
        Iterator<CoroutineExceptionHandler> it = f17456a.iterator();
        while (it.hasNext()) {
            try {
                it.next().handleException(coroutineContext, th);
            } catch (Throwable th2) {
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, CoroutineExceptionHandler.e(th, th2));
            }
        }
        Thread currentThread2 = Thread.currentThread();
        try {
            uel.b bVar = uel.d;
            ued.c(th, new uls(coroutineContext));
            uel.b(ueu.d);
        } catch (Throwable th3) {
            uel.b bVar2 = uel.d;
            uel.b(createFailure.b(th3));
        }
        currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th);
    }
}
