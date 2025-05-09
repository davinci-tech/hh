package com.huawei.pluginachievement.manager.model;

/* loaded from: classes9.dex */
public class AchieveResource {
    private String level1 = "";
    private String level2 = "";
    private int steps;

    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public String acquireLevel1() {
        return this.level1;
    }

    public void setLevel1(String str) {
        this.level1 = str;
    }

    public String acquireLevel2() {
        return this.level2;
    }

    public void setLevel2(String str) {
        this.level2 = str;
    }

    public String toString() {
        return "AchieveResource{steps=" + this.steps + ", level1=" + this.level1 + ", level2=" + this.level2 + '}';
    }
}
