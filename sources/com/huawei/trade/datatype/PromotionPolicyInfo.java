package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class PromotionPolicyInfo {

    @SerializedName("dialogButtonName")
    private String dialogButtonName;

    @SerializedName("dialogFlag")
    private boolean dialogFlag;

    @SerializedName("dialogPicUrl")
    private String dialogPicUrl;

    @SerializedName("dialogSubTitle")
    private String dialogSubTitle;

    @SerializedName("dialogTitle")
    private String dialogTitle;

    @SerializedName("productId")
    private String productId;

    @SerializedName("promotionId")
    private String promotionId;

    @SerializedName("promotionPolicyId")
    private String promotionPolicyId;

    @SerializedName("tahitiDialogPicUrl")
    private String tahitiDialogPicUrl;

    public String getPromotionPolicyId() {
        return this.promotionPolicyId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public boolean isDialogFlag() {
        return this.dialogFlag;
    }

    public String getDialogPicUrl() {
        return this.dialogPicUrl;
    }

    public String getTahitiDialogPicUrl() {
        return this.tahitiDialogPicUrl;
    }

    public String getDialogTitle() {
        return this.dialogTitle;
    }

    public String getDialogSubTitle() {
        return this.dialogSubTitle;
    }

    public String getDialogButtonName() {
        return this.dialogButtonName;
    }

    public String toString() {
        return "PromotionPolicyInfo{promotionPolicyId='" + this.promotionPolicyId + "', productId='" + this.productId + "', promotionId='" + this.promotionId + "', dialogFlag='" + this.dialogFlag + "', dialogPicUrl='" + this.dialogPicUrl + "', tahitiDialogPicUrl='" + this.tahitiDialogPicUrl + "', dialogTitle='" + this.dialogTitle + "', dialogSubTitle='" + this.dialogSubTitle + "', dialogButtonName='" + this.dialogButtonName + "'}";
    }
}
