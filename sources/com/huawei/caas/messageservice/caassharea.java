package com.huawei.caas.messageservice;

/* loaded from: classes8.dex */
public final class caassharea {
    private static HwCaasShareManager caassharea;

    public static void caassharea(HwCaasShareManager hwCaasShareManager) {
        caassharea = hwCaasShareManager;
    }

    public static float caassharea() {
        HwCaasShareManager hwCaasShareManager = caassharea;
        if (hwCaasShareManager == null) {
            return 0.0f;
        }
        return hwCaasShareManager.caassharea.getResources().getDisplayMetrics().density;
    }
}
