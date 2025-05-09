package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class ProfessionalSleep {
    private int allSleepTime;
    private int awakeTime;

    @SerializedName("bedTime")
    private int bedTime;
    private int daySleepTime;
    private int deepSleepPart;
    private int deepSleepTime;

    @SerializedName("deviceBedTime")
    private int deviceBedTime;

    @SerializedName("deviceCategory")
    private int deviceCategory;
    private int dreamTime;
    private long fallAsleepTime;
    private long goBedTime;

    @SerializedName("lastAvgBreathrate")
    private Integer lastAvgBreathrate;

    @SerializedName("lastAvgHeartrate")
    private Integer lastAvgHeartrate;

    @SerializedName("lastAvgHrv")
    private Integer lastAvgHrv;

    @SerializedName("lastAvgSpO2")
    private Integer lastAvgSpO2;

    @SerializedName("lastBreathrateDayToBaseline")
    private Integer lastBreathrateDayToBaseline;

    @SerializedName("lastHeartrateDayToBaseline")
    private Integer lastHeartrateDayToBaseline;

    @SerializedName("lastHrvDayToBaseline")
    private Integer lastHrvDayToBaseline;

    @SerializedName("lastMaxBreathrateBaseline")
    private Integer lastMaxBreathrateBaseline;

    @SerializedName("lastMaxHeartrateBaseline")
    private Integer lastMaxHeartrateBaseline;

    @SerializedName("lastMaxHrvBaseline")
    private Integer lastMaxHrvBaseline;

    @SerializedName("lastMaxSpO2Baseline")
    private Integer lastMaxSpO2Baseline;

    @SerializedName("lastMinBreathrateBaseline")
    private Integer lastMinBreathrateBaseline;

    @SerializedName("lastMinHeartrateBaseline")
    private Integer lastMinHeartrateBaseline;

    @SerializedName("lastMinHrvBaseline")
    private Integer lastMinHrvBaseline;

    @SerializedName("lastMinSpO2Baseline")
    private Integer lastMinSpO2Baseline;

    @SerializedName("lastSpO2DayToBaseline")
    private Integer lastSpO2DayToBaseline;
    private int lightSleepTime;

    @SerializedName("maxBreathrate")
    private double maxBreathrate;

    @SerializedName("maxHeartrate")
    private int maxHeartrate;

    @SerializedName("maxOxygenSaturation")
    private double maxOxygenSaturation;

    @SerializedName("minBreathrate")
    private double minBreathrate;

    @SerializedName("minHeartrate")
    private int minHeartrate;

    @SerializedName("minOxygenSaturation")
    private double minOxygenSaturation;

    @SerializedName("newSleepLatency")
    private int newSleepLatency;

    @SerializedName("risingTime")
    private long risingTime;
    private int sleepEfficiency;
    private int sleepLatency;
    private int sleepScore;
    private String sleepSuggestion;

    @SerializedName("snoreFreq")
    private int snoreFrequency;
    private float validData;

    @SerializedName("wakeupCnt")
    private int wakeUpCount;

    @SerializedName("wakeUpFeeling")
    private int wakeUpFeeling;
    private long wakeupTime;

    public long getFallAsleepTime() {
        return this.fallAsleepTime;
    }

    public long getWakeupTime() {
        return this.wakeupTime;
    }

    public int getSleepScore() {
        return this.sleepScore;
    }

    public int getSleepLatency() {
        return this.sleepLatency;
    }

    public long getGoBedTime() {
        return this.goBedTime;
    }

    public float getValidData() {
        return this.validData;
    }

    public int getSleepEfficiency() {
        return this.sleepEfficiency;
    }

    public int getLightSleepTime() {
        return this.lightSleepTime;
    }

    public int getDeepSleepTime() {
        return this.deepSleepTime;
    }

    public int getDreamTime() {
        return this.dreamTime;
    }

    public int getAwakeTime() {
        return this.awakeTime;
    }

    public int getAllSleepTime() {
        return this.allSleepTime;
    }

    public int getWakeUpCount() {
        return this.wakeUpCount;
    }

    public int getDeepSleepPart() {
        return this.deepSleepPart;
    }

    public int getSnoreFrequency() {
        return this.snoreFrequency;
    }

    public int getDaySleepTime() {
        return this.daySleepTime;
    }

    public int getBedTime() {
        return this.bedTime;
    }

    public int getDeviceCategory() {
        return this.deviceCategory;
    }

    public String getSleepSuggestion() {
        return this.sleepSuggestion;
    }

    public void setFallAsleepTime(long j) {
        this.fallAsleepTime = j;
    }

    public void setWakeupTime(long j) {
        this.wakeupTime = j;
    }

    public void setSleepScore(int i) {
        this.sleepScore = i;
    }

    public void setSleepLatency(int i) {
        this.sleepLatency = i;
    }

    public void setGoBedTime(long j) {
        this.goBedTime = j;
    }

    public void setValidData(float f) {
        this.validData = f;
    }

    public void setSleepEfficiency(int i) {
        this.sleepEfficiency = i;
    }

    public void setLightSleepTime(int i) {
        this.lightSleepTime = i;
    }

    public void setDeepSleepTime(int i) {
        this.deepSleepTime = i;
    }

    public void setDreamTime(int i) {
        this.dreamTime = i;
    }

    public void setAwakeTime(int i) {
        this.awakeTime = i;
    }

    public void setAllSleepTime(int i) {
        this.allSleepTime = i;
    }

    public void setWakeUpCount(int i) {
        this.wakeUpCount = i;
    }

    public void setDeepSleepPart(int i) {
        this.deepSleepPart = i;
    }

    public void setSnoreFrequency(int i) {
        this.snoreFrequency = i;
    }

    public void setDaySleepTime(int i) {
        this.daySleepTime = i;
    }

    public void setBedTime(int i) {
        this.bedTime = i;
    }

    public void setDeviceCategory(int i) {
        this.deviceCategory = i;
    }

    public void setSleepSuggestion(String str) {
        this.sleepSuggestion = str;
    }

    public int getMinHeartrate() {
        return this.minHeartrate;
    }

    public void setMinHeartrate(int i) {
        this.minHeartrate = i;
    }

    public int getMaxHeartrate() {
        return this.maxHeartrate;
    }

    public void setMaxHeartrate(int i) {
        this.maxHeartrate = i;
    }

    public double getMinOxygenSaturation() {
        return this.minOxygenSaturation;
    }

    public void setMinOxygenSaturation(double d) {
        this.minOxygenSaturation = d;
    }

    public double getMaxOxygenSaturation() {
        return this.maxOxygenSaturation;
    }

    public void setMaxOxygenSaturation(double d) {
        this.maxOxygenSaturation = d;
    }

    public double getMinBreathrate() {
        return this.minBreathrate;
    }

    public void setMinBreathrate(double d) {
        this.minBreathrate = d;
    }

    public double getMaxBreathrate() {
        return this.maxBreathrate;
    }

    public void setMaxBreathrate(double d) {
        this.maxBreathrate = d;
    }

    public int getWakeUpFeeling() {
        return this.wakeUpFeeling;
    }

    public void setWakeUpFeeling(int i) {
        this.wakeUpFeeling = i;
    }

    public long getRisingTime() {
        return this.risingTime;
    }

    public void setRisingTime(long j) {
        this.risingTime = j;
    }

    public int getDeviceBedTime() {
        return this.deviceBedTime;
    }

    public void setDeviceBedTime(int i) {
        this.deviceBedTime = i;
    }

    public int getNewSleepLatency() {
        return this.newSleepLatency;
    }

    public void setNewSleepLatency(int i) {
        this.newSleepLatency = i;
    }

    public Integer getLastAvgHeartrate() {
        return this.lastAvgHeartrate;
    }

    public void setLastAvgHeartrate(Integer num) {
        this.lastAvgHeartrate = num;
    }

    public Integer getLastMinHeartrateBaseline() {
        return this.lastMinHeartrateBaseline;
    }

    public void setLastMinHeartrateBaseline(Integer num) {
        this.lastMinHeartrateBaseline = num;
    }

    public Integer getLastMaxHeartrateBaseline() {
        return this.lastMaxHeartrateBaseline;
    }

    public void setLastMaxHeartrateBaseline(Integer num) {
        this.lastMaxHeartrateBaseline = num;
    }

    public Integer getLastHeartrateDayToBaseline() {
        return this.lastHeartrateDayToBaseline;
    }

    public void setLastHeartrateDayToBaseline(Integer num) {
        this.lastHeartrateDayToBaseline = num;
    }

    public Integer getLastAvgSpO2() {
        return this.lastAvgSpO2;
    }

    public void setLastAvgSpO2(Integer num) {
        this.lastAvgSpO2 = num;
    }

    public Integer getLastMinSpO2Baseline() {
        return this.lastMinSpO2Baseline;
    }

    public void setLastMinSpO2Baseline(Integer num) {
        this.lastMinSpO2Baseline = num;
    }

    public Integer getLastMaxSpO2Baseline() {
        return this.lastMaxSpO2Baseline;
    }

    public void setLastMaxSpO2Baseline(Integer num) {
        this.lastMaxSpO2Baseline = num;
    }

    public Integer getLastSpO2DayToBaseline() {
        return this.lastSpO2DayToBaseline;
    }

    public void setLastSpO2DayToBaseline(Integer num) {
        this.lastSpO2DayToBaseline = num;
    }

    public Integer getLastAvgBreathrate() {
        return this.lastAvgBreathrate;
    }

    public void setLastAvgBreathrate(Integer num) {
        this.lastAvgBreathrate = num;
    }

    public Integer getLastMinBreathrateBaseline() {
        return this.lastMinBreathrateBaseline;
    }

    public void setLastMinBreathrateBaseline(Integer num) {
        this.lastMinBreathrateBaseline = num;
    }

    public Integer getLastMaxBreathrateBaseline() {
        return this.lastMaxBreathrateBaseline;
    }

    public void setLastMaxBreathrateBaseline(Integer num) {
        this.lastMaxBreathrateBaseline = num;
    }

    public Integer getLastBreathrateDayToBaseline() {
        return this.lastBreathrateDayToBaseline;
    }

    public void setLastBreathrateDayToBaseline(Integer num) {
        this.lastBreathrateDayToBaseline = num;
    }

    public Integer getLastAvgHrv() {
        return this.lastAvgHrv;
    }

    public void setLastAvgHrv(Integer num) {
        this.lastAvgHrv = num;
    }

    public Integer getLastMinHrvBaseline() {
        return this.lastMinHrvBaseline;
    }

    public void setLastMinHrvBaseline(Integer num) {
        this.lastMinHrvBaseline = num;
    }

    public Integer getLastMaxHrvBaseline() {
        return this.lastMaxHrvBaseline;
    }

    public void setLastMaxHrvBaseline(Integer num) {
        this.lastMaxHrvBaseline = num;
    }

    public Integer getLastHrvDayToBaseline() {
        return this.lastHrvDayToBaseline;
    }

    public void setLastHrvDayToBaseline(Integer num) {
        this.lastHrvDayToBaseline = num;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ProfessionalSleep{ft=");
        stringBuffer.append(this.fallAsleepTime);
        stringBuffer.append(", wt=").append(this.wakeupTime);
        stringBuffer.append(", sc=").append(this.sleepScore);
        stringBuffer.append(", sl=").append(this.sleepLatency);
        stringBuffer.append(", g=").append(this.goBedTime);
        stringBuffer.append(", v=").append(this.validData);
        stringBuffer.append(", se=").append(this.sleepEfficiency);
        stringBuffer.append(", l=").append(this.lightSleepTime);
        stringBuffer.append(", d=").append(this.deepSleepTime);
        stringBuffer.append(", dr=").append(this.dreamTime);
        stringBuffer.append(", aw=").append(this.awakeTime);
        stringBuffer.append(", al=").append(this.allSleepTime);
        stringBuffer.append(", w=").append(this.wakeUpCount);
        stringBuffer.append(", dp=").append(this.deepSleepPart);
        stringBuffer.append(", sf=").append(this.snoreFrequency);
        stringBuffer.append(", ds=").append(this.daySleepTime);
        stringBuffer.append(", b=").append(this.bedTime);
        stringBuffer.append(", dc=").append(this.deviceCategory);
        stringBuffer.append(", ss=").append(this.sleepSuggestion);
        stringBuffer.append(", minH=").append(this.minHeartrate);
        stringBuffer.append(", maxH=").append(this.maxHeartrate);
        stringBuffer.append(", minO=").append(this.minOxygenSaturation);
        stringBuffer.append(", maxO=").append(this.maxOxygenSaturation);
        stringBuffer.append(", minB=").append(this.minBreathrate);
        stringBuffer.append(", maxB=").append(this.maxBreathrate);
        stringBuffer.append(", wf=").append(this.wakeUpFeeling);
        stringBuffer.append(", r=").append(this.risingTime);
        stringBuffer.append(", db=").append(this.deviceBedTime);
        stringBuffer.append(", n=").append(this.newSleepLatency);
        stringBuffer.append(", ah=").append(this.lastAvgHeartrate);
        stringBuffer.append(", minHB=").append(this.lastMinHeartrateBaseline);
        stringBuffer.append(", maxHB=").append(this.lastMaxHeartrateBaseline);
        stringBuffer.append(", hd=").append(this.lastHeartrateDayToBaseline);
        stringBuffer.append(", as=").append(this.lastAvgSpO2);
        stringBuffer.append(", minSB=").append(this.lastMinSpO2Baseline);
        stringBuffer.append(", maxSB=").append(this.lastMaxSpO2Baseline);
        stringBuffer.append(", sd=").append(this.lastSpO2DayToBaseline);
        stringBuffer.append(", ab=").append(this.lastAvgBreathrate);
        stringBuffer.append(", minBB=").append(this.lastMinBreathrateBaseline);
        stringBuffer.append(", maxBB=").append(this.lastMaxBreathrateBaseline);
        stringBuffer.append(", bd=").append(this.lastBreathrateDayToBaseline);
        stringBuffer.append(", ahr=").append(this.lastAvgHrv);
        stringBuffer.append(", minHr=").append(this.lastMinHrvBaseline);
        stringBuffer.append(", maxHr=").append(this.lastMaxHrvBaseline);
        stringBuffer.append(", hrd=").append(this.lastHrvDayToBaseline);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
