package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class PPGTypeRstPointClone {

    @SerializedName("end")
    private int end;

    @SerializedName("start")
    private int start;

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int i) {
        this.end = i;
    }
}
