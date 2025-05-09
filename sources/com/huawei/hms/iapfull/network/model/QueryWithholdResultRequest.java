package com.huawei.hms.iapfull.network.model;

/* loaded from: classes4.dex */
public class QueryWithholdResultRequest {
    private String accountId;
    private String applicationID;
    private String at;
    private String deviceId;
    private String deviceuuID;
    private String noiseTamp;
    private String pactNo;
    private String paySiteUrl = null;
    private String requestId;
    private String sourceID;
    private String srvCountry;
    private String withholdDeviceID;
    private String withholdUserId;

    public void setWithholdUserId(String str) {
        this.withholdUserId = str;
    }

    public void setWithholdDeviceID(String str) {
        this.withholdDeviceID = str;
    }

    public void setSrvCountry(String str) {
        this.srvCountry = str;
    }

    public void setSourceID(String str) {
        this.sourceID = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setPaySiteUrl(String str) {
        this.paySiteUrl = str;
    }

    public void setPactNo(String str) {
        this.pactNo = str;
    }

    public void setNoiseTamp(String str) {
        this.noiseTamp = str;
    }

    public void setDeviceuuID(String str) {
        this.deviceuuID = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setAt(String str) {
        this.at = str;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public void setAccountId(String str) {
        this.accountId = str;
    }

    public String getWithholdUserId() {
        return this.withholdUserId;
    }

    public String getWithholdDeviceID() {
        return this.withholdDeviceID;
    }

    public String getSrvCountry() {
        return this.srvCountry;
    }

    public String getSourceID() {
        return this.sourceID;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getPaySiteUrl() {
        return this.paySiteUrl;
    }

    public String getPactNo() {
        return this.pactNo;
    }

    public String getNoiseTamp() {
        return this.noiseTamp;
    }

    public String getDeviceuuID() {
        return this.deviceuuID;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getAt() {
        return this.at;
    }

    public String getApplicationID() {
        return this.applicationID;
    }

    public String getAccountId() {
        return this.accountId;
    }
}
