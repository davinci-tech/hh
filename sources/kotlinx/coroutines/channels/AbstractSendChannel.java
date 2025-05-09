package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.ALREADY_SELECTED;
import defpackage.ASSERTIONS_ENABLED;
import defpackage.C0350umy;
import defpackage.CONDITION_FALSE;
import defpackage.NO_DECISION;
import defpackage.REMOVE_PREPARED;
import defpackage.RESUMED;
import defpackage.baseContinuationImplClass;
import defpackage.bindCancellationFun;
import defpackage.classSimpleName;
import defpackage.createFailure;
import defpackage.dispatcherFailure;
import defpackage.getOrCreateCancellableContinuation;
import defpackage.probeCoroutineCreated;
import defpackage.startDirect;
import defpackage.ued;
import defpackage.uel;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.uhy;
import defpackage.uii;
import defpackage.ukr;
import defpackage.unf;
import defpackage.ung;
import defpackage.uno;
import defpackage.unq;
import defpackage.upc;
import defpackage.upj;
import defpackage.upu;
import defpackage.uqc;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000006:\u0004defgB)\u0012 \u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u000e\u0012\u0002\b\u00030\u0011j\u0006\u0012\u0002\b\u0003`\u00122\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\u00032\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010!\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b!\u0010\"J\u001b\u0010!\u001a\u00020\b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b!\u0010#J)\u0010&\u001a\u00020\u00032\u0018\u0010%\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`$H\u0016¢\u0006\u0004\b&\u0010\u0007J\u0019\u0010'\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0004\b+\u0010,J#\u0010/\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010.\u001a\u0006\u0012\u0002\b\u00030-H\u0014¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u000201H\u0014¢\u0006\u0004\b2\u00103JX\u00109\u001a\u00020\u0003\"\u0004\b\u0001\u001042\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010-2\u0006\u0010\u0010\u001a\u00028\u00002(\u00108\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000107\u0012\u0006\u0012\u0004\u0018\u00010\u001a05H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010;J\u001d\u0010=\u001a\b\u0012\u0002\b\u0003\u0018\u00010<2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b=\u0010>J\u001b\u0010?\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b?\u0010;J\u0017\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010<H\u0014¢\u0006\u0004\b@\u0010AJ\u0011\u0010B\u001a\u0004\u0018\u00010\u0018H\u0004¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016¢\u0006\u0004\bE\u0010FJ$\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030G2\u0006\u0010\u0010\u001a\u00028\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bH\u0010,J+\u0010J\u001a\u00020\u0003*\u0006\u0012\u0002\b\u0003072\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020D8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\bL\u0010FR\u001a\u0010P\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u001a\u0010R\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010OR\u0014\u0010S\u001a\u00020\n8$X¤\u0004¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020\n8$X¤\u0004¢\u0006\u0006\u001a\u0004\bU\u0010TR\u0011\u0010V\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0014\u0010W\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010TR#\u0010[\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000060X8F¢\u0006\u0006\u001a\u0004\bY\u0010ZR.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00048\u0004X\u0085\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\\R\u001a\u0010^\u001a\u00020]8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020D8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bb\u0010F\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006h"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", FunctionSetBeanReader.BI_ELEMENT, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "trySend", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    protected final Function1<E, ueu> onUndeliveredElement;
    private final upj queue = new upj();
    private volatile /* synthetic */ Object onCloseHandler = null;

    protected abstract boolean isBufferAlwaysFull();

    protected abstract boolean isBufferFull();

    protected void onClosedIdempotent(LockFreeLinkedListNode closed) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSendChannel(Function1<? super E, ueu> function1) {
        this.onUndeliveredElement = function1;
    }

    protected final upj getQueue() {
        return this.queue;
    }

    public Object offerInternal(E element) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        upu tryResumeReceive;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return C0350umy.b;
            }
            tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
        } while (tryResumeReceive == null);
        if (ASSERTIONS_ENABLED.a() && tryResumeReceive != RESUMED.c) {
            throw new AssertionError();
        }
        takeFirstReceiveOrPeekClosed.completeResumeReceive(element);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        c<E> describeTryOffer = describeTryOffer(element);
        Object performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> b2 = describeTryOffer.b();
        b2.completeResumeReceive(element);
        return b2.getOfferResult();
    }

    protected final ung<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        ung<?> ungVar = prevNode instanceof ung ? (ung) prevNode : null;
        if (ungVar == null) {
            return null;
        }
        helpClose(ungVar);
        return ungVar;
    }

    protected final ung<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        ung<?> ungVar = nextNode instanceof ung ? (ung) nextNode : null;
        if (ungVar == null) {
            return null;
        }
        helpClose(ungVar);
        return ungVar;
    }

    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", ParamConstants.CallbackMethod.ON_PREPARE, "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class e extends LockFreeLinkedListNode.CondAddOp {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AbstractSendChannel f14489a;
        final /* synthetic */ LockFreeLinkedListNode d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(LockFreeLinkedListNode lockFreeLinkedListNode, AbstractSendChannel abstractSendChannel) {
            super(lockFreeLinkedListNode);
            this.d = lockFreeLinkedListNode;
            this.f14489a = abstractSendChannel;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (this.f14489a.isBufferFull()) {
                return null;
            }
            return CONDITION_FALSE.d();
        }
    }

    protected final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode removeOrNext;
        upj upjVar = this.queue;
        while (true) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) upjVar.getNext();
            if (lockFreeLinkedListNode != upjVar && (lockFreeLinkedListNode instanceof Send)) {
                if (((((Send) lockFreeLinkedListNode) instanceof ung) && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        lockFreeLinkedListNode = null;
        return (Send) lockFreeLinkedListNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode prevNode;
        upj upjVar = this.queue;
        a aVar = new a(element);
        do {
            prevNode = upjVar.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(aVar, upjVar));
        return null;
    }

    protected final LockFreeLinkedListNode.d<?> describeSendBuffered(E e2) {
        return new b(this.queue, e2);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", FunctionSetBeanReader.BI_ELEMENT, "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    static class b<E> extends LockFreeLinkedListNode.d<a<? extends E>> {
        public b(upj upjVar, E e) {
            super(upjVar, new a(e));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof ung) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return C0350umy.b;
            }
            return null;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e2, Continuation<? super ueu> continuation) {
        Object sendSuspend;
        return (offerInternal(e2) != C0350umy.f17474a && (sendSuspend = sendSuspend(e2, continuation)) == ugw.a()) ? sendSuspend : ueu.d;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(E element) {
        uqc b2;
        try {
            return SendChannel.d.b(this, element);
        } catch (Throwable th) {
            Function1<E, ueu> function1 = this.onUndeliveredElement;
            if (function1 != null && (b2 = bindCancellationFun.b(function1, element, null, 2, null)) != null) {
                ued.c(b2, th);
                throw b2;
            }
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    public final Object mo979trySendJP2dKIU(E element) {
        Object offerInternal = offerInternal(element);
        if (offerInternal == C0350umy.f17474a) {
            return unf.b.a(ueu.d);
        }
        if (offerInternal == C0350umy.b) {
            ung<?> closedForSend = getClosedForSend();
            return closedForSend == null ? unf.b.e() : unf.b.c(helpCloseAndGetSendException(closedForSend));
        }
        if (offerInternal instanceof ung) {
            return unf.b.c(helpCloseAndGetSendException((ung) offerInternal));
        }
        throw new IllegalStateException(uhy.b("trySend returned ", offerInternal).toString());
    }

    private final Throwable helpCloseAndGetSendException(ung<?> ungVar) {
        helpClose(ungVar);
        return ungVar.b();
    }

    private final Throwable helpCloseAndGetSendException(E e2, ung<?> ungVar) {
        uqc b2;
        helpClose(ungVar);
        Function1<E, ueu> function1 = this.onUndeliveredElement;
        if (function1 != null && (b2 = bindCancellationFun.b(function1, e2, null, 2, null)) != null) {
            ued.c(b2, ungVar.b());
            throw b2;
        }
        return ungVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, E e2, ung<?> ungVar) {
        uqc b2;
        helpClose(ungVar);
        Throwable b3 = ungVar.b();
        Function1<E, ueu> function1 = this.onUndeliveredElement;
        if (function1 != null && (b2 = bindCancellationFun.b(function1, e2, null, 2, null)) != null) {
            uqc uqcVar = b2;
            ued.c(uqcVar, b3);
            uel.b bVar = uel.d;
            continuation.resumeWith(uel.b(createFailure.b((Throwable) uqcVar)));
            return;
        }
        uel.b bVar2 = uel.d;
        continuation.resumeWith(uel.b(createFailure.b(b3)));
    }

    public Object enqueueSend(Send send) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            upj upjVar = this.queue;
            do {
                prevNode = upjVar.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, upjVar));
            return null;
        }
        upj upjVar2 = this.queue;
        Send send2 = send;
        e eVar = new e(send2, this);
        do {
            LockFreeLinkedListNode prevNode2 = upjVar2.getPrevNode();
            if (prevNode2 instanceof ReceiveOrClosed) {
                return prevNode2;
            }
            tryCondAddNext = prevNode2.tryCondAddNext(send2, upjVar2, eVar);
            if (tryCondAddNext == 1) {
                return null;
            }
        } while (tryCondAddNext != 2);
        return C0350umy.d;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        boolean z;
        ung<?> ungVar = new ung<>(cause);
        upj upjVar = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = upjVar.getPrevNode();
            z = true;
            if (!(!(prevNode instanceof ung))) {
                z = false;
                break;
            }
            if (prevNode.addNext(ungVar, upjVar)) {
                break;
            }
        }
        if (!z) {
            ungVar = (ung) this.queue.getPrevNode();
        }
        helpClose(ungVar);
        if (z) {
            invokeOnCloseHandler(cause);
        }
        return z;
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object obj = this.onCloseHandler;
        if (obj == null || obj == C0350umy.e || !ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, obj, C0350umy.e)) {
            return;
        }
        ((Function1) uii.e(obj, 1)).invoke(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, ueu> handler) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, handler)) {
            Object obj = this.onCloseHandler;
            if (obj == C0350umy.e) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException(uhy.b("Another handler was already registered: ", obj));
        }
        ung<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, handler, C0350umy.e)) {
            return;
        }
        handler.invoke(closedForSend.d);
    }

    private final void helpClose(ung<?> ungVar) {
        Object b2 = upc.b(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = ungVar.getPrevNode();
            Receive receive = prevNode instanceof Receive ? (Receive) prevNode : null;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                b2 = upc.a(b2, receive);
            }
        }
        if (b2 != null) {
            if (!(b2 instanceof ArrayList)) {
                ((Receive) b2).resumeReceiveClosed(ungVar);
            } else if (b2 != null) {
                ArrayList arrayList = (ArrayList) b2;
                int size = arrayList.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i = size - 1;
                        ((Receive) arrayList.get(size)).resumeReceiveClosed(ungVar);
                        if (i < 0) {
                            break;
                        } else {
                            size = i;
                        }
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            }
        }
        onClosedIdempotent(ungVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ?? r1;
        LockFreeLinkedListNode removeOrNext;
        upj upjVar = this.queue;
        while (true) {
            r1 = (LockFreeLinkedListNode) upjVar.getNext();
            if (r1 != upjVar && (r1 instanceof ReceiveOrClosed)) {
                if (((((ReceiveOrClosed) r1) instanceof ung) && !r1.isRemoved()) || (removeOrNext = r1.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        r1 = 0;
        return (ReceiveOrClosed) r1;
    }

    protected final c<E> describeTryOffer(E e2) {
        return new c<>(e2, this.queue);
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", FunctionSetBeanReader.BI_ELEMENT, "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class c<E> extends LockFreeLinkedListNode.b<ReceiveOrClosed<? super E>> {
        public final E c;

        public c(E e, upj upjVar) {
            super(upjVar);
            this.c = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.b, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof ung) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return C0350umy.b;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.c cVar) {
            upu tryResumeReceive = ((ReceiveOrClosed) cVar.b).tryResumeReceive(this.c, cVar);
            if (tryResumeReceive == null) {
                return REMOVE_PREPARED.c;
            }
            if (tryResumeReceive == NO_DECISION.c) {
                return NO_DECISION.c;
            }
            if (!ASSERTIONS_ENABLED.a() || tryResumeReceive == RESUMED.c) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001JV\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u00028\u00002(\u0010\t\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"kotlinx/coroutines/channels/AbstractSendChannel$onSend$1", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "registerSelectClause2", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", RemoteMessageConst.MessageBody.PARAM, "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class g implements SelectClause2<E, SendChannel<? super E>> {
        final /* synthetic */ AbstractSendChannel<E> d;

        g(AbstractSendChannel<E> abstractSendChannel) {
            this.d = abstractSendChannel;
        }

        @Override // kotlinx.coroutines.selects.SelectClause2
        public <R> void registerSelectClause2(SelectInstance<? super R> select, E param, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
            this.d.registerSelectSend(select, param, block);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new g(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
        while (!select.isSelected()) {
            if (isFullImpl()) {
                d dVar = new d(element, this, select, block);
                Object enqueueSend = enqueueSend(dVar);
                if (enqueueSend == null) {
                    select.disposeOnSelect(dVar);
                    return;
                }
                if (enqueueSend instanceof ung) {
                    throw baseContinuationImplClass.a(helpCloseAndGetSendException(element, (ung) enqueueSend));
                }
                if (enqueueSend != C0350umy.d && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueSend + ' ').toString());
                }
            }
            Object offerSelectInternal = offerSelectInternal(element, select);
            if (offerSelectInternal == ALREADY_SELECTED.e()) {
                return;
            }
            if (offerSelectInternal != C0350umy.b && offerSelectInternal != NO_DECISION.c) {
                if (offerSelectInternal == C0350umy.f17474a) {
                    startDirect.e(block, this, select.getCompletion());
                    return;
                } else {
                    if (!(offerSelectInternal instanceof ung)) {
                        throw new IllegalStateException(uhy.b("offerSelectInternal returned ", offerSelectInternal).toString());
                    }
                    throw baseContinuationImplClass.a(helpCloseAndGetSendException(element, (ung) offerSelectInternal));
                }
            }
        }
    }

    public String toString() {
        return classSimpleName.b(this) + '@' + classSimpleName.d(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String b2;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof ung) {
            b2 = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            b2 = "ReceiveQueued";
        } else {
            b2 = nextNode instanceof Send ? "SendQueued" : uhy.b("UNEXPECTED:", nextNode);
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode == nextNode) {
            return b2;
        }
        String str = b2 + ",queueSize=" + countQueueSize();
        if (!(prevNode instanceof ung)) {
            return str;
        }
        return str + ",closedForSend=" + prevNode;
    }

    private final int countQueueSize() {
        upj upjVar = this.queue;
        int i = 0;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) upjVar.getNext(); !uhy.e(lockFreeLinkedListNode, upjVar); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                i++;
            }
        }
        return i;
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BV\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t\u0012(\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016R7\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "undeliveredElement", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    static final class d<E, R> extends Send implements DisposableHandle {

        /* renamed from: a, reason: collision with root package name */
        private final E f14488a;
        public final AbstractSendChannel<E> b;
        public final SelectInstance<R> c;
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> e;

        @Override // kotlinx.coroutines.channels.Send
        /* renamed from: getPollResult */
        public E getE() {
            return this.f14488a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public d(E e, AbstractSendChannel<E> abstractSendChannel, SelectInstance<? super R> selectInstance, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.f14488a = e;
            this.b = abstractSendChannel;
            this.c = selectInstance;
            this.e = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public upu tryResumeSend(LockFreeLinkedListNode.c cVar) {
            return (upu) this.c.trySelectOther(cVar);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            dispatcherFailure.e(this.e, this.b, this.c.getCompletion(), null, 4, null);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                undeliveredElement();
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(ung<?> ungVar) {
            if (this.c.trySelect()) {
                this.c.resumeSelectWithException(ungVar.b());
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void undeliveredElement() {
            Function1<E, ueu> function1 = this.b.onUndeliveredElement;
            if (function1 == null) {
                return;
            }
            bindCancellationFun.d(function1, getE(), this.c.getCompletion().getF17451a());
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect@" + classSimpleName.d(this) + g4.k + getE() + ")[" + this.b + ", " + this.c + ']';
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Send;", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class a<E> extends Send {
        public final E e;

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        public a(E e) {
            this.e = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        /* renamed from: getPollResult, reason: from getter */
        public Object getE() {
            return this.e;
        }

        @Override // kotlinx.coroutines.channels.Send
        public upu tryResumeSend(LockFreeLinkedListNode.c cVar) {
            upu upuVar = RESUMED.c;
            if (cVar != null) {
                cVar.e();
            }
            return upuVar;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(ung<?> ungVar) {
            if (ASSERTIONS_ENABLED.a()) {
                throw new AssertionError();
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendBuffered@" + classSimpleName.d(this) + g4.k + this.e + g4.l;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sendSuspend(E e2, Continuation<? super ueu> continuation) {
        uno unoVar;
        ukr b2 = getOrCreateCancellableContinuation.b(ugw.a(continuation));
        ukr ukrVar = b2;
        while (true) {
            if (isFullImpl()) {
                if (this.onUndeliveredElement == null) {
                    unoVar = new unq(e2, ukrVar);
                } else {
                    unoVar = new uno(e2, ukrVar, this.onUndeliveredElement);
                }
                Object enqueueSend = enqueueSend(unoVar);
                if (enqueueSend == null) {
                    getOrCreateCancellableContinuation.b(ukrVar, unoVar);
                    break;
                }
                if (enqueueSend instanceof ung) {
                    helpCloseAndResumeWithSendException(ukrVar, e2, (ung) enqueueSend);
                    break;
                }
                if (enqueueSend != C0350umy.d && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(uhy.b("enqueueSend returned ", enqueueSend).toString());
                }
            }
            Object offerInternal = offerInternal(e2);
            if (offerInternal == C0350umy.f17474a) {
                uel.b bVar = uel.d;
                ukrVar.resumeWith(uel.b(ueu.d));
                break;
            }
            if (offerInternal != C0350umy.b) {
                if (offerInternal instanceof ung) {
                    helpCloseAndResumeWithSendException(ukrVar, e2, (ung) offerInternal);
                } else {
                    throw new IllegalStateException(uhy.b("offerInternal returned ", offerInternal).toString());
                }
            }
        }
        Object e3 = b2.e();
        if (e3 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e3 == ugw.a() ? e3 : ueu.d;
    }

    protected String getBufferDebugString() {
        return "";
    }
}
