package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class HeartRateRiseAlarmBasic {
    private Integer alarmTimes;
    private Integer duration;
    private Integer maxHeartRate;
    private Integer minHeartRate;

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public Integer getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(Integer num) {
        this.maxHeartRate = num;
    }

    public Integer getMinHeartRate() {
        return this.minHeartRate;
    }

    public void setMinHeartRate(Integer num) {
        this.minHeartRate = num;
    }

    public Integer getAlarmTimes() {
        return this.alarmTimes;
    }

    public void setAlarmTimes(Integer num) {
        this.alarmTimes = num;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HeartRateRiseAlarmBasic{duration=");
        stringBuffer.append(this.duration);
        stringBuffer.append(", maxHeartRate=").append(this.maxHeartRate);
        stringBuffer.append(", minHeartRate=").append(this.minHeartRate);
        stringBuffer.append(", alarmTimes=").append(this.alarmTimes);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
