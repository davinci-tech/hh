package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

/* loaded from: classes5.dex */
public class SmartSimpleCalendarBean extends SimpleCalendarBean {
    private int availability;
    private String duration;
    private int event_calendar_type;
    private String event_endTimezone;
    private int event_status;
    private String event_timeZone;
    private String exdate;
    private int hasExtendedProperties;
    private int method;
    private String organizer;
    private int selfAttendeeStatus;
    private String syncId;
    private int visible;

    public SmartSimpleCalendarBean() {
        this.syncId = "";
        this.event_timeZone = "";
        this.event_endTimezone = "";
        this.duration = "";
        this.organizer = "";
        this.exdate = "";
    }

    public SmartSimpleCalendarBean(String str, String str2, String str3, int i, String str4, int i2, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i3, String str12, String str13, int i4, int i5, int i6, String str14, long j, long j2, int i7, int i8, String str15, int i9, int i10, String str16, String str17, int i11, int i12) {
        super(str2, str3, i, str4, i2, str5, str6, str7, str8, str9, str14, j, j2, i7, i8, str15, i10, str16);
        this.syncId = str;
        this.event_timeZone = str10;
        this.event_endTimezone = str11;
        this.event_status = i3;
        this.duration = str12;
        this.organizer = str13;
        this.selfAttendeeStatus = i4;
        this.availability = i5;
        this.hasExtendedProperties = i6;
        this.method = i9;
        this.exdate = str17;
        this.visible = i11;
        this.event_calendar_type = i12;
    }

    public String getSyncId() {
        return this.syncId;
    }

    public void setSyncId(String str) {
        this.syncId = str;
    }

    public String getEventTimezone() {
        return this.event_timeZone;
    }

    public void setEventTimezone(String str) {
        this.event_timeZone = str;
    }

    public String getEventEndTimezone() {
        return this.event_endTimezone;
    }

    public void setEventEndTimezone(String str) {
        this.event_endTimezone = str;
    }

    public int getEventStatus() {
        return this.event_status;
    }

    public void setEventStatus(int i) {
        this.event_status = i;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(String str) {
        this.organizer = str;
    }

    public int getSelfAttendeeStatus() {
        return this.selfAttendeeStatus;
    }

    public void setSelfAttendeeStatus(int i) {
        this.selfAttendeeStatus = i;
    }

    public int getAvailability() {
        return this.availability;
    }

    public void setAvailability(int i) {
        this.availability = i;
    }

    public int getHasExtendedProperties() {
        return this.hasExtendedProperties;
    }

    public void setHasExtendedProperties(int i) {
        this.hasExtendedProperties = i;
    }

    public int getMethod() {
        return this.method;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    public String getExdate() {
        return this.exdate;
    }

    public void setExdate(String str) {
        this.exdate = str;
    }

    public int getVisible() {
        return this.visible;
    }

    public void setVisible(int i) {
        this.visible = i;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean
    public String toString() {
        return super.toString() + "SmartSimpleCalendarBean{syncId='" + this.syncId + "', event_timeZone='" + this.event_timeZone + "', event_endTimezone='" + this.event_endTimezone + "', event_status=" + this.event_status + ", duration='" + this.duration + "', organizer='" + this.organizer + "', selfAttendeeStatus=" + this.selfAttendeeStatus + ", availability=" + this.availability + ", hasExtendedProperties=" + this.hasExtendedProperties + ", method=" + this.method + ", exdate='" + this.exdate + "', visible='" + this.visible + "', calendarType='" + this.event_calendar_type + "'}";
    }
}
