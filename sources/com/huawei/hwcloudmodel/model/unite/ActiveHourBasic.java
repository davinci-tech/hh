package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class ActiveHourBasic {

    @SerializedName("countActiveHour")
    private int countActiveHour;

    @SerializedName("generateTime")
    private long generateTime;

    public long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(long j) {
        this.generateTime = j;
    }

    public int getCountActiveHour() {
        return this.countActiveHour;
    }

    public void setCountActiveHour(int i) {
        this.countActiveHour = i;
    }

    public String toString() {
        return "ActiveHourBasic{generateTime=" + this.generateTime + ", activeHourCount=" + this.countActiveHour + '}';
    }
}
