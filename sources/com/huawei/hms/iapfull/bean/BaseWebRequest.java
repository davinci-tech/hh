package com.huawei.hms.iapfull.bean;

/* loaded from: classes4.dex */
public class BaseWebRequest {
    protected String applicationID;
    protected String clientID;
    protected String reservedInfor;

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setClientID(String str) {
        this.clientID = str;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getClientID() {
        return this.clientID;
    }

    public String getApplicationID() {
        return this.applicationID;
    }
}
