package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class SourceTimeRange {

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("start")
    @Expose
    private int start;

    public String toString() {
        return "SourceTimeRange{start=" + this.start + ", duration=" + this.duration + '}';
    }

    public SourceTimeRange(int i, int i2) {
        this.start = i;
        this.duration = i2;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getStart() {
        int i = this.start;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public SourceTimeRange withStart(int i) {
        this.start = i;
        return this;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public final int getDuration() {
        int i = this.duration;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public SourceTimeRange withDuration(int i) {
        this.duration = i;
        return this;
    }
}
