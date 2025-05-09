package com.huawei.hwcommonmodel.fitnessdatatype;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class StudentHeartRateZoneMgr {
    public static final int DEFAULT_TYPE = 0;
    private static final String SET_STUDENT_HEART_RATE_DATA = "student_heart_all_data";
    private static final String STUDENT_HEART_RATE_TABLE = "student_heart_zone_set";
    private static final String TAG = "Heart_StudentHeartRateZoneMgr";
    private HeartRateThresholdConfig mStudentHeartRateData;

    public StudentHeartRateZoneMgr(int i) {
        this.mStudentHeartRateData = new HeartRateThresholdConfig(0, i);
    }

    public HeartRateThresholdConfig getStudentHeartRateThresholdData() {
        return this.mStudentHeartRateData;
    }

    public void setHeartRateConfig(boolean z, int i, int i2, int i3, int i4) {
        this.mStudentHeartRateData.setWarningEnable(z);
        this.mStudentHeartRateData.setWarningLimit(i);
        this.mStudentHeartRateData.setClassifyMethod(i2);
        this.mStudentHeartRateData.setMaxThreshold(i3);
        this.mStudentHeartRateData.setRestHeartRate(i4);
    }

    public void setMaxHeartRateThreshold(int i, int i2, int i3, int i4, int i5) {
        this.mStudentHeartRateData.setAnaerobicThreshold(i);
        this.mStudentHeartRateData.setAerobicThreshold(i2);
        this.mStudentHeartRateData.setFatBurnThreshold(i3);
        this.mStudentHeartRateData.setWarmUpThreshold(i4);
        this.mStudentHeartRateData.setFitnessThreshold(i5);
    }

    public void setHrrHeartRateZoneThreshold(int i, int i2, int i3, int i4, int i5) {
        this.mStudentHeartRateData.setAnaerobicAdvanceThreshold(i);
        this.mStudentHeartRateData.setAnaerobicBaseThreshold(i2);
        this.mStudentHeartRateData.setLacticAcidThreshold(i3);
        this.mStudentHeartRateData.setAerobicAdvanceThreshold(i4);
        this.mStudentHeartRateData.setAerobicBaseThreshold(i5);
    }

    public int getOldMaxThreshold() {
        return this.mStudentHeartRateData.getOldMaxThreshold();
    }

    public void setStudentWarningLimitStatus(boolean z) {
        this.mStudentHeartRateData.getHeartZoneStateConfig().setIsSetWarningLimit(z);
    }

    public boolean getStudentWarningLimitStatus() {
        return this.mStudentHeartRateData.getHeartZoneStateConfig().getIsSetWarningLimit();
    }

    public void setStudentHeartDataToSp() {
        String json = new Gson().toJson(this.mStudentHeartRateData);
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(STUDENT_HEART_RATE_TABLE, 0).edit();
        edit.putString(SET_STUDENT_HEART_RATE_DATA, json);
        edit.apply();
        LogUtil.c(TAG, json);
    }

    public String getStudentHeartData() {
        return BaseApplication.getContext().getSharedPreferences(STUDENT_HEART_RATE_TABLE, 0).getString(SET_STUDENT_HEART_RATE_DATA, "");
    }
}
