package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.DarkModeStatus;

/* loaded from: classes9.dex */
public class DarkModeStatusResponse extends HHI<DarkModeStatus> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getWifiStatus failed: ";
    }

    public DarkModeStatus getDarkModeStatus() {
        return getStatus();
    }

    public DarkModeStatusResponse(DarkModeStatus darkModeStatus) {
        super(darkModeStatus);
    }
}
