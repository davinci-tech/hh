package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

import java.util.List;

/* loaded from: classes5.dex */
public class DeviceCalendarSyncInfo {
    private int count;
    private List<SmartSimpleCalendarBean> scheduleList;

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public List<SmartSimpleCalendarBean> getScheduleList() {
        return this.scheduleList;
    }

    public void setScheduleList(List<SmartSimpleCalendarBean> list) {
        this.scheduleList = list;
    }
}
