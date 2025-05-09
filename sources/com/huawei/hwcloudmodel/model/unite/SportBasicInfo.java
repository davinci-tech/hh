package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class SportBasicInfo {
    private float altitude;
    private int calorie;
    private int count;
    private int distance;
    private int duration;
    private int floor;
    private int steps;

    public int fetchSteps() {
        return this.steps;
    }

    public void configSteps(int i) {
        this.steps = i;
    }

    public int fetchDistance() {
        return this.distance;
    }

    public void configDistance(int i) {
        this.distance = i;
    }

    public int fetchCalorie() {
        return this.calorie;
    }

    public void configCalorie(int i) {
        this.calorie = i;
    }

    public float fetchAltitude() {
        return this.altitude;
    }

    public void configAltitude(float f) {
        this.altitude = f;
    }

    public int fetchFloor() {
        return this.floor;
    }

    public void configFloor(int i) {
        this.floor = i;
    }

    public int fetchDuration() {
        return this.duration;
    }

    public void configDuration(int i) {
        this.duration = i;
    }

    public int fetchCount() {
        return this.count;
    }

    public void configCount(int i) {
        this.count = i;
    }

    public String toString() {
        return "SportBasicInfo{steps=" + this.steps + ", distance=" + this.distance + ", calorie=" + this.calorie + ", altitude=" + this.altitude + ", floor=" + this.floor + ", duration=" + this.duration + ", count=" + this.count + '}';
    }
}
