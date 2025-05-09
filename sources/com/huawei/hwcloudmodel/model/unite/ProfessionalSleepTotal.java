package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class ProfessionalSleepTotal {
    private int dataSource;
    private long deviceCode;
    private long generateTime;
    private ProfessionalSleep professionalSleep;
    private int recordDay;
    private String timeZone;

    public int getRecordDay() {
        return this.recordDay;
    }

    public long getGenerateTime() {
        return this.generateTime;
    }

    public int getDataSource() {
        return this.dataSource;
    }

    public long getDeviceCode() {
        return this.deviceCode;
    }

    public ProfessionalSleep getProfessionalSleep() {
        return this.professionalSleep;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setRecordDay(int i) {
        this.recordDay = i;
    }

    public void setGenerateTime(long j) {
        this.generateTime = j;
    }

    public void setDataSource(int i) {
        this.dataSource = i;
    }

    public void setDeviceCode(long j) {
        this.deviceCode = j;
    }

    public void setProfessionalSleep(ProfessionalSleep professionalSleep) {
        this.professionalSleep = professionalSleep;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        return "ProfessionalSleepTotal{recordDay=" + this.recordDay + ", generateTime=" + this.generateTime + ", dataSource=" + this.dataSource + ", deviceCode=" + this.deviceCode + ", professionalSleep=" + this.professionalSleep + ", timeZone='" + this.timeZone + "'}";
    }
}
