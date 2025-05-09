package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class GamePayReq extends PayReq {

    @Packed
    public String channelId;

    @Packed
    public String cpId;

    @Packed
    public String sdkVersionName;

    public String getSdkVersionName() {
        return this.sdkVersionName;
    }

    public String getCpID() {
        return this.cpId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public GamePayReq(PayReq payReq, String str) {
        this.sdkVersionName = "6.13.0.300";
        this.cpId = str;
        this.amount = payReq.amount;
        this.country = payReq.country;
        this.currency = payReq.currency;
        this.expireTime = payReq.expireTime;
        this.partnerIDs = payReq.partnerIDs;
        this.productDesc = payReq.productDesc;
        this.productName = payReq.productName;
        this.validTime = payReq.validTime;
        this.applicationID = payReq.applicationID;
        this.merchantName = payReq.merchantName;
        this.requestId = payReq.requestId;
        this.extReserved = payReq.extReserved;
        this.merchantId = payReq.merchantId;
        this.sign = payReq.sign;
        this.signatureAlgorithm = payReq.signatureAlgorithm;
        this.url = payReq.url;
        this.sdkChannel = payReq.sdkChannel;
        this.serviceCatalog = payReq.serviceCatalog;
        this.urlVer = payReq.urlVer;
        this.reservedInfor = payReq.reservedInfor;
    }

    public GamePayReq() {
    }
}
