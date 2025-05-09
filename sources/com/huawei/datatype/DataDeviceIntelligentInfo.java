package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class DataDeviceIntelligentInfo {
    private String deviceHiv;
    private String deviceaManu;
    private String productId;

    public String getDeviceManu() {
        return (String) jdy.d(this.deviceaManu);
    }

    public void setDeviceManu(String str) {
        this.deviceaManu = (String) jdy.d(str);
    }

    public String getDeviceProductId() {
        return (String) jdy.d(this.productId);
    }

    public void setDeviceProductId(String str) {
        this.productId = (String) jdy.d(str);
    }

    public String getDeviceHiv() {
        return (String) jdy.d(this.deviceHiv);
    }

    public void setDeviceHiv(String str) {
        this.deviceHiv = (String) jdy.d(str);
    }

    public String toString() {
        return "DataDeviceIntelligentInfo{deviceaManu=" + this.deviceaManu + ", productId=" + this.productId + ", deviceHiv=" + this.deviceHiv + '}';
    }
}
