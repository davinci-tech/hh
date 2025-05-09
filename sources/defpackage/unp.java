package defpackage;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt", "kotlinx/coroutines/flow/FlowKt__ChannelsKt", "kotlinx/coroutines/flow/FlowKt__CollectKt", "kotlinx/coroutines/flow/FlowKt__CollectionKt", "kotlinx/coroutines/flow/FlowKt__ContextKt", "kotlinx/coroutines/flow/FlowKt__CountKt", "kotlinx/coroutines/flow/FlowKt__DelayKt", "kotlinx/coroutines/flow/FlowKt__DistinctKt", "kotlinx/coroutines/flow/FlowKt__EmittersKt", "kotlinx/coroutines/flow/FlowKt__ErrorsKt", "kotlinx/coroutines/flow/FlowKt__LimitKt", "kotlinx/coroutines/flow/FlowKt__MergeKt", "kotlinx/coroutines/flow/FlowKt__MigrationKt", "kotlinx/coroutines/flow/FlowKt__ReduceKt", "kotlinx/coroutines/flow/FlowKt__ShareKt", "kotlinx/coroutines/flow/FlowKt__TransformKt", "kotlinx/coroutines/flow/FlowKt__ZipKt"}, k = 4, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class unp {
    public static final <T> Flow<T> b(Function2<? super FlowCollector<? super T>, ? super Continuation<? super ueu>, ? extends Object> function2) {
        return callbackFlow.b(function2);
    }

    public static final void b(FlowCollector<?> flowCollector) {
        ensureActive.b(flowCollector);
    }

    public static final <T> Flow<T> c(T t) {
        return callbackFlow.a(t);
    }

    public static final <T> Object e(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, Continuation<? super ueu> continuation) {
        return asFlow.a(flowCollector, receiveChannel, continuation);
    }

    public static final <T> StateFlow<T> e(MutableStateFlow<T> mutableStateFlow) {
        return asSharedFlow.e(mutableStateFlow);
    }
}
