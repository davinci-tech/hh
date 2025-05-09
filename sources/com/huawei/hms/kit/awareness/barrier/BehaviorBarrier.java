package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.b.a;
import com.huawei.hms.kit.awareness.barrier.internal.d.c;

/* loaded from: classes9.dex */
public final class BehaviorBarrier {
    public static final int BEHAVIOR_IN_VEHICLE = 0;
    public static final int BEHAVIOR_ON_BICYCLE = 1;
    public static final int BEHAVIOR_ON_FOOT = 2;
    public static final int BEHAVIOR_RUNNING = 8;
    public static final int BEHAVIOR_STILL = 3;
    public static final int BEHAVIOR_UNKNOWN = 4;
    public static final int BEHAVIOR_WALKING = 7;

    public static AwarenessBarrier keeping(int... iArr) {
        if (c.a(iArr)) {
            return a.a(4, iArr);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier ending(int... iArr) {
        if (c.a(iArr)) {
            return a.a(3, iArr);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier beginning(int... iArr) {
        if (c.a(iArr)) {
            return a.a(2, iArr);
        }
        throw new IllegalArgumentException();
    }

    private BehaviorBarrier() {
    }
}
