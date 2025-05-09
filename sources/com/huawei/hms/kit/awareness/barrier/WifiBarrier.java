package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.k.a;
import com.huawei.hms.kit.awareness.barrier.internal.d.l;

/* loaded from: classes9.dex */
public final class WifiBarrier {
    public static AwarenessBarrier keeping(int i, String str, String str2) {
        if (l.a(i)) {
            return a.a(i, 0, str, str2);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier keeping(int i) {
        if (l.a(i)) {
            return a.a(i, 0, null, null);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier enabling() {
        return a.a(-1, 1, null, null);
    }

    public static AwarenessBarrier disconnecting(String str, String str2) {
        return a.a(-1, 4, str, str2);
    }

    public static AwarenessBarrier disconnecting() {
        return a.a(-1, 4, null, null);
    }

    public static AwarenessBarrier disabling() {
        return a.a(-1, 2, null, null);
    }

    public static AwarenessBarrier connecting(String str, String str2) {
        return a.a(-1, 3, str, str2);
    }

    public static AwarenessBarrier connecting() {
        return a.a(-1, 3, null, null);
    }
}
