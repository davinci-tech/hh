package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportSleep extends AnnualData {
    private int animalImage;
    private double breLevel;
    private String fallAsleepTime;
    private int sleepDuration;
    private double sleepScore;
    private int sleepTimes;
    private String wakeupTime;

    public AnnualReportSleep() {
        super(EnumAnnualType.REPORT_SLEEP.value());
    }

    public void saveSleepDuration(int i) {
        this.sleepDuration = i;
    }

    public void saveSleepScore(double d) {
        this.sleepScore = d;
    }

    public void saveAnimalImage(int i) {
        this.animalImage = i;
    }

    public void saveFallAsleepTime(String str) {
        this.fallAsleepTime = str;
    }

    public void saveWakeupTime(String str) {
        this.wakeupTime = str;
    }

    public void setBreLevel(double d) {
        this.breLevel = d;
    }

    public void setSleepTimes(int i) {
        this.sleepTimes = i;
    }

    public String toString() {
        return "AnnualReportSleep{sleepDuration=" + this.sleepDuration + ", sleepScore=" + this.sleepScore + ", animalImage=" + this.animalImage + ", fallAsleepTime='" + this.fallAsleepTime + "', wakeupTime='" + this.wakeupTime + "', breLevel=" + this.breLevel + ", sleepTimes=" + this.sleepTimes + '}';
    }
}
