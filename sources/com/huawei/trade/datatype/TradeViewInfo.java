package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class TradeViewInfo extends TradeBaseInfo {

    @SerializedName("buttonName")
    private String buttonName;

    @SerializedName("fromPageTitle")
    private String fromPageTitle;

    @SerializedName("positionId")
    private String positionId;

    @SerializedName("resId")
    private String resId;

    @SerializedName("resType")
    private int resType;

    @SerializedName("trigResName")
    private String trigResName;

    public TradeViewInfo(String str) {
        this("", 0, str);
    }

    public TradeViewInfo(String str, int i) {
        this(str, i, "");
    }

    public TradeViewInfo(String str, int i, String str2) {
        this.resId = str;
        this.resType = i;
        this.productId = str2;
    }

    public String getResId() {
        return this.resId;
    }

    public int getResType() {
        return this.resType;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    public void setButtonName(String str) {
        this.buttonName = str;
    }

    public String getPositionId() {
        return this.positionId;
    }

    public String getFromPageTitle() {
        return this.fromPageTitle;
    }

    public void setPositionId(String str) {
        this.positionId = str;
    }

    public void setFromPageTitle(String str) {
        this.fromPageTitle = str;
    }

    public String getTrigResName() {
        return this.trigResName;
    }

    public void setTrigResName(String str) {
        this.trigResName = str;
    }
}
