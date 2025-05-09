package defpackage;

import defpackage.uel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\bH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"classSimpleName", "", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulk, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class classSimpleName {
    public static final String d(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String e(Continuation<?> continuation) {
        Object b;
        if (continuation instanceof uoz) {
            return continuation.toString();
        }
        try {
            uel.b bVar = uel.d;
            b = uel.b(continuation + '@' + d(continuation));
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            b = uel.b(createFailure.b(th));
        }
        if (uel.e(b) != null) {
            b = ((Object) continuation.getClass().getName()) + '@' + d(continuation);
        }
        return (String) b;
    }

    public static final String b(Object obj) {
        return obj.getClass().getSimpleName();
    }
}
