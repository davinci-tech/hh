package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.e.a;
import com.huawei.hms.kit.awareness.barrier.internal.type.g;

/* loaded from: classes9.dex */
public final class LocationBarrier {
    public static AwarenessBarrier stay(double d, double d2, double d3, long j) {
        a a2 = a.a(d, d2, d3, g.IN, j);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier exit(double d, double d2, double d3) {
        a a2 = a.a(d, d2, d3, g.EXITING, 0L);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier enter(double d, double d2, double d3) {
        a a2 = a.a(d, d2, d3, g.ENTERING, 0L);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    private LocationBarrier() {
    }
}
