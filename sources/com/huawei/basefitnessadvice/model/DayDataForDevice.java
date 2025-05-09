package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class DayDataForDevice {

    @SerializedName("dayIndex")
    private int mDayIndex;

    @SerializedName("intCourseDayList")
    private List<IntCourseDayData> mIntCourseDayDataList;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    public List<IntCourseDayData> getIntPlanDayDataList() {
        return this.mIntCourseDayDataList;
    }

    public void setIntPlanDayDataList(List<IntCourseDayData> list) {
        this.mIntCourseDayDataList = list;
    }

    public int getDayIndex() {
        return this.mDayIndex;
    }

    public void setDayIndex(int i) {
        this.mDayIndex = i;
    }

    public int getPunchFlag() {
        return this.mPunchFlag;
    }

    public void setPunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public static class IntCourseDayData {

        @SerializedName("courseId")
        private String mCourseId;

        @SerializedName("punchFlag")
        private int mPunchFlag;

        public String getCourseId() {
            return this.mCourseId;
        }

        public void setCourseId(String str) {
            this.mCourseId = str;
        }

        public int getPunchFlag() {
            return this.mPunchFlag;
        }

        public void setPunchFlag(int i) {
            this.mPunchFlag = i;
        }
    }
}
