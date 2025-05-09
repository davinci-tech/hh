package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import defpackage.baseContinuationImplClass;
import defpackage.createFailure;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.unf;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0017J\u0014\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H'J\u001a\u0010\u0014\u001a\u00020\u00152\u0010\b\u0002\u0010\u0016\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019H&J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH¦\u0002J\u000f\u0010\u001c\u001a\u0004\u0018\u00018\u0000H\u0017¢\u0006\u0002\u0010\u001dJ\u0011\u0010\u001e\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH¦@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b!\u0010\u001fJ\u0013\u0010\"\u001a\u0004\u0018\u00018\u0000H\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b$\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000bX¦\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\"\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0006\u001a\u0004\b\u0013\u0010\r\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", ExifInterface.LONGITUDE_EAST, "", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveOrNull", "tryReceive", "tryReceive-PtdJZtk", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ReceiveChannel<E> {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ void cancel();

    void cancel(CancellationException cause);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ boolean cancel(Throwable cause);

    SelectClause1<E> getOnReceive();

    SelectClause1<unf<E>> getOnReceiveCatching();

    SelectClause1<E> getOnReceiveOrNull();

    boolean isClosedForReceive();

    boolean isEmpty();

    ChannelIterator<E> iterator();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    E poll();

    Object receive(Continuation<? super E> continuation);

    /* renamed from: receiveCatching-JP2dKIU */
    Object mo977receiveCatchingJP2dKIU(Continuation<? super unf<? extends E>> continuation);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    Object receiveOrNull(Continuation<? super E> continuation);

    /* renamed from: tryReceive-PtdJZtk */
    Object mo978tryReceivePtdJZtk();

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class c {
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
        public static <E> E a(ReceiveChannel<? extends E> receiveChannel) {
            Object mo978tryReceivePtdJZtk = receiveChannel.mo978tryReceivePtdJZtk();
            if (unf.i(mo978tryReceivePtdJZtk)) {
                return (E) unf.a(mo978tryReceivePtdJZtk);
            }
            Throwable c = unf.c(mo978tryReceivePtdJZtk);
            if (c == null) {
                return null;
            }
            throw baseContinuationImplClass.a(c);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @kotlin.ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static <E> java.lang.Object e(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r4, kotlin.coroutines.Continuation<? super E> r5) {
            /*
                boolean r0 = r5 instanceof kotlinx.coroutines.channels.ReceiveChannel.c.a
                if (r0 == 0) goto L14
                r0 = r5
                kotlinx.coroutines.channels.ReceiveChannel$c$a r0 = (kotlinx.coroutines.channels.ReceiveChannel.c.a) r0
                int r1 = r0.b
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r5 = r0.b
                int r5 = r5 + r2
                r0.b = r5
                goto L19
            L14:
                kotlinx.coroutines.channels.ReceiveChannel$c$a r0 = new kotlinx.coroutines.channels.ReceiveChannel$c$a
                r0.<init>(r5)
            L19:
                java.lang.Object r5 = r0.c
                java.lang.Object r1 = defpackage.ugw.a()
                int r2 = r0.b
                r3 = 1
                if (r2 == 0) goto L38
                if (r2 != r3) goto L30
                defpackage.createFailure.b(r5)
                unf r5 = (defpackage.unf) r5
                java.lang.Object r4 = r5.getD()
                goto L44
            L30:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L38:
                defpackage.createFailure.b(r5)
                r0.b = r3
                java.lang.Object r4 = r4.mo977receiveCatchingJP2dKIU(r0)
                if (r4 != r1) goto L44
                return r1
            L44:
                java.lang.Object r4 = defpackage.unf.b(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ReceiveChannel.c.e(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001JJ\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0001\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062$\u0010\u0007\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"kotlinx/coroutines/channels/ReceiveChannel$onReceiveOrNull$1", "Lkotlinx/coroutines/selects/SelectClause1;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes10.dex */
        public static final class d implements SelectClause1<E> {
            final /* synthetic */ ReceiveChannel<E> b;

            /* JADX WARN: Multi-variable type inference failed */
            d(ReceiveChannel<? extends E> receiveChannel) {
                this.b = receiveChannel;
            }

            /* JADX INFO: Add missing generic type declarations: [R] */
            @Metadata(d1 = {"\u0000\b\n\u0002\b\u0004\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0006\b\u0001\u0010\u0002 \u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "R", ExifInterface.LONGITUDE_EAST, "it", "Lkotlinx/coroutines/channels/ChannelResult;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.channels.ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1", f = "Channel.kt", i = {}, l = {375}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: kotlinx.coroutines.channels.ReceiveChannel$c$d$d, reason: collision with other inner class name */
            static final class C0317d<R> extends SuspendLambda implements Function2<unf<? extends E>, Continuation<? super R>, Object> {

                /* renamed from: a, reason: collision with root package name */
                int f14492a;
                /* synthetic */ Object b;
                final /* synthetic */ Function2<E, Continuation<? super R>, Object> e;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object a2 = ugw.a();
                    int i = this.f14492a;
                    if (i == 0) {
                        createFailure.b(obj);
                        Object d = ((unf) this.b).getD();
                        Throwable c = unf.c(d);
                        if (c != null) {
                            throw c;
                        }
                        Function2<E, Continuation<? super R>, Object> function2 = this.e;
                        Object b = unf.b(d);
                        this.f14492a = 1;
                        obj = function2.invoke(b, this);
                        if (obj == a2) {
                            return a2;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        createFailure.b(obj);
                    }
                    return obj;
                }

                public final Object e(Object obj, Continuation<? super R> continuation) {
                    return ((C0317d) create(unf.e(obj), continuation)).invokeSuspend(ueu.d);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return e(((unf) obj).getD(), (Continuation) obj2);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
                    C0317d c0317d = new C0317d(this.e, continuation);
                    c0317d.b = obj;
                    return c0317d;
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C0317d(Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C0317d> continuation) {
                    super(2, continuation);
                    this.e = function2;
                }
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super E, ? super Continuation<? super R>, ? extends Object> block) {
                this.b.getOnReceiveCatching().registerSelectClause1(select, new C0317d(block, null));
            }
        }

        public static <E> SelectClause1<E> e(ReceiveChannel<? extends E> receiveChannel) {
            return new d(receiveChannel);
        }

        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "kotlinx.coroutines.channels.ReceiveChannel$DefaultImpls", f = "Channel.kt", i = {}, l = {354}, m = "receiveOrNull", n = {}, s = {})
        static final class a<E> extends ContinuationImpl {
            int b;
            /* synthetic */ Object c;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.c = obj;
                this.b |= Integer.MIN_VALUE;
                return c.e(null, this);
            }

            a(Continuation<? super a> continuation) {
                super(continuation);
            }
        }
    }
}
