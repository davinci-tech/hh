package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes7.dex */
public class UserSleepCycleInfo implements Serializable {

    @SerializedName("serialVersionUID")
    private static final long serialVersionUID = -3185409940237567052L;

    @SerializedName("cycleEnd")
    private Integer cycleEnd;

    @SerializedName("cycleStart")
    private Integer cycleStart;

    public Integer getCycleStart() {
        return this.cycleStart;
    }

    public void setCycleStart(Integer num) {
        this.cycleStart = num;
    }

    public Integer getCycleEnd() {
        return this.cycleEnd;
    }

    public void setCycleEnd(Integer num) {
        this.cycleEnd = num;
    }

    public String toString() {
        return "UpdateBindDeviceReq{cycleStart=" + this.cycleStart + ", cycleEnd=" + this.cycleEnd + '}';
    }
}
