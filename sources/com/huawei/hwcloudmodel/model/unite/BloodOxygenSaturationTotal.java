package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class BloodOxygenSaturationTotal {
    private BloodOxygenSaturationBasic bloodOxygenSaturationBasic;
    private Integer dataSource;
    private Long deviceCode;
    private Long generateTime;
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

    public void setDataSource(Integer num) {
        this.dataSource = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public BloodOxygenSaturationBasic getBloodOxygenSaturationBasic() {
        return this.bloodOxygenSaturationBasic;
    }

    public void setBloodOxygenSaturationBasic(BloodOxygenSaturationBasic bloodOxygenSaturationBasic) {
        this.bloodOxygenSaturationBasic = bloodOxygenSaturationBasic;
    }
}
