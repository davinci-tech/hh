package com.huawei.hms.iap.entity;

import android.app.PendingIntent;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class g extends AbstractMessageEntity {

    @Packed
    private String errMsg;

    @Packed
    private String paymentData;

    @Packed
    private String paymentSignature;

    @Packed
    private PendingIntent pendingIntent;

    @Packed
    private int retCode;

    @Packed
    private String signatureAlgorithm;

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getReturnCode() {
        return this.retCode;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public String getPaymentSignature() {
        return this.paymentSignature;
    }

    public String getPaymentData() {
        return this.paymentData;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
