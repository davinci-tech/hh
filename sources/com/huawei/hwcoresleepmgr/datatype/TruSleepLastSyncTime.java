package com.huawei.hwcoresleepmgr.datatype;

import defpackage.iyl;

/* loaded from: classes5.dex */
public class TruSleepLastSyncTime {
    private String deviceMac;
    private String lastSyncTime;

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public String getLastSyncTime() {
        return this.lastSyncTime;
    }

    public void setLastSyncTime(String str) {
        this.lastSyncTime = str;
    }

    public String toString() {
        return "TruSleepLastSynctime:deviceMac = " + iyl.d().e(this.deviceMac) + ",lastSyncTime = " + this.lastSyncTime;
    }
}
