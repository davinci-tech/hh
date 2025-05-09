package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes5.dex */
public class IndoorEquipTrainerData extends FitnessData {
    public static final Parcelable.Creator<IndoorEquipTrainerData> CREATOR = new Parcelable.Creator<IndoorEquipTrainerData>() { // from class: com.huawei.indoorequip.datastruct.IndoorEquipTrainerData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndoorEquipTrainerData createFromParcel(Parcel parcel) {
            return new IndoorEquipTrainerData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndoorEquipTrainerData[] newArray(int i) {
            return new IndoorEquipTrainerData[i];
        }
    };
    public static final int CROSSTRAINER_FLAGS_AVERAGE_POWER = 512;
    public static final int CROSSTRAINER_FLAGS_AVERAGE_SPEED = 2;
    public static final int CROSSTRAINER_FLAGS_ELAPSED_TIME = 8192;
    public static final int CROSSTRAINER_FLAGS_ELEVATION_GAIN = 32;
    public static final int CROSSTRAINER_FLAGS_EXPENDED_ENERGY = 1024;
    public static final int CROSSTRAINER_FLAGS_HEART_RATE = 2048;
    public static final int CROSSTRAINER_FLAGS_INCLINATION_AND_SETTING = 64;
    public static final int CROSSTRAINER_FLAGS_INSTANTANEOUS_POWER = 256;
    public static final int CROSSTRAINER_FLAGS_INSTANTANEOUS_SPEED = 1;
    public static final int CROSSTRAINER_FLAGS_METABOLIC_EQUIVALENT = 4096;
    public static final int CROSSTRAINER_FLAGS_MOVEMENT_DIRECTION = 32768;
    public static final int CROSSTRAINER_FLAGS_REMAINING_TIME = 16384;
    public static final int CROSSTRAINER_FLAGS_RESISTANCE_LEVEL = 128;
    public static final int CROSSTRAINER_FLAGS_STEP_COUNT = 8;
    public static final int CROSSTRAINER_FLAGS_STRIDE_COUNT = 16;
    public static final int CROSSTRAINER_FLAGS_TOTAL_DISTANCE = 4;
    private static final int DEFAULT_INT_VALUE = 0;
    public static final short FLAGS_MORE_DATA = 1;
    public static final short INDOORBIKE_FLAGS_AVERAGE_CADENCE = 8;
    public static final short INDOORBIKE_FLAGS_AVERAGE_POWER = 128;
    public static final short INDOORBIKE_FLAGS_AVERAGE_SPEED = 2;
    public static final short INDOORBIKE_FLAGS_ELAPSED_TIME = 2048;
    public static final short INDOORBIKE_FLAGS_EXPENDED_ENERGY = 256;
    public static final short INDOORBIKE_FLAGS_HEART_RATE = 512;
    public static final short INDOORBIKE_FLAGS_INSTANTANEOUS_CADENCE = 4;
    public static final short INDOORBIKE_FLAGS_INSTANTANEOUS_POWER = 64;
    public static final short INDOORBIKE_FLAGS_INSTANTANEOUS_SPEED = 1;
    public static final short INDOORBIKE_FLAGS_METABOLIC_EQUIVALENT = 1024;
    public static final short INDOORBIKE_FLAGS_REMAINING_TIME = 4096;
    public static final short INDOORBIKE_FLAGS_RESISTANCE_LEVEL = 32;
    public static final short INDOORBIKE_FLAGS_TOTAL_DISTANCE = 16;
    public static final int ROWER_FLAGS_AVERAGE_PACE = 16;
    public static final int ROWER_FLAGS_AVERAGE_POWER = 64;
    public static final int ROWER_FLAGS_AVERAGE_STROKE_RATE = 2;
    public static final int ROWER_FLAGS_ELAPSED_TIME = 2048;
    public static final int ROWER_FLAGS_EXPENDED_ENERGY = 256;
    public static final int ROWER_FLAGS_HEART_RATE = 512;
    public static final int ROWER_FLAGS_INSTANTANEOUS_PACE = 8;
    public static final int ROWER_FLAGS_INSTANTANEOUS_POWER = 32;
    public static final int ROWER_FLAGS_METABOLIC_EQUIVALENT = 1024;
    public static final int ROWER_FLAGS_REMAINING_TIME = 4096;
    public static final int ROWER_FLAGS_RESERVED_DATA = 8192;
    public static final int ROWER_FLAGS_RESISTANCE_LEVEL = 128;
    public static final int ROWER_FLAGS_STROKE_RATE_STROKE_COUNT = 1;
    public static final int ROWER_FLAGS_TOTAL_DISTANCE = 4;
    public static final short TREADMILL_FLAGS_AVERAGE_PACE = 64;
    public static final short TREADMILL_FLAGS_AVERAGE_SPEED = 2;
    public static final short TREADMILL_FLAGS_ELAPSED_TIME = 1024;
    public static final short TREADMILL_FLAGS_ELEVATION_GAIN = 16;
    public static final short TREADMILL_FLAGS_EXPENDED_ENERGY = 128;
    public static final short TREADMILL_FLAGS_FORCE_AND_POWER = 4096;
    public static final short TREADMILL_FLAGS_HEART_RATE = 256;
    public static final short TREADMILL_FLAGS_INCLINATION_AND_SETTING = 8;
    public static final short TREADMILL_FLAGS_INSTANTANEOUS_PACE = 32;
    public static final short TREADMILL_FLAGS_METABOLIC_EQUIVALENT = 512;
    public static final short TREADMILL_FLAGS_REMAINING_TIME = 2048;
    public static final short TREADMILL_FLAGS_TOTAL_DISTANCE = 4;
    public static final short TREADMILL_FLAGS_TOTAL_ENERGY = 16384;
    public static final short TREADMILL_FLAGS_TOTAL_STEP = 8192;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IndoorEquipTrainerData() {
        this(0);
    }

    public IndoorEquipTrainerData(int i) {
        clearData();
        setFitnessDataType(i);
    }

    protected IndoorEquipTrainerData(Parcel parcel) {
        super(parcel);
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int getFlags() {
        return this.mFlags;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }

    public void setPowerInCalPerMin(int i) {
        setAveragePower(i);
    }

    public int getPowerInCalPerMin() {
        return getAveragePower();
    }

    public void setStartTime(int i) {
        this.mFitnessData.put(35, Integer.valueOf(i));
    }

    public int getStartTime() {
        if (this.mFitnessData.get(35) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(35)).intValue();
        }
        return 0;
    }

    public void setElapsedTimeStringForShow(String str) {
        this.mFitnessData.put(34, str);
    }

    public String getElapsedTimeStringForShow() {
        if (this.mFitnessData.get(34) instanceof String) {
            return (String) this.mFitnessData.get(34);
        }
        return null;
    }

    public void setRevsPerMinute(int i) {
        this.mFitnessData.put(5, Integer.valueOf(i));
    }

    public int getRevsPerMinute() {
        if (this.mFitnessData.get(5) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(5)).intValue();
        }
        return 0;
    }

    public int getStepPerMinute() {
        if (this.mFitnessData.get(31) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(31)).intValue();
        }
        return 0;
    }

    public void setStepPerMinute(int i) {
        if (i == 65535) {
            this.mFitnessData.put(31, 0);
        } else {
            this.mFitnessData.put(31, Integer.valueOf(i));
        }
    }

    public int getAverateStepRate() {
        if (this.mFitnessData.get(33) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(33)).intValue();
        }
        return 0;
    }

    public void setAverateStepRate(int i) {
        if (i == 65535) {
            this.mFitnessData.put(33, 0);
        } else {
            this.mFitnessData.put(33, Integer.valueOf(i));
        }
    }

    public int getStrideCount() {
        if (this.mFitnessData.get(23) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(23)).intValue();
        }
        return 0;
    }

    public void setStrideCount(int i) {
        this.mFitnessData.put(23, Integer.valueOf(i));
    }

    public boolean getMovementDirection() {
        if (this.mFitnessData.get(29) instanceof Boolean) {
            return ((Boolean) this.mFitnessData.get(29)).booleanValue();
        }
        return false;
    }

    public void setMovementDirection(boolean z) {
        this.mFitnessData.put(29, Boolean.valueOf(z));
    }

    public int getAverageStrokeRate() {
        if (this.mFitnessData.get(28) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(28)).intValue();
        }
        return 0;
    }

    public void setAverageStrokeRate(int i) {
        this.mFitnessData.put(28, Integer.valueOf(i));
    }

    public int getStrokeCount() {
        if (this.mFitnessData.get(27) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(27)).intValue();
        }
        return 0;
    }

    public void setStrokeCount(int i) {
        this.mFitnessData.put(27, Integer.valueOf(i));
    }

    public int getStrokeRate() {
        if (this.mFitnessData.get(26) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(26)).intValue();
        }
        return 0;
    }

    public void setStrokeRate(int i) {
        this.mFitnessData.put(26, Integer.valueOf(i));
    }

    public int getAveragePower() {
        if (this.mFitnessData.get(25) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(25)).intValue();
        }
        return 0;
    }

    public void setAveragePower(int i) {
        this.mFitnessData.put(25, Integer.valueOf(i));
    }

    public int getInstantaneousPower() {
        if (this.mFitnessData.get(24) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(24)).intValue();
        }
        return 0;
    }

    public void setInstantaneousPower(int i) {
        this.mFitnessData.put(24, Integer.valueOf(i));
    }

    public int getResistanceLevel() {
        if (this.mFitnessData.get(22) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(22)).intValue();
        }
        return 0;
    }

    public void setResistanceLevel(int i) {
        this.mFitnessData.put(22, Integer.valueOf(i));
    }

    public int getAverageCadence() {
        if (this.mFitnessData.get(32) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(32)).intValue();
        }
        return 0;
    }

    public void setAverageCadence(int i) {
        this.mFitnessData.put(32, Integer.valueOf(i));
    }

    public int getInstantaneousCadence() {
        if (this.mFitnessData.get(31) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(31)).intValue();
        }
        return 0;
    }

    public void setInstantaneousCadence(int i) {
        this.mFitnessData.put(31, Integer.valueOf(i));
    }

    public int getInstantaneousSpeed() {
        if (this.mFitnessData.get(3) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(3)).intValue();
        }
        return 0;
    }

    public void setInstantaneousSpeed(int i) {
        this.mFitnessData.put(3, Integer.valueOf(i));
    }

    public int getAverageSpeed() {
        if (this.mFitnessData.get(9) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(9)).intValue();
        }
        return 0;
    }

    public void setAverageSpeed(int i) {
        if (i == 32767) {
            this.mFitnessData.put(9, 0);
        } else {
            this.mFitnessData.put(9, Integer.valueOf(i));
        }
    }

    public int getTotalDistance() {
        if (this.mFitnessData.get(1) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(1)).intValue();
        }
        return 0;
    }

    public void setTotalDistance(int i) {
        this.mFitnessData.put(1, Integer.valueOf(i));
    }

    public int getTotalStep() {
        if (this.mFitnessData.get(8) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(8)).intValue();
        }
        return 0;
    }

    public void setTotalStep(int i) {
        this.mFitnessData.put(8, Integer.valueOf(i));
    }

    public int getInclination() {
        if (this.mFitnessData.get(10) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(10)).intValue();
        }
        return 0;
    }

    public void setInclination(int i) {
        if (i == 32767) {
            this.mFitnessData.put(10, 0);
        } else {
            this.mFitnessData.put(10, Integer.valueOf(i));
        }
    }

    public int getRampAngleSetting() {
        if (this.mFitnessData.get(11) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(11)).intValue();
        }
        return 0;
    }

    public void setRampAngleSetting(int i) {
        if (i == 32767) {
            this.mFitnessData.put(11, 0);
        } else {
            this.mFitnessData.put(11, Integer.valueOf(i));
        }
    }

    public int getPositiveElevationGain() {
        if (this.mFitnessData.get(12) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(12)).intValue();
        }
        return 0;
    }

    public void setPositiveElevationGain(int i) {
        if (i == 32767) {
            this.mFitnessData.put(12, 0);
        } else {
            this.mFitnessData.put(12, Integer.valueOf(i));
        }
    }

    public int getNegativeElevationGain() {
        if (this.mFitnessData.get(13) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(13)).intValue();
        }
        return 0;
    }

    public void setNegativeElevationGain(int i) {
        if (i == 32767) {
            this.mFitnessData.put(13, 0);
        } else {
            this.mFitnessData.put(13, Integer.valueOf(i));
        }
    }

    public int getInstantaneousPace() {
        if (this.mFitnessData.get(14) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(14)).intValue();
        }
        return 0;
    }

    public void setInstantaneousPace(int i) {
        if (i == 255) {
            this.mFitnessData.put(14, 0);
        } else {
            this.mFitnessData.put(14, Integer.valueOf(i));
        }
    }

    public int getAveragePace() {
        if (this.mFitnessData.get(15) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(15)).intValue();
        }
        return 0;
    }

    public void setAveragePace(int i) {
        if (i == 255) {
            this.mFitnessData.put(15, 0);
        } else {
            this.mFitnessData.put(15, Integer.valueOf(i));
        }
    }

    public int getTotalEnergy() {
        if (this.mFitnessData.get(6) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(6)).intValue();
        }
        return 0;
    }

    public void setTotalEnergy(int i) {
        this.mFitnessData.put(6, Integer.valueOf(i));
    }

    public int getEnergyPerHour() {
        if (this.mFitnessData.get(16) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(16)).intValue();
        }
        return 0;
    }

    public void setEnergyPerHour(int i) {
        if (i == 32767) {
            this.mFitnessData.put(16, 0);
        } else {
            this.mFitnessData.put(16, Integer.valueOf(i));
        }
    }

    public int getEnergyPerMin() {
        if (this.mFitnessData.get(17) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(17)).intValue();
        }
        return 0;
    }

    public void setEnergyPerMin(int i) {
        if (i == 255) {
            this.mFitnessData.put(17, 0);
        } else {
            this.mFitnessData.put(17, Integer.valueOf(i));
        }
    }

    public int getHeartRate() {
        if (this.mFitnessData.get(18) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(18)).intValue();
        }
        return 0;
    }

    public void setHeartRate(int i) {
        this.mFitnessData.put(18, Integer.valueOf(i));
    }

    public int getMetabolicEquivalent() {
        if (this.mFitnessData.get(30) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(30)).intValue();
        }
        return 0;
    }

    public void setMetabolicEquivalent(int i) {
        this.mFitnessData.put(30, Integer.valueOf(i));
    }

    public int getElapsedTime() {
        if (this.mFitnessData.get(2) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(2)).intValue();
        }
        return 0;
    }

    public void setElapsedTime(int i) {
        this.mFitnessData.put(2, Integer.valueOf(i));
    }

    public int getRemainingTime() {
        if (this.mFitnessData.get(19) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(19)).intValue();
        }
        return 0;
    }

    public void setRemainingTime(int i) {
        this.mFitnessData.put(19, Integer.valueOf(i));
    }

    public int getWeight() {
        if (this.mFitnessData.get(37) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(37)).intValue();
        }
        return 0;
    }

    public void setWeight(int i) {
        this.mFitnessData.put(37, Integer.valueOf(i));
    }

    public int getStrokeCountForStrengthModel() {
        if (this.mFitnessData.get(38) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(38)).intValue();
        }
        return 0;
    }

    public void setStrokeCountForStrengthModel(int i) {
        this.mFitnessData.put(38, Integer.valueOf(i));
    }

    public int getTotalActionGroups() {
        if (this.mFitnessData.get(39) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(39)).intValue();
        }
        return 0;
    }

    public void setTotalActionGroups(int i) {
        this.mFitnessData.put(39, Integer.valueOf(i));
    }

    public int getNumberOfActionsPerGroup() {
        if (this.mFitnessData.get(40) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(40)).intValue();
        }
        return 0;
    }

    public void setNumberOfActionsPerGroup(int i) {
        this.mFitnessData.put(40, Integer.valueOf(i));
    }

    public int getFrequencyOfAction() {
        if (this.mFitnessData.get(41) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(41)).intValue();
        }
        return 0;
    }

    public void setFrequencyOfAction(int i) {
        this.mFitnessData.put(41, Integer.valueOf(i));
    }

    public int getForceOnBelt() {
        if (this.mFitnessData.get(20) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(20)).intValue();
        }
        return 0;
    }

    public void setForceOnBelt(int i) {
        if (i == 32767) {
            this.mFitnessData.put(20, 0);
        } else {
            this.mFitnessData.put(20, Integer.valueOf(i));
        }
    }

    public int getPowerOutPut() {
        if (this.mFitnessData.get(7) instanceof Integer) {
            return ((Integer) this.mFitnessData.get(7)).intValue();
        }
        return 0;
    }

    public void setPowerOutPut(int i) {
        if (i == 32767) {
            this.mFitnessData.put(7, 0);
        } else {
            this.mFitnessData.put(7, Integer.valueOf(i));
        }
    }
}
