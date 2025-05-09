package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.trade.datatype.PromotionPolicyInfo;

/* loaded from: classes4.dex */
public class PromotionPolicyRsq extends BaseRsp {

    @SerializedName("promotionPolicyInfo")
    private PromotionPolicyInfo promotionPolicyInfo;

    public PromotionPolicyInfo getPromotionPolicyInfo() {
        return this.promotionPolicyInfo;
    }
}
