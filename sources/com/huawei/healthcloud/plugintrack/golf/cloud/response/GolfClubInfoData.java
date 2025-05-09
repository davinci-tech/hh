package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class GolfClubInfoData {

    @SerializedName("id")
    private int mGolfClubId;

    @SerializedName("isEnable")
    private boolean mIsEnable;

    @SerializedName("maxDis")
    private double mMaxStrikeRange;

    @SerializedName("minDis")
    private double mMinStrikeRange;

    public int getGolfClubId() {
        return this.mGolfClubId;
    }

    public void setGolfClubId(int i) {
        this.mGolfClubId = i;
    }

    public double getMinStrikeRange() {
        return this.mMinStrikeRange;
    }

    public void setMinStrikeRange(double d) {
        this.mMinStrikeRange = d;
    }

    public double getMaxStrikeRange() {
        return this.mMaxStrikeRange;
    }

    public void setMaxStrikeRange(double d) {
        this.mMaxStrikeRange = d;
    }

    public boolean isEnable() {
        return this.mIsEnable;
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
    }
}
