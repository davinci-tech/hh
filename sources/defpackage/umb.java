package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.JobCancellingNode;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u000fB0\u0012'\u0010\b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fR5\u0010\b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/InvokeOnCancelling;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "handler", "<init>", "(Lkotlin/jvm/functions/Function1;)V", TrackConstants$Opers.INVOKE, "(Ljava/lang/Throwable;)V", "Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/JobCancellingNode;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
final class umb extends JobCancellingNode {
    private static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(umb.class, "_invoked");
    private volatile /* synthetic */ int _invoked = 0;
    private final Function1<Throwable, ueu> b;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
        invoke2(th);
        return ueu.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public umb(Function1<? super Throwable, ueu> function1) {
        this.b = function1;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(Throwable cause) {
        if (d.compareAndSet(this, 0, 1)) {
            this.b.invoke(cause);
        }
    }
}
