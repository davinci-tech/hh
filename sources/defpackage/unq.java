package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.hms.network.embedded.g4;
import defpackage.uel;
import kotlin.Metadata;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.channels.Send;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\u0014\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/SendElement;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Send;", "pollResult", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class unq<E> extends Send {
    private final E b;
    public final CancellableContinuation<ueu> e;

    @Override // kotlinx.coroutines.channels.Send
    public E getPollResult() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public unq(E e, CancellableContinuation<? super ueu> cancellableContinuation) {
        this.b = e;
        this.e = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public upu tryResumeSend(LockFreeLinkedListNode.c cVar) {
        Object tryResume = this.e.tryResume(ueu.d, cVar == null ? null : cVar.f14498a);
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

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        this.e.completeResume(RESUMED.c);
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(ung<?> ungVar) {
        CancellableContinuation<ueu> cancellableContinuation = this.e;
        uel.b bVar = uel.d;
        cancellableContinuation.resumeWith(uel.b(createFailure.b(ungVar.b())));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return classSimpleName.b(this) + '@' + classSimpleName.d(this) + g4.k + getPollResult() + g4.l;
    }
}
