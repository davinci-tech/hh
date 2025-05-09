package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.BehaviorStatus;

/* loaded from: classes9.dex */
public class BehaviorResponse extends HHI<BehaviorStatus> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "queryBehaviorResult failed: ";
    }

    public BehaviorStatus getBehaviorStatus() {
        return getStatus();
    }

    public BehaviorResponse(BehaviorStatus behaviorStatus) {
        super(behaviorStatus);
    }
}
