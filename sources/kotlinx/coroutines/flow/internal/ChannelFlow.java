package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.ASSERTIONS_ENABLED;
import defpackage.awaitClose;
import defpackage.classSimpleName;
import defpackage.createFailure;
import defpackage.isActive;
import defpackage.ueu;
import defpackage.ufe;
import defpackage.ugq;
import defpackage.ugw;
import defpackage.uhy;
import defpackage.unp;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u001f\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\fH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ&\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH$J\u0010\u0010\u001f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010 H\u0016J&\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010\u001c\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0016H\u0016R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R9\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000b8@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/internal/FusibleFlow;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collectToFun", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "getCollectToFun$kotlinx_coroutines_core", "()Lkotlin/jvm/functions/Function2;", "produceCapacity", "getProduceCapacity$kotlinx_coroutines_core", "()I", "additionalToStringProps", "", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "dropChannelOperators", "Lkotlinx/coroutines/flow/Flow;", "fuse", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class ChannelFlow<T> implements FusibleFlow<T> {
    public final int capacity;
    public final CoroutineContext context;
    public final BufferOverflow onBufferOverflow;

    protected String additionalToStringProps() {
        return null;
    }

    protected abstract Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super ueu> continuation);

    protected abstract ChannelFlow<T> create(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow);

    public Flow<T> dropChannelOperators() {
        return null;
    }

    public ChannelFlow(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        this.context = coroutineContext;
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (ASSERTIONS_ENABLED.a() && i == -1) {
            throw new AssertionError();
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collectToFun$1", f = "ChannelFlow.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    static final class b extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        /* synthetic */ Object f14495a;
        final /* synthetic */ ChannelFlow<T> b;
        int e;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object a2 = ugw.a();
            int i = this.e;
            if (i == 0) {
                createFailure.b(obj);
                ProducerScope<? super T> producerScope = (ProducerScope) this.f14495a;
                this.e = 1;
                if (this.b.collectTo(producerScope, this) == a2) {
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
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final Object invoke(ProducerScope<? super T> producerScope, Continuation<? super ueu> continuation) {
            return ((b) create(producerScope, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            b bVar = new b(this.b, continuation);
            bVar.f14495a = obj;
            return bVar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ChannelFlow<T> channelFlow, Continuation<? super b> continuation) {
            super(2, continuation);
            this.b = channelFlow;
        }
    }

    public final Function2<ProducerScope<? super T>, Continuation<? super ueu>, Object> getCollectToFun$kotlinx_coroutines_core() {
        return new b(this, null);
    }

    public final int getProduceCapacity$kotlinx_coroutines_core() {
        int i = this.capacity;
        if (i == -3) {
            return -2;
        }
        return i;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow) {
        if (ASSERTIONS_ENABLED.a() && capacity == -1) {
            throw new AssertionError();
        }
        CoroutineContext plus = context.plus(this.context);
        if (onBufferOverflow == BufferOverflow.SUSPEND) {
            int i = this.capacity;
            if (i != -3) {
                if (capacity != -3) {
                    if (i != -2) {
                        if (capacity != -2) {
                            if (ASSERTIONS_ENABLED.a() && this.capacity < 0) {
                                throw new AssertionError();
                            }
                            if (ASSERTIONS_ENABLED.a() && capacity < 0) {
                                throw new AssertionError();
                            }
                            i = this.capacity + capacity;
                            if (i < 0) {
                                capacity = Integer.MAX_VALUE;
                            }
                        }
                    }
                }
                capacity = i;
            }
            onBufferOverflow = this.onBufferOverflow;
        }
        if (uhy.e(plus, this.context) && capacity == this.capacity && onBufferOverflow == this.onBufferOverflow) {
            return this;
        }
        return create(plus, capacity, onBufferOverflow);
    }

    public ReceiveChannel<T> produceImpl(CoroutineScope scope) {
        return awaitClose.e(scope, this.context, getProduceCapacity$kotlinx_coroutines_core(), this.onBufferOverflow, CoroutineStart.ATOMIC, null, getCollectToFun$kotlinx_coroutines_core(), 16, null);
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collect$2", f = "ChannelFlow.kt", i = {}, l = {123}, m = "invokeSuspend", n = {}, s = {})
    static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        int f14496a;
        private /* synthetic */ Object b;
        final /* synthetic */ FlowCollector<T> c;
        final /* synthetic */ ChannelFlow<T> e;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object a2 = ugw.a();
            int i = this.f14496a;
            if (i == 0) {
                createFailure.b(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.b;
                this.f14496a = 1;
                if (unp.e(this.c, this.e.produceImpl(coroutineScope), this) == a2) {
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
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ueu> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            c cVar = new c(this.c, this.e, continuation);
            cVar.b = obj;
            return cVar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        c(FlowCollector<? super T> flowCollector, ChannelFlow<T> channelFlow, Continuation<? super c> continuation) {
            super(2, continuation);
            this.c = flowCollector;
            this.e = channelFlow;
        }
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlow channelFlow, FlowCollector flowCollector, Continuation continuation) {
        Object e = isActive.e(new c(flowCollector, channelFlow, null), continuation);
        return e == ugw.a() ? e : ueu.d;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        String additionalToStringProps = additionalToStringProps();
        if (additionalToStringProps != null) {
            arrayList.add(additionalToStringProps);
        }
        if (this.context != ugq.f17420a) {
            arrayList.add(uhy.b("context=", this.context));
        }
        int i = this.capacity;
        if (i != -3) {
            arrayList.add(uhy.b("capacity=", Integer.valueOf(i)));
        }
        if (this.onBufferOverflow != BufferOverflow.SUSPEND) {
            arrayList.add(uhy.b("onBufferOverflow=", this.onBufferOverflow));
        }
        return classSimpleName.b(this) + '[' + ufe.b(arrayList, ", ", null, null, 0, null, null, 62, null) + ']';
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super ueu> continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }
}
