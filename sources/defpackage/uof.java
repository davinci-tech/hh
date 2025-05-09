package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import defpackage.uel;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0013B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ)\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f0\u000b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0002J\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "<init>", "()V", "Lkotlinx/coroutines/flow/StateFlowImpl;", "flow", "", "allocateLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)Z", "", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lkotlin/coroutines/Continuation;", "freeLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "()Z", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
final class uof extends AbstractSharedFlowSlot<uoc<?>> {
    static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(uof.class, Object.class, "_state");
    volatile /* synthetic */ Object _state = null;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean allocateLocked(uoc<?> uocVar) {
        upu upuVar;
        if (this._state != null) {
            return false;
        }
        upuVar = NONE.b;
        this._state = upuVar;
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Continuation<ueu>[] freeLocked(uoc<?> uocVar) {
        this._state = null;
        return EMPTY_RESUMES.e;
    }

    public final boolean b() {
        upu upuVar;
        upu upuVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
        upuVar = NONE.b;
        Object andSet = atomicReferenceFieldUpdater.getAndSet(this, upuVar);
        uhy.d(andSet);
        if (ASSERTIONS_ENABLED.a() && !(!(andSet instanceof ukr))) {
            throw new AssertionError();
        }
        upuVar2 = NONE.c;
        return andSet == upuVar2;
    }

    public final void c() {
        upu upuVar;
        upu upuVar2;
        upu upuVar3;
        upu upuVar4;
        while (true) {
            Object obj = this._state;
            if (obj == null) {
                return;
            }
            upuVar = NONE.c;
            if (obj == upuVar) {
                return;
            }
            upuVar2 = NONE.b;
            if (obj == upuVar2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
                upuVar3 = NONE.c;
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, upuVar3)) {
                    return;
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = b;
                upuVar4 = NONE.b;
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater2, this, obj, upuVar4)) {
                    uel.b bVar = uel.d;
                    ((ukr) obj).resumeWith(uel.b(ueu.d));
                    return;
                }
            }
        }
    }

    public final Object d(Continuation<? super ueu> continuation) {
        upu upuVar;
        upu upuVar2;
        ukr ukrVar = new ukr(ugw.a(continuation), 1);
        ukrVar.initCancellability();
        ukr ukrVar2 = ukrVar;
        if (ASSERTIONS_ENABLED.a() && !(true ^ (this._state instanceof ukr))) {
            throw new AssertionError();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
        upuVar = NONE.b;
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, upuVar, ukrVar2)) {
            if (ASSERTIONS_ENABLED.a()) {
                Object obj = this._state;
                upuVar2 = NONE.c;
                if (obj != upuVar2) {
                    throw new AssertionError();
                }
            }
            uel.b bVar = uel.d;
            ukrVar2.resumeWith(uel.b(ueu.d));
        }
        Object e = ukrVar.e();
        if (e == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e == ugw.a() ? e : ueu.d;
    }
}
