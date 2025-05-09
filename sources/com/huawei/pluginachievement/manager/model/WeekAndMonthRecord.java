package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class WeekAndMonthRecord extends mcz {
    private String britishValue;
    private int dataSource;
    private long endTimestamp;
    private int maxReportNo;
    private int minReportNo;
    private int recentType;
    private int reportNo;
    private long startTimestamp;
    private String value;
    private String valueFromCloud;

    public WeekAndMonthRecord() {
        super(20);
    }

    public long getStartTimestamp() {
        return this.startTimestamp;
    }

    public void setStartTimestamp(Long l) {
        this.startTimestamp = l.longValue();
    }

    public long getEndTimestamp() {
        return this.endTimestamp;
    }

    public void setEndTimestamp(Long l) {
        this.endTimestamp = l.longValue();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getBritishValue() {
        return this.britishValue;
    }

    public void setBritishValue(String str) {
        this.britishValue = str;
    }

    public int getRecentType() {
        return this.recentType;
    }

    public void setRecentType(int i) {
        this.recentType = i;
    }

    public int getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(int i) {
        this.dataSource = i;
    }

    public void setMaxReportNo(int i) {
        this.maxReportNo = i;
    }

    public int getMinReportNo() {
        return this.minReportNo;
    }

    public void setMinReportNo(int i) {
        this.minReportNo = i;
    }

    public int getReportNo() {
        return this.reportNo;
    }

    public void setReportNo(int i) {
        this.reportNo = i;
    }

    public String getValueFromCloud() {
        return this.valueFromCloud;
    }

    public void setValueFromCloud(String str) {
        this.valueFromCloud = str;
    }

    public String toString() {
        return "WeekAndMonthRecord{startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", value='" + this.value + "', britishValue='" + this.britishValue + "', recentType=" + this.recentType + ", dataSource=" + this.dataSource + ", maxReportNo=" + this.maxReportNo + ", minReportNo=" + this.minReportNo + ", reportNo=" + this.reportNo + '}';
    }
}
