package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class SleepBasic {
    private Integer awakeDuration;
    private Integer awakeTimes;
    private Integer deepDuration;
    private String detail;
    private Long endTime;
    private Long fallAsleepTime;
    private Integer lightDuration;
    private Long startTime;
    private Integer totalDuration;
    private Long wakeupTime;

    public Integer getAwakeDuration() {
        return this.awakeDuration;
    }

    public void setAwakeDuration(Integer num) {
        this.awakeDuration = num;
    }

    public Integer getAwakeTimes() {
        return this.awakeTimes;
    }

    public void setAwakeTimes(Integer num) {
        this.awakeTimes = num;
    }

    public Integer getDeepDuration() {
        return this.deepDuration;
    }

    public void setDeepDuration(Integer num) {
        this.deepDuration = num;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Integer getLightDuration() {
        return this.lightDuration;
    }

    public void setLightDuration(Integer num) {
        this.lightDuration = num;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Integer getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(Integer num) {
        this.totalDuration = num;
    }

    public void saveFallAsleepTime(Long l) {
        this.fallAsleepTime = l;
    }

    public Long loadFallAsleepTime() {
        return this.fallAsleepTime;
    }

    public void saveWakeupTime(Long l) {
        this.wakeupTime = l;
    }

    public Long loadWakeupTime() {
        return this.wakeupTime;
    }

    public String toString() {
        return "SleepBasic{awakeDuration=" + this.awakeDuration + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", deepDuration=" + this.deepDuration + ", lightDuration=" + this.lightDuration + ", awakeTimes=" + this.awakeTimes + ", totalDuration=" + this.totalDuration + ", detail='" + this.detail + "', fallAsleepTime=" + this.fallAsleepTime + ", wakeupTime=" + this.wakeupTime + '}';
    }
}
