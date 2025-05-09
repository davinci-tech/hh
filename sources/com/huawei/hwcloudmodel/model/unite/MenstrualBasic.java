package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class MenstrualBasic {

    @SerializedName("dysmenorrhea")
    private Integer mDysmenorrhea;

    @SerializedName("menstrualQuantity")
    private Integer mMenstrualQuantity;

    @SerializedName("physicalStatus")
    private Integer mPhysicalStatus;

    @SerializedName("status")
    private Integer mStatus;

    @SerializedName("subStatus")
    private Integer mSubStatus;

    public Integer getStatus() {
        return this.mStatus;
    }

    public void setStatus(Integer num) {
        this.mStatus = num;
    }

    public Integer getSubStatus() {
        return this.mSubStatus;
    }

    public void setSubStatus(Integer num) {
        this.mSubStatus = num;
    }

    public Integer getMenstrualQuantity() {
        return this.mMenstrualQuantity;
    }

    public void setMenstrualQuantity(Integer num) {
        this.mMenstrualQuantity = num;
    }

    public Integer getDysmenorrhea() {
        return this.mDysmenorrhea;
    }

    public void setDysmenorrhea(Integer num) {
        this.mDysmenorrhea = num;
    }

    public Integer getPhysicalStatus() {
        return this.mPhysicalStatus;
    }

    public void setPhysicalStatus(Integer num) {
        this.mPhysicalStatus = num;
    }
}
