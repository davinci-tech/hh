package kotlinx.coroutines.sync;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.ALREADY_SELECTED;
import defpackage.EMPTY_LOCKED;
import defpackage.NO_DECISION;
import defpackage.RESUMED;
import defpackage.dispatcherFailure;
import defpackage.getOrCreateCancellableContinuation;
import defpackage.probeCoroutineCreated;
import defpackage.startDirect;
import defpackage.ueu;
import defpackage.ugw;
import defpackage.uhy;
import defpackage.ukr;
import defpackage.upj;
import defpackage.upu;
import defpackage.uqr;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00112\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 :\u0006$%&'()B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJT\u0010\u0014\u001a\u00020\t\"\u0004\b\u0000\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\"\u0010\u0013\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u0019\u0010\bJ\u0019\u0010\u001a\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001dR\"\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "", "locked", "<init>", "(Z)V", "", "owner", "holdsLock", "(Ljava/lang/Object;)Z", "", "lock", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "toString", "()Ljava/lang/String;", "tryLock", "unlock", "(Ljava/lang/Object;)V", "isLocked", "()Z", "isLockedEmptyQueueState$kotlinx_coroutines_core", "isLockedEmptyQueueState", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "onLock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    volatile /* synthetic */ Object _state;

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object obj, Continuation<? super ueu> continuation) {
        Object d2;
        return (!tryLock(obj) && (d2 = d(obj, continuation)) == ugw.a()) ? d2 : ueu.d;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class j extends Lambda implements Function1<Throwable, ueu> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f14505a;

        public final void d(Throwable th) {
            MutexImpl.this.unlock(this.f14505a);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ ueu invoke(Throwable th) {
            d(th);
            return ueu.d;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(Object obj) {
            super(1);
            this.f14505a = obj;
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(SelectInstance<? super R> select, Object owner, Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> block) {
        upu upuVar;
        upu upuVar2;
        while (!select.isSelected()) {
            Object obj = this._state;
            if (obj instanceof uqr) {
                uqr uqrVar = (uqr) obj;
                Object obj2 = uqrVar.c;
                upuVar = EMPTY_LOCKED.d;
                if (obj2 != upuVar) {
                    ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, this, obj, new e(uqrVar.c));
                } else {
                    Object performAtomicTrySelect = select.performAtomicTrySelect(new c(this, owner));
                    if (performAtomicTrySelect == null) {
                        startDirect.e(block, this, select.getCompletion());
                        return;
                    } else {
                        if (performAtomicTrySelect == ALREADY_SELECTED.e()) {
                            return;
                        }
                        upuVar2 = EMPTY_LOCKED.f17507a;
                        if (performAtomicTrySelect != upuVar2 && performAtomicTrySelect != NO_DECISION.c) {
                            throw new IllegalStateException(uhy.b("performAtomicTrySelect(TryLockDesc) returned ", performAtomicTrySelect).toString());
                        }
                    }
                }
            } else if (obj instanceof e) {
                e eVar = (e) obj;
                if (eVar.f14504a == owner) {
                    throw new IllegalStateException(uhy.b("Already locked by ", owner).toString());
                }
                d dVar = new d(owner, select, block);
                eVar.addLast(dVar);
                if (this._state == obj || !dVar.take()) {
                    select.disposeOnSelect(dVar);
                    return;
                }
            } else {
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(uhy.b("Illegal state ", obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", ParamConstants.CallbackMethod.ON_PREPARE, "PrepareOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class c extends AtomicDesc {
        public final Object b;
        public final MutexImpl e;

        public c(MutexImpl mutexImpl, Object obj) {
            this.e = mutexImpl;
            this.b = obj;
        }

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "(Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/internal/AtomicOp;)V", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
        final class b extends OpDescriptor {

            /* renamed from: a, reason: collision with root package name */
            private final AtomicOp<?> f14502a;

            public b(AtomicOp<?> atomicOp) {
                this.f14502a = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public AtomicOp<?> getAtomicOp() {
                return this.f14502a;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public Object perform(Object affected) {
                Object atomicOp = getAtomicOp().isDecided() ? EMPTY_LOCKED.e : getAtomicOp();
                if (affected == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
                }
                ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl.b, (MutexImpl) affected, this, atomicOp);
                return null;
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public Object prepare(AtomicOp<?> op) {
            uqr uqrVar;
            upu upuVar;
            b bVar = new b(op);
            MutexImpl mutexImpl = this.e;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = MutexImpl.b;
            uqrVar = EMPTY_LOCKED.e;
            if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, mutexImpl, uqrVar, bVar)) {
                upuVar = EMPTY_LOCKED.f17507a;
                return upuVar;
            }
            return bVar.perform(this.e);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void complete(AtomicOp<?> op, Object failure) {
            uqr uqrVar;
            if (failure != null) {
                uqrVar = EMPTY_LOCKED.e;
            } else {
                Object obj = this.b;
                uqrVar = obj == null ? EMPTY_LOCKED.b : new uqr(obj);
            }
            ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl.b, this.e, op, uqrVar);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(Object owner) {
        Object obj = this._state;
        return !(obj instanceof uqr) ? !((obj instanceof e) && ((e) obj).f14504a == owner) : ((uqr) obj).c != owner;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "owner", "", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class e extends upj {

        /* renamed from: a, reason: collision with root package name */
        public Object f14504a;

        public e(Object obj) {
            this.f14504a = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockedQueue[" + this.f14504a + ']';
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b¢\u0004\u0018\u00002\u00020\u000f2\u00020\u0010B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0007J\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH&¢\u0006\u0004\b\f\u0010\u000bR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "", "owner", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "", "completeResumeLockWaiter", "()V", "dispose", "", "take", "()Z", "tryResumeLockWaiter", "Ljava/lang/Object;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;"}, k = 1, mv = {1, 6, 0}, xi = 48)
    abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        private static final /* synthetic */ AtomicIntegerFieldUpdater isTaken$FU = AtomicIntegerFieldUpdater.newUpdater(LockWaiter.class, "isTaken");
        private volatile /* synthetic */ int isTaken = 0;
        public final Object owner;

        public abstract void completeResumeLockWaiter();

        public abstract boolean tryResumeLockWaiter();

        public LockWaiter(Object obj) {
            this.owner = obj;
        }

        public final boolean take() {
            return isTaken$FU.compareAndSet(this, 0, 1);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            remove();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u001d\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeLockWaiter", "toString", "", "tryResumeLockWaiter", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class b extends LockWaiter {

        /* renamed from: a, reason: collision with root package name */
        private final CancellableContinuation<ueu> f14501a;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Object obj, CancellableContinuation<? super ueu> cancellableContinuation) {
            super(obj);
            this.f14501a = cancellableContinuation;
        }

        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
        static final class d extends Lambda implements Function1<Throwable, ueu> {
            final /* synthetic */ b c;
            final /* synthetic */ MutexImpl e;

            @Override // kotlin.jvm.functions.Function1
            public /* synthetic */ ueu invoke(Throwable th) {
                c(th);
                return ueu.d;
            }

            public final void c(Throwable th) {
                this.e.unlock(this.c.owner);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            d(MutexImpl mutexImpl, b bVar) {
                super(1);
                this.e = mutexImpl;
                this.c = bVar;
            }
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean tryResumeLockWaiter() {
            return take() && this.f14501a.tryResume(ueu.d, null, new d(MutexImpl.this, this)) != null;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter() {
            this.f14501a.completeResume(RESUMED.c);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockCont[" + this.owner + ", " + this.f14501a + "] for " + MutexImpl.this;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002R\u00020\u0003BD\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R1\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "completeResumeLockWaiter", "", "toString", "", "tryResumeLockWaiter", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class d<R> extends LockWaiter {
        public final SelectInstance<R> c;
        public final Function2<Mutex, Continuation<? super R>, Object> e;

        /* JADX WARN: Multi-variable type inference failed */
        public d(Object obj, SelectInstance<? super R> selectInstance, Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            super(obj);
            this.c = selectInstance;
            this.e = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean tryResumeLockWaiter() {
            return take() && this.c.trySelect();
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "R", "it", "", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
        static final class c extends Lambda implements Function1<Throwable, ueu> {
            final /* synthetic */ d<R> c;
            final /* synthetic */ MutexImpl d;

            @Override // kotlin.jvm.functions.Function1
            public /* synthetic */ ueu invoke(Throwable th) {
                b(th);
                return ueu.d;
            }

            public final void b(Throwable th) {
                this.d.unlock(this.c.owner);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(MutexImpl mutexImpl, d<R> dVar) {
                super(1);
                this.d = mutexImpl;
                this.c = dVar;
            }
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter() {
            dispatcherFailure.e(this.e, MutexImpl.this, this.c.getCompletion(), new c(MutexImpl.this, this));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockSelect[" + this.owner + ", " + this.c + "] for " + MutexImpl.this;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/sync/MutexImpl;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "complete", "", "affected", "failure", "", ParamConstants.CallbackMethod.ON_PREPARE, "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class a extends AtomicOp<MutexImpl> {
        public final e e;

        public a(e eVar) {
            this.e = eVar;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Object prepare(MutexImpl mutexImpl) {
            upu upuVar;
            if (this.e.d()) {
                return null;
            }
            upuVar = EMPTY_LOCKED.f;
            return upuVar;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void complete(MutexImpl mutexImpl, Object obj) {
            ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(MutexImpl.b, mutexImpl, this, obj == null ? EMPTY_LOCKED.e : this.e);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        upu upuVar;
        while (true) {
            Object obj = this._state;
            if (obj instanceof uqr) {
                Object obj2 = ((uqr) obj).c;
                upuVar = EMPTY_LOCKED.d;
                return obj2 != upuVar;
            }
            if (obj instanceof e) {
                return true;
            }
            if (!(obj instanceof OpDescriptor)) {
                throw new IllegalStateException(uhy.b("Illegal state ", obj).toString());
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object owner) {
        upu upuVar;
        while (true) {
            Object obj = this._state;
            if (obj instanceof uqr) {
                Object obj2 = ((uqr) obj).c;
                upuVar = EMPTY_LOCKED.d;
                if (obj2 != upuVar) {
                    return false;
                }
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, this, obj, owner == null ? EMPTY_LOCKED.b : new uqr(owner))) {
                    return true;
                }
            } else {
                if (obj instanceof e) {
                    if (((e) obj).f14504a != owner) {
                        return false;
                    }
                    throw new IllegalStateException(uhy.b("Already locked by ", owner).toString());
                }
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(uhy.b("Illegal state ", obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    private final Object d(Object obj, Continuation<? super ueu> continuation) {
        upu upuVar;
        b bVar;
        ukr b2 = getOrCreateCancellableContinuation.b(ugw.a(continuation));
        ukr ukrVar = b2;
        b bVar2 = new b(obj, ukrVar);
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof uqr) {
                uqr uqrVar = (uqr) obj2;
                Object obj3 = uqrVar.c;
                upuVar = EMPTY_LOCKED.d;
                if (obj3 != upuVar) {
                    ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, this, obj2, new e(uqrVar.c));
                } else {
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, this, obj2, obj == null ? EMPTY_LOCKED.b : new uqr(obj))) {
                        ukrVar.resume(ueu.d, new j(obj));
                        break;
                    }
                }
            } else if (obj2 instanceof e) {
                e eVar = (e) obj2;
                if (eVar.f14504a == obj) {
                    throw new IllegalStateException(uhy.b("Already locked by ", obj).toString());
                }
                bVar = bVar2;
                eVar.addLast(bVar);
                if (this._state == obj2 || !bVar2.take()) {
                    break;
                }
                bVar2 = new b(obj, ukrVar);
            } else {
                if (!(obj2 instanceof OpDescriptor)) {
                    throw new IllegalStateException(uhy.b("Illegal state ", obj2).toString());
                }
                ((OpDescriptor) obj2).perform(this);
            }
        }
        getOrCreateCancellableContinuation.b(ukrVar, bVar);
        Object e2 = b2.e();
        if (e2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e2 == ugw.a() ? e2 : ueu.d;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object owner) {
        uqr uqrVar;
        upu upuVar;
        while (true) {
            Object obj = this._state;
            if (obj instanceof uqr) {
                if (owner == null) {
                    Object obj2 = ((uqr) obj).c;
                    upuVar = EMPTY_LOCKED.d;
                    if (obj2 == upuVar) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    uqr uqrVar2 = (uqr) obj;
                    if (uqrVar2.c != owner) {
                        throw new IllegalStateException(("Mutex is locked by " + uqrVar2.c + " but expected " + owner).toString());
                    }
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
                uqrVar = EMPTY_LOCKED.e;
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, uqrVar)) {
                    return;
                }
            } else if (obj instanceof OpDescriptor) {
                ((OpDescriptor) obj).perform(this);
            } else if (obj instanceof e) {
                if (owner != null) {
                    e eVar = (e) obj;
                    if (eVar.f14504a != owner) {
                        throw new IllegalStateException(("Mutex is locked by " + eVar.f14504a + " but expected " + owner).toString());
                    }
                }
                e eVar2 = (e) obj;
                LockFreeLinkedListNode removeFirstOrNull = eVar2.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    a aVar = new a(eVar2);
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, this, obj, aVar) && aVar.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) removeFirstOrNull;
                    if (lockWaiter.tryResumeLockWaiter()) {
                        Object obj3 = lockWaiter.owner;
                        if (obj3 == null) {
                            obj3 = EMPTY_LOCKED.c;
                        }
                        eVar2.f14504a = obj3;
                        lockWaiter.completeResumeLockWaiter();
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(uhy.b("Illegal state ", obj).toString());
            }
        }
    }

    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof uqr) {
                return "Mutex[" + ((uqr) obj).c + ']';
            }
            if (!(obj instanceof OpDescriptor)) {
                if (!(obj instanceof e)) {
                    throw new IllegalStateException(uhy.b("Illegal state ", obj).toString());
                }
                return "Mutex[" + ((e) obj).f14504a + ']';
            }
            ((OpDescriptor) obj).perform(this);
        }
    }
}
