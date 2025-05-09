package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.b.HHI;

/* loaded from: classes9.dex */
public class BarrierQueryResponse extends HHI<BarrierStatusMap> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "queryBarriers failed!";
    }

    public BarrierStatusMap getBarrierStatusMap() {
        return getStatus();
    }

    public BarrierQueryResponse(BarrierStatusMap barrierStatusMap) {
        super(barrierStatusMap);
    }
}
