package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.createFailure;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.uhy;
import defpackage.uot;
import defpackage.withContextUndispatched;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J'\u0010\u0016\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001f\u0010\u0019\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0004X\u0085\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flowCollect", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {
    protected final Flow<S> flow;

    protected abstract Object flowCollect(FlowCollector<? super T> flowCollector, Continuation<? super ueu> continuation);

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowOperator(Flow<? extends S> flow, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        super(coroutineContext, i, bufferOverflow);
        this.flow = flow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object collectWithContextUndispatched(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext, Continuation<? super ueu> continuation) {
        FlowCollector a2;
        a2 = withContextUndispatched.a(flowCollector, continuation.getContext());
        Object c = withContextUndispatched.c(coroutineContext, a2, null, new a(this, null), continuation, 4, null);
        return c == ugw.a() ? c : ueu.d;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "it", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", f = "ChannelFlow.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    static final class a extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        int f14497a;
        final /* synthetic */ ChannelFlowOperator<S, T> d;
        /* synthetic */ Object e;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object a2 = ugw.a();
            int i = this.f14497a;
            if (i == 0) {
                createFailure.b(obj);
                FlowCollector<? super T> flowCollector = (FlowCollector) this.e;
                this.f14497a = 1;
                if (this.d.flowCollect(flowCollector, this) == a2) {
                    return a2;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                createFailure.b(obj);
            }
            return ueu.d;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super ueu> continuation) {
            return ((a) create(flowCollector, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            a aVar = new a(this.d, continuation);
            aVar.e = obj;
            return aVar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ChannelFlowOperator<S, T> channelFlowOperator, Continuation<? super a> continuation) {
            super(2, continuation);
            this.d = channelFlowOperator;
        }
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator, ProducerScope producerScope, Continuation continuation) {
        Object flowCollect = channelFlowOperator.flowCollect(new uot(producerScope), continuation);
        return flowCollect == ugw.a() ? flowCollect : ueu.d;
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        if (channelFlowOperator.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext plus = context.plus(channelFlowOperator.context);
            if (uhy.e(plus, context)) {
                Object flowCollect = channelFlowOperator.flowCollect(flowCollector, continuation);
                return flowCollect == ugw.a() ? flowCollect : ueu.d;
            }
            if (uhy.e(plus.get(ContinuationInterceptor.INSTANCE), context.get(ContinuationInterceptor.INSTANCE))) {
                Object collectWithContextUndispatched = channelFlowOperator.collectWithContextUndispatched(flowCollector, plus, continuation);
                return collectWithContextUndispatched == ugw.a() ? collectWithContextUndispatched : ueu.d;
            }
        }
        Object collect = super.collect(flowCollector, continuation);
        return collect == ugw.a() ? collect : ueu.d;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.flow + " -> " + super.toString();
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super ueu> continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super ueu> continuation) {
        return collect$suspendImpl((ChannelFlowOperator) this, (FlowCollector) flowCollector, (Continuation) continuation);
    }
}
