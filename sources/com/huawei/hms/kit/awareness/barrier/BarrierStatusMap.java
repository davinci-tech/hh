package com.huawei.hms.kit.awareness.barrier;

import java.util.Set;

/* loaded from: classes4.dex */
public interface BarrierStatusMap {
    Set<String> getBarrierLabels();

    BarrierStatus getBarrierStatus(String str);
}
