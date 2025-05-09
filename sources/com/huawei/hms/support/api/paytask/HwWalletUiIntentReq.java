package com.huawei.hms.support.api.paytask;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class HwWalletUiIntentReq implements IMessageEntity {

    @Packed
    public int type = -1;

    public int getType() {
        return this.type;
    }
}
