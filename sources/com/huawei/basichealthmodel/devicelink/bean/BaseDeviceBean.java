package com.huawei.basichealthmodel.devicelink.bean;

/* loaded from: classes3.dex */
public class BaseDeviceBean {
    private int msgType;
    private double msgVer = 3.0d;

    public double getMsgVer() {
        return this.msgVer;
    }

    public void setMsgVer(double d) {
        this.msgVer = d;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public String toString() {
        return "BaseDeviceBean{msgVer=" + this.msgVer + ", msgType=" + this.msgType + "'}";
    }
}
