package com.huawei.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdy;
import defpackage.jec;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class SmartAlarmInfo {
    private static final String TAG = "SmartAlarmInfo";
    private int smartAlarmIndex = 1;
    private int smartAlarmEnable = 0;

    @SerializedName("smartAlarmStartTime_hour")
    private int smartAlarmStartTimeHour = 7;

    @SerializedName("smartAlarmStartTime_mins")
    private int smartAlarmStartTimeMins = 0;
    private long smartAlarmTime = 0;
    private int smartAlarmRepeat = 31;
    private int smartAlarmAheadTime = 5;

    public int getSmartAlarmIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmIndex))).intValue();
    }

    public void setSmartAlarmIndex(int i) {
        this.smartAlarmIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartAlarmEnable() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmEnable))).intValue();
    }

    public void setSmartAlarmEnable(int i) {
        this.smartAlarmEnable = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartAlarmStartTimeHour() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmStartTimeHour))).intValue();
    }

    public void setSmartAlarmStartTimeHour(int i) {
        this.smartAlarmStartTimeHour = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartAlarmStartTimeMins() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmStartTimeMins))).intValue();
    }

    public void setSmartAlarmStartTimeMins(int i) {
        this.smartAlarmStartTimeMins = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartAlarmRepeat() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmRepeat))).intValue();
    }

    public void setSmartAlarmRepeat(int i) {
        this.smartAlarmRepeat = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartAlarmAheadTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.smartAlarmAheadTime))).intValue();
    }

    public void setSmartAlarmAheadTime(int i) {
        this.smartAlarmAheadTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getSmartAlarmTime() {
        return ((Long) jdy.d(Long.valueOf(this.smartAlarmTime))).longValue();
    }

    public void setSmartAlarmTime(long j) {
        this.smartAlarmTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public SmartAlarmInfo resetSmartAlarm(SmartAlarmInfo smartAlarmInfo, int i) {
        Date e = jec.e();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(e);
        LogUtil.a(TAG, "once saveOnceSmartAlarm StartTime_hour ", Integer.valueOf(smartAlarmInfo.getSmartAlarmStartTimeHour()), ", StartTime_mins ", Integer.valueOf(smartAlarmInfo.getSmartAlarmStartTimeMins()));
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), smartAlarmInfo.getSmartAlarmStartTimeHour(), smartAlarmInfo.getSmartAlarmStartTimeMins(), 0);
        Date time = calendar.getTime();
        LogUtil.a(TAG, "once today alarm ", jec.x(calendar.getTime()), ", today long ", Long.valueOf(calendar.getTime().getTime()));
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        LogUtil.a(TAG, "once curTime ", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis > jec.l(calendar.getTime())) {
            time = jec.p(calendar.getTime());
        }
        LogUtil.a(TAG, "once alarmDate ", jec.x(time));
        LogUtil.a(TAG, "once alarmDate long ", Long.valueOf(jec.l(time)));
        smartAlarmInfo.setSmartAlarmTime(jec.l(time));
        smartAlarmInfo.setSmartAlarmIndex(i + 1);
        return smartAlarmInfo;
    }

    public String toString() {
        return "SmartAlarmInfo{smartAlarmIndex=" + this.smartAlarmIndex + ", smartAlarmEnable=" + this.smartAlarmEnable + ", smartAlarmStartTimeHour=" + this.smartAlarmStartTimeHour + ", smartAlarmStartTimeMins=" + this.smartAlarmStartTimeMins + ", smartAlarmTime=" + this.smartAlarmTime + ", smartAlarmRepeat=" + this.smartAlarmRepeat + ", smartAlarmAheadTime=" + this.smartAlarmAheadTime + '}';
    }
}
