package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.j.a;
import com.huawei.hms.kit.awareness.barrier.internal.d.k;

/* loaded from: classes9.dex */
public final class ScreenBarrier {
    public static AwarenessBarrier screenUnlock() {
        return a.a(3);
    }

    public static AwarenessBarrier screenOn() {
        return a.a(1);
    }

    public static AwarenessBarrier screenOff() {
        return a.a(2);
    }

    public static AwarenessBarrier keeping(int i) {
        if (k.a(i)) {
            return a.a(i, 0);
        }
        throw new IllegalArgumentException();
    }

    private ScreenBarrier() {
    }
}
