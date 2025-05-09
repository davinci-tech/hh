package com.huawei.basefitnessadvice.model.intplan;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class DayInfo {

    @SerializedName("adjustDay")
    private String adjustDay;

    @SerializedName("courseIdList")
    private List<String> courseIdList;

    @SerializedName("dayStatus")
    private Integer dayStatus;

    private DayInfo(Builder builder) {
        this.adjustDay = builder.adjustDay;
        this.dayStatus = builder.dayStatus;
        this.courseIdList = builder.courseIdList;
    }

    public static final class Builder {
        private String adjustDay;
        private List<String> courseIdList;
        private Integer dayStatus;

        public Builder adjustDay(String str) {
            this.adjustDay = str;
            return this;
        }

        public Builder dayStatus(Integer num) {
            this.dayStatus = num;
            return this;
        }

        public Builder courseIdList(List<String> list) {
            this.courseIdList = list;
            return this;
        }

        public DayInfo build() {
            return new DayInfo(this);
        }
    }
}
