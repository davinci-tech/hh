package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes9.dex */
public class TreadmillData extends FitnessData {
    public static final Parcelable.Creator<TreadmillData> CREATOR = new Parcelable.Creator<TreadmillData>() { // from class: com.huawei.indoorequip.datastruct.TreadmillData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TreadmillData createFromParcel(Parcel parcel) {
            return new TreadmillData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TreadmillData[] newArray(int i) {
            return new TreadmillData[i];
        }
    };
    public static final short DEFAULT_VALUE_7FFF = Short.MAX_VALUE;
    public static final short DEFAULT_VALUE_FF = 255;
    public static final short FLAGS_AVERAGE_PACE = 64;
    public static final short FLAGS_AVERAGE_SPEED = 2;
    public static final short FLAGS_ELAPSED_TIME = 1024;
    public static final short FLAGS_ELEVATION_GAIN = 16;
    public static final short FLAGS_EXPENDED_ENERGY = 128;
    public static final short FLAGS_FORCE_AND_POWER = 4096;
    public static final short FLAGS_HEART_RATE = 256;
    public static final short FLAGS_INCLINATION_AND_SETTING = 8;
    public static final short FLAGS_INSTANTANEOUS_PACE = 32;
    public static final short FLAGS_INSTANTANEOUS_SPEED = 8192;
    public static final short FLAGS_METABOLIC_EQUIVALENT = 512;
    public static final short FLAGS_MORE_DATA = 1;
    public static final short FLAGS_REMAINING_TIME = 2048;
    public static final short FLAGS_TOTAL_DISTANCE = 4;
    public static final short FLAGS_TOTAL_ENERGY = 16384;
    private int mAveragePace;
    private int mAverageSpeed;
    private int mElapsedTime;
    private int mEnergyPerHour;
    private int mEnergyPerMin;
    private int mFlags;
    private int mForceOnBelt;
    private int mHeartRate;
    private int mInclination;
    private int mInstantaneousPace;
    private int mInstantaneousSpeed;
    private int mMetabolicEquivalent;
    private int mNegativeElevationGain;
    private int mPositiveElevationGain;
    private int mPowerOutPut;
    private int mRampAngleSetting;
    private int mRemainingTime;
    private int mTotalDistance;
    private int mTotalEnergy;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected TreadmillData(Parcel parcel) {
        this.mFlags = parcel.readInt();
        this.mInstantaneousSpeed = parcel.readInt();
        this.mAverageSpeed = parcel.readInt();
        this.mTotalDistance = parcel.readInt();
        this.mInclination = parcel.readInt();
        this.mRampAngleSetting = parcel.readInt();
        this.mPositiveElevationGain = parcel.readInt();
        this.mNegativeElevationGain = parcel.readInt();
        this.mInstantaneousPace = parcel.readInt();
        this.mAveragePace = parcel.readInt();
        this.mTotalEnergy = parcel.readInt();
        this.mEnergyPerHour = parcel.readInt();
        this.mEnergyPerMin = parcel.readInt();
        this.mHeartRate = parcel.readInt();
        this.mMetabolicEquivalent = parcel.readInt();
        this.mElapsedTime = parcel.readInt();
        this.mRemainingTime = parcel.readInt();
        this.mForceOnBelt = parcel.readInt();
        this.mPowerOutPut = parcel.readInt();
    }

    public TreadmillData() {
        setFitnessDataType(3);
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
        this.mFlags |= 8192;
    }

    public int getAverageSpeed() {
        return this.mAverageSpeed;
    }

    public void setAverageSpeed(int i) {
        if (i == 32767) {
            this.mAverageSpeed = 0;
        } else {
            this.mAverageSpeed = i;
        }
        this.mFlags |= 2;
    }

    public int getTotalDistance() {
        return this.mTotalDistance;
    }

    public void setTotalDistance(int i) {
        this.mTotalDistance = i;
        this.mFlags |= 4;
    }

    public int getInclination() {
        return this.mInclination;
    }

    public void setInclination(int i) {
        if (i == 32767) {
            this.mInclination = 0;
        } else {
            this.mInclination = i;
        }
        this.mFlags |= 8;
    }

    public int getRampAngleSetting() {
        return this.mRampAngleSetting;
    }

    public void setRampAngleSetting(int i) {
        if (i == 32767) {
            this.mRampAngleSetting = 0;
        } else {
            this.mRampAngleSetting = i;
        }
        this.mFlags |= 8;
    }

    public int getPositiveElevationGain() {
        return this.mPositiveElevationGain;
    }

    public void setPositiveElevationGain(int i) {
        if (i == 32767) {
            this.mPositiveElevationGain = 0;
        } else {
            this.mPositiveElevationGain = i;
        }
        this.mFlags |= 16;
    }

    public int getNegativeElevationGain() {
        return this.mNegativeElevationGain;
    }

    public void setNegativeElevationGain(int i) {
        if (i == 32767) {
            this.mNegativeElevationGain = 0;
        } else {
            this.mNegativeElevationGain = i;
        }
        this.mFlags |= 16;
    }

    public int getInstantaneousPace() {
        return this.mInstantaneousPace;
    }

    public void setInstantaneousPace(int i) {
        if (i == 255) {
            this.mInstantaneousPace = 0;
        } else {
            this.mInstantaneousPace = i;
        }
        this.mFlags |= 32;
    }

    public int getAveragePace() {
        return this.mAveragePace;
    }

    public void setAveragePace(int i) {
        if (i == 255) {
            this.mAveragePace = 0;
        } else {
            this.mAveragePace = i;
        }
        this.mFlags |= 64;
    }

    public int getTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setTotalEnergy(int i) {
        this.mTotalEnergy = i;
        this.mFlags |= 16384;
    }

    public int getEnergyPerHour() {
        return this.mEnergyPerHour;
    }

    public void setEnergyPerHour(int i) {
        if (i == 32767) {
            this.mEnergyPerHour = 0;
        } else {
            this.mEnergyPerHour = i;
        }
        this.mFlags |= 128;
    }

    public int getEnergyPerMin() {
        return this.mEnergyPerMin;
    }

    public void setEnergyPerMin(int i) {
        if (i == 255) {
            this.mEnergyPerMin = 0;
        } else {
            this.mEnergyPerMin = i;
        }
        this.mFlags |= 128;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }

    public void setHeartRate(int i) {
        this.mHeartRate = i;
        this.mFlags |= 256;
    }

    public int getMetabolicEquivalent() {
        return this.mMetabolicEquivalent;
    }

    public void setMetabolicEquivalent(int i) {
        this.mMetabolicEquivalent = i;
        this.mFlags |= 512;
    }

    public int getElapsedTime() {
        return this.mElapsedTime;
    }

    public void setElapsedTime(int i) {
        this.mElapsedTime = i;
        this.mFlags |= 1024;
    }

    public int getRemainingTime() {
        return this.mRemainingTime;
    }

    public void setRemainingTime(int i) {
        this.mRemainingTime = i;
        this.mFlags |= 2048;
    }

    public int getForceOnBelt() {
        return this.mForceOnBelt;
    }

    public void setForceOnBelt(int i) {
        this.mForceOnBelt = i;
        this.mFlags |= 4096;
    }

    public int getPowerOutPut() {
        return this.mPowerOutPut;
    }

    public void setPowerOutPut(int i) {
        this.mPowerOutPut = i;
        this.mFlags |= 4096;
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mInstantaneousSpeed);
        parcel.writeInt(this.mAverageSpeed);
        parcel.writeInt(this.mTotalDistance);
        parcel.writeInt(this.mInclination);
        parcel.writeInt(this.mRampAngleSetting);
        parcel.writeInt(this.mPositiveElevationGain);
        parcel.writeInt(this.mNegativeElevationGain);
        parcel.writeInt(this.mInstantaneousPace);
        parcel.writeInt(this.mAveragePace);
        parcel.writeInt(this.mTotalEnergy);
        parcel.writeInt(this.mEnergyPerHour);
        parcel.writeInt(this.mEnergyPerMin);
        parcel.writeInt(this.mHeartRate);
        parcel.writeInt(this.mMetabolicEquivalent);
        parcel.writeInt(this.mElapsedTime);
        parcel.writeInt(this.mRemainingTime);
        parcel.writeInt(this.mForceOnBelt);
        parcel.writeInt(this.mPowerOutPut);
    }
}
