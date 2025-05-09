package com.huawei.hms.iapfull.network.model;

/* loaded from: classes9.dex */
public class WeChatPayInfo {
    private String appID;
    private String nonceStr;
    private String paySign;
    private String preEntrustWebID;
    private String prepayid;
    private String strPackage;
    private String timeStamp;

    public void setWechatPaySignAppid(String str) {
        this.appID = str;
    }

    public void setWeChatSign(String str) {
        this.paySign = str;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public void setPrepayId(String str) {
        this.prepayid = str;
    }

    public void setPreEntrustWebID(String str) {
        this.preEntrustWebID = str;
    }

    public void setPackageValue(String str) {
        this.strPackage = str;
    }

    public void setNonceStr(String str) {
        this.nonceStr = str;
    }

    public String getWechatPaySignAppid() {
        return this.appID;
    }

    public String getWeChatSign() {
        return this.paySign;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public String getPrepayId() {
        return this.prepayid;
    }

    public String getPreEntrustWebID() {
        return this.preEntrustWebID;
    }

    public String getPackageValue() {
        return this.strPackage;
    }

    public String getNonceStr() {
        return this.nonceStr;
    }
}
