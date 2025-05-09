package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.AmbientLightStatus;

/* loaded from: classes9.dex */
public class AmbientLightResponse extends HHI<AmbientLightStatus> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getLightIntensity failed";
    }

    public AmbientLightStatus getAmbientLightStatus() {
        return getStatus();
    }

    public AmbientLightResponse(AmbientLightStatus ambientLightStatus) {
        super(ambientLightStatus);
    }
}
