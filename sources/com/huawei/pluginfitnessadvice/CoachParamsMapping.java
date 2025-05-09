package com.huawei.pluginfitnessadvice;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class CoachParamsMapping implements Serializable {
    private static final long serialVersionUID = 1;

    @SerializedName("adjustValue")
    private int mAdjustValue;

    @SerializedName("oldValue")
    private int mOldValue;

    @SerializedName("type")
    private int mType;

    public CoachParamsMapping(int i, int i2, int i3) {
        this.mType = i;
        this.mOldValue = i2;
        this.mAdjustValue = i3;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getOldValue() {
        return this.mOldValue;
    }

    public void setOldValue(int i) {
        this.mOldValue = i;
    }
}
