package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class PromotionInfoReq implements IRequest {

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("clientVersion")
    private String clientVersion;

    @SerializedName("country")
    private String country;

    @SerializedName("productId")
    private String productId;

    @SerializedName("promotionId")
    private String promotionId;

    @SerializedName("promotionPolicyId")
    private String promotionPolicyId;

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public void setPromotionPolicyId(String str) {
        this.promotionPolicyId = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/getPromotionalOffer";
    }
}
