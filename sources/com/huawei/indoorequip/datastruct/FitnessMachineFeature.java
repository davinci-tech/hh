package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes5.dex */
public class FitnessMachineFeature extends FitnessData {
    public static final Parcelable.Creator<FitnessMachineFeature> CREATOR = new Parcelable.Creator<FitnessMachineFeature>() { // from class: com.huawei.indoorequip.datastruct.FitnessMachineFeature.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FitnessMachineFeature createFromParcel(Parcel parcel) {
            return new FitnessMachineFeature(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FitnessMachineFeature[] newArray(int i) {
            return new FitnessMachineFeature[i];
        }
    };
    public static final int FEATURE_ELEVATION_GAIN = 16;
    public static final int FEATURE_FITNESS_AVERAGE_SPEED = 1;
    public static final int FEATURE_FITNESS_CADENCE = 2;
    public static final int FEATURE_FITNESS_ELAPSE_TIME = 4096;
    public static final int FEATURE_FITNESS_EXPENDED_ENERGY = 512;
    public static final int FEATURE_FITNESS_FORCE_AND_POWER_OUTPUT = 32768;
    public static final int FEATURE_FITNESS_HEART_RATE = 1024;
    public static final int FEATURE_FITNESS_INCLINATION = 8;
    public static final int FEATURE_FITNESS_META_EQUIVALENT = 2048;
    public static final int FEATURE_FITNESS_PACE = 32;
    public static final int FEATURE_FITNESS_POWER = 16384;
    public static final int FEATURE_FITNESS_REMAINING_TIME = 8192;
    public static final int FEATURE_FITNESS_RESISTANCE_LEVEL = 128;
    public static final int FEATURE_FITNESS_STEP_COUNT = 64;
    public static final int FEATURE_FITNESS_STRIDE_COUNT = 256;
    public static final int FEATURE_FITNESS_TOTAL_DISTANCE = 4;
    public static final int FEATURE_FITNESS_USER_DATA_RETENTION = 65536;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FitnessMachineFeature() {
        clearData();
        setFitnessDataType(1);
    }

    public FitnessMachineFeature(Parcel parcel) {
        super(parcel);
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int getFitnessMachineFeature() {
        return ((Integer) this.mFitnessData.get(50)).intValue();
    }

    public void setFitnessMachineFeature(int i) {
        this.mFitnessData.put(50, Integer.valueOf(i));
    }

    public int getTargetSettingFeature() {
        return ((Integer) this.mFitnessData.get(51)).intValue();
    }

    public void setTargetSettingFeature(int i) {
        this.mFitnessData.put(51, Integer.valueOf(i));
    }
}
