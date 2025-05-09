package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

/* loaded from: classes5.dex */
public class CalendarSyncStateBean {
    private String deviceIdentify;
    private String deviceTableName;
    private long minor;
    private int syncAllFlag;

    public CalendarSyncStateBean(String str, String str2, int i, long j) {
        this.deviceIdentify = str;
        this.deviceTableName = str2;
        this.syncAllFlag = i;
        this.minor = j;
    }

    public String getDeviceIdentify() {
        return this.deviceIdentify;
    }

    public void setDeviceIdentify(String str) {
        this.deviceIdentify = str;
    }

    public String getDeviceTableName() {
        return this.deviceTableName;
    }

    public void setDeviceTableName(String str) {
        this.deviceTableName = str;
    }

    public int getSyncAllFlag() {
        return this.syncAllFlag;
    }

    public void setSyncAllFlag(int i) {
        this.syncAllFlag = i;
    }

    public String toString() {
        return "CalendarSyncStateBean{deviceIdentify='" + this.deviceIdentify + "', deviceTableName='" + this.deviceTableName + "', syncAllFlag=" + this.syncAllFlag + ", minor=" + this.minor + '}';
    }

    public long getMinor() {
        return this.minor;
    }

    public void setMinor(long j) {
        this.minor = j;
    }
}
