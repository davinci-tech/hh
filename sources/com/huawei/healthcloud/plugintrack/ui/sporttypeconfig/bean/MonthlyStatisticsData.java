package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class MonthlyStatisticsData {
    public static final String BACKUP = "BACKUP";
    public static final String MAIN = "MAIN";
    public static final int STAT_CALORIE_INDEX = 1;
    public static final int STAT_EXPAND_INDEX = 3;
    public static final int STAT_MAIN_INDEX = 0;
    public static final int STAT_TIME_INDEX = 2;

    @SerializedName("position")
    private String mPosition;

    @SerializedName("requestId")
    private int mRequestId;

    @SerializedName("type")
    private String mType;

    public String getPosition() {
        return this.mPosition;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String toString() {
        return "MonthlyStatisticsData position" + this.mPosition + "type" + this.mType + "requestStringgetRequestId" + this.mRequestId;
    }
}
