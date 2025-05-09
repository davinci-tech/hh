package com.huawei.hwsportmodel;

import com.amap.api.services.core.AMapException;

/* loaded from: classes5.dex */
public enum SkipAchieveType {
    ACHIEVE_LEVEL_1(1, 8, 8, "L004", "pic_keepitup"),
    ACHIEVE_LEVEL_2(2, 20, 20, "L005", "pic_notbad"),
    ACHIEVE_LEVEL_3(3, 35, 35, "L006", "pic_good"),
    ACHIEVE_LEVEL_4(4, 50, 50, "L007", "pic_great"),
    ACHIEVE_LEVEL_5(5, 80, 80, "L008", "pic_wonderful"),
    ACHIEVE_LEVEL_6(6, -1, 120, "L009", "pic_excellent"),
    ACHIEVE_LEVEL_7(7, 103, 200, "L010", "pic_super"),
    ACHIEVE_LEVEL_8(8, 124, 500, "L011", "pic_outstanding"),
    ACHIEVE_LEVEL_9(9, 140, 900, "L012", "pic_perfect"),
    ACHIEVE_LEVEL_10(10, 160, AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING, "L013", "pic_incredible"),
    ACHIEVE_LEVEL_11(11, -1, 2700, "L014", "pic_amazing");

    private int mLevel;
    private String mPicPath;
    private int mSkipTimes;
    private int mSkipTimesForOneMinute;
    private String mVoicePath;

    public int getSkipTimesForOneMinute() {
        return this.mSkipTimesForOneMinute;
    }

    public int getSkipTimes() {
        return this.mSkipTimes;
    }

    public String getVoicePath() {
        return this.mVoicePath;
    }

    public String getPicPath() {
        return this.mPicPath;
    }

    public int getLevel() {
        return this.mLevel;
    }

    SkipAchieveType(int i, int i2, int i3, String str, String str2) {
        this.mLevel = i;
        this.mSkipTimesForOneMinute = i2;
        this.mSkipTimes = i3;
        this.mVoicePath = str;
        this.mPicPath = str2;
    }
}
