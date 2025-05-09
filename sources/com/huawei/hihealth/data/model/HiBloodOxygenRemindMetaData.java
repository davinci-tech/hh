package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class HiBloodOxygenRemindMetaData {

    @SerializedName("mThreshold")
    private int mThreshold;

    public HiBloodOxygenRemindMetaData(int i) {
        this.mThreshold = i;
    }

    public int getThreshold() {
        return this.mThreshold;
    }

    public void setThreshold(int i) {
        this.mThreshold = i;
    }
}
