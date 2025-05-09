package com.huawei.hwsportmodel;

/* loaded from: classes5.dex */
public enum StandFlexionAchieveType {
    ACHIEVE_LEVEL_ONE(60, "L008", "pic_amazing"),
    ACHIEVE_LEVEL_TWO(70, "L009", "pic_amazing"),
    ACHIEVE_LEVEL_THREE(80, "L010", "pic_amazing"),
    ACHIEVE_LEVEL_FOUR(90, "L011", "pic_amazing"),
    ACHIEVE_LEVEL_FIVE(100, "L012", "pic_amazing");

    private String mPicPath;
    private int mScore;
    private String mVoicePath;

    public String getVoicePath() {
        return this.mVoicePath;
    }

    public String getPicPath() {
        return this.mPicPath;
    }

    public int getScore() {
        return this.mScore;
    }

    StandFlexionAchieveType(int i, String str, String str2) {
        this.mScore = i;
        this.mVoicePath = str;
        this.mPicPath = str2;
    }
}
