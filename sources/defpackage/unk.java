package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.embedded.g4;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.channels.Receive;
import kotlinx.coroutines.channels.ReceiveOrClosed;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0014J\u0015\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001dJ!\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0014¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\rH\u0014J\n\u0010$\u001a\u0004\u0018\u00010\u0017H\u0014J\u0016\u0010%\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0014J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0002R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000eR\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isEmpty", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "value", "", "enqueueReceiveInternal", "receive", "Lkotlinx/coroutines/channels/Receive;", "offerInternal", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotent", "wasClosed", "pollInternal", "pollSelectInternal", "updateValueLocked", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class unk<E> extends AbstractChannel<E> {
    private final ReentrantLock c;
    private Object e;

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    public unk(Function1<? super E, ueu> function1) {
        super(function1);
        this.c = new ReentrantLock();
        this.e = C0350umy.c;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.e == C0350umy.c;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerInternal(E element) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        upu tryResumeReceive;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            ung<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.e == C0350umy.c) {
                do {
                    takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (takeFirstReceiveOrPeekClosed != null) {
                        if (takeFirstReceiveOrPeekClosed instanceof ung) {
                            return takeFirstReceiveOrPeekClosed;
                        }
                        tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
                    }
                } while (tryResumeReceive == null);
                if (ASSERTIONS_ENABLED.a() && tryResumeReceive != RESUMED.c) {
                    throw new AssertionError();
                }
                ueu ueuVar = ueu.d;
                reentrantLock.unlock();
                takeFirstReceiveOrPeekClosed.completeResumeReceive(element);
                return takeFirstReceiveOrPeekClosed.getOfferResult();
            }
            uqc e = e(element);
            if (e != null) {
                throw e;
            }
            return C0350umy.f17474a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        Object performAtomicTrySelect;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            ung<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.e == C0350umy.c) {
                do {
                    AbstractSendChannel.c<E> describeTryOffer = describeTryOffer(element);
                    performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
                    if (performAtomicTrySelect == null) {
                        ReceiveOrClosed<? super E> b = describeTryOffer.b();
                        ueu ueuVar = ueu.d;
                        reentrantLock.unlock();
                        uhy.d(b);
                        ReceiveOrClosed<? super E> receiveOrClosed = b;
                        receiveOrClosed.completeResumeReceive(element);
                        return receiveOrClosed.getOfferResult();
                    }
                    if (performAtomicTrySelect == C0350umy.b) {
                    }
                } while (performAtomicTrySelect == NO_DECISION.c);
                if (performAtomicTrySelect != ALREADY_SELECTED.e() && !(performAtomicTrySelect instanceof ung)) {
                    throw new IllegalStateException(uhy.b("performAtomicTrySelect(describeTryOffer) returned ", performAtomicTrySelect).toString());
                }
                return performAtomicTrySelect;
            }
            if (!select.trySelect()) {
                return ALREADY_SELECTED.e();
            }
            uqc e = e(element);
            if (e != null) {
                throw e;
            }
            return C0350umy.f17474a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public Object pollInternal() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (this.e == C0350umy.c) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = C0350umy.i;
                }
                return closedForSend;
            }
            Object obj = this.e;
            this.e = C0350umy.c;
            ueu ueuVar = ueu.d;
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public Object pollSelectInternal(SelectInstance<?> select) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (this.e == C0350umy.c) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = C0350umy.i;
                }
                return closedForSend;
            }
            if (!select.trySelect()) {
                return ALREADY_SELECTED.e();
            }
            Object obj = this.e;
            this.e = C0350umy.c;
            ueu ueuVar = ueu.d;
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean wasClosed) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            uqc e = e(C0350umy.c);
            ueu ueuVar = ueu.d;
            reentrantLock.unlock();
            super.onCancelIdempotent(wasClosed);
            if (e != null) {
                throw e;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    private final uqc e(Object obj) {
        Function1<E, ueu> function1;
        Object obj2 = this.e;
        uqc uqcVar = null;
        if (obj2 != C0350umy.c && (function1 = this.onUndeliveredElement) != null) {
            uqcVar = bindCancellationFun.b(function1, obj2, null, 2, null);
        }
        this.e = obj;
        return uqcVar;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public String getBufferDebugString() {
        return "(value=" + this.e + g4.l;
    }
}
