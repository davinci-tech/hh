package com.huawei.hwcloudmodel.model.ecgservice;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class EcgGiftCardResponse extends CloudCommonReponse {

    @SerializedName("donorId")
    private long mDonorId;

    @SerializedName("expireTime")
    private long mExpireTime;

    @SerializedName("pushInfo")
    private String mPushInfo;

    public String getPushInfo() {
        return this.mPushInfo;
    }

    public long getDonorId() {
        return this.mDonorId;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "EcgGiftCardResponse{pushInfo=" + this.mPushInfo + ", donorId=" + this.mDonorId + ", expireTime=" + this.mExpireTime + "}";
    }
}
