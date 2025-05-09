package defpackage;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\">\u0010\u0000\u001a,\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"emitFun", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/coroutines/Continuation;", "", "getEmitFun$annotations", "()V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: uoo, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class emitFun {
    private static final Function3<FlowCollector<Object>, Object, Continuation<? super ueu>, Object> d = (Function3) uii.e(e.f17488a, 3);

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: uoo$e */
    final /* synthetic */ class e extends uia implements Function3<FlowCollector<? super Object>, Object, ueu>, SuspendFunction {

        /* renamed from: a, reason: collision with root package name */
        public static final e f17488a = new e();

        @Override // kotlin.jvm.functions.Function3
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<Object> flowCollector, Object obj, Continuation<? super ueu> continuation) {
            return flowCollector.emit(obj, continuation);
        }

        e() {
            super(3, FlowCollector.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }
    }
}
