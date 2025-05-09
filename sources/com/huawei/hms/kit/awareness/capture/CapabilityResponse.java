package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;

/* loaded from: classes9.dex */
public class CapabilityResponse extends HHI<CapabilityStatus> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "querySupportingCapabilities failed";
    }

    public CapabilityStatus getCapabilityStatus() {
        return getStatus();
    }

    public CapabilityResponse(CapabilityStatus capabilityStatus) {
        super(capabilityStatus);
    }
}
