package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

import java.util.Objects;

/* loaded from: classes5.dex */
public class CalendarDbBean {
    private int allDay;
    private String calendarFeature;
    private long dtend;
    private long dtstart;
    private String eventId;
    private String eventUuid;
    private int operation;

    public void setEventUuid(String str) {
    }

    public CalendarDbBean(String str, int i, String str2, long j, long j2, int i2, String str3) {
        this.eventUuid = str;
        this.operation = i;
        this.eventId = str2;
        this.dtstart = j;
        this.dtend = j2;
        this.allDay = i2;
        this.calendarFeature = str3;
    }

    public String getEventUuid() {
        return this.eventUuid;
    }

    public int getOperation() {
        return this.operation;
    }

    public void setOperation(int i) {
        this.operation = i;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    public String getCalendarFeature() {
        return this.calendarFeature;
    }

    public void setCalendarFeature(String str) {
        this.calendarFeature = str;
    }

    public long getDtstart() {
        return this.dtstart;
    }

    public void setDtstart(long j) {
        this.dtstart = j;
    }

    public long getDtend() {
        return this.dtend;
    }

    public void setDtend(long j) {
        this.dtend = j;
    }

    public int getAllDay() {
        return this.allDay;
    }

    public void setAllDay(int i) {
        this.allDay = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CalendarDbBean calendarDbBean = (CalendarDbBean) obj;
        return this.operation == calendarDbBean.operation && this.dtstart == calendarDbBean.dtstart && this.dtend == calendarDbBean.dtend && this.allDay == calendarDbBean.allDay && this.eventUuid.equals(calendarDbBean.eventUuid) && this.eventId.equals(calendarDbBean.eventId) && this.calendarFeature.equals(calendarDbBean.calendarFeature);
    }

    public int hashCode() {
        return Objects.hash(this.eventUuid, Integer.valueOf(this.operation), this.eventId, Long.valueOf(this.dtstart), Long.valueOf(this.dtend), Integer.valueOf(this.allDay), this.calendarFeature);
    }
}
