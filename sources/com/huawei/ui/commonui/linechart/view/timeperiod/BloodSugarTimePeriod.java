package com.huawei.ui.commonui.linechart.view.timeperiod;

import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public enum BloodSugarTimePeriod {
    MID_NIGHT(2015, 0, 5, R$drawable.ic_blood_sugar_midnight, TimePeriodString.STR_MID_NIGHT),
    BEFORE_BREAKFAST(2008, 0, 10, R$drawable.ic_blood_sugar_before_breakfast, TimePeriodString.STR_BEFORE_BREAKFAST),
    AFTER_BREAKFAST(2009, 5, 12, R$drawable.ic_blood_sugar_after_breakfast, TimePeriodString.STR_AFTER_BREAKFAST),
    BEFORE_LUNCH(2010, 10, 16, R$drawable.ic_blood_sugar_before_lunch, TimePeriodString.STR_BEFORE_LUNCH),
    AFTER_LUNCH(2011, 10, 18, R$drawable.ic_blood_sugar_after_lunch, TimePeriodString.STR_AFTER_LUNCH),
    BEFORE_DINNER(2012, 12, 24, R$drawable.ic_blood_sugar_before_dinner, TimePeriodString.STR_BEFORE_DINNER),
    AFTER_DINNER(2013, 16, 24, R$drawable.ic_blood_sugar_after_dinner, TimePeriodString.STR_AFTER_DINNER),
    BEFORE_SLEEP(2014, 18, 24, R$drawable.ic_blood_sugar_before_sleep, TimePeriodString.STR_BEFORE_SLEEP),
    RANDOM(2106, 0, 24, R$drawable.ic_blood_sugar_random, TimePeriodString.STR_RANDOM);

    private final int mCode;
    private final int mEndHours;
    private final int mIconResId;
    private final int mStartHours;
    private TimePeriodString mTimePeriodString;

    BloodSugarTimePeriod(int i, int i2, int i3, int i4, TimePeriodString timePeriodString) {
        this.mCode = i;
        this.mStartHours = i2;
        this.mEndHours = i3;
        this.mIconResId = i4;
        this.mTimePeriodString = timePeriodString;
    }

    public static ArrayList<BloodSugarTimePeriod> getTimePeriodsByHours(int i) {
        BloodSugarTimePeriod[] values = values();
        ArrayList<BloodSugarTimePeriod> arrayList = new ArrayList<>(values.length);
        for (BloodSugarTimePeriod bloodSugarTimePeriod : values) {
            if (i >= bloodSugarTimePeriod.mStartHours && i < bloodSugarTimePeriod.mEndHours) {
                arrayList.add(bloodSugarTimePeriod);
            }
        }
        return arrayList;
    }

    public static BloodSugarTimePeriod getTimePeriodByCode(int i) {
        for (BloodSugarTimePeriod bloodSugarTimePeriod : values()) {
            if (bloodSugarTimePeriod != null && bloodSugarTimePeriod.getCode() == i) {
                return bloodSugarTimePeriod;
            }
        }
        return null;
    }

    public int getCode() {
        return this.mCode;
    }

    public int getTimePeriodNameRes() {
        return this.mTimePeriodString.getTimePeriodNameRes();
    }

    public int getTimePeriodDescriptionRes() {
        return this.mTimePeriodString.getTimePeriodDescriptionRes();
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    enum TimePeriodString {
        STR_MID_NIGHT(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning, R$string.IDS_hw_show_healthdata_bloodsugar_early_morning_desc, R$string.IDS_hw_show_healthdata_bloodsugar_night_range),
        STR_BEFORE_BREAKFAST(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast, R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast_desc, R$string.IDS_hw_show_healthdata_bloodsugar_limosis_range),
        STR_AFTER_BREAKFAST(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast, R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast_desc, R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast_range),
        STR_BEFORE_LUNCH(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch, R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch_desc, R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch_range),
        STR_AFTER_LUNCH(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch, R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch_desc, R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch_range),
        STR_BEFORE_DINNER(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner, R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner_desc, R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner_range),
        STR_AFTER_DINNER(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner, R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner_desc, R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner_range),
        STR_BEFORE_SLEEP(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep, R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_desc, R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_range),
        STR_RANDOM(R$string.IDS_hw_show_healthdata_bloodsugar_random_time, R$string.IDS_hw_show_healthdata_bloodsugar_random_time_desc, 0);

        private final int mSuggestBloodSugarRangeRes;
        private final int mTimePeriodDescriptionRes;
        private final int mTimePeriodNameRes;

        TimePeriodString(int i, int i2, int i3) {
            this.mTimePeriodNameRes = i;
            this.mTimePeriodDescriptionRes = i2;
            this.mSuggestBloodSugarRangeRes = i3;
        }

        int getTimePeriodNameRes() {
            return this.mTimePeriodNameRes;
        }

        int getTimePeriodDescriptionRes() {
            return this.mTimePeriodDescriptionRes;
        }
    }
}
