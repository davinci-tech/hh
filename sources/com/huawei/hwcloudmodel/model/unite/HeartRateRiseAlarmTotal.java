package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class HeartRateRiseAlarmTotal {
    private Integer dataSource;
    private Long deviceCode;
    private Long generateTime;
    private HeartRateRiseAlarmBasic heartRateRiseAlarmBasic;
    private Integer recordDay;
    private String timeZone;

    public Integer getRecordDay() {
        return this.recordDay;
    }

    public void setRecordDay(Integer num) {
        this.recordDay = num;
    }

    public Long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(Long l) {
        this.generateTime = l;
    }

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

    public HeartRateRiseAlarmBasic getHeartRateRiseAlarmBasic() {
        return this.heartRateRiseAlarmBasic;
    }

    public void setHeartRateRiseAlarmBasic(HeartRateRiseAlarmBasic heartRateRiseAlarmBasic) {
        this.heartRateRiseAlarmBasic = heartRateRiseAlarmBasic;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }
}
