package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class SleepTotal {
    private Integer dataSource;
    private Long deviceCode;
    private Long generateTime;
    private Integer recordDay;
    private SleepBasic sleepBasic;
    private String timeZone;

    public Integer getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(Integer num) {
        this.dataSource = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public Integer getRecordDay() {
        return this.recordDay;
    }

    public void setRecordDay(Integer num) {
        this.recordDay = num;
    }

    public SleepBasic getSleepBasic() {
        return this.sleepBasic;
    }

    public void setSleepBasic(SleepBasic sleepBasic) {
        this.sleepBasic = sleepBasic;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public Long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(Long l) {
        this.generateTime = l;
    }

    public String toString() {
        return "SleepTotal{}";
    }
}
