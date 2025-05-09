package defpackage;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\b0\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/flow/internal/UndispatchedContextCollector;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "downstream", "emitContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "countOrElement", "", "emitRef", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
final class uor<T> implements FlowCollector<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f17490a;
    private final Function2<T, Continuation<? super ueu>, Object> d;
    private final CoroutineContext e;

    public uor(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext) {
        this.e = coroutineContext;
        this.f17490a = NO_THREAD_ELEMENTS.b(coroutineContext);
        this.d = new a(flowCollector, null);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.UndispatchedContextCollector$emitRef$1", f = "ChannelFlow.kt", i = {}, l = {com.huawei.hms.kit.awareness.barrier.internal.e.a.K}, m = "invokeSuspend", n = {}, s = {})
    static final class a extends SuspendLambda implements Function2<T, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        /* synthetic */ Object f17491a;
        final /* synthetic */ FlowCollector<T> d;
        int e;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object a2 = ugw.a();
            int i = this.e;
            if (i == 0) {
                createFailure.b(obj);
                Object obj2 = this.f17491a;
                this.e = 1;
                if (this.d.emit(obj2, this) == a2) {
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
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Object invoke(T t, Continuation<? super ueu> continuation) {
            return ((a) create(t, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            a aVar = new a(this.d, continuation);
            aVar.f17491a = obj;
            return aVar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        a(FlowCollector<? super T> flowCollector, Continuation<? super a> continuation) {
            super(2, continuation);
            this.d = flowCollector;
        }
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t, Continuation<? super ueu> continuation) {
        Object e = withContextUndispatched.e(this.e, t, this.f17490a, this.d, continuation);
        return e == ugw.a() ? e : ueu.d;
    }
}
