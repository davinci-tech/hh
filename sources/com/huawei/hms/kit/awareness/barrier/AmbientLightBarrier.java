package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.d.a;

/* loaded from: classes9.dex */
public final class AmbientLightBarrier {
    public static AwarenessBarrier range(float f, float f2) {
        a a2 = a.a(f, f2, 1);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier below(float f) {
        a a2 = a.a(0.0f, f, 0);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier above(float f) {
        a a2 = a.a(f, Float.MAX_VALUE, 2);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    private AmbientLightBarrier() {
    }
}
