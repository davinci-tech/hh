package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class WalletUiIntentReq implements IMessageEntity {

    @Packed
    private int type;

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
