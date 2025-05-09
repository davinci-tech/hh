package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;

/* loaded from: classes6.dex */
public class CpInfo {

    @SerializedName("co")
    private int color;

    @SerializedName("de")
    private String description;

    @SerializedName("mo")
    private int mode;

    @SerializedName("na")
    private String name;

    @SerializedName("ty")
    private int type = MarkPoint.MarkType.COMMON.type();

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "CpInfo{color=" + this.color + ", mode=" + this.mode + ", type=" + this.type + ", name=" + this.name + ", description='" + this.description + "'}";
    }
}
