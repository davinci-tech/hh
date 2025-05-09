package com.huawei.health.plan.model;

import android.text.TextUtils;
import defpackage.gic;
import defpackage.moe;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class PlanStat {
    private int mBestRecordForAllFiveKm;
    private int mBestRecordForAllHalfMarathon;
    private int mBestRecordForAllMarathon;
    private int mBestRecordForAllTenKm;
    private int mBestRecordForFirstOneKm;
    private int mBestRecordForFiveKm;
    private int mBestRecordForHalfMarathon;
    private int mBestRecordForMarathon;
    private int mBestRecordForOneKm;
    private int mBestRecordForTenKm;
    private float mFarthestRunning;
    private float mHighestCompleteRate;
    private int mLongestRunning;
    private int mLongestTimePerWeek;
    private float mMostCaloriePerWeek;
    private String mMostWorkoutName;
    private int mMostWorkoutTimes;

    public int getLongestTimePerWeek() {
        return this.mLongestTimePerWeek;
    }

    public void saveLongestTimePerWeek(int i) {
        this.mLongestTimePerWeek = i;
    }

    public float getMostCaloriePerWeek() {
        return this.mMostCaloriePerWeek;
    }

    public void saveMostCaloriePerWeek(float f) {
        this.mMostCaloriePerWeek = f;
    }

    public float getHighestCompleteRate() {
        return this.mHighestCompleteRate;
    }

    public void saveHighestCompleteRate(float f) {
        this.mHighestCompleteRate = f;
    }

    public int getMostWorkoutTimes() {
        return this.mMostWorkoutTimes;
    }

    public void saveMostWorkoutTimes(int i) {
        this.mMostWorkoutTimes = i;
    }

    public String getMostWorkoutName() {
        return this.mMostWorkoutName;
    }

    public void setMostWorkoutName(String str) {
        this.mMostWorkoutName = str;
    }

    public float getFarthestRunning() {
        return this.mFarthestRunning;
    }

    public void setFarthestRunning(float f) {
        this.mFarthestRunning = f;
    }

    public int getLongestRunning() {
        return this.mLongestRunning;
    }

    public void setLongestRunning(int i) {
        this.mLongestRunning = i;
    }

    public int getBestRecordForOneKm() {
        return this.mBestRecordForOneKm;
    }

    public void setBestRecordForOneKm(int i) {
        this.mBestRecordForOneKm = i;
    }

    public int getBestRecordForFiveKm() {
        return this.mBestRecordForFiveKm;
    }

    public void setBestRecordForFiveKm(int i) {
        this.mBestRecordForFiveKm = i;
    }

    public int getBestRecordForTenKm() {
        return this.mBestRecordForTenKm;
    }

    public void setBestRecordForTenKm(int i) {
        this.mBestRecordForTenKm = i;
    }

    public int getBestRecordForHalfMarathon() {
        return this.mBestRecordForHalfMarathon;
    }

    public void setBestRecordForHalfMarathon(int i) {
        this.mBestRecordForHalfMarathon = i;
    }

    public int getBestRecordForMarathon() {
        return this.mBestRecordForMarathon;
    }

    public void setBestRecordForMarathon(int i) {
        this.mBestRecordForMarathon = i;
    }

    public int getBestRecordForFirstOneKm() {
        return this.mBestRecordForFirstOneKm;
    }

    public void setBestRecordForFirstOneKm(int i) {
        this.mBestRecordForFirstOneKm = i;
    }

    public int getBestRecordForAllFiveKm() {
        return this.mBestRecordForAllFiveKm;
    }

    public void setBestRecordForAllFiveKm(int i) {
        this.mBestRecordForAllFiveKm = i;
    }

    public int getBestRecordForAllTenKm() {
        return this.mBestRecordForAllTenKm;
    }

    public void setBestRecordForAllTenKm(int i) {
        this.mBestRecordForAllTenKm = i;
    }

    public int getBestRecordForAllHalfMarathon() {
        return this.mBestRecordForAllHalfMarathon;
    }

    public void setBestRecordForAllHalfMarathon(int i) {
        this.mBestRecordForAllHalfMarathon = i;
    }

    public int getBestRecordForAllMarathon() {
        return this.mBestRecordForAllMarathon;
    }

    public void setBestRecordForAllMarathon(int i) {
        this.mBestRecordForAllMarathon = i;
    }

    public void setValue(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            setBestRecordForAll(i, str2);
        } else {
            setBestRecord(i, str2);
        }
    }

    private void setBestRecord(int i, String str) {
        switch (i) {
            case 0:
                setBestRecordForFiveKm(gic.e((Object) str));
                break;
            case 1:
                setBestRecordForTenKm(gic.e((Object) str));
                break;
            case 2:
                setBestRecordForHalfMarathon(gic.e((Object) str));
                break;
            case 3:
                setBestRecordForMarathon(gic.e((Object) str));
                break;
            case 4:
                setBestRecordForOneKm(gic.e((Object) str));
                break;
            case 5:
                setFarthestRunning(gic.b((Object) str).floatValue());
                break;
            case 6:
                setLongestRunning(gic.e((Object) str));
                break;
            case 7:
                setBestRecordForFirstOneKm(gic.e((Object) str));
                break;
            case 8:
                saveLongestTimePerWeek(gic.e((Object) str));
                break;
            case 9:
                saveMostCaloriePerWeek(gic.b((Object) str).floatValue());
                break;
            case 10:
                saveHighestCompleteRate(moe.a(gic.b((Object) str).floatValue()));
                break;
            case 11:
                saveMostWorkoutTimes(gic.e((Object) str));
                break;
            case 12:
                setMostWorkoutName(StringUtils.c((Object) str));
                break;
        }
    }

    private void setBestRecordForAll(int i, String str) {
        if (i == 0) {
            setBestRecordForAllFiveKm(gic.e((Object) str));
            return;
        }
        if (i == 1) {
            setBestRecordForAllTenKm(gic.e((Object) str));
        } else if (i == 2) {
            setBestRecordForAllHalfMarathon(gic.e((Object) str));
        } else {
            if (i != 3) {
                return;
            }
            setBestRecordForAllMarathon(gic.e((Object) str));
        }
    }

    public Object getValue(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return getBestRecordForAll(i);
        }
        return getBestRecord(i);
    }

    private Object getBestRecord(int i) {
        switch (i) {
            case 0:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForFiveKm())));
            case 1:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForTenKm())));
            case 2:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForHalfMarathon())));
            case 3:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForMarathon())));
            case 4:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForOneKm())));
            case 5:
                return gic.b(Float.valueOf(getFarthestRunning()));
            case 6:
                return Integer.valueOf(gic.e(Integer.valueOf(getLongestRunning())));
            case 7:
                return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForFirstOneKm())));
            case 8:
                return Integer.valueOf(gic.e(Integer.valueOf(getLongestTimePerWeek())));
            case 9:
                return gic.b(Float.valueOf(getMostCaloriePerWeek()));
            case 10:
                return gic.b(Float.valueOf(getHighestCompleteRate()));
            case 11:
                return Integer.valueOf(gic.e(Integer.valueOf(getMostWorkoutTimes())));
            case 12:
                return StringUtils.c((Object) getMostWorkoutName());
            default:
                return null;
        }
    }

    private Object getBestRecordForAll(int i) {
        if (i == 0) {
            return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForAllFiveKm())));
        }
        if (i == 1) {
            return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForAllTenKm())));
        }
        if (i == 2) {
            return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForAllHalfMarathon())));
        }
        if (i != 3) {
            return null;
        }
        return Integer.valueOf(gic.e(Integer.valueOf(getBestRecordForAllMarathon())));
    }
}
