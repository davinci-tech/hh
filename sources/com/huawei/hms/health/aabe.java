package com.huawei.hms.health;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes4.dex */
class aabe extends Handler {
    final /* synthetic */ aabf aab;

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            aabz.aabb("HealthKitTransparentFragment", "get finish activity message: " + message.what);
            if (message.what == 2388) {
                this.aab.aab();
            }
        } catch (Throwable unused) {
            aabz.aab("HealthKitTransparentFragment", "TransparentFragment handle msg has exception");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aabe(aabf aabfVar, Looper looper) {
        super(looper);
        this.aab = aabfVar;
    }
}
