package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.g.a;
import com.huawei.hms.kit.awareness.barrier.internal.d.h;

/* loaded from: classes9.dex */
public final class BluetoothBarrier {
    public static AwarenessBarrier keep(int i, int i2) {
        if (h.b(i) && h.a(i2)) {
            return a.a(0, i, i2);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier disconnecting(int i) {
        if (h.b(i)) {
            return a.a(2, i);
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier connecting(int i) {
        if (h.b(i)) {
            return a.a(1, i);
        }
        throw new IllegalArgumentException();
    }

    private BluetoothBarrier() {
    }
}
