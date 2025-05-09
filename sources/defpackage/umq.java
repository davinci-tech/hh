package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.JobNode;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B4\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002R)\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/SelectJoinOnCompletion;", "R", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", TrackConstants$Opers.INVOKE, "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
final class umq<R> extends JobNode {

    /* renamed from: a, reason: collision with root package name */
    private final Function1<Continuation<? super R>, Object> f17468a;
    private final SelectInstance<R> e;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
        invoke2(th);
        return ueu.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public umq(SelectInstance<? super R> selectInstance, Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.e = selectInstance;
        this.f17468a = function1;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(Throwable cause) {
        if (this.e.trySelect()) {
            dispatcherFailure.e(this.f17468a, this.e.getCompletion());
        }
    }
}
