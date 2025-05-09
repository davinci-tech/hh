package com.huawei.health.suggestion.h5pro;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.intplan.IntPlanBean;

/* loaded from: classes.dex */
public class AiFitnessCreateParam extends IntPlanBean {

    @SerializedName("isModifyWeight")
    private boolean mIsModifyWeight;

    public boolean isModifyWeight() {
        return this.mIsModifyWeight;
    }

    public void setModifyWeight(boolean z) {
        this.mIsModifyWeight = z;
    }
}
