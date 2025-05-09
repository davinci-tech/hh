package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes7.dex */
public class ExerciseTimeBasic {

    @SerializedName("generateTime")
    private long generateTime;

    @SerializedName("intensityMap")
    private Map<Integer, Integer> intensityMap;

    @SerializedName("totalMidHighIntensity")
    private int totalMidHighIntensity;

    public long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(long j) {
        this.generateTime = j;
    }

    public int getTotalMidHighIntensity() {
        return this.totalMidHighIntensity;
    }

    public void setTotalMidHighIntensity(int i) {
        this.totalMidHighIntensity = i;
    }

    public Map<Integer, Integer> getIntensityMap() {
        return this.intensityMap;
    }

    public void setIntensityMap(Map<Integer, Integer> map) {
        this.intensityMap = map;
    }

    public String toString() {
        return "ExerciseTimeBasic{generateTime=" + this.generateTime + ", totalMidHighIntensity=" + this.totalMidHighIntensity + ", intensityMap=" + this.intensityMap + '}';
    }
}
