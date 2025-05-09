package defpackage;

import kotlin.Metadata;
import kotlinx.coroutines.Delay;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\b\u0010\u0006\u001a\u00020\u0001H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay", "()Lkotlinx/coroutines/Delay;", "defaultMainDelayOptIn", "", "initializeDefaultDelay", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulp, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class DefaultDelay {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f17459a = upy.c("kotlinx.coroutines.main.delay", false);
    private static final Delay b = a();

    public static final Delay d() {
        return b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
    
        if (r1 == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlinx.coroutines.Delay a() {
        /*
            boolean r0 = defpackage.DefaultDelay.f17459a
            if (r0 != 0) goto L9
            ulj r0 = defpackage.ulj.e
            kotlinx.coroutines.Delay r0 = (kotlinx.coroutines.Delay) r0
            return r0
        L9:
            kotlinx.coroutines.MainCoroutineDispatcher r0 = defpackage.ulo.a()
            boolean r1 = defpackage.FAST_SERVICE_LOADER_PROPERTY_NAME.c(r0)
            if (r1 != 0) goto L17
            boolean r1 = r0 instanceof kotlinx.coroutines.Delay
            if (r1 != 0) goto L19
        L17:
            ulj r0 = defpackage.ulj.e
        L19:
            kotlinx.coroutines.Delay r0 = (kotlinx.coroutines.Delay) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DefaultDelay.a():kotlinx.coroutines.Delay");
    }
}
