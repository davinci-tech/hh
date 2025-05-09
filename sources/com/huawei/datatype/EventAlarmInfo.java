package com.huawei.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdy;
import defpackage.jec;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class EventAlarmInfo {
    private static final String TAG = "EventAlarmInfo";
    private int eventAlarmIndex = 1;
    private int eventAlarmEnable = 0;

    @SerializedName("eventAlarmStartTime_hour")
    private int eventAlarmStartTimeHour = 7;

    @SerializedName("eventAlarmStartTime_mins")
    private int eventAlarmStartTimeMins = 0;
    private int eventAlarmRepeat = 0;
    private String eventAlarmName = "";
    private long eventAlarmTime = 0;
    private long createTimestamp = 0;
    private long modifiedTimestamp = 0;
    private int eventAlarmLegalHolidayEnable = 0;

    public long getCreateTimestamp() {
        return ((Long) jdy.d(Long.valueOf(this.createTimestamp))).longValue();
    }

    public void setCreateTimestamp(long j) {
        this.createTimestamp = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long getModifiedTimestamp() {
        return ((Long) jdy.d(Long.valueOf(this.modifiedTimestamp))).longValue();
    }

    public void setModifiedTimestamp(long j) {
        this.modifiedTimestamp = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getEventAlarmIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmIndex))).intValue();
    }

    public void setEventAlarmIndex(int i) {
        this.eventAlarmIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getEventAlarmEnable() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmEnable))).intValue();
    }

    public void setEventAlarmEnable(int i) {
        this.eventAlarmEnable = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getEventAlarmStartTimeHour() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmStartTimeHour))).intValue();
    }

    public void setEventAlarmStartTimeHour(int i) {
        this.eventAlarmStartTimeHour = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getEventAlarmStartTimeMin() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmStartTimeMins))).intValue();
    }

    public void setEventAlarmStartTimeMins(int i) {
        this.eventAlarmStartTimeMins = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getEventAlarmRepeat() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmRepeat))).intValue();
    }

    public void setEventAlarmRepeat(int i) {
        this.eventAlarmRepeat = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getEventAlarmName() {
        return (String) jdy.d(this.eventAlarmName);
    }

    public void setEventAlarmName(String str) {
        this.eventAlarmName = (String) jdy.d(str);
    }

    public long getEventAlarmTime() {
        return ((Long) jdy.d(Long.valueOf(this.eventAlarmTime))).longValue();
    }

    public void setEventAlarmTime(long j) {
        this.eventAlarmTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getEventAlarmLegalHolidayEnable() {
        return ((Integer) jdy.d(Integer.valueOf(this.eventAlarmLegalHolidayEnable))).intValue();
    }

    public void setEventAlarmLegalHolidayEnable(int i) {
        this.eventAlarmLegalHolidayEnable = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public EventAlarmInfo resetAlarm(EventAlarmInfo eventAlarmInfo, int i) {
        Date e = jec.e();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(e);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), eventAlarmInfo.getEventAlarmStartTimeHour(), eventAlarmInfo.getEventAlarmStartTimeMin(), 0);
        Date time = calendar.getTime();
        LogUtil.a(TAG, "once today alarm ", jec.x(calendar.getTime()) + ", today long = " + calendar.getTime().getTime());
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        LogUtil.a(TAG, ",once curTime ", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis > jec.l(calendar.getTime())) {
            time = jec.p(calendar.getTime());
        }
        LogUtil.a(TAG, "once alarmDate ", jec.x(time));
        LogUtil.a(TAG, "once alarmDate long ", Long.valueOf(jec.l(time)));
        eventAlarmInfo.setEventAlarmTime(jec.l(time));
        eventAlarmInfo.setEventAlarmIndex(i + 1);
        return eventAlarmInfo;
    }

    public String toString() {
        return "EventAlarmInfo{eventAlarmIndex=" + this.eventAlarmIndex + ", eventAlarmEnable=" + this.eventAlarmEnable + ", eventAlarmStartTimeHour=" + this.eventAlarmStartTimeHour + ", eventAlarmStartTimeMins=" + this.eventAlarmStartTimeMins + ", eventAlarmRepeat=" + this.eventAlarmRepeat + ", eventAlarmName='" + this.eventAlarmName + "', eventAlarmTime=" + this.eventAlarmTime + "', eventAlarmHolidayEnable=" + this.eventAlarmLegalHolidayEnable + ", createTimestamp=" + this.createTimestamp + ", modifiedTimestamp=" + this.modifiedTimestamp + '}';
    }
}
