package com.huawei.hms.support.api.entity.hwid;

import android.content.Intent;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public abstract class PickerIHwIDRespEntity extends AbstractMessageEntity {

    @Packed
    private Intent mData;

    @Packed
    private int mRetCode;

    public int getRetCode() {
        return this.mRetCode;
    }

    public void setRetCode(int i) {
        this.mRetCode = i;
    }

    public Intent getData() {
        return this.mData;
    }

    public void setData(Intent intent) {
        this.mData = intent;
    }
}
