package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class a extends AbstractMessageEntity {

    @Packed
    private String consumedPurchaseData;

    @Packed
    private String dataSignature;

    @Packed
    private String errMsg;

    @Packed
    private int returnCode;

    @Packed
    private String signatureAlgorithm;

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getDataSignature() {
        return this.dataSignature;
    }

    public String getConsumePurchaseData() {
        return this.consumedPurchaseData;
    }
}
