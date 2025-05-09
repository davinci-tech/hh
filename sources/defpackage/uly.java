package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancelHandler;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B.\u0012'\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R/\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/InvokeOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "handler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;)V", TrackConstants$Opers.INVOKE, "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
final class uly extends CancelHandler {

    /* renamed from: a, reason: collision with root package name */
    private final Function1<Throwable, ueu> f17462a;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
        invoke2(th);
        return ueu.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public uly(Function1<? super Throwable, ueu> function1) {
        this.f17462a = function1;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(Throwable cause) {
        this.f17462a.invoke(cause);
    }

    public String toString() {
        return "InvokeOnCancel[" + classSimpleName.b(this.f17462a) + '@' + classSimpleName.d(this) + ']';
    }
}
