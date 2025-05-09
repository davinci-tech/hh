package com.huawei.multisimsdk.multidevicemanager.common;

/* loaded from: classes5.dex */
public abstract class AuthParam {
    private int slotId = -1;
    private String imsi = "";

    public abstract int getAuthType();

    public String getIdentifyCode() {
        return null;
    }

    public String getPhoneNumber() {
        return null;
    }

    public int getSlotId() {
        return this.slotId;
    }

    public void setSlotId(int i) {
        this.slotId = i;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }
}
