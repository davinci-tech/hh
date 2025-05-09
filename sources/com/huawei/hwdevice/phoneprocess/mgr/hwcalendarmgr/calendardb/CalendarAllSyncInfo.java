package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

import java.util.List;

/* loaded from: classes5.dex */
public class CalendarAllSyncInfo {
    private String major;
    private long minor;
    private List<SimpleCalendarBean> scheduleList;

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String str) {
        this.major = str;
    }

    public long getMinor() {
        return this.minor;
    }

    public void setMinor(long j) {
        this.minor = j;
    }

    public List<SimpleCalendarBean> getCalendarBeanList() {
        return this.scheduleList;
    }

    public void setCalendarBeanList(List<SimpleCalendarBean> list) {
        this.scheduleList = list;
    }

    public String toString() {
        return "CalendarAllSyncInfo{minor=" + this.minor + ", scheduleList=" + this.scheduleList + '}';
    }
}
