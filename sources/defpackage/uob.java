package defpackage;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BE\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012-\u0010\u0004\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R:\u0010\u0004\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/flow/SubscribedFlowCollector;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "action", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSubscription", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uob<T> implements FlowCollector<T> {
    private final FlowCollector<T> b;
    private final Function2<FlowCollector<? super T>, Continuation<? super ueu>, Object> d;

    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object c(kotlin.coroutines.Continuation<? super defpackage.ueu> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof uob.b
            if (r0 == 0) goto L14
            r0 = r7
            uob$b r0 = (uob.b) r0
            int r1 = r0.b
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.b
            int r7 = r7 + r2
            r0.b = r7
            goto L19
        L14:
            uob$b r0 = new uob$b
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.e
            java.lang.Object r1 = defpackage.ugw.a()
            int r2 = r0.b
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            defpackage.createFailure.b(r7)
            goto L7b
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L35:
            java.lang.Object r2 = r0.d
            uop r2 = (defpackage.uop) r2
            java.lang.Object r4 = r0.c
            uob r4 = (defpackage.uob) r4
            defpackage.createFailure.b(r7)     // Catch: java.lang.Throwable -> L41
            goto L62
        L41:
            r7 = move-exception
            goto L83
        L43:
            defpackage.createFailure.b(r7)
            kotlinx.coroutines.flow.FlowCollector<T> r7 = r6.b
            kotlin.coroutines.CoroutineContext r2 = r0.getD()
            uop r5 = new uop
            r5.<init>(r7, r2)
            kotlin.jvm.functions.Function2<kotlinx.coroutines.flow.FlowCollector<? super T>, kotlin.coroutines.Continuation<? super ueu>, java.lang.Object> r7 = r6.d     // Catch: java.lang.Throwable -> L81
            r0.c = r6     // Catch: java.lang.Throwable -> L81
            r0.d = r5     // Catch: java.lang.Throwable -> L81
            r0.b = r4     // Catch: java.lang.Throwable -> L81
            java.lang.Object r7 = r7.invoke(r5, r0)     // Catch: java.lang.Throwable -> L81
            if (r7 != r1) goto L60
            return r1
        L60:
            r4 = r6
            r2 = r5
        L62:
            r2.releaseIntercepted()
            kotlinx.coroutines.flow.FlowCollector<T> r7 = r4.b
            boolean r2 = r7 instanceof defpackage.uob
            if (r2 == 0) goto L7e
            uob r7 = (defpackage.uob) r7
            r2 = 0
            r0.c = r2
            r0.d = r2
            r0.b = r3
            java.lang.Object r7 = r7.c(r0)
            if (r7 != r1) goto L7b
            return r1
        L7b:
            ueu r7 = defpackage.ueu.d
            return r7
        L7e:
            ueu r7 = defpackage.ueu.d
            return r7
        L81:
            r7 = move-exception
            r2 = r5
        L83:
            r2.releaseIntercepted()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uob.c(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.SubscribedFlowCollector", f = "Share.kt", i = {0, 0}, l = {419, 423}, m = "onSubscription", n = {"this", "safeCollector"}, s = {"L$0", "L$1"})
    static final class b extends ContinuationImpl {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ uob<T> f17482a;
        int b;
        Object c;
        Object d;
        /* synthetic */ Object e;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.e = obj;
            this.b |= Integer.MIN_VALUE;
            return this.f17482a.c(this);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(uob<T> uobVar, Continuation<? super b> continuation) {
            super(continuation);
            this.f17482a = uobVar;
        }
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t, Continuation<? super ueu> continuation) {
        return this.b.emit(t, continuation);
    }
}
