package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes9.dex */
public class DefaultTrainerData extends FitnessData {
    public static final Parcelable.Creator<DefaultTrainerData> CREATOR = new Parcelable.Creator<DefaultTrainerData>() { // from class: com.huawei.indoorequip.datastruct.DefaultTrainerData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefaultTrainerData createFromParcel(Parcel parcel) {
            return new DefaultTrainerData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefaultTrainerData[] newArray(int i) {
            return new DefaultTrainerData[i];
        }
    };
    public static final int FLAGS_AVERAGE_POWER = 1024;
    public static final int FLAGS_AVERAGE_SPEED = 2;
    public static final int FLAGS_AVERAGE_STEP = 16;
    public static final int FLAGS_ELAPSED_TIME = 65536;
    public static final int FLAGS_ELAPSED_TIME_STRING = 524288;
    public static final int FLAGS_ELEVATION_GAIN = 64;
    public static final int FLAGS_ENERGY_PER_HOUR = 8192;
    public static final int FLAGS_ENERGY_PER_MIN = 4096;
    public static final int FLAGS_HEART_RATE = 16384;
    public static final int FLAGS_INCLINATION_AND_SETTING = 128;
    public static final int FLAGS_INSTANTANEOUS_POWER = 512;
    public static final int FLAGS_INSTANTANEOUS_SPEED = 1;
    public static final int FLAGS_METABOLIC_EQUIVALENT = 32768;
    public static final int FLAGS_POWER_IN_CAL_PER_MIN = 2097152;
    public static final int FLAGS_REMAINING_TIME = 131072;
    public static final int FLAGS_RESISTANCE_LEVEL = 256;
    public static final int FLAGS_REVS_SPEED = 1048576;
    public static final int FLAGS_START_TIME = 262144;
    public static final int FLAGS_STEP_PER_MINUTE = 8;
    public static final int FLAGS_STRIDE_COUNT = 32;
    public static final int FLAGS_TOTAL_DISTANCE = 4;
    public static final int FLAGS_TOTAL_ENERGY = 2048;
    private int mElapsedTime;
    private String mElapsedTimeStringForShow;
    private int mFlags;
    private int mHeartRate;
    private int mInstantaneousPower;
    private int mInstantaneousSpeed;
    private int mPositiveElevationGain;
    private int mPowerInCalPerMin;
    private int mRevsPerMinute;
    private int mStartTime;
    private int mStepPerMinute;
    private int mStrideCount;
    private int mTotalDistance;
    private int mTotalEnergy;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DefaultTrainerData() {
        this.mFlags = 0;
        setFitnessDataType(99);
    }

    protected DefaultTrainerData(Parcel parcel) {
        this.mFlags = 0;
        this.mFlags = parcel.readInt();
        this.mInstantaneousSpeed = parcel.readInt();
        this.mTotalDistance = parcel.readInt();
        this.mStepPerMinute = parcel.readInt();
        this.mStrideCount = parcel.readInt();
        this.mPositiveElevationGain = parcel.readInt();
        this.mInstantaneousPower = parcel.readInt();
        this.mTotalEnergy = parcel.readInt();
        this.mHeartRate = parcel.readInt();
        this.mElapsedTime = parcel.readInt();
        this.mElapsedTimeStringForShow = parcel.readString();
        this.mStartTime = parcel.readInt();
        this.mRevsPerMinute = parcel.readInt();
        this.mPowerInCalPerMin = parcel.readInt();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mInstantaneousSpeed);
        parcel.writeInt(this.mTotalDistance);
        parcel.writeInt(this.mStepPerMinute);
        parcel.writeInt(this.mStrideCount);
        parcel.writeInt(this.mPositiveElevationGain);
        parcel.writeInt(this.mInstantaneousPower);
        parcel.writeInt(this.mTotalEnergy);
        parcel.writeInt(this.mHeartRate);
        parcel.writeInt(this.mElapsedTime);
        parcel.writeString(this.mElapsedTimeStringForShow);
        parcel.writeInt(this.mStartTime);
        parcel.writeInt(this.mRevsPerMinute);
        parcel.writeInt(this.mPowerInCalPerMin);
    }

    public int getFlags() {
        return this.mFlags;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }

    public int getInstantaneousSpeed() {
        return this.mInstantaneousSpeed;
    }

    public void setInstantaneousSpeed(int i) {
        this.mInstantaneousSpeed = i;
        this.mFlags |= 1;
    }

    public int getTotalDistance() {
        return this.mTotalDistance;
    }

    public void setTotalDistance(int i) {
        this.mTotalDistance = i;
        this.mFlags |= 4;
    }

    public int getStepPerMinute() {
        return this.mStepPerMinute;
    }

    public void setStepPerMinute(int i) {
        this.mStepPerMinute = i;
        this.mFlags |= 8;
    }

    public int getRevsPerMinute() {
        return this.mRevsPerMinute;
    }

    public void setRevsPerMinute(int i) {
        this.mRevsPerMinute = i;
        this.mFlags |= 1048576;
    }

    public int getStrideCount() {
        return this.mStrideCount;
    }

    public void setStrideCount(int i) {
        this.mStrideCount = i;
        this.mFlags |= 32;
    }

    public int getPositiveElevationGain() {
        return this.mPositiveElevationGain;
    }

    public void setPositiveElevationGain(int i) {
        this.mPositiveElevationGain = i;
        this.mFlags |= 64;
    }

    public int getInstantaneousPower() {
        return this.mInstantaneousPower;
    }

    public void setInstantaneousPower(int i) {
        this.mInstantaneousPower = i;
        this.mFlags |= 512;
    }

    public int getTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setTotalEnergy(int i) {
        this.mTotalEnergy = i;
        this.mFlags |= 2048;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }

    public void setHeartRate(int i) {
        this.mHeartRate = i;
        this.mFlags |= 16384;
    }

    public int getElapsedTime() {
        return this.mElapsedTime;
    }

    public void setElapsedTime(int i) {
        this.mElapsedTime = i;
        this.mFlags |= 65536;
    }

    public void setElapsedTimeStringForShow(String str) {
        this.mElapsedTimeStringForShow = str;
        this.mFlags |= 524288;
    }

    public int getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(int i) {
        this.mStartTime = i;
        this.mFlags |= 262144;
    }

    public int getPowerInCalPerMin() {
        return this.mPowerInCalPerMin;
    }

    public void setPowerInCalPerMin(int i) {
        this.mPowerInCalPerMin = i;
        this.mFlags |= 2097152;
    }
}
