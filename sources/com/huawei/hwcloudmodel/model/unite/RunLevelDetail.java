package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class RunLevelDetail {

    @SerializedName("condition")
    private float condition;

    @SerializedName("currentRunLevel")
    private float currentRunLevel;

    @SerializedName("fatigue")
    private float fatigue;

    @SerializedName("fitness")
    private float fitness;

    @SerializedName("realRunLevel")
    private float realRunLevel;

    @SerializedName("totalPoint")
    private float totalPoint;

    public float getCurrentRunLevel() {
        return this.currentRunLevel;
    }

    public void setCurrentRunLevel(float f) {
        this.currentRunLevel = f;
    }

    public float getRealRunLevel() {
        return this.realRunLevel;
    }

    public void setRealRunLevel(float f) {
        this.realRunLevel = f;
    }

    public float getTotalPoint() {
        return this.totalPoint;
    }

    public void setTotalPoint(float f) {
        this.totalPoint = f;
    }

    public float getFitness() {
        return (float) UnitUtil.a(this.fitness, 1);
    }

    public void setFitness(float f) {
        this.fitness = f;
    }

    public float getFatigue() {
        return (float) UnitUtil.a(this.fatigue, 1);
    }

    public void setFatigue(float f) {
        this.fatigue = f;
    }

    public float getCondition() {
        return ((float) UnitUtil.a(this.fitness, 1)) - ((float) UnitUtil.a(this.fatigue, 1));
    }

    public void setCondition(float f) {
        this.condition = f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("RunLevelDetail{currentRunLevel=");
        sb.append(this.currentRunLevel);
        sb.append(", realRunLevel=");
        sb.append(this.realRunLevel);
        sb.append(", totalPoint=");
        sb.append(this.totalPoint);
        sb.append(", fitness=");
        sb.append(this.fitness);
        sb.append(", fatigue=");
        sb.append(this.fatigue);
        sb.append(", condition=");
        sb.append(this.condition);
        return sb.toString();
    }
}
