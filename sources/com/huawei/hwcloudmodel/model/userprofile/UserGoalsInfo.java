package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes7.dex */
public class UserGoalsInfo implements Serializable {
    private static final long serialVersionUID = -3185409940237567052L;

    @SerializedName("goalPeriod")
    private Integer goalPeriod;

    @SerializedName("goalType")
    private Integer goalType;

    @SerializedName("goalValue")
    private String goalValue;

    public Integer getGoalType() {
        return this.goalType;
    }

    public void setGoalType(Integer num) {
        this.goalType = num;
    }

    public Integer getGoalPeriod() {
        return this.goalPeriod;
    }

    public void setGoalPeriod(Integer num) {
        this.goalPeriod = num;
    }

    public String getGoalValue() {
        return this.goalValue;
    }

    public void setGoalValue(String str) {
        this.goalValue = str;
    }

    public String toString() {
        return "UserGoalsInfo{goalType=" + this.goalType + ", goalPeriod=" + this.goalPeriod + '}';
    }
}
