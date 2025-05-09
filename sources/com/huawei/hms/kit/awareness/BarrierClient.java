package com.huawei.hms.kit.awareness;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryRequest;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryResponse;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;

/* loaded from: classes4.dex */
public interface BarrierClient extends Client {
    Task<BarrierQueryResponse> queryBarriers(BarrierQueryRequest barrierQueryRequest);

    Task<Void> updateBarriers(BarrierUpdateRequest barrierUpdateRequest);

    Task<Void> updateBarriers(BarrierUpdateRequest barrierUpdateRequest, boolean z);
}
