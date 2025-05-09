package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class HeartRateTotal {
    private int dataSource;
    private long deviceCode;
    private long generateTime;
    private HeartRateBasic heartRateBasic;
    private int recordDay;
    private String timeZone;

    public int getRecordDay() {
        return this.recordDay;
    }

    public void setRecordDay(int i) {
        this.recordDay = i;
    }

    public long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(long j) {
        this.generateTime = j;
    }

    public int getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(int i) {
        this.dataSource = i;
    }

    public long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(long j) {
        this.deviceCode = j;
    }

    public HeartRateBasic getHeartRateBasic() {
        return this.heartRateBasic;
    }

    public void setHeartRateBasic(HeartRateBasic heartRateBasic) {
        this.heartRateBasic = heartRateBasic;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HeartRateTotal{recordDay=");
        stringBuffer.append(this.recordDay);
        stringBuffer.append(", generateTime=").append(this.generateTime);
        stringBuffer.append(", dataSource=").append(this.dataSource);
        stringBuffer.append(", deviceCode=").append(this.deviceCode);
        stringBuffer.append(", heartRateState=").append(this.heartRateBasic);
        stringBuffer.append(", timeZone='").append(this.timeZone).append("'}");
        return stringBuffer.toString();
    }
}
