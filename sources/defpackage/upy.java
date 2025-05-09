package defpackage;

import kotlin.Metadata;

@Metadata(d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"}, k = 4, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class upy {
    public static final long a(String str, long j, long j2, long j3) {
        return systemProp.d(str, j, j2, j3);
    }

    public static final String c(String str) {
        return AVAILABLE_PROCESSORS.d(str);
    }

    public static final boolean c(String str, boolean z) {
        return systemProp.e(str, z);
    }

    public static final int d() {
        return AVAILABLE_PROCESSORS.e();
    }

    public static final int d(String str, int i, int i2, int i3) {
        return systemProp.b(str, i, i2, i3);
    }
}
