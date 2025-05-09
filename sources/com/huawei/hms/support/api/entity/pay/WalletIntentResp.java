package com.huawei.hms.support.api.entity.pay;

import android.app.PendingIntent;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class WalletIntentResp extends AbstractMessageEntity {

    @Packed
    public PendingIntent pendingIntent;

    private static <T> T get(T t) {
        return t;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public PendingIntent getPendingIntent() {
        return (PendingIntent) get(this.pendingIntent);
    }
}
