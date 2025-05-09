package defpackage;

import com.huawei.hiai.awareness.client.AwarenessRequest;
import defpackage.uig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharingCommand;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/flow/StartedLazily;", "Lkotlinx/coroutines/flow/SharingStarted;", "()V", AwarenessRequest.Field.COMMAND, "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uod implements SharingStarted {

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/SharingCommand;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$1", f = "SharingStarted.kt", i = {}, l = {155}, m = "invokeSuspend", n = {}, s = {})
    static final class a extends SuspendLambda implements Function2<FlowCollector<? super SharingCommand>, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ StateFlow<Integer> f17484a;
        int b;
        private /* synthetic */ Object c;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object a2 = ugw.a();
            int i = this.b;
            if (i == 0) {
                createFailure.b(obj);
                FlowCollector flowCollector = (FlowCollector) this.c;
                uig.c cVar = new uig.c();
                this.b = 1;
                if (this.f17484a.collect(new AnonymousClass4(cVar, flowCollector), this) == a2) {
                    return a2;
                }
            } else if (i == 1) {
                createFailure.b(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new uek();
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "count", "", "emit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: uod$a$4, reason: invalid class name */
        static final class AnonymousClass4<T> implements FlowCollector, SuspendFunction {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ uig.c f17485a;
            final /* synthetic */ FlowCollector<SharingCommand> b;

            /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object d(int r5, kotlin.coroutines.Continuation<? super defpackage.ueu> r6) {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof uod.a.AnonymousClass4.d
                    if (r0 == 0) goto L14
                    r0 = r6
                    uod$a$4$d r0 = (uod.a.AnonymousClass4.d) r0
                    int r1 = r0.d
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L14
                    int r6 = r0.d
                    int r6 = r6 + r2
                    r0.d = r6
                    goto L19
                L14:
                    uod$a$4$d r0 = new uod$a$4$d
                    r0.<init>(r4, r6)
                L19:
                    java.lang.Object r6 = r0.b
                    java.lang.Object r1 = defpackage.ugw.a()
                    int r2 = r0.d
                    r3 = 1
                    if (r2 == 0) goto L32
                    if (r2 != r3) goto L2a
                    defpackage.createFailure.b(r6)
                    goto L4e
                L2a:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r6)
                    throw r5
                L32:
                    defpackage.createFailure.b(r6)
                    if (r5 <= 0) goto L51
                    uig$c r5 = r4.f17485a
                    boolean r5 = r5.c
                    if (r5 != 0) goto L51
                    uig$c r5 = r4.f17485a
                    r5.c = r3
                    kotlinx.coroutines.flow.FlowCollector<kotlinx.coroutines.flow.SharingCommand> r5 = r4.b
                    kotlinx.coroutines.flow.SharingCommand r6 = kotlinx.coroutines.flow.SharingCommand.START
                    r0.d = r3
                    java.lang.Object r5 = r5.emit(r6, r0)
                    if (r5 != r1) goto L4e
                    return r1
                L4e:
                    ueu r5 = defpackage.ueu.d
                    return r5
                L51:
                    ueu r5 = defpackage.ueu.d
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: uod.a.AnonymousClass4.d(int, kotlin.coroutines.Continuation):java.lang.Object");
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return d(((Number) obj).intValue(), continuation);
            }

            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$1$1", f = "SharingStarted.kt", i = {}, l = {158}, m = "emit", n = {}, s = {})
            /* renamed from: uod$a$4$d */
            static final class d extends ContinuationImpl {
                /* synthetic */ Object b;
                int d;
                final /* synthetic */ AnonymousClass4<T> e;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    this.b = obj;
                    this.d |= Integer.MIN_VALUE;
                    return this.e.d(0, this);
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                d(AnonymousClass4<? super T> anonymousClass4, Continuation<? super d> continuation) {
                    super(continuation);
                    this.e = anonymousClass4;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass4(uig.c cVar, FlowCollector<? super SharingCommand> flowCollector) {
                this.f17485a = cVar;
                this.b = flowCollector;
            }
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<? super SharingCommand> flowCollector, Continuation<? super ueu> continuation) {
            return ((a) create(flowCollector, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            a aVar = new a(this.f17484a, continuation);
            aVar.c = obj;
            return aVar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(StateFlow<Integer> stateFlow, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f17484a = stateFlow;
        }
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> subscriptionCount) {
        return unp.b(new a(subscriptionCount, null));
    }

    public String toString() {
        return "SharingStarted.Lazily";
    }
}
