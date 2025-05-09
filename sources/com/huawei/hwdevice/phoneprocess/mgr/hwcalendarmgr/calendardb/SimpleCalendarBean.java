package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

/* loaded from: classes5.dex */
public class SimpleCalendarBean {
    private String account_name;
    private String account_type;
    private int all_day;
    private int calendar_color;
    private String calendar_displayName;
    private String calendar_id;
    private String description;
    private long dtend;
    private long dtstart;
    private String event_id;
    private String event_location;
    private String event_uuid;
    private int has_alarm;
    private String minutes;
    private int operation;
    private String rrule;
    private int state;
    private String title;

    public SimpleCalendarBean() {
        this.event_uuid = "";
        this.event_id = "";
        this.calendar_id = "";
        this.account_name = "";
        this.account_type = "";
        this.calendar_displayName = "";
        this.title = "";
        this.event_location = "";
        this.description = "";
        this.minutes = "";
        this.rrule = "";
    }

    public SimpleCalendarBean(String str, String str2, int i, String str3, int i2, String str4, String str5, String str6, String str7, String str8, String str9, long j, long j2, int i3, int i4, String str10, int i5, String str11) {
        this.event_uuid = str;
        this.event_id = str2;
        this.operation = i;
        this.calendar_id = str3;
        this.calendar_color = i2;
        this.account_name = str4;
        this.account_type = str5;
        this.calendar_displayName = str6;
        this.title = str7;
        this.event_location = str8;
        this.description = str9;
        this.dtstart = j;
        this.dtend = j2;
        this.all_day = i3;
        this.has_alarm = i4;
        this.minutes = str10;
        this.state = i5;
        this.rrule = str11;
    }

    public String getEventUuid() {
        return this.event_uuid;
    }

    public void setEventUuid(String str) {
        this.event_uuid = str;
    }

    public int getOperation() {
        return this.operation;
    }

    public void setOperation(int i) {
        this.operation = i;
    }

    public String getEventId() {
        return this.event_id;
    }

    public void setEventId(String str) {
        this.event_id = str;
    }

    public String getCalendarId() {
        return this.calendar_id;
    }

    public void setCalendar_id(String str) {
        this.calendar_id = str;
    }

    public int getCalendarColor() {
        return this.calendar_color;
    }

    public void setCalendarColor(int i) {
        this.calendar_color = i;
    }

    public String getAccountName() {
        return this.account_name;
    }

    public void setAccountName(String str) {
        this.account_name = str;
    }

    public String getAccountType() {
        return this.account_type;
    }

    public void setAccountType(String str) {
        this.account_type = str;
    }

    public String getCalendarDisplayName() {
        return this.calendar_displayName;
    }

    public void setCalendarDisplayName(String str) {
        this.calendar_displayName = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getEventLocation() {
        return this.event_location;
    }

    public void setEventLocation(String str) {
        this.event_location = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
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
        return this.all_day;
    }

    public void setAllDay(int i) {
        this.all_day = i;
    }

    public int getHasAlarm() {
        return this.has_alarm;
    }

    public void setHas_alarm(int i) {
        this.has_alarm = i;
    }

    public String getMinutes() {
        return this.minutes;
    }

    public void setMinutes(String str) {
        this.minutes = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getRule() {
        return this.rrule;
    }

    public void setRule(String str) {
        this.rrule = str;
    }

    public String toString() {
        return "SimpleCalendarBean{event_uuid=" + this.event_uuid + ", event_id=" + this.event_id + ", operation=" + this.operation + ", calendar_id=" + this.calendar_id + ", calendar_color=" + this.calendar_color + ", description='" + this.description + "', dtstart=" + this.dtstart + ", dtend=" + this.dtend + ", all_day=" + this.all_day + ", has_alarm=" + this.has_alarm + ", minutes=" + this.minutes + ", state=" + this.state + ", rule='" + this.rrule + "'}";
    }
}
