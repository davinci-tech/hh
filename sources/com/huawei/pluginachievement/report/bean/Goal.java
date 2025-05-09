package com.huawei.pluginachievement.report.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes6.dex */
public class Goal {

    @SerializedName("completeValue")
    private double completeValue;

    @SerializedName("createTime")
    private long createTime;

    @SerializedName("currentWeight")
    private double currentWeight;

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("frequency")
    private int frequency;

    @SerializedName("goalType")
    private int goalType;

    @SerializedName("goalValue")
    private double goalValue;

    @SerializedName("id")
    private long id;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long modifyTime;

    @SerializedName("recordDays")
    private List<Long> recordDays;

    @SerializedName("startTime")
    private long startTime;

    @SerializedName("status")
    private int status;

    @SerializedName("timesType")
    private int timesType;

    @SerializedName("unitValue")
    private double unitValue;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getGoalType() {
        return this.goalType;
    }

    public void setGoalType(int i) {
        this.goalType = i;
    }

    public double getGoalValue() {
        return this.goalValue;
    }

    public void setGoalValue(double d) {
        this.goalValue = d;
    }

    public double getCompleteValue() {
        return this.completeValue;
    }

    public void setCompleteValue(double d) {
        this.completeValue = d;
    }

    public List<Long> getRecordDays() {
        return this.recordDays;
    }

    public void setRecordDays(List<Long> list) {
        this.recordDays = list;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public double getUnitValue() {
        return this.unitValue;
    }

    public void setUnitValue(double d) {
        this.unitValue = d;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long j) {
        this.modifyTime = j;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public double getCurrentWeight() {
        return this.currentWeight;
    }

    public void setCurrentWeight(double d) {
        this.currentWeight = d;
    }

    public int getTimesType() {
        return this.timesType;
    }

    public void setTimesType(int i) {
        this.timesType = i;
    }
}
