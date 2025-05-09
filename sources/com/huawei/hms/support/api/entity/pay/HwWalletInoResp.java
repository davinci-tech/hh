package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class HwWalletInoResp extends AbstractMessageEntity {

    @Packed
    public String result;

    private static <T> T get(T t) {
        return t;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getResult() {
        return (String) get(this.result);
    }
}
