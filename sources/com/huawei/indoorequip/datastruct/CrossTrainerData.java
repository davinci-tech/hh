package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes9.dex */
public class CrossTrainerData extends FitnessData {
    public static final Parcelable.Creator<CrossTrainerData> CREATOR = new Parcelable.Creator<CrossTrainerData>() { // from class: com.huawei.indoorequip.datastruct.CrossTrainerData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CrossTrainerData createFromParcel(Parcel parcel) {
            return new CrossTrainerData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CrossTrainerData[] newArray(int i) {
            return new CrossTrainerData[i];
        }
    };
    public static final int FLAGS_AVERAGE_POWER = 512;
    public static final int FLAGS_AVERAGE_SPEED = 2;
    public static final int FLAGS_ELAPSED_TIME = 8192;
    public static final int FLAGS_ELEVATION_GAIN = 32;
    public static final int FLAGS_EXPENDED_ENERGY = 1024;
    public static final int FLAGS_HEART_RATE = 2048;
    public static final int FLAGS_INCLINATION_AND_SETTING = 64;
    public static final int FLAGS_INSTANTANEOUS_POWER = 256;
    public static final int FLAGS_INSTANTANEOUS_SPEED = 1;
    public static final int FLAGS_METABOLIC_EQUIVALENT = 4096;
    public static final int FLAGS_REMAINING_TIME = 16384;
    public static final int FLAGS_RESISTANCE_LEVEL = 128;
    public static final int FLAGS_STEP_COUNT = 8;
    public static final int FLAGS_STRIDE_COUNT = 16;
    public static final int FLAGS_TOTAL_DISTANCE = 4;
    public static final int FLAGS_TOTAL_ENERGY = 32768;
    private int mAveragePower;
    private int mAverageSpeed;
    private int mAverageStepRate;
    private int mElapsedTime;
    private int mEnergyPerHour;
    private int mEnergyPerMin;
    private int mFlags;
    private int mHeartRate;
    private int mInclination;
    private int mInstantaneousPower;
    private int mInstantaneousSpeed;
    private int mMetabolicEquivalent;
    private int mNegativeElevationGain;
    private int mPositiveElevationGain;
    private int mRampAngleSetting;
    private int mRemainingTime;
    private int mResistanceLevel;
    private int mStepPerMinute;
    private int mStrideCount;
    private int mTotalDistance;
    private int mTotalEnergy;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CrossTrainerData() {
        this.mFlags = 0;
        setFitnessDataType(6);
    }

    protected CrossTrainerData(Parcel parcel) {
        this.mFlags = 0;
        this.mFlags = parcel.readInt();
        this.mInstantaneousSpeed = parcel.readInt();
        this.mAverageSpeed = parcel.readInt();
        this.mTotalDistance = parcel.readInt();
        this.mStepPerMinute = parcel.readInt();
        this.mAverageStepRate = parcel.readInt();
        this.mStrideCount = parcel.readInt();
        this.mInclination = parcel.readInt();
        this.mRampAngleSetting = parcel.readInt();
        this.mPositiveElevationGain = parcel.readInt();
        this.mNegativeElevationGain = parcel.readInt();
        this.mResistanceLevel = parcel.readInt();
        this.mInstantaneousPower = parcel.readInt();
        this.mAveragePower = parcel.readInt();
        this.mTotalEnergy = parcel.readInt();
        this.mEnergyPerHour = parcel.readInt();
        this.mEnergyPerMin = parcel.readInt();
        this.mHeartRate = parcel.readInt();
        this.mMetabolicEquivalent = parcel.readInt();
        this.mElapsedTime = parcel.readInt();
        this.mRemainingTime = parcel.readInt();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mInstantaneousSpeed);
        parcel.writeInt(this.mAverageSpeed);
        parcel.writeInt(this.mTotalDistance);
        parcel.writeInt(this.mStepPerMinute);
        parcel.writeInt(this.mAverageStepRate);
        parcel.writeInt(this.mStrideCount);
        parcel.writeInt(this.mInclination);
        parcel.writeInt(this.mRampAngleSetting);
        parcel.writeInt(this.mPositiveElevationGain);
        parcel.writeInt(this.mNegativeElevationGain);
        parcel.writeInt(this.mResistanceLevel);
        parcel.writeInt(this.mInstantaneousPower);
        parcel.writeInt(this.mAveragePower);
        parcel.writeInt(this.mTotalEnergy);
        parcel.writeInt(this.mEnergyPerHour);
        parcel.writeInt(this.mEnergyPerMin);
        parcel.writeInt(this.mHeartRate);
        parcel.writeInt(this.mMetabolicEquivalent);
        parcel.writeInt(this.mElapsedTime);
        parcel.writeInt(this.mRemainingTime);
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

    public int getAverageSpeed() {
        return this.mAverageSpeed;
    }

    public void setAverageSpeed(int i) {
        this.mAverageSpeed = i;
        this.mFlags |= 2;
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

    public int getAverageStepRate() {
        return this.mAverageStepRate;
    }

    public void setAverageStepRate(int i) {
        this.mAverageStepRate = i;
        this.mFlags |= 8;
    }

    public int getStrideCount() {
        return this.mStrideCount;
    }

    public void setStrideCount(int i) {
        this.mStrideCount = i;
        this.mFlags |= 16;
    }

    public int getInclination() {
        return this.mInclination;
    }

    public void setInclination(int i) {
        this.mInclination = i;
        this.mFlags |= 64;
    }

    public int getRampAngleSetting() {
        return this.mRampAngleSetting;
    }

    public void setRampAngleSetting(int i) {
        this.mRampAngleSetting = i;
        this.mFlags |= 64;
    }

    public int getPositiveElevationGain() {
        return this.mPositiveElevationGain;
    }

    public void setPositiveElevationGain(int i) {
        this.mPositiveElevationGain = i;
        this.mFlags |= 32;
    }

    public int getNegativeElevationGain() {
        return this.mNegativeElevationGain;
    }

    public void setNegativeElevationGain(int i) {
        this.mNegativeElevationGain = i;
        this.mFlags |= 32;
    }

    public int getResistanceLevel() {
        return this.mResistanceLevel;
    }

    public void setResistanceLevel(int i) {
        this.mResistanceLevel = i;
        this.mFlags |= 128;
    }

    public int getInstantaneousPower() {
        return this.mInstantaneousPower;
    }

    public void setInstantaneousPower(int i) {
        this.mInstantaneousPower = i;
        this.mFlags |= 256;
    }

    public int getAveragePower() {
        return this.mAveragePower;
    }

    public void setAveragePower(int i) {
        this.mAveragePower = i;
        this.mFlags |= 512;
    }

    public int getTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setTotalEnergy(int i) {
        this.mTotalEnergy = i;
        this.mFlags |= 32768;
    }

    public int getEnergyPerHour() {
        return this.mEnergyPerHour;
    }

    public void setEnergyPerHour(int i) {
        this.mEnergyPerHour = i;
        this.mFlags |= 1024;
    }

    public int getEnergyPerMin() {
        return this.mEnergyPerMin;
    }

    public void setEnergyPerMin(int i) {
        this.mEnergyPerMin = i;
        this.mFlags |= 1024;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }

    public void setHeartRate(int i) {
        this.mHeartRate = i;
        this.mFlags |= 2048;
    }

    public int getMetabolicEquivalent() {
        return this.mMetabolicEquivalent;
    }

    public void setMetabolicEquivalent(int i) {
        this.mMetabolicEquivalent = i;
        this.mFlags |= 4096;
    }

    public int getElapsedTime() {
        return this.mElapsedTime;
    }

    public void setElapsedTime(int i) {
        this.mElapsedTime = i;
        this.mFlags |= 8192;
    }

    public int getRemainingTime() {
        return this.mRemainingTime;
    }

    public void setRemainingTime(int i) {
        this.mRemainingTime = i;
        this.mFlags |= 16384;
    }
}
