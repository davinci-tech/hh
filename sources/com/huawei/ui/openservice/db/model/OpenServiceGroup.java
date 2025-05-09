package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class OpenServiceGroup {
    private int position;
    private String serviceTypeDescription;
    private String serviceTypeID;
    private String serviceTypeName;

    public String getServiceTypeID() {
        return this.serviceTypeID;
    }

    public void setServiceTypeID(String str) {
        this.serviceTypeID = str;
    }

    public String getServiceTypeName() {
        return this.serviceTypeName;
    }

    public void setServiceTypeName(String str) {
        this.serviceTypeName = str;
    }

    public String getServiceTypeDescription() {
        return this.serviceTypeDescription;
    }

    public void setServiceTypeDescription(String str) {
        this.serviceTypeDescription = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }
}
