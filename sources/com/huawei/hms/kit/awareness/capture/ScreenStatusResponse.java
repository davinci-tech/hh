package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.ScreenStatus;

/* loaded from: classes9.dex */
public class ScreenStatusResponse extends HHI<ScreenStatus> {
    public ScreenStatus getScreenStatus() {
        return getStatus();
    }

    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getScreenStatus failed";
    }

    public ScreenStatusResponse(ScreenStatus screenStatus) {
        super(screenStatus);
    }
}
