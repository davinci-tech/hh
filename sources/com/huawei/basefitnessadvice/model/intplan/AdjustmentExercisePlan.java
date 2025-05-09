package com.huawei.basefitnessadvice.model.intplan;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class AdjustmentExercisePlan {

    @SerializedName("exeDays")
    private String exeDays;

    @SerializedName("startDay")
    private String startDay;

    private AdjustmentExercisePlan(Builder builder) {
        this.startDay = builder.startDay;
        this.exeDays = builder.exeDays;
    }

    /* loaded from: classes8.dex */
    public static final class Builder {
        private String exeDays;
        private String startDay;

        public Builder startDay(String str) {
            this.startDay = str;
            return this;
        }

        public Builder exeDays(String str) {
            this.exeDays = str;
            return this;
        }

        public AdjustmentExercisePlan build() {
            return new AdjustmentExercisePlan(this);
        }
    }
}
