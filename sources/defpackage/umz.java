package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.embedded.g4;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Receive;
import kotlinx.coroutines.channels.ReceiveOrClosed;
import kotlinx.coroutines.channels.Send;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000BB9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012 \u0010\t\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ#\u0010 \u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0012H\u0014¢\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u0004\u0018\u00010\u0017H\u0014¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b'\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010+R\u001e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00102\u001a\u00020/8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b7\u00106R\u0014\u00108\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0014\u00109\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b9\u00106R\u0014\u0010:\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u00106R\u0014\u0010;\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00106R\u0018\u0010>\u001a\u00060<j\u0002`=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010@¨\u0006A"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", ExifInterface.LONGITUDE_EAST, "", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "currentSize", FunctionSetBeanReader.BI_ELEMENT, "enqueueElement", "(ILjava/lang/Object;)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "ensureCapacity", "(I)V", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "wasClosed", "onCancelIdempotent", "(Z)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "updateBufferSize", "(I)Lkotlinx/coroutines/internal/Symbol;", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "I", "head", "isBufferAlwaysEmpty", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class umz<E> extends AbstractChannel<E> {

    /* renamed from: a, reason: collision with root package name */
    private final ReentrantLock f17475a;
    private final BufferOverflow b;
    private Object[] c;
    private int d;
    private final int e;
    private volatile /* synthetic */ int size;

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    public umz(int i, BufferOverflow bufferOverflow, Function1<? super E, ueu> function1) {
        super(function1);
        this.e = i;
        this.b = bufferOverflow;
        if (i < 1) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i + " was specified").toString());
        }
        this.f17475a = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i, 8)];
        uez.e(objArr, C0350umy.c, 0, 0, 6, null);
        this.c = objArr;
        this.size = 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.e && this.b == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerInternal(E element) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        upu tryResumeReceive;
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            int i = this.size;
            ung<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            upu d = d(i);
            if (d != null) {
                return d;
            }
            if (i == 0) {
                do {
                    takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (takeFirstReceiveOrPeekClosed != null) {
                        if (takeFirstReceiveOrPeekClosed instanceof ung) {
                            this.size = i;
                            return takeFirstReceiveOrPeekClosed;
                        }
                        tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
                    }
                } while (tryResumeReceive == null);
                if (ASSERTIONS_ENABLED.a() && tryResumeReceive != RESUMED.c) {
                    throw new AssertionError();
                }
                this.size = i;
                ueu ueuVar = ueu.d;
                reentrantLock.unlock();
                takeFirstReceiveOrPeekClosed.completeResumeReceive(element);
                return takeFirstReceiveOrPeekClosed.getOfferResult();
            }
            e(i, element);
            return C0350umy.f17474a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        Object performAtomicTrySelect;
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            int i = this.size;
            ung<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            upu d = d(i);
            if (d != null) {
                return d;
            }
            if (i == 0) {
                do {
                    AbstractSendChannel.c<E> describeTryOffer = describeTryOffer(element);
                    performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
                    if (performAtomicTrySelect == null) {
                        this.size = i;
                        ReceiveOrClosed<? super E> b2 = describeTryOffer.b();
                        ueu ueuVar = ueu.d;
                        reentrantLock.unlock();
                        uhy.d(b2);
                        ReceiveOrClosed<? super E> receiveOrClosed = b2;
                        receiveOrClosed.completeResumeReceive(element);
                        return receiveOrClosed.getOfferResult();
                    }
                    if (performAtomicTrySelect == C0350umy.b) {
                    }
                } while (performAtomicTrySelect == NO_DECISION.c);
                if (performAtomicTrySelect != ALREADY_SELECTED.e() && !(performAtomicTrySelect instanceof ung)) {
                    throw new IllegalStateException(uhy.b("performAtomicTrySelect(describeTryOffer) returned ", performAtomicTrySelect).toString());
                }
                this.size = i;
                return performAtomicTrySelect;
            }
            if (!select.trySelect()) {
                this.size = i;
                return ALREADY_SELECTED.e();
            }
            e(i, element);
            return C0350umy.f17474a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object enqueueSend(Send send) {
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    private final upu d(int i) {
        if (i < this.e) {
            this.size = i + 1;
            return null;
        }
        int i2 = b.d[this.b.ordinal()];
        if (i2 == 1) {
            return C0350umy.b;
        }
        if (i2 == 2) {
            return C0350umy.f17474a;
        }
        if (i2 == 3) {
            return null;
        }
        throw new uep();
    }

    private final void e(int i, E e) {
        if (i < this.e) {
            a(i);
            Object[] objArr = this.c;
            objArr[(this.d + i) % objArr.length] = e;
        } else {
            if (ASSERTIONS_ENABLED.a() && this.b != BufferOverflow.DROP_OLDEST) {
                throw new AssertionError();
            }
            Object[] objArr2 = this.c;
            int i2 = this.d;
            objArr2[i2 % objArr2.length] = null;
            objArr2[(i + i2) % objArr2.length] = e;
            this.d = (i2 + 1) % objArr2.length;
        }
    }

    private final void a(int i) {
        Object[] objArr = this.c;
        if (i >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.e);
            Object[] objArr2 = new Object[min];
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr3 = this.c;
                objArr2[i2] = objArr3[(this.d + i2) % objArr3.length];
            }
            uez.b((upu[]) objArr2, C0350umy.c, i, min);
            this.c = objArr2;
            this.d = 0;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public Object pollInternal() {
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = C0350umy.i;
                }
                return closedForSend;
            }
            Object[] objArr = this.c;
            int i2 = this.d;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object obj2 = C0350umy.i;
            boolean z = false;
            if (i == this.e) {
                Send send2 = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    upu tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (tryResumeSend != null) {
                        if (ASSERTIONS_ENABLED.a() && tryResumeSend != RESUMED.c) {
                            throw new AssertionError();
                        }
                        obj2 = takeFirstSendOrPeekClosed.getE();
                        z = true;
                        send = takeFirstSendOrPeekClosed;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send2 = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != C0350umy.i && !(obj2 instanceof ung)) {
                this.size = i;
                Object[] objArr2 = this.c;
                objArr2[(this.d + i) % objArr2.length] = obj2;
            }
            this.d = (this.d + 1) % this.c.length;
            ueu ueuVar = ueu.d;
            if (z) {
                uhy.d(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097 A[Catch: all -> 0x00c0, TRY_LEAVE, TryCatch #0 {all -> 0x00c0, blocks: (B:3:0x0007, B:5:0x000b, B:7:0x0011, B:11:0x0017, B:13:0x002b, B:49:0x0038, B:29:0x007d, B:31:0x0081, B:33:0x0085, B:34:0x00a7, B:39:0x0091, B:41:0x0097, B:15:0x0048, B:17:0x004d, B:21:0x0052, B:23:0x0058, B:26:0x0064, B:44:0x006c, B:45:0x007b), top: B:2:0x0007 }] */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object pollSelectInternal(kotlinx.coroutines.selects.SelectInstance<?> r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.f17475a
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            int r1 = r8.size     // Catch: java.lang.Throwable -> Lc0
            if (r1 != 0) goto L17
            ung r9 = r8.getClosedForSend()     // Catch: java.lang.Throwable -> Lc0
            if (r9 != 0) goto L13
            upu r9 = defpackage.C0350umy.i     // Catch: java.lang.Throwable -> Lc0
        L13:
            r0.unlock()
            return r9
        L17:
            java.lang.Object[] r2 = r8.c     // Catch: java.lang.Throwable -> Lc0
            int r3 = r8.d     // Catch: java.lang.Throwable -> Lc0
            r4 = r2[r3]     // Catch: java.lang.Throwable -> Lc0
            r5 = 0
            r2[r3] = r5     // Catch: java.lang.Throwable -> Lc0
            int r2 = r1 + (-1)
            r8.size = r2     // Catch: java.lang.Throwable -> Lc0
            upu r2 = defpackage.C0350umy.i     // Catch: java.lang.Throwable -> Lc0
            int r3 = r8.e     // Catch: java.lang.Throwable -> Lc0
            r6 = 1
            if (r1 != r3) goto L7c
        L2b:
            kotlinx.coroutines.channels.AbstractChannel$i r3 = r8.describeTryPoll()     // Catch: java.lang.Throwable -> Lc0
            r7 = r3
            kotlinx.coroutines.internal.AtomicDesc r7 = (kotlinx.coroutines.internal.AtomicDesc) r7     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object r7 = r9.performAtomicTrySelect(r7)     // Catch: java.lang.Throwable -> Lc0
            if (r7 != 0) goto L48
            java.lang.Object r5 = r3.b()     // Catch: java.lang.Throwable -> Lc0
            defpackage.uhy.d(r5)     // Catch: java.lang.Throwable -> Lc0
            r2 = r5
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object r2 = r2.getE()     // Catch: java.lang.Throwable -> Lc0
            r3 = r6
            goto L7d
        L48:
            upu r3 = defpackage.C0350umy.i     // Catch: java.lang.Throwable -> Lc0
            if (r7 != r3) goto L4d
            goto L7c
        L4d:
            java.lang.Object r3 = defpackage.NO_DECISION.c     // Catch: java.lang.Throwable -> Lc0
            if (r7 != r3) goto L52
            goto L2b
        L52:
            java.lang.Object r2 = defpackage.ALREADY_SELECTED.e()     // Catch: java.lang.Throwable -> Lc0
            if (r7 != r2) goto L64
            r8.size = r1     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object[] r9 = r8.c     // Catch: java.lang.Throwable -> Lc0
            int r1 = r8.d     // Catch: java.lang.Throwable -> Lc0
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lc0
            r0.unlock()
            return r7
        L64:
            boolean r2 = r7 instanceof defpackage.ung     // Catch: java.lang.Throwable -> Lc0
            if (r2 == 0) goto L6c
            r3 = r6
            r2 = r7
            r5 = r2
            goto L7d
        L6c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lc0
            java.lang.String r1 = "performAtomicTrySelect(describeTryOffer) returned "
            java.lang.String r1 = defpackage.uhy.b(r1, r7)     // Catch: java.lang.Throwable -> Lc0
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc0
            r9.<init>(r1)     // Catch: java.lang.Throwable -> Lc0
            throw r9     // Catch: java.lang.Throwable -> Lc0
        L7c:
            r3 = 0
        L7d:
            upu r7 = defpackage.C0350umy.i     // Catch: java.lang.Throwable -> Lc0
            if (r2 == r7) goto L91
            boolean r7 = r2 instanceof defpackage.ung     // Catch: java.lang.Throwable -> Lc0
            if (r7 != 0) goto L91
            r8.size = r1     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object[] r9 = r8.c     // Catch: java.lang.Throwable -> Lc0
            int r7 = r8.d     // Catch: java.lang.Throwable -> Lc0
            int r7 = r7 + r1
            int r1 = r9.length     // Catch: java.lang.Throwable -> Lc0
            int r7 = r7 % r1
            r9[r7] = r2     // Catch: java.lang.Throwable -> Lc0
            goto La7
        L91:
            boolean r9 = r9.trySelect()     // Catch: java.lang.Throwable -> Lc0
            if (r9 != 0) goto La7
            r8.size = r1     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object[] r9 = r8.c     // Catch: java.lang.Throwable -> Lc0
            int r1 = r8.d     // Catch: java.lang.Throwable -> Lc0
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lc0
            java.lang.Object r9 = defpackage.ALREADY_SELECTED.e()     // Catch: java.lang.Throwable -> Lc0
            r0.unlock()
            return r9
        La7:
            int r9 = r8.d     // Catch: java.lang.Throwable -> Lc0
            int r9 = r9 + r6
            java.lang.Object[] r1 = r8.c     // Catch: java.lang.Throwable -> Lc0
            int r1 = r1.length     // Catch: java.lang.Throwable -> Lc0
            int r9 = r9 % r1
            r8.d = r9     // Catch: java.lang.Throwable -> Lc0
            ueu r9 = defpackage.ueu.d     // Catch: java.lang.Throwable -> Lc0
            r0.unlock()
            if (r3 == 0) goto Lbf
            defpackage.uhy.d(r5)
            kotlinx.coroutines.channels.Send r5 = (kotlinx.coroutines.channels.Send) r5
            r5.completeResumeSend()
        Lbf:
            return r4
        Lc0:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.umz.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean wasClosed) {
        Function1<E, ueu> function1 = this.onUndeliveredElement;
        ReentrantLock reentrantLock = this.f17475a;
        reentrantLock.lock();
        try {
            int i = this.size;
            uqc uqcVar = null;
            int i2 = 0;
            while (i2 < i) {
                i2++;
                Object obj = this.c[this.d];
                if (function1 != null && obj != C0350umy.c) {
                    uqcVar = bindCancellationFun.a((Function1<? super Object, ueu>) function1, obj, uqcVar);
                }
                this.c[this.d] = C0350umy.c;
                this.d = (this.d + 1) % this.c.length;
            }
            this.size = 0;
            ueu ueuVar = ueu.d;
            reentrantLock.unlock();
            super.onCancelIdempotent(wasClosed);
            if (uqcVar != null) {
                throw uqcVar;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.e + ",size=" + this.size + g4.l;
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public final /* synthetic */ class b {
        public static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            d = iArr;
        }
    }
}
