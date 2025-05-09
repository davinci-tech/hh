package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.c.a;
import com.huawei.hms.kit.awareness.barrier.internal.d.d;

/* loaded from: classes9.dex */
public final class HeadsetBarrier {
    public static AwarenessBarrier keeping(int i) {
        if (d.a(i)) {
            return a.a(i, 0);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier disconnecting() {
        return a.a(2);
    }

    public static AwarenessBarrier connecting() {
        return a.a(1);
    }

    private HeadsetBarrier() {
    }
}
