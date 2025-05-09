package com.huawei.hwcloudmodel.model.unite;

import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class DevInfo {
    private String devType;
    private String fwv;
    private String hiv;
    private String hwv;
    private int mProtType;
    private String mac;
    private String manu;
    private String model;
    private String prodId;
    private String sn;
    private String swv;

    public void setSn(String str) {
        this.sn = str;
    }

    public String getSn() {
        return this.sn;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setDevType(String str) {
        this.devType = str;
    }

    public String getDevType() {
        return this.devType;
    }

    public void setManu(String str) {
        this.manu = str;
    }

    public String getManu() {
        return this.manu;
    }

    public void setProdId(String str) {
        this.prodId = str;
    }

    public String getProdId() {
        return this.prodId;
    }

    public void setHiv(String str) {
        this.hiv = str;
    }

    public String getHiv() {
        return this.hiv;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setFwv(String str) {
        this.fwv = str;
    }

    public String getFwv() {
        return this.fwv;
    }

    public void setHwv(String str) {
        this.hwv = str;
    }

    public String getHwv() {
        return this.hwv;
    }

    public void setSwv(String str) {
        this.swv = str;
    }

    public String getSwv() {
        return this.swv;
    }

    public void setProtType(int i) {
        this.mProtType = i;
    }

    public int getProtType() {
        return this.mProtType;
    }

    public String toString() {
        return "DevInfo{sn='" + CommonUtil.l(this.sn) + "', model='" + this.model + "', devType='" + this.devType + "', manu='" + this.manu + "', prodId='" + this.prodId + "', hiv='" + this.hwv + "', mac='" + CommonUtil.l(this.mac) + "', fwv='" + this.fwv + "', hwv='" + this.hwv + "', swv='" + this.swv + "', mProtType='" + this.mProtType + "'}";
    }
}
