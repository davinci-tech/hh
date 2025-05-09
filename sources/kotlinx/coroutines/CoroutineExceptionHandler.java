package kotlinx.coroutines;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlin/coroutines/CoroutineContext$Element;", "handleException", "", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", TrackConstants$Events.EXCEPTION, "", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {

    /* renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.c;

    void handleException(CoroutineContext context, Throwable exception);

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.CoroutineExceptionHandler$a, reason: from kotlin metadata */
    public static final class Companion implements CoroutineContext.Key<CoroutineExceptionHandler> {
        static final /* synthetic */ Companion c = new Companion();

        private Companion() {
        }
    }
}
