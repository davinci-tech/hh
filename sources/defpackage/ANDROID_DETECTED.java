package defpackage;

import defpackage.uel;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"ANDROID_DETECTED", "", "getANDROID_DETECTED", "()Z", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: upd, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class ANDROID_DETECTED {
    private static final boolean e;

    static {
        Object b;
        try {
            uel.b bVar = uel.d;
            b = uel.b(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            b = uel.b(createFailure.b(th));
        }
        e = uel.c(b);
    }

    public static final boolean a() {
        return e;
    }
}
