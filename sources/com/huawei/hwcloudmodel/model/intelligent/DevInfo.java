package com.huawei.hwcloudmodel.model.intelligent;

/* loaded from: classes5.dex */
public class DevInfo {
    private String devType;
    private String fwv;
    private String hiv;
    private String hwv;
    private String mac;
    private String manu;
    private String model;
    private String prodId;
    private String protType;
    private String sn;
    private String swv;

    public String getSn() {
        return this.sn;
    }

    public void setSn(String str) {
        this.sn = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getDevType() {
        return this.devType;
    }

    public void setDevType(String str) {
        this.devType = str;
    }

    public String getManu() {
        return this.manu;
    }

    public void setManu(String str) {
        this.manu = str;
    }

    public String getProdId() {
        return this.prodId;
    }

    public void setProdId(String str) {
        this.prodId = str;
    }

    public String getHiv() {
        return this.hiv;
    }

    public void setHiv(String str) {
        this.hiv = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getFwv() {
        return this.fwv;
    }

    public void setFwv(String str) {
        this.fwv = str;
    }

    public String getHwv() {
        return this.hwv;
    }

    public void setHwv(String str) {
        this.hwv = str;
    }

    public String getSwv() {
        return this.swv;
    }

    public void setSwv(String str) {
        this.swv = str;
    }

    public String getProtType() {
        return this.protType;
    }

    public void setProtType(String str) {
        this.protType = str;
    }

    public String toString() {
        return "DevInfo{model=" + this.model + ", devType=" + this.devType + ", manu=" + this.manu + ", prodId=" + this.prodId + ", hiv=" + this.hiv + ", mac=" + this.mac + ", fwv=" + this.fwv + ", swv=" + this.swv + ", protType=" + this.protType + "}";
    }
}
