package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.uel;
import kotlin.Metadata;
import kotlinx.coroutines.Incomplete;
import kotlinx.coroutines.JobNode;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", TrackConstants$Opers.INVOKE, "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
final class umk<T> extends JobNode {
    private final ukr<T> d;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
        invoke2(th);
        return ueu.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public umk(ukr<? super T> ukrVar) {
        this.d = ukrVar;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(Throwable cause) {
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        if (ASSERTIONS_ENABLED.a() && !(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new AssertionError();
        }
        if (state$kotlinx_coroutines_core instanceof ula) {
            ukr<T> ukrVar = this.d;
            uel.b bVar = uel.d;
            ukrVar.resumeWith(uel.b(createFailure.b(((ula) state$kotlinx_coroutines_core).d)));
        } else {
            ukr<T> ukrVar2 = this.d;
            uel.b bVar2 = uel.d;
            ukrVar2.resumeWith(uel.b(COMPLETING_ALREADY.c(state$kotlinx_coroutines_core)));
        }
    }
}
