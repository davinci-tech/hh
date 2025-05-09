package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class EventRecord extends mcz {
    private String endTime;
    private String eventStatus;
    private int eventType;
    private String key;
    private String keyType;
    private String medalID;
    private String medalName;
    private String startTime;
    private long timestamp;
    private String timezone;
    private String value;

    public EventRecord() {
        super(10);
    }

    public String acquireMedalID() {
        return this.medalID;
    }

    public void saveMedalID(String str) {
        this.medalID = str;
    }

    public int acquireEventType() {
        return this.eventType;
    }

    public void saveEventType(int i) {
        this.eventType = i;
    }

    public long acquireTimestamp() {
        return this.timestamp;
    }

    public void saveTimestamp(long j) {
        this.timestamp = j;
    }

    public String acquireTimezone() {
        return this.timezone;
    }

    public void saveTimezone(String str) {
        this.timezone = str;
    }

    public String acquireKey() {
        return this.key;
    }

    public void saveKey(String str) {
        this.key = str;
    }

    public String acquireKeyType() {
        return this.keyType;
    }

    public void saveKeyType(String str) {
        this.keyType = str;
    }

    public String acquireValue() {
        return this.value;
    }

    public void saveValue(String str) {
        this.value = str;
    }

    public String acquireMedalName() {
        return this.medalName;
    }

    public void saveMedalName(String str) {
        this.medalName = str;
    }

    public String acquireEndTime() {
        return this.endTime;
    }

    public void saveEndTime(String str) {
        this.endTime = str;
    }

    public String acquireStartTime() {
        return this.startTime;
    }

    public void saveStartTime(String str) {
        this.startTime = str;
    }

    public String acquireEventStatus() {
        return this.eventStatus;
    }

    public void saveEventStatus(String str) {
        this.eventStatus = str;
    }

    public String toString() {
        return "EventRecord{medalID='" + this.medalID + "', medalName='" + this.medalName + "', eventType=" + this.eventType + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', timestamp=" + this.timestamp + ", timezone='" + this.timezone + "', eventStatus='" + this.eventStatus + "', key='" + this.key + "', keyType='" + this.keyType + "', value='" + this.value + "'}";
    }
}
