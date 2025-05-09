package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.ALREADY_SELECTED;
import defpackage.ASSERTIONS_ENABLED;
import defpackage.C0350umy;
import defpackage.CONDITION_FALSE;
import defpackage.NO_DECISION;
import defpackage.REMOVE_PREPARED;
import defpackage.RESUMED;
import defpackage.baseContinuationImplClass;
import defpackage.bindCancellationFun;
import defpackage.boxBoolean;
import defpackage.classSimpleName;
import defpackage.createFailure;
import defpackage.dispatcherFailure;
import defpackage.getOrCreateCancellableContinuation;
import defpackage.probeCoroutineCreated;
import defpackage.startDirect;
import defpackage.uel;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.uhy;
import defpackage.ukr;
import defpackage.unf;
import defpackage.ung;
import defpackage.upc;
import defpackage.upj;
import defpackage.upu;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0007STUVWXYB'\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0007J\u0016\u0010\u0019\u001a\u00020\u00062\u000e\u0010\u001a\u001a\n\u0018\u00010\u001cj\u0004\u0018\u0001`\u001dJ\u0017\u0010\u001e\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0000¢\u0006\u0002\b\u001fJ\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0004J\u0016\u0010\"\u001a\u00020\n2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0002J\u0016\u0010%\u001a\u00020\n2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0014JR\u0010&\u001a\u00020\n\"\u0004\b\u0001\u0010'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)2$\u0010*\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010,\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0-\u0012\u0006\u0012\u0004\u0018\u00010,0+2\u0006\u0010.\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0002\u00100J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0086\u0002J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\nH\u0014J/\u00105\u001a\u00020\u00062\f\u00106\u001a\b\u0012\u0004\u0012\u000208072\n\u00109\u001a\u0006\u0012\u0002\b\u00030:H\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u0010<J\b\u0010=\u001a\u00020\u0006H\u0014J\b\u0010>\u001a\u00020\u0006H\u0014J\n\u0010?\u001a\u0004\u0018\u00010,H\u0014J\u0016\u0010@\u001a\u0004\u0018\u00010,2\n\u0010(\u001a\u0006\u0012\u0002\b\u00030)H\u0014J\u0011\u0010#\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\"\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0086@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bC\u0010AJ\u001f\u0010D\u001a\u0002H'\"\u0004\b\u0001\u0010'2\u0006\u0010.\u001a\u00020/H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010EJR\u0010F\u001a\u00020\u0006\"\u0004\b\u0001\u0010'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)2\u0006\u0010.\u001a\u00020/2$\u0010*\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010,\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0-\u0012\u0006\u0012\u0004\u0018\u00010,0+H\u0002ø\u0001\u0000¢\u0006\u0002\u0010GJ \u0010H\u001a\u00020\u00062\n\u0010I\u001a\u0006\u0012\u0002\b\u00030J2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030$H\u0002J\u0010\u0010K\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010LH\u0014J\u001c\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bN\u0010OJX\u0010P\u001a\u00020\u0006\"\u0004\b\u0001\u0010'* \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010,\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0-\u0012\u0006\u0012\u0004\u0018\u00010,0+2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)2\u0006\u0010.\u001a\u00020/2\b\u0010Q\u001a\u0004\u0018\u00010,H\u0002ø\u0001\u0000¢\u0006\u0002\u0010RR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0012\u0010\u000e\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00170\u00138Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Z"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "isEmptyImpl", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "cancelInternal$kotlinx_coroutines_core", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "enqueueReceiveInternal", "enqueueReceiveSelect", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)Z", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onCancelIdempotent", "wasClosed", "onCancelIdempotentList", "list", "Lkotlinx/coroutines/internal/InlineList;", "Lkotlinx/coroutines/channels/Send;", "closed", "Lkotlinx/coroutines/channels/Closed;", "onCancelIdempotentList-w-w6eGU", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "onReceiveDequeued", "onReceiveEnqueued", "pollInternal", "pollSelectInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveSuspend", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerSelectReceiveMode", "(Lkotlinx/coroutines/selects/SelectInstance;ILkotlin/jvm/functions/Function2;)V", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "tryStartBlockUnintercepted", "value", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/selects/SelectInstance;ILjava/lang/Object;)V", "Itr", "ReceiveElement", "ReceiveElementWithUndeliveredHandler", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryPollDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {
    protected abstract boolean isBufferAlwaysEmpty();

    protected abstract boolean isBufferEmpty();

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", ParamConstants.CallbackMethod.ON_PREPARE, "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class f extends LockFreeLinkedListNode.CondAddOp {
        final /* synthetic */ AbstractChannel c;
        final /* synthetic */ LockFreeLinkedListNode d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(LockFreeLinkedListNode lockFreeLinkedListNode, AbstractChannel abstractChannel) {
            super(lockFreeLinkedListNode);
            this.d = lockFreeLinkedListNode;
            this.c = abstractChannel;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (this.c.isBufferEmpty()) {
                return null;
            }
            return CONDITION_FALSE.d();
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceiveOrNull() {
        return Channel.a.e(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public E poll() {
        return (E) Channel.a.b(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation<? super E> continuation) {
        return Channel.a.a(this, continuation);
    }

    public AbstractChannel(Function1<? super E, ueu> function1) {
        super(function1);
    }

    protected Object pollInternal() {
        while (true) {
            Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return C0350umy.i;
            }
            upu tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
            if (tryResumeSend != null) {
                if (ASSERTIONS_ENABLED.a() && tryResumeSend != RESUMED.c) {
                    throw new AssertionError();
                }
                takeFirstSendOrPeekClosed.completeResumeSend();
                return takeFirstSendOrPeekClosed.getE();
            }
            takeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    protected Object pollSelectInternal(SelectInstance<?> select) {
        i<E> describeTryPoll = describeTryPoll();
        Object performAtomicTrySelect = select.performAtomicTrySelect(describeTryPoll);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        describeTryPoll.b().completeResumeSend();
        return describeTryPoll.b().getE();
    }

    protected final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return isEmptyImpl();
    }

    protected final boolean isEmptyImpl() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        return (pollInternal == C0350umy.i || (pollInternal instanceof ung)) ? receiveSuspend(0, continuation) : pollInternal;
    }

    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysEmpty()) {
            upj queue = getQueue();
            do {
                prevNode = queue.getPrevNode();
                if (!(!(prevNode instanceof Send))) {
                }
            } while (!prevNode.addNext(receive, queue));
            return true;
        }
        upj queue2 = getQueue();
        Receive<? super E> receive2 = receive;
        f fVar = new f(receive2, this);
        do {
            LockFreeLinkedListNode prevNode2 = queue2.getPrevNode();
            if (!(!(prevNode2 instanceof Send))) {
                break;
            }
            tryCondAddNext = prevNode2.tryCondAddNext(receive2, queue2, fVar);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean enqueueReceive(Receive<? super E> receive) {
        boolean enqueueReceiveInternal = enqueueReceiveInternal(receive);
        if (enqueueReceiveInternal) {
            onReceiveEnqueued();
        }
        return enqueueReceiveInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo977receiveCatchingJP2dKIU(kotlin.coroutines.Continuation<? super defpackage.unf<? extends E>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.AbstractChannel.k
            if (r0 == 0) goto L14
            r0 = r5
            kotlinx.coroutines.channels.AbstractChannel$k r0 = (kotlinx.coroutines.channels.AbstractChannel.k) r0
            int r1 = r0.b
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.b
            int r5 = r5 + r2
            r0.b = r5
            goto L19
        L14:
            kotlinx.coroutines.channels.AbstractChannel$k r0 = new kotlinx.coroutines.channels.AbstractChannel$k
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.c
            java.lang.Object r1 = defpackage.ugw.a()
            int r2 = r0.b
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            defpackage.createFailure.b(r5)
            goto L5c
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            defpackage.createFailure.b(r5)
            java.lang.Object r5 = r4.pollInternal()
            upu r2 = defpackage.C0350umy.i
            if (r5 == r2) goto L53
            boolean r0 = r5 instanceof defpackage.ung
            if (r0 == 0) goto L4c
            unf$d r0 = defpackage.unf.b
            ung r5 = (defpackage.ung) r5
            java.lang.Throwable r5 = r5.d
            java.lang.Object r5 = r0.c(r5)
            goto L52
        L4c:
            unf$d r0 = defpackage.unf.b
            java.lang.Object r5 = r0.a(r5)
        L52:
            return r5
        L53:
            r0.b = r3
            java.lang.Object r5 = r4.receiveSuspend(r3, r0)
            if (r5 != r1) goto L5c
            return r1
        L5c:
            unf r5 = (defpackage.unf) r5
            java.lang.Object r5 = r5.getD()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.mo977receiveCatchingJP2dKIU(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public final Object mo978tryReceivePtdJZtk() {
        Object pollInternal = pollInternal();
        return pollInternal == C0350umy.i ? unf.b.e() : pollInternal instanceof ung ? unf.b.c(((ung) pollInternal).d) : unf.b.a(pollInternal);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cause) {
        if (isClosedForReceive()) {
            return;
        }
        if (cause == null) {
            cause = new CancellationException(uhy.b(classSimpleName.b(this), (Object) " was cancelled"));
        }
        cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: cancelInternal$kotlinx_coroutines_core, reason: merged with bridge method [inline-methods] */
    public final boolean cancel(Throwable cause) {
        boolean close = close(cause);
        onCancelIdempotent(close);
        return close;
    }

    public void onCancelIdempotent(boolean wasClosed) {
        ung<?> closedForSend = getClosedForSend();
        if (closedForSend == null) {
            throw new IllegalStateException("Cannot happen".toString());
        }
        Object b2 = upc.b(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
            if (!(prevNode instanceof upj)) {
                if (ASSERTIONS_ENABLED.a() && !(prevNode instanceof Send)) {
                    throw new AssertionError();
                }
                if (!prevNode.remove()) {
                    prevNode.helpRemove();
                } else {
                    b2 = upc.a(b2, (Send) prevNode);
                }
            } else {
                mo976onCancelIdempotentListww6eGU(b2, closedForSend);
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final ChannelIterator<E> iterator() {
        return new b(this);
    }

    protected final i<E> describeTryPoll() {
        return new i<>(getQueue());
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\f\u001a\u0004\u0018\u00010\t2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "onRemoved", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class i<E> extends LockFreeLinkedListNode.b<Send> {
        public i(upj upjVar) {
            super(upjVar);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.b, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof ung) {
                return affected;
            }
            if (affected instanceof Send) {
                return null;
            }
            return C0350umy.i;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.c cVar) {
            upu tryResumeSend = ((Send) cVar.b).tryResumeSend(cVar);
            if (tryResumeSend == null) {
                return REMOVE_PREPARED.c;
            }
            if (tryResumeSend == NO_DECISION.c) {
                return NO_DECISION.c;
            }
            if (!ASSERTIONS_ENABLED.a() || tryResumeSend == RESUMED.c) {
                return null;
            }
            throw new AssertionError();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(LockFreeLinkedListNode affected) {
            ((Send) affected).undeliveredElement();
        }
    }

    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001JH\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0001\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\"\u0010\u0007\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"kotlinx/coroutines/channels/AbstractChannel$onReceive$1", "Lkotlinx/coroutines/selects/SelectClause1;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class g implements SelectClause1<E> {
        final /* synthetic */ AbstractChannel<E> c;

        g(AbstractChannel<E> abstractChannel) {
            this.c = abstractChannel;
        }

        @Override // kotlinx.coroutines.selects.SelectClause1
        public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super E, ? super Continuation<? super R>, ? extends Object> block) {
            this.c.registerSelectReceiveMode(select, 0, block);
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<E> getOnReceive() {
        return new g(this);
    }

    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001JQ\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072(\u0010\b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tH\u0016ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\fø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"kotlinx/coroutines/channels/AbstractChannel$onReceiveCatching$1", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlinx/coroutines/channels/ChannelResult;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class h implements SelectClause1<unf<? extends E>> {
        final /* synthetic */ AbstractChannel<E> b;

        h(AbstractChannel<E> abstractChannel) {
            this.b = abstractChannel;
        }

        @Override // kotlinx.coroutines.selects.SelectClause1
        public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super unf<? extends E>, ? super Continuation<? super R>, ? extends Object> block) {
            this.b.registerSelectReceiveMode(select, 1, block);
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<unf<E>> getOnReceiveCatching() {
        return new h(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectReceiveMode(SelectInstance<? super R> select, int receiveMode, Function2<Object, ? super Continuation<? super R>, ? extends Object> block) {
        while (!select.isSelected()) {
            if (isEmptyImpl()) {
                if (enqueueReceiveSelect(select, block, receiveMode)) {
                    return;
                }
            } else {
                Object pollSelectInternal = pollSelectInternal(select);
                if (pollSelectInternal == ALREADY_SELECTED.e()) {
                    return;
                }
                if (pollSelectInternal != C0350umy.i && pollSelectInternal != NO_DECISION.c) {
                    tryStartBlockUnintercepted(block, select, receiveMode, pollSelectInternal);
                }
            }
        }
    }

    private final <R> void tryStartBlockUnintercepted(Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i2, Object obj) {
        boolean z = obj instanceof ung;
        if (!z) {
            if (i2 != 1) {
                startDirect.e(function2, obj, selectInstance.getCompletion());
                return;
            } else {
                unf.d dVar = unf.b;
                startDirect.e(function2, unf.e(z ? dVar.c(((ung) obj).d) : dVar.a(obj)), selectInstance.getCompletion());
                return;
            }
        }
        if (i2 == 0) {
            throw baseContinuationImplClass.a(((ung) obj).a());
        }
        if (i2 == 1 && selectInstance.trySelect()) {
            startDirect.e(function2, unf.e(unf.b.c(((ung) obj).d)), selectInstance.getCompletion());
        }
    }

    private final <R> boolean enqueueReceiveSelect(SelectInstance<? super R> select, Function2<Object, ? super Continuation<? super R>, ? extends Object> block, int receiveMode) {
        e eVar = new e(this, select, block, receiveMode);
        boolean enqueueReceive = enqueueReceive(eVar);
        if (enqueueReceive) {
            select.disposeOnSelect(eVar);
        }
        return enqueueReceive;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof ung)) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeReceiveOnCancel(CancellableContinuation<?> cont, Receive<?> receive) {
        cont.invokeOnCancellation(new j(receive));
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/BeforeResumeCancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", TrackConstants$Opers.INVOKE, "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class j extends BeforeResumeCancelHandler {
        private final Receive<?> c;

        public j(Receive<?> receive) {
            this.c = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
            invoke2(th);
            return ueu.d;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            if (this.c.remove()) {
                AbstractChannel.this.onReceiveDequeued();
            }
        }

        public String toString() {
            return "RemoveReceiveOnCancel[" + this.c + ']';
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\f\u001a\u00020\rH\u0096Bø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0011\u0010\u0010\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0011\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\tR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class b<E> implements ChannelIterator<E> {
        public final AbstractChannel<E> c;
        private Object d = C0350umy.i;

        public b(AbstractChannel<E> abstractChannel) {
            this.c = abstractChannel;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public /* synthetic */ Object next(Continuation continuation) {
            return ChannelIterator.b.d(this, continuation);
        }

        /* renamed from: b, reason: from getter */
        public final Object getD() {
            return this.d;
        }

        public final void c(Object obj) {
            this.d = obj;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> continuation) {
            if (getD() != C0350umy.i) {
                return boxBoolean.a(a(getD()));
            }
            c(this.c.pollInternal());
            return getD() != C0350umy.i ? boxBoolean.a(a(getD())) : a(continuation);
        }

        private final boolean a(Object obj) {
            if (!(obj instanceof ung)) {
                return true;
            }
            ung ungVar = (ung) obj;
            if (ungVar.d == null) {
                return false;
            }
            throw baseContinuationImplClass.a(ungVar.a());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            E e = (E) this.d;
            if (e instanceof ung) {
                throw baseContinuationImplClass.a(((ung) e).a());
            }
            if (e != C0350umy.i) {
                this.d = C0350umy.i;
                return e;
            }
            throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
        }

        private final Object a(Continuation<? super Boolean> continuation) {
            ukr b = getOrCreateCancellableContinuation.b(ugw.a(continuation));
            ukr ukrVar = b;
            d dVar = new d(this, ukrVar);
            while (true) {
                d dVar2 = dVar;
                if (this.c.enqueueReceive(dVar2)) {
                    this.c.removeReceiveOnCancel(ukrVar, dVar2);
                    break;
                }
                Object pollInternal = this.c.pollInternal();
                c(pollInternal);
                if (pollInternal instanceof ung) {
                    ung ungVar = (ung) pollInternal;
                    if (ungVar.d == null) {
                        uel.b bVar = uel.d;
                        ukrVar.resumeWith(uel.b(boxBoolean.a(false)));
                    } else {
                        uel.b bVar2 = uel.d;
                        ukrVar.resumeWith(uel.b(createFailure.b(ungVar.a())));
                    }
                } else if (pollInternal != C0350umy.i) {
                    Boolean a2 = boxBoolean.a(true);
                    Function1<E, ueu> function1 = this.c.onUndeliveredElement;
                    ukrVar.resume(a2, function1 == null ? null : bindCancellationFun.a((Function1<? super Object, ueu>) function1, pollInternal, ukrVar.getContext()));
                }
            }
            Object e = b.e();
            if (e == ugw.a()) {
                probeCoroutineCreated.b(continuation);
            }
            return e;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016J!\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000b\u001a\u00028\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Receive;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "", "(Lkotlinx/coroutines/CancellableContinuation;I)V", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "resumeValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static class a<E> extends Receive<E> {

        /* renamed from: a, reason: collision with root package name */
        public final CancellableContinuation<Object> f14485a;
        public final int d;

        public a(CancellableContinuation<Object> cancellableContinuation, int i) {
            this.f14485a = cancellableContinuation;
            this.d = i;
        }

        public final Object d(E e) {
            return this.d == 1 ? unf.e(unf.b.a(e)) : e;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public upu tryResumeReceive(E e, LockFreeLinkedListNode.c cVar) {
            Object tryResume = this.f14485a.tryResume(d(e), cVar == null ? null : cVar.f14498a, resumeOnCancellationFun(e));
            if (tryResume == null) {
                return null;
            }
            if (ASSERTIONS_ENABLED.a() && tryResume != RESUMED.c) {
                throw new AssertionError();
            }
            if (cVar != null) {
                cVar.e();
            }
            return RESUMED.c;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            this.f14485a.completeResume(RESUMED.c);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(ung<?> ungVar) {
            if (this.d != 1) {
                CancellableContinuation<Object> cancellableContinuation = this.f14485a;
                uel.b bVar = uel.d;
                cancellableContinuation.resumeWith(uel.b(createFailure.b(ungVar.a())));
            } else {
                CancellableContinuation<Object> cancellableContinuation2 = this.f14485a;
                uel.b bVar2 = uel.d;
                cancellableContinuation2.resumeWith(uel.b(unf.e(unf.b.c(ungVar.d))));
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveElement@" + classSimpleName.d(this) + "[receiveMode=" + this.d + ']';
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B;\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010R&\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElementWithUndeliveredHandler;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlinx/coroutines/CancellableContinuation;ILkotlin/jvm/functions/Function1;)V", "resumeOnCancellationFun", "", "value", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class c<E> extends a<E> {
        public final Function1<E, ueu> c;

        /* JADX WARN: Multi-variable type inference failed */
        public c(CancellableContinuation<Object> cancellableContinuation, int i, Function1<? super E, ueu> function1) {
            super(cancellableContinuation, i);
            this.c = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, ueu> resumeOnCancellationFun(E value) {
            return bindCancellationFun.a(this.c, value, this.f14485a.getContext());
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\n2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J!\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00028\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "resumeOnCancellationFun", "Lkotlin/Function1;", "", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static class d<E> extends Receive<E> {
        public final b<E> b;
        public final CancellableContinuation<Boolean> d;

        /* JADX WARN: Multi-variable type inference failed */
        public d(b<E> bVar, CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.b = bVar;
            this.d = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public upu tryResumeReceive(E e, LockFreeLinkedListNode.c cVar) {
            Object tryResume = this.d.tryResume(true, cVar == null ? null : cVar.f14498a, resumeOnCancellationFun(e));
            if (tryResume == null) {
                return null;
            }
            if (ASSERTIONS_ENABLED.a() && tryResume != RESUMED.c) {
                throw new AssertionError();
            }
            if (cVar != null) {
                cVar.e();
            }
            return RESUMED.c;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            this.b.c(value);
            this.d.completeResume(RESUMED.c);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(ung<?> ungVar) {
            Object tryResumeWithException;
            if (ungVar.d == null) {
                tryResumeWithException = CancellableContinuation.d.e(this.d, false, null, 2, null);
            } else {
                tryResumeWithException = this.d.tryResumeWithException(ungVar.a());
            }
            if (tryResumeWithException != null) {
                this.b.c(ungVar);
                this.d.completeResume(tryResumeWithException);
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, ueu> resumeOnCancellationFun(E value) {
            Function1<E, ueu> function1 = this.b.c.onUndeliveredElement;
            if (function1 == null) {
                return null;
            }
            return bindCancellationFun.a(function1, value, this.d.getContext());
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return uhy.b("ReceiveHasNext@", (Object) classSimpleName.d(this));
        }
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004BR\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012$\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\u0006\u0010\r\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J#\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00172\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u00122\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J!\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0013\u001a\u00028\u00022\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0002\u0010#R3\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "dispose", "resumeOnCancellationFun", "Lkotlin/Function1;", "", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class e<R, E> extends Receive<E> implements DisposableHandle {

        /* renamed from: a, reason: collision with root package name */
        public final Function2<Object, Continuation<? super R>, Object> f14486a;
        public final SelectInstance<R> b;
        public final AbstractChannel<E> c;
        public final int e;

        /* JADX WARN: Multi-variable type inference failed */
        public e(AbstractChannel<E> abstractChannel, SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
            this.c = abstractChannel;
            this.b = selectInstance;
            this.f14486a = function2;
            this.e = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public upu tryResumeReceive(E e, LockFreeLinkedListNode.c cVar) {
            return (upu) this.b.trySelectOther(cVar);
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            dispatcherFailure.e(this.f14486a, this.e == 1 ? unf.e(unf.b.a(value)) : value, this.b.getCompletion(), resumeOnCancellationFun(value));
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(ung<?> ungVar) {
            if (this.b.trySelect()) {
                int i = this.e;
                if (i == 0) {
                    this.b.resumeSelectWithException(ungVar.a());
                } else {
                    if (i != 1) {
                        return;
                    }
                    dispatcherFailure.e(this.f14486a, unf.e(unf.b.c(ungVar.d)), this.b.getCompletion(), null, 4, null);
                }
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                this.c.onReceiveDequeued();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, ueu> resumeOnCancellationFun(E value) {
            Function1<E, ueu> function1 = this.c.onUndeliveredElement;
            if (function1 == null) {
                return null;
            }
            return bindCancellationFun.a(function1, value, this.b.getCompletion().getContext());
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveSelect@" + classSimpleName.d(this) + '[' + this.b + ",receiveMode=" + this.e + ']';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Object receiveSuspend(int i2, Continuation<? super R> continuation) {
        a aVar;
        ukr b2 = getOrCreateCancellableContinuation.b(ugw.a(continuation));
        ukr ukrVar = b2;
        if (this.onUndeliveredElement == null) {
            aVar = new a(ukrVar, i2);
        } else {
            aVar = new c(ukrVar, i2, this.onUndeliveredElement);
        }
        while (true) {
            Receive receive = aVar;
            if (enqueueReceive(receive)) {
                removeReceiveOnCancel(ukrVar, receive);
                break;
            }
            Object pollInternal = pollInternal();
            if (pollInternal instanceof ung) {
                aVar.resumeReceiveClosed((ung) pollInternal);
                break;
            }
            if (pollInternal != C0350umy.i) {
                ukrVar.resume(aVar.d(pollInternal), aVar.resumeOnCancellationFun(pollInternal));
                break;
            }
        }
        Object e2 = b2.e();
        if (e2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e2;
    }

    /* renamed from: onCancelIdempotentList-w-w6eGU, reason: not valid java name */
    protected void mo976onCancelIdempotentListww6eGU(Object obj, ung<?> ungVar) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            ((Send) obj).resumeSendClosed(ungVar);
            return;
        }
        if (obj != null) {
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i2 = size - 1;
                ((Send) arrayList.get(size)).resumeSendClosed(ungVar);
                if (i2 < 0) {
                    return;
                } else {
                    size = i2;
                }
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.AbstractChannel", f = "AbstractChannel.kt", i = {}, l = {633}, m = "receiveCatching-JP2dKIU", n = {}, s = {})
    static final class k extends ContinuationImpl {
        int b;
        /* synthetic */ Object c;
        final /* synthetic */ AbstractChannel<E> d;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.c = obj;
            this.b |= Integer.MIN_VALUE;
            Object mo977receiveCatchingJP2dKIU = this.d.mo977receiveCatchingJP2dKIU(this);
            return mo977receiveCatchingJP2dKIU == ugw.a() ? mo977receiveCatchingJP2dKIU : unf.e(mo977receiveCatchingJP2dKIU);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(AbstractChannel<E> abstractChannel, Continuation<? super k> continuation) {
            super(continuation);
            this.d = abstractChannel;
        }
    }
}
