package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.a.a;
import com.huawei.hms.kit.awareness.barrier.internal.f.b;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import java.util.Arrays;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class BeaconBarrier {
    public static AwarenessBarrier missed(BeaconStatus.Filter... filterArr) {
        b.a(filterArr != null && filterArr.length > 0);
        return a.a(Arrays.asList(filterArr), 1);
    }

    public static AwarenessBarrier missed(Collection<BeaconStatus.Filter> collection) {
        b.a((collection == null || collection.isEmpty()) ? false : true);
        return a.a(collection, 1);
    }

    public static AwarenessBarrier keep(BeaconStatus.Filter... filterArr) {
        b.a(filterArr != null && filterArr.length > 0);
        return a.a(Arrays.asList(filterArr), 2);
    }

    public static AwarenessBarrier keep(Collection<BeaconStatus.Filter> collection) {
        b.a((collection == null || collection.isEmpty()) ? false : true);
        return a.a(collection, 2);
    }

    public static AwarenessBarrier discover(BeaconStatus.Filter... filterArr) {
        b.a(filterArr != null && filterArr.length > 0);
        return a.a(Arrays.asList(filterArr), 0);
    }

    public static AwarenessBarrier discover(Collection<BeaconStatus.Filter> collection) {
        b.a((collection == null || collection.isEmpty()) ? false : true);
        return a.a(collection, 0);
    }

    private BeaconBarrier() {
    }
}
