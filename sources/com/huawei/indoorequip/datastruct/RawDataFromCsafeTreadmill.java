package com.huawei.indoorequip.datastruct;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class RawDataFromCsafeTreadmill implements Serializable {
    private static final int HOUR_NINE = 9;
    private static final int HOUR_TO_SECOND = 3600;
    private static final int MINUTE_TO_SECOND = 60;
    private static final String TAG = "Track_IDEQ_DataFromTM";
    private static final int TIME_MINIMUM = 2;
    private static final String ZERO_USED_TO_FILL_TIME_SHOWING = "0";
    private static final long serialVersionUID = 8829975621220483374L;
    private int mStateOfTreadmill = -1;
    private int mDistance = 0;
    private int mDistanceUnit = 0;
    private int mSteps = 0;
    private int mCalorie = 0;
    private int mHeartRateFromTreadmill = 0;
    private int mSpeed = 0;
    private int mSpeedUnit = 0;
    private int mPower = 0;
    private int mPowerUnit = 0;
    private int mCreep = 0;
    private int mCreepUnit = 0;
    private int mSec = 0;
    private int mMin = 0;
    private long mHour = 0;
    private String mDuringTimeForShow = "00:00:00";
    private long mDuringSeconds = 0;
    private long mStartSecond = 0;
    private int mFirstStateOfTm = -1;
    private boolean isItTimeHasBeenSet = false;
    private boolean isItDistanceHasBeenSet = false;
    private boolean isItCalorieHasBeenSet = false;
    private boolean isItHeartRateHasBeenSet = false;
    private boolean isItSpeedHasBeenSet = false;
    private boolean isItPowerHasBeenSet = false;
    private boolean isItCreepHasBeenSet = false;
    private boolean isItStateHasBeenSet = false;

    public int getStateOfTreadmill() {
        return this.mStateOfTreadmill;
    }

    public void setStateOfTreadmill(int i) {
        this.mStateOfTreadmill = i;
        this.isItStateHasBeenSet = true;
    }

    public int getDistance() {
        return this.mDistance;
    }

    public void setDistance(int i, int i2) {
        this.mDistance = i;
        this.mDistanceUnit = i2;
        this.isItDistanceHasBeenSet = true;
    }

    public int getDistanceUnit() {
        return this.mDistanceUnit;
    }

    public int getSteps() {
        return this.mSteps;
    }

    public void setSteps(int i) {
        this.mSteps = i;
    }

    public int getCalorie() {
        return this.mCalorie;
    }

    public void setCalorie(int i) {
        this.mCalorie = i;
        this.isItCalorieHasBeenSet = true;
    }

    public int getHeartRateFromTreadmill() {
        return this.mHeartRateFromTreadmill;
    }

    public void setHeartRateFromTreadmill(int i) {
        this.mHeartRateFromTreadmill = i;
        this.isItHeartRateHasBeenSet = true;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i, int i2) {
        this.mSpeed = i;
        this.mSpeedUnit = i2;
        this.isItSpeedHasBeenSet = true;
    }

    public int getSpeedUnit() {
        return this.mSpeedUnit;
    }

    public int getPower() {
        return this.mPower;
    }

    public void setPower(int i, int i2) {
        this.mPower = i;
        this.mPowerUnit = i2;
        this.isItPowerHasBeenSet = true;
    }

    public int getPowerUnit() {
        return this.mPowerUnit;
    }

    public int getCreep() {
        return this.mCreep;
    }

    public void setCreep(int i, int i2) {
        this.mCreep = i;
        this.mCreepUnit = i2;
        this.isItCreepHasBeenSet = true;
    }

    public int getCreepUnit() {
        return this.mCreepUnit;
    }

    public void setStartSecond(int i, int i2, int i3) {
        LogUtil.a(TAG, "in setStartSecond: input is second:", String.valueOf(i), ",min:", String.valueOf(i2), ",hour:", String.valueOf(i3));
        long j = i + (i2 * 60) + (i3 * 3600);
        if (j >= 2) {
            this.mStartSecond = j;
        } else {
            this.mStartSecond = 0L;
        }
        LogUtil.a(TAG, "out setStartSecond: mStartSecond is ", Long.valueOf(this.mStartSecond));
    }

    public long getStartSecond() {
        return this.mStartSecond;
    }

    public void setDuringTime(int i, int i2, int i3) {
        String str;
        String str2;
        String str3;
        long j = i + (i2 * 60) + (i3 * 3600);
        this.mDuringSeconds = j;
        if (j < 0) {
            if (j >= 2) {
                this.mStartSecond = j;
            } else {
                this.mStartSecond = 0L;
            }
            this.mDuringSeconds = j;
        }
        long j2 = this.mDuringSeconds;
        this.mHour = j2 / 3600;
        int i4 = (int) (j2 % 3600);
        this.mMin = i4 / 60;
        this.mSec = i4 % 60;
        StringBuilder sb = new StringBuilder();
        long j3 = this.mHour;
        if (j3 > 9) {
            str = Long.toString(j3);
        } else {
            str = "0" + Long.toString(this.mHour);
        }
        sb.append(str);
        sb.append(":");
        int i5 = this.mMin;
        if (i5 > 9) {
            str2 = Integer.toString(i5);
        } else {
            str2 = "0" + Integer.toString(this.mMin);
        }
        sb.append(str2);
        sb.append(":");
        int i6 = this.mSec;
        if (i6 > 9) {
            str3 = Integer.toString(i6);
        } else {
            str3 = "0" + Integer.toString(this.mSec);
        }
        sb.append(str3);
        this.mDuringTimeForShow = sb.toString();
        this.isItTimeHasBeenSet = true;
    }

    public int getSec() {
        return this.mSec;
    }

    public int getMin() {
        return this.mMin;
    }

    public long getHour() {
        return this.mHour;
    }

    public String getDuringTimeForShow() {
        return this.mDuringTimeForShow;
    }

    public long getDuringSeconds() {
        return this.mDuringSeconds;
    }

    public int getFirstStateOfTm() {
        return this.mFirstStateOfTm;
    }

    public void setFirstStateOfTm(int i) {
        this.mFirstStateOfTm = i;
    }

    public void clearAllFlags() {
        this.isItTimeHasBeenSet = false;
        this.isItDistanceHasBeenSet = false;
        this.isItCalorieHasBeenSet = false;
        this.isItHeartRateHasBeenSet = false;
        this.isItSpeedHasBeenSet = false;
        this.isItPowerHasBeenSet = false;
        this.isItCreepHasBeenSet = false;
        this.isItStateHasBeenSet = false;
    }

    public void clearDataFlags() {
        this.isItTimeHasBeenSet = false;
        this.isItDistanceHasBeenSet = false;
        this.isItCalorieHasBeenSet = false;
        this.isItHeartRateHasBeenSet = false;
        this.isItSpeedHasBeenSet = false;
        this.isItPowerHasBeenSet = false;
        this.isItCreepHasBeenSet = false;
    }

    public boolean isItStateHasBeenSet() {
        return this.isItStateHasBeenSet;
    }

    public boolean isItTimeHasBeenSet() {
        return this.isItTimeHasBeenSet;
    }

    public boolean isItDistanceHasBeenSet() {
        return this.isItDistanceHasBeenSet;
    }

    public boolean isItCalorieHasBeenSet() {
        return this.isItCalorieHasBeenSet;
    }

    public boolean isItHeartRateHasBeenSet() {
        return this.isItHeartRateHasBeenSet;
    }

    public boolean isItSpeedHasBeenSet() {
        return this.isItSpeedHasBeenSet;
    }

    public boolean isItPowerHasBeenSet() {
        return this.isItPowerHasBeenSet;
    }

    public boolean isItCreepHasBeenSet() {
        return this.isItCreepHasBeenSet;
    }

    public boolean isThereAnyFlagsHasBeenSet() {
        return this.isItTimeHasBeenSet || this.isItDistanceHasBeenSet || this.isItCalorieHasBeenSet || this.isItHeartRateHasBeenSet || this.isItSpeedHasBeenSet || this.isItPowerHasBeenSet || this.isItCreepHasBeenSet || this.isItStateHasBeenSet;
    }

    public boolean isThereAnyDataHasBeenSet() {
        return this.isItTimeHasBeenSet || this.isItDistanceHasBeenSet || this.isItCalorieHasBeenSet || this.isItHeartRateHasBeenSet || this.isItSpeedHasBeenSet || this.isItPowerHasBeenSet || this.isItCreepHasBeenSet;
    }

    public void resetAllMembers() {
        LogUtil.a(TAG, "resetAllMembers");
        this.mStateOfTreadmill = -1;
        this.mDistance = 0;
        this.mDistanceUnit = 0;
        this.mSteps = 0;
        this.mCalorie = 0;
        this.mHeartRateFromTreadmill = 0;
        this.mSpeed = 0;
        this.mSpeedUnit = 0;
        this.mPower = 0;
        this.mPowerUnit = 0;
        this.mCreep = 0;
        this.mCreepUnit = 0;
        this.mSec = 0;
        this.mMin = 0;
        this.mHour = 0L;
        this.mDuringTimeForShow = "00:00:00";
        this.mDuringSeconds = 0L;
        this.mStartSecond = 0L;
        this.mFirstStateOfTm = -1;
        this.isItTimeHasBeenSet = false;
        this.isItDistanceHasBeenSet = false;
        this.isItCalorieHasBeenSet = false;
        this.isItHeartRateHasBeenSet = false;
        this.isItSpeedHasBeenSet = false;
        this.isItPowerHasBeenSet = false;
        this.isItCreepHasBeenSet = false;
        this.isItStateHasBeenSet = false;
    }
}
