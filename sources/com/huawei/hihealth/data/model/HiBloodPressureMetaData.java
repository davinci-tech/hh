package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class HiBloodPressureMetaData {

    @SerializedName("activityActions")
    private List<String> mActivityActions;

    public void setActivityActions(List<String> list) {
        this.mActivityActions = list;
    }

    public List<String> getActivityActions() {
        return this.mActivityActions;
    }
}
