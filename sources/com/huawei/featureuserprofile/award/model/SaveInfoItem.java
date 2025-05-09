package com.huawei.featureuserprofile.award.model;

import java.io.Serializable;

/* loaded from: classes7.dex */
public class SaveInfoItem implements Serializable {
    private static final long serialVersionUID = -5041790259753424538L;
    private String activityId;
    private String address;
    private String awardRecordId;
    private String name;
    private String remark;
    private String telephone;

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public String getAwardRecordId() {
        return this.awardRecordId;
    }

    public void setAwardRecordId(String str) {
        this.awardRecordId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }
}
