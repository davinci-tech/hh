package com.huawei.hwsportmodel;

/* loaded from: classes5.dex */
public enum SupineLegAchieveType {
    ACHIEVE_LEVEL_1(10, 5, "N010", "L115", "pic_keepitup"),
    ACHIEVE_LEVEL_2(20, 10, "N020", "L116", "pic_notbad"),
    ACHIEVE_LEVEL_3(30, 20, "N030", "L117", "pic_great"),
    ACHIEVE_LEVEL_4(40, 30, "N040", "L007", "pic_amazing"),
    ACHIEVE_LEVEL_5(50, 40, "N050", "L118", "pic_wonderful"),
    ACHIEVE_LEVEL_6(60, 50, "N060", "L119", "pic_excellent"),
    ACHIEVE_LEVEL_7(70, 60, "N070", "L007", "pic_amazing"),
    ACHIEVE_LEVEL_8(80, 70, "N080", "", ""),
    ACHIEVE_LEVEL_9(90, 80, "N090", "", ""),
    ACHIEVE_LEVEL_10(100, 90, "N100", "", "");

    private int mContinuesTimes;
    private String mContinuesVoicePath;
    private int mLegRaiseTimes;
    private String mPicPath;
    private String mVoicePath;

    public int getLegRaiseTimes() {
        return this.mLegRaiseTimes;
    }

    public String getVoicePath() {
        return this.mVoicePath;
    }

    public String getPicPath() {
        return this.mPicPath;
    }

    public int getContinuesTimes() {
        return this.mContinuesTimes;
    }

    public String getContinuesVoicePath() {
        return this.mContinuesVoicePath;
    }

    SupineLegAchieveType(int i, int i2, String str, String str2, String str3) {
        this.mLegRaiseTimes = i;
        this.mContinuesTimes = i2;
        this.mVoicePath = str;
        this.mContinuesVoicePath = str2;
        this.mPicPath = str3;
    }
}
