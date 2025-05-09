package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class UserRunLevelData {

    @SerializedName("aSpeed")
    private float anaerobicUpResult;

    @SerializedName("currentRunLevel")
    private float currentRunLevel;

    @SerializedName("eSpeed")
    private float easyRunUpResult;

    @SerializedName("halfMarathon")
    private int halfMarathon;

    @SerializedName("tSpeed")
    private float lacticAcidUpResult;

    @SerializedName("lastCondition")
    private float lastCondition;

    @SerializedName("lastCurrentRunLevel")
    private float lastCurrentRunLevel;

    @SerializedName("lastFatigue")
    private float lastFatigue;

    @SerializedName("lastFitness")
    private float lastFitness;

    @SerializedName("lastRanking")
    private float lastRanking;

    @SerializedName("lastRecordDay")
    private int lastRecordDay;

    @SerializedName("lastTotalPoint")
    private float lastTotalPoint;

    @SerializedName("marathon")
    private int marathon;

    @SerializedName("mSpeed")
    private float marathonUpResult;

    @SerializedName("ranking")
    private float ranking;

    @SerializedName("rSpeed")
    private float takeOxygenLowResult;

    @SerializedName("iSpeed")
    private float takeOxygenUpResult;

    @SerializedName("km5")
    private int timeForFiveKm;

    @SerializedName("km1")
    private int timeForOneKm;

    @SerializedName("km10")
    private int timeForTenKm;

    @SerializedName("km3")
    private int timeForThreeKm;

    public float getCurrentRunLevel() {
        return this.currentRunLevel;
    }

    public void setCurrentRunLevel(float f) {
        this.currentRunLevel = f;
    }

    public float getEasyRunUpResult() {
        return this.easyRunUpResult;
    }

    public void setEasyRunUpResult(float f) {
        this.easyRunUpResult = f;
    }

    public float getMarathonUpResult() {
        return this.marathonUpResult;
    }

    public void setMarathonUpResult(float f) {
        this.marathonUpResult = f;
    }

    public float getLacticAcidUpResult() {
        return this.lacticAcidUpResult;
    }

    public void setLacticAcidUpResult(float f) {
        this.lacticAcidUpResult = f;
    }

    public float getAnaerobicUpResult() {
        return this.anaerobicUpResult;
    }

    public void setAnaerobicUpResult(float f) {
        this.anaerobicUpResult = f;
    }

    public float getTakeOxygenUpResult() {
        return this.takeOxygenUpResult;
    }

    public void setTakeOxygenUpResult(float f) {
        this.takeOxygenUpResult = f;
    }

    public float getTakeOxygenLowResult() {
        return this.takeOxygenLowResult;
    }

    public void setTakeOxygenLowResult(float f) {
        this.takeOxygenLowResult = f;
    }

    public int getTimeForOneKm() {
        return this.timeForOneKm;
    }

    public void setTimeForOneKm(int i) {
        this.timeForOneKm = i;
    }

    public int getTimeForThreeKm() {
        return this.timeForThreeKm;
    }

    public void setTimeForThreeKm(int i) {
        this.timeForThreeKm = i;
    }

    public int getTimeForFiveKm() {
        return this.timeForFiveKm;
    }

    public void setTimeForFiveKm(int i) {
        this.timeForFiveKm = i;
    }

    public int getTimeForTenKm() {
        return this.timeForTenKm;
    }

    public void setTimeForTenKm(int i) {
        this.timeForTenKm = i;
    }

    public int getHalfMarathon() {
        return this.halfMarathon;
    }

    public void setHalfMarathon(int i) {
        this.halfMarathon = i;
    }

    public int getMarathon() {
        return this.marathon;
    }

    public void setMarathon(int i) {
        this.marathon = i;
    }

    public float getRanking() {
        return this.ranking;
    }

    public void setRanking(float f) {
        this.ranking = f;
    }

    public int getLastRecordDay() {
        return this.lastRecordDay;
    }

    public void setLastRecordDay(int i) {
        this.lastRecordDay = i;
    }

    public float getLastTotalPoint() {
        return this.lastTotalPoint;
    }

    public void setLastTotalPoint(float f) {
        this.lastTotalPoint = f;
    }

    public float getLastCurrentRunLevel() {
        return this.lastCurrentRunLevel;
    }

    public void setLastCurrentRunLevel(float f) {
        this.lastCurrentRunLevel = f;
    }

    public float getLastRanking() {
        return this.lastRanking;
    }

    public void setLastRanking(float f) {
        this.lastRanking = f;
    }

    public float getLastFitness() {
        return (float) UnitUtil.a(this.lastFitness, 1);
    }

    public void setLastFitness(float f) {
        this.lastFitness = f;
    }

    public float getLastFatigue() {
        return (float) UnitUtil.a(this.lastFatigue, 1);
    }

    public void setLastFatigue(float f) {
        this.lastFatigue = f;
    }

    public float getLastCondition() {
        return ((float) UnitUtil.a(this.lastFitness, 1)) - ((float) UnitUtil.a(this.lastFatigue, 1));
    }

    public void setLastCondition(float f) {
        this.lastCondition = f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("UserRunLevelData{currentRunLevel=");
        sb.append(this.currentRunLevel);
        sb.append(", easyRunUpResult=");
        sb.append(this.easyRunUpResult);
        sb.append(", marathonUpResult=");
        sb.append(this.marathonUpResult);
        sb.append(", lacticAcidUpResult=");
        sb.append(this.lacticAcidUpResult);
        sb.append(", anaerobicUpResult=");
        sb.append(this.anaerobicUpResult);
        sb.append(", takeOxygenUpResult=");
        sb.append(this.takeOxygenUpResult);
        sb.append(", takeOxygenLowResult=");
        sb.append(this.takeOxygenLowResult);
        sb.append(", timeForOneKm=");
        sb.append(this.timeForOneKm);
        sb.append(", timeForThreeKm=");
        sb.append(this.timeForThreeKm);
        sb.append(", timeForFiveKm=");
        sb.append(this.timeForFiveKm);
        sb.append(", timeForTenKm=");
        sb.append(this.timeForTenKm);
        sb.append(", halfMarathon=");
        sb.append(this.halfMarathon);
        sb.append(", marathon=");
        sb.append(this.marathon);
        sb.append(", ranking=");
        sb.append(this.ranking);
        sb.append(", lastRecordDay=");
        sb.append(this.lastRecordDay);
        sb.append(", lastTotalPoint=");
        sb.append(this.lastTotalPoint);
        sb.append(", lastCurrentRunLevel=");
        sb.append(this.lastCurrentRunLevel);
        sb.append(", lastRanking=");
        sb.append(this.lastRanking);
        sb.append(", lastFitness=");
        sb.append(this.lastFitness);
        sb.append(", lastFatigue=");
        sb.append(this.lastFatigue);
        sb.append(", lastCondition=");
        sb.append(this.lastCondition);
        sb.append('}');
        return sb.toString();
    }
}
