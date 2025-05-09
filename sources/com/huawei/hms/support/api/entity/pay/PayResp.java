package com.huawei.hms.support.api.entity.pay;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class PayResp extends AbstractMessageEntity {

    @Packed
    public Intent intent;

    @Packed
    public PendingIntent pendingIntent;

    @Packed
    public int retCode;

    public void setPendingIntent(Context context, Intent intent) {
        this.pendingIntent = PendingIntent.getActivity(context, new SecureRandom().nextInt(), intent, AMapEngineUtils.HALF_MAX_P20_WIDTH);
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }
}
