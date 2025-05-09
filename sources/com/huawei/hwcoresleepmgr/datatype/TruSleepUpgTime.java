package com.huawei.hwcoresleepmgr.datatype;

import defpackage.iyl;

/* loaded from: classes5.dex */
public class TruSleepUpgTime {
    private String deviceMac;
    private String lastUpgEndTime;

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public String getLastUpgEndTime() {
        return this.lastUpgEndTime;
    }

    public void setLastUpgEndTime(String str) {
        this.lastUpgEndTime = str;
    }

    public String toString() {
        return "TruSleepUpgTime:deviceMac =" + iyl.d().e(this.deviceMac) + ",lastUpgEndTime = " + this.lastUpgEndTime;
    }
}
