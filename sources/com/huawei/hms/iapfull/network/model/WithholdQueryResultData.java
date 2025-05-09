package com.huawei.hms.iapfull.network.model;

import java.io.Serializable;

/* loaded from: classes9.dex */
public class WithholdQueryResultData implements Serializable {
    private static final long serialVersionUID = 1;
    private String appName;
    private String applicationID;
    private String channel;
    private String merchantId;
    private String pactNo;
    private String pactTime;
    private String srvCountry;

    public void setSrvCountry(String str) {
        this.srvCountry = str;
    }

    public void setPactTime(String str) {
        this.pactTime = str;
    }

    public void setPactNo(String str) {
        this.pactNo = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getSrvCountry() {
        return this.srvCountry;
    }

    public String getPactTime() {
        return this.pactTime;
    }

    public String getPactNo() {
        return this.pactNo;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getApplicationID() {
        return this.applicationID;
    }

    public String getAppName() {
        return this.appName;
    }
}
