package com.huawei.hms.hihealth;

import com.huawei.hms.health.aabz;
import java.util.TimerTask;

/* loaded from: classes4.dex */
class aaba extends TimerTask {
    final /* synthetic */ HiHealthKitClient aab;

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        aabz.aabb("HiHealthKitClient", "disconnect delay timer task run");
        HiHealthKitClient.aabj(this.aab);
        this.aab.aabh = null;
    }

    aaba(HiHealthKitClient hiHealthKitClient) {
        this.aab = hiHealthKitClient;
    }
}
