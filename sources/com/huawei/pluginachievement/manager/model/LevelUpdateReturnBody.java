package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class LevelUpdateReturnBody extends mcz {
    private String resultDes;
    private int rewardExperience;
    private int totalExperience;

    public LevelUpdateReturnBody() {
        super(17);
    }

    public void saveRewardExperience(int i) {
        this.rewardExperience = i;
    }

    public int acquireTotalExperience() {
        return this.totalExperience;
    }

    public void saveTotalExperience(int i) {
        this.totalExperience = i;
    }

    public void saveResultDes(String str) {
        this.resultDes = str;
    }

    public String toString() {
        return "LevelUpdateReturnBody{rewardExperience=" + this.rewardExperience + ", totalExperience=" + this.totalExperience + ", resultDes='" + this.resultDes + "'}";
    }
}
